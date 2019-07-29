package com.example.hamid.dhealth.ui.Activities;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activeledgersdk.ActiveLedgerSDK;
import com.example.activeledgersdk.utility.Utility;
import com.example.hamid.dhealth.ActiveLedgerHelper;
import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.data.Preference.PreferenceKeys;
import com.example.hamid.dhealth.data.Preference.PreferenceManager;
import com.example.hamid.dhealth.data.localdb.Entity.Doctor;
import com.example.hamid.dhealth.data.localdb.Entity.Patient;
import com.example.hamid.dhealth.data.localdb.Entity.Report;
import com.example.hamid.dhealth.factory.ViewModelFactory;
import com.example.hamid.dhealth.ui.viewmodel.AppViewModel;
import com.example.hamid.dhealth.utils.FileUtils;
import com.example.hamid.dhealth.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
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
    TextView tv_lbl_assigned_to;
    Button btn_update;
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    PreferenceManager preferenceManager;

    private Disposable disposable;
    private ArrayList<String> doctors_array = new ArrayList<>();
    private ArrayList<String> patients_array = new ArrayList<>();
    private AppViewModel mViewModel;
    private ProgressBar progressBar;

    private AppViewModel appViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        appViewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
        setContentView(R.layout.activity_report_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(report.getTitle());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initLayouts();

        populateLayout(report);

        mViewModel = ViewModelProviders.of(this).get(AppViewModel.class);

        ArrayList<String> options = new ArrayList<>();


        if (preferenceManager.readFromPref(this, PreferenceKeys.INSTANCE.getSP_PROFILE_TYPE(), PreferenceKeys.INSTANCE.getLBL_DOCTOR()).equalsIgnoreCase(PreferenceKeys.INSTANCE.getLBL_DOCTOR())) {


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


        if (preferenceManager.readFromPref(this, PreferenceKeys.INSTANCE.getSP_PROFILE_TYPE(), PreferenceKeys.INSTANCE.getLBL_DOCTOR()).equalsIgnoreCase(PreferenceKeys.INSTANCE.getLBL_DOCTOR())) {
            tv_lbl_assigned_to.setVisibility(View.GONE);
            et_assigned_to.setVisibility(View.GONE);
            btn_update.setVisibility(View.GONE);
        }




        }

    private void populateLayout(Report report) {

        if (report != null) {
            et_name.setText(report.getOwnership());
            et_title.setText(report.getTitle());
            et_assigned_to = (MultiSelectSpinner) findViewById(R.id.et_assigned_to);
            et_description.setText(report.getDescription());
            et_document.setText(report.getFileName());

            if (preferenceManager.readFromPref(this, PreferenceKeys.INSTANCE.getSP_PROFILE_TYPE(), PreferenceKeys.INSTANCE.getLBL_DOCTOR()).equalsIgnoreCase(PreferenceKeys.INSTANCE.getLBL_DOCTOR())) {
                if (report.getDoctors().isEmpty()) {
                    et_assigned_to.setVisibility(View.GONE);
                    tv_lbl_assigned_to.setVisibility(View.GONE);
                    btn_update.setVisibility(View.GONE);
                } else {
                    et_assigned_to.setVisibility(View.VISIBLE);
                    tv_lbl_assigned_to.setVisibility(View.VISIBLE);
                    btn_update.setVisibility(View.VISIBLE);
                    extractIDPatients(report.getDoctors());
                }
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
        tv_lbl_assigned_to = (TextView) findViewById(R.id.tv_lbl_assigned_to);
        btn_update = (Button) findViewById(R.id.btn_update);

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

    public void showPDF(View view) {
        openPDF(report.getFileName(), FileUtils.INSTANCE.saveFile(report.getFileName(), report.getContent()));
    }

    public void updateReport(View view) {
        progressBar.setVisibility(View.VISIBLE);


        ActiveLedgerSDK.KEYNAME = ActiveLedgerHelper.Companion.getInstance().getKeyname();
        ActiveLedgerSDK.keyType = ActiveLedgerHelper.Companion.getInstance().getKeyType();

        String name = et_name.getText().toString();
        String title = et_title.getText().toString();
        String status = "";
        String uploaddate = "";
        String assignedto = "";
        String signeddate = "";
        String description = et_description.getText().toString();
        String base64document = report.getContent();
        String documentName = report.getFileName();
        String email = preferenceManager.readFromPref(this, PreferenceKeys.INSTANCE.getSP_EMAIL(), "");
        JSONObject updateReportTransaction;

        if (preferenceManager.readFromPref(this, PreferenceKeys.INSTANCE.getSP_PROFILE_TYPE(), PreferenceKeys.INSTANCE.getLBL_DOCTOR()).equalsIgnoreCase(PreferenceKeys.INSTANCE.getLBL_DOCTOR())) {
            updateReportTransaction = ActiveLedgerHelper.Companion.getInstance().createUpdateReportTransaction(null, ActiveLedgerSDK.getInstance().getKeyType(), name, title,
                    status, uploaddate, assignedto, signeddate, description, base64document, documentName, patients_array, email);
            report.setDoctors(patients_array.toString());

        } else {
            updateReportTransaction = ActiveLedgerHelper.Companion.getInstance().createUpdateReportTransaction(null, ActiveLedgerSDK.getInstance().getKeyType(), name, title,
                    status, uploaddate, assignedto, signeddate, description, base64document, documentName, doctors_array, email);
            report.setDoctors(doctors_array.toString());

        }


        String transactionString = Utility.getInstance().convertJSONObjectToString(updateReportTransaction);

        Utils.INSTANCE.Log("UpdateReport Transaction", transactionString);
        Log.e("UpdateReport token", preferenceManager.readFromPref(this, PreferenceKeys.INSTANCE.getSP_APP_TOKEN(), "null"));

        appViewModel.sendTransaction(preferenceManager.readFromPref(this, PreferenceKeys.INSTANCE.getSP_APP_TOKEN(), "null"), transactionString)
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
                            Utils.INSTANCE.Log("UpdateReport res--->", response.body() + "");

                            Toast.makeText(ReportDetailActivity.this, "Report Update Successfully!", Toast.LENGTH_SHORT).show();

                            if (report != null)
                                appViewModel.updateReport(report);
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
