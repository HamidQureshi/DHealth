package com.example.hamid.dhealth.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.activeledgersdk.ActiveLedgerSDK;
import com.example.activeledgersdk.utility.Utility;
import com.example.hamid.dhealth.ActiveLedgerHelper;
import com.example.hamid.dhealth.FileUtils;
import com.example.hamid.dhealth.Fragments.DoctorPatientViewModel;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Doctor;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Patient;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Report;
import com.example.hamid.dhealth.MedicalRepository.DataRepository;
import com.example.hamid.dhealth.MedicalRepository.HTTP.HttpClient;
import com.example.hamid.dhealth.Preference.PreferenceKeys;
import com.example.hamid.dhealth.Preference.PreferenceManager;
import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ReportDetailActivity extends AppCompatActivity {

    public static final String REPORT_DATA = "report_data";
    public static Report report = null;
    EditText et_title, et_name, et_description, et_document;
    MultiSelectSpinner et_assigned_to;
    private Disposable disposable;
    private ArrayList<String> doctors_array = new ArrayList<>();
    private ArrayList<String> patients_array = new ArrayList<>();
    private DoctorPatientViewModel mViewModel;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(report.getFileName());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initLayouts();

        populateLayout(report);

        mViewModel = ViewModelProviders.of(this).get(DoctorPatientViewModel.class);

        ArrayList<String> options = new ArrayList<>();


        if (PreferenceManager.getINSTANCE().readFromPref(this, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR).equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {


            List<Patient> patientList = mViewModel.getPatient_list().getValue();
            if (patientList != null) {
                for (int i = 0; i < patientList.size(); i++) {
                    options.add(patientList.get(i).getEmail());
                }

            }

            mViewModel.getPatientList().observe(this, new android.arch.lifecycle.Observer<List<Patient>>() {
                @Override
                public void onChanged(@Nullable final List<Patient> patients) {

                    options.clear();

                    for (int i = 0; i < patients.size(); i++) {
                        options.add(patients.get(i).getFirst_name() + " " + patients.get(i).getLast_name());
                        Log.e("--->", patients.get(i).getFirst_name());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReportDetailActivity.this, android.R.layout.simple_list_item_multiple_choice, options);

                    et_assigned_to
                            .setListAdapter(adapter)
                            .setListener(new MultiSelectSpinner.MultiSpinnerListener() {
                                @Override
                                public void onItemsSelected(boolean[] selected) {
                                    patients_array.clear();
                                    for (int i = 0; i < selected.length; i++) {
                                        if (selected[i]) {
                                            patients_array.add("" + patients.get(i).getIdentity());
                                        }
                                    }
                                }
                            })
                            .setAllCheckedText("All")
                            .setAllUncheckedText("none selected")
                            .setSelectAll(false)
                            .setMinSelectedItems(1);

                    for (int i = 0; i < patients.size(); i++) {
                        for (int j = 0; j < patients_array.size(); j++)
                            if (patients.get(i).getIdentity().equalsIgnoreCase(patients_array.get(j))) {
                                Log.e("patients selected", i + "= " + patients.get(i).getFirst_name());
                                et_assigned_to.selectItem(i, true);
                            }
                    }
                }
            });


        } else {

            List<Doctor> doctorList = mViewModel.getDoctor_list().getValue();
            if (doctorList != null) {
                for (int i = 0; i < doctorList.size(); i++) {
                    options.add(doctorList.get(i).getEmail());
                }

            }

            mViewModel.getDoctorList().observe(this, new android.arch.lifecycle.Observer<List<Doctor>>() {
                @Override
                public void onChanged(@Nullable final List<Doctor> doctors) {

                    options.clear();

                    for (int i = 0; i < doctors.size(); i++) {
                        options.add(doctors.get(i).getFirst_name() + " " + doctors.get(i).getLast_name());
                        Log.e("--->", doctors.get(i).getFirst_name());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReportDetailActivity.this, android.R.layout.simple_list_item_multiple_choice, options);

                    et_assigned_to
                            .setListAdapter(adapter)
                            .setListener(new MultiSelectSpinner.MultiSpinnerListener() {
                                @Override
                                public void onItemsSelected(boolean[] selected) {
                                    doctors_array.clear();
                                    for (int i = 0; i < selected.length; i++) {
                                        if (selected[i]) {
                                            doctors_array.add("" + doctors.get(i).getIdentity());
                                        }
                                    }
                                }
                            })
                            .setAllCheckedText("All")
                            .setAllUncheckedText("none selected")
                            .setSelectAll(false)
                            .setMinSelectedItems(1);

                    for (int i = 0; i < doctors.size(); i++) {
                        for (int j = 0; j < doctors_array.size(); j++)
                            if (doctors.get(i).getIdentity().equalsIgnoreCase(doctors_array.get(j))) {
                                Log.e("doctors selected", i + "= " + doctors.get(i).getFirst_name());
                                et_assigned_to.selectItem(i, true);
                            }
                    }
                }
            });


        }


    }

    private void populateLayout(Report report) {

        if (report != null) {
            et_name.setText(report.getOwnership());
            et_title.setText(report.getTitle());
            et_assigned_to = (MultiSelectSpinner) findViewById(R.id.et_assigned_to);
            et_description.setText(report.getDescription());
            et_document.setText(report.getFileName());

            if (PreferenceManager.getINSTANCE().readFromPref(this, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR).equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {
                extractIDPatients(report.getDoctors());
            } else {
                extractIDDoctors(report.getDoctors());

            }
        }

    }


    private void initLayouts() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_title = (EditText) findViewById(R.id.et_title);
        et_description = (EditText) findViewById(R.id.et_description);
        et_document = (EditText) findViewById(R.id.et_document);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.edit_options_menu, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
//        else if (id == R.id.edit) {
//
//            Toast.makeText(this, "Profile Editing Enabled", Toast.LENGTH_SHORT).show();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void showPDF(View view) {
        openPDF(report.getFileName(), FileUtils.saveFile(report.getFileName(), report.getContent()));
    }

    public void updateReport(View view) {
        progressBar.setVisibility(View.VISIBLE);


        ActiveLedgerSDK.KEYNAME = ActiveLedgerHelper.getInstance().getKeyname();
        ActiveLedgerSDK.keyType = ActiveLedgerHelper.getInstance().getKeyType();

        String name = et_name.getText().toString();
        String title = et_title.getText().toString();
        String status = "";
        String uploaddate = "";
        String assignedto = "";
        String signeddate = "";
        String description = et_description.getText().toString();
        String base64document = report.getContent();
        String documentName = report.getFileName();
        String email = PreferenceManager.getINSTANCE().readFromPref(this, PreferenceKeys.SP_EMAIL, "");
        JSONObject updateReportTransaction;

        if (PreferenceManager.getINSTANCE().readFromPref(this, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR).equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {
            updateReportTransaction = ActiveLedgerHelper.getInstance().createUpdateReportTransaction(null, ActiveLedgerSDK.getInstance().getKeyType(), name, title,
                    status, uploaddate, assignedto, signeddate, description, base64document, documentName, patients_array, email);
        } else {
            updateReportTransaction = ActiveLedgerHelper.getInstance().createUpdateReportTransaction(null, ActiveLedgerSDK.getInstance().getKeyType(), name, title,
                    status, uploaddate, assignedto, signeddate, description, base64document, documentName, doctors_array, email);
        }


        String transactionString = Utility.getInstance().convertJSONObjectToString(updateReportTransaction);

        Utils.Log("UpdateReport Transaction", transactionString);
        Log.e("UpdateReport token", com.example.hamid.dhealth.Preference.PreferenceManager.getINSTANCE().readFromPref(this, PreferenceKeys.SP_APP_TOKEN, "null"));

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
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onNext(Response<String> response) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("UpdateReport code--->", response.code() + "");
                        if (response.code() == 200) {
                            Utils.Log("UpdateReport res--->", response.body() + "");

                            Toast.makeText(ReportDetailActivity.this, "Report Update Successfully!", Toast.LENGTH_SHORT).show();

                            DataRepository dataRepository = DataRepository.getINSTANCE(getApplication());
                            if (report != null)
                                dataRepository.updateReport(report);
                            finish();

                        } else {
                            Toast.makeText(ReportDetailActivity.this, "Report Update  Failed!", Toast.LENGTH_SHORT).show();
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


    public void extractIDDoctors(String doctors) {
        try {
            JSONArray jsonArray = new JSONArray(doctors);
            for (int i = 0; i < jsonArray.length(); i++) {
                doctors_array.add("" + jsonArray.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void extractIDPatients(String patients) {
        try {
            JSONArray jsonArray = new JSONArray(patients);
            for (int i = 0; i < jsonArray.length(); i++) {
                patients_array.add("" + jsonArray.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void openPDF(String filename, Uri uri) {
        Intent intent = new Intent(this, PDFViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("URI", uri.toString());
        bundle.putString("filename", filename);
        bundle.putBoolean("showAttachButton", false);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
