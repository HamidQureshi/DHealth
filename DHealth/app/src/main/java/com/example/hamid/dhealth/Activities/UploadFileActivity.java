package com.example.hamid.dhealth.Activities;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activeledgersdk.ActiveLedgerSDK;
import com.example.activeledgersdk.utility.Utility;
import com.example.hamid.dhealth.ActiveLedgerHelper;
import com.example.hamid.dhealth.FileUtils;
import com.example.hamid.dhealth.Fragments.DoctorPatientViewModel;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Doctor;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Report;
import com.example.hamid.dhealth.MedicalRepository.DataRepository;
import com.example.hamid.dhealth.MedicalRepository.HTTP.HttpClient;
import com.example.hamid.dhealth.Preference.PreferenceKeys;
import com.example.hamid.dhealth.Preference.PreferenceManager;
import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.Utils.Utils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class UploadFileActivity extends AppCompatActivity {

    private static final String TAG = "UploadFileActivity";
    private static final int READ_REQUEST_CODE = 42;
    private static final int WRITE_REQUEST_CODE = 43;
    static Uri uri = null;
    EditText  et_title, et_uploaddate, et_signeddate, et_description, et_content;
    MultiSelectSpinner et_assigned_to;
    TextView tv_staus, tv_document, et_name;
    Report report = null;
    String base64File = null;
    private Disposable disposable;
    private ProgressBar progressBar;
    private DoctorPatientViewModel mViewModel;
    private ArrayList<String> doctors_array = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewModel = ViewModelProviders.of(this).get(DoctorPatientViewModel.class);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initLayout();
        setUpSpinner();

    }

    private void setUpSpinner() {


        ArrayList<String> options = new ArrayList<>();


        List<Doctor> doctorList = mViewModel.getDoctor_list().getValue();
        if (doctorList != null) {
            for (int i = 0; i < doctorList.size(); i++) {
                options.add(doctorList.get(i).getEmail());
            }

        }

        mViewModel.getDoctorList().observe(this, new android.arch.lifecycle.Observer<List<Doctor>>() {
            @Override
            public void onChanged(@Nullable final List<Doctor> doctors) {
                Log.e("--1->", "called");

                options.clear();

                for (int i = 0; i < doctors.size(); i++) {
                    options.add(doctors.get(i).getFirst_name() + " " +doctors.get(i).getLast_name());
                    Log.e("--->", doctors.get(i).getFirst_name());
                }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UploadFileActivity.this, android.R.layout.simple_list_item_multiple_choice, options);

                et_assigned_to
                        .setListAdapter(adapter)
                        .setListener(new MultiSelectSpinner.MultiSpinnerListener() {
                            @Override
                            public void onItemsSelected(boolean[] selected) {
                                doctors_array.clear();
                                for(int i=0;i<selected.length ;i++) {
                                    if (selected[i]){
                                        doctors_array.add(""+doctors.get(i).getIdentity());
                                    }
                                }
                            }
                        })
                        .setAllCheckedText("All")
                        .setAllUncheckedText("none selected")
                        .setSelectAll(false)
                        .setMinSelectedItems(1);


            }
        });

    }

    private void initLayout() {

        et_name = (TextView) findViewById(R.id.et_name);
        String firstName = PreferenceManager.getINSTANCE().readFromPref(this,PreferenceKeys.SP_NAME,"");
        String lastName = PreferenceManager.getINSTANCE().readFromPref(this,PreferenceKeys.SP_LAST_NAME,"");
        et_name.setText(firstName+" "+lastName);
        et_title = (EditText) findViewById(R.id.et_title);
        et_uploaddate = (EditText) findViewById(R.id.et_uploaddate);
        et_assigned_to = (MultiSelectSpinner) findViewById(R.id.et_assigned_to);
        et_signeddate = (EditText) findViewById(R.id.et_signeddate);
        et_description = (EditText) findViewById(R.id.et_description);
        et_content = (EditText) findViewById(R.id.et_content);
        tv_staus = (TextView) findViewById(R.id.tv_staus);
        tv_document = (TextView) findViewById(R.id.tv_document);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void AddReport(View view) {

        progressBar.setVisibility(View.VISIBLE);

        if(uri == null){
            Toast.makeText(this,"Select a File first",Toast.LENGTH_LONG);
            return;
        }

        String title = et_title.getText().toString();
        String name = et_name.getText().toString();
        String uploaddate = et_uploaddate.getText().toString();
        String assignedto = ""; //et_assigned_to.getText().toString();
        String signeddate = et_signeddate.getText().toString();
        String description = et_description.getText().toString();
        String content = et_content.getText().toString();
        String status = tv_staus.getText().toString();
        report = new Report(title, description, name, assignedto, uploaddate, signeddate, content, status);

        //convert the contents to base 64
        base64File = FileUtils.getBase64FromURI(this, uri);
        Log.e(TAG, "file--> " + base64File);

        String fileName = FileUtils.getFileName(this, uri);

        //send the report to ledger
        uploadReport(name, title, status, uploaddate, assignedto, signeddate, description, "", fileName, doctors_array);

    }

    public void uploadFILE(View view) {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

//        intent.setType("image/*");
        intent.setType("application/pdf");
//        intent.setType("*/*");


        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            if (resultData != null) {
                uri = resultData.getData();
                Log.i(TAG, "Uri: " + uri.toString());

                if (null != uri) {

                    try {
                        tv_document.setText(FileUtils.getFileName(this, uri));

                        Log.i(TAG, "FileContent: " + FileUtils.readTextFromUri(this, uri));
                        openPDF(uri);

                        //convert the contents to base 64
                        base64File = FileUtils.getBase64FromURI(this, uri);
                        Log.e(TAG, "file--> " + base64File);


//                        //convert back from base 64
//                        //create a pdf file with diff name
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            // Permission is not granted

                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                WRITE_REQUEST_CODE);
                        }
                        else{
                            FileUtils.saveFile(this, uri, base64File);
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case WRITE_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    FileUtils.saveFile(this, uri, base64File);

                } else {
                    Toast.makeText(this, "Writing permission denied",Toast.LENGTH_LONG);
                }
                return;
            }

        }
    }


    private void openPDF(Uri uri) {
        Intent intent = new Intent(this, PDFViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("URI", uri.toString());
        intent.putExtras(bundle);
        //TODO should be start activity for result
        startActivity(intent);
    }


    public void uploadReport(String name, String title, String status, String uploaddate, String assignedto,
                             String signeddate, String description, String base64document, String documentName, ArrayList<String> doctors_array) {


        ActiveLedgerSDK.KEYNAME = ActiveLedgerHelper.getInstance().getKeyname();
        ActiveLedgerSDK.keyType = ActiveLedgerHelper.getInstance().getKeyType();

        JSONObject uploadReportTransaction = ActiveLedgerHelper.getInstance().createUploadReportTransaction(null, ActiveLedgerSDK.getInstance().getKeyType(), name, title,
                status, uploaddate, assignedto, signeddate, description, base64document, documentName,doctors_array);

        String transactionString = Utility.getInstance().convertJSONObjectToString(uploadReportTransaction);

        Utils.Log("UploadReport Transaction", transactionString);
        Log.e("UploadReport token", com.example.hamid.dhealth.Preference.PreferenceManager.getINSTANCE().readFromPref(this, PreferenceKeys.SP_APP_TOKEN, "null"));

        HttpClient.getInstance().sendTransaction(com.example.hamid.dhealth.Preference.PreferenceManager.getINSTANCE().readFromPref(this, PreferenceKeys.SP_APP_TOKEN, "null"), transactionString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onNext(Response<String> response) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("UploadReport code--->", response.code() + "");
                        if (response.code() == 200) {
                            Utils.Log("UploadReport res--->", response.body() + "");

                            Toast.makeText(UploadFileActivity.this, "Report Uploaded Successfully!", Toast.LENGTH_LONG);

                            //add the report to db if response is 200
                            DataRepository dataRepository = DataRepository.getINSTANCE(getApplication());
                            if (report != null)
                                dataRepository.insertReport(report);
                            finish();

                        } else {
                            Toast.makeText(UploadFileActivity.this, "Report Uploading  Failed!", Toast.LENGTH_LONG);
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

}
