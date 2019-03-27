package com.example.hamid.dhealth.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Doctor;
import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.Utils.Utils;

public class DoctorPatientDescriptionActivity extends AppCompatActivity {

    public static final String DOCTOR_DATA = "doctor_data";

    ImageView iv_dp;
    TextView et_name;
    TextView et_last_name, et_email, et_dob, et_phone, et_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_patient_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initLayouts();

        Doctor doctor = (Doctor) getIntent().getSerializableExtra(DOCTOR_DATA);

        if (doctor != null) {
            et_name.setText(doctor.getFirst_name());
            et_last_name.setText(doctor.getLast_name());
            et_email.setText(doctor.getEmail());
            et_phone.setText(doctor.getPhone_number());
            et_address.setText(doctor.getAddress());
            et_dob.setText(doctor.getDate_of_birth());
            iv_dp.setImageBitmap(Utils.decodeBase64(doctor.getDp()));

        }


    }

    private void initLayouts() {

        iv_dp = (ImageView) findViewById(R.id.iv_dp);
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


}
