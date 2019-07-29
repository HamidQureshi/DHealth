package com.example.hamid.dhealth.ui.Activities;

import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid.dhealth.data.localdb.Entity.Doctor;
import com.example.hamid.dhealth.data.localdb.Entity.Patient;
import com.example.hamid.dhealth.data.Preference.PreferenceKeys;
import com.example.hamid.dhealth.data.Preference.PreferenceManager;
import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.utils.Utils;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class DoctorPatientDescriptionActivity extends AppCompatActivity {

    public static final String DATA = "data";

    ImageView iv_dp,iv_dp_bg;
    TextView et_name;
    TextView et_last_name, et_email, et_dob, et_phone, et_address;

    @Inject
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_patient_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initLayouts();

        if(preferenceManager == null){
            preferenceManager = new PreferenceManager();
        }
        if (preferenceManager.readFromPref(this, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR).equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {
            Patient patient = (Patient) getIntent().getSerializableExtra(DATA);
            if (patient != null) {
                et_name.setText(patient.getFirst_name());
                et_last_name.setText(patient.getLast_name());
                et_email.setText(patient.getEmail());
                et_phone.setText(patient.getPhone_number());
                et_address.setText(patient.getAddress());
                et_dob.setText(patient.getDate_of_birth());
                iv_dp.setImageBitmap(Utils.decodeBase64(patient.getDp()));
//                createPaletteAsync(Utils.decodeBase64(patient.getDp()));
            }
        } else {
            Doctor doctor = (Doctor) getIntent().getSerializableExtra(DATA);
            if (doctor != null) {
                et_name.setText(doctor.getFirst_name());
                et_last_name.setText(doctor.getLast_name());
                et_email.setText(doctor.getEmail());
                et_phone.setText(doctor.getPhone_number());
                et_address.setText(doctor.getAddress());
                et_dob.setText(doctor.getDate_of_birth());
                iv_dp.setImageBitmap(Utils.decodeBase64(doctor.getDp()));
//                createPaletteAsync(Utils.decodeBase64(doctor.getDp()));
            }
        }

        setTitle(et_name.getText() + " " + et_last_name.getText());

    }

    private void initLayouts() {
        iv_dp = (ImageView) findViewById(R.id.iv_dp);
        iv_dp_bg = (ImageView) findViewById(R.id.iv_dp_bg);
        et_name = (TextView) findViewById(R.id.et_name);
        et_last_name = (TextView) findViewById(R.id.et_last_name);
        et_email = (TextView) findViewById(R.id.et_email);
        et_phone = (TextView) findViewById(R.id.et_phone);
        et_address = (TextView) findViewById(R.id.et_address);
        et_dob = (TextView) findViewById(R.id.et_dob);
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

    // Generate palette asynchronously and use it on a different
//    public void createPaletteAsync(Bitmap bitmap) {
//        if(bitmap ==null)
//            return;
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            public void onGenerated(Palette p) {
//                iv_dp_bg.setBackgroundColor( p.getDarkVibrantColor(getResources().getColor(R.color.colorPrimary)));
//            }
//        });
//    }


}
