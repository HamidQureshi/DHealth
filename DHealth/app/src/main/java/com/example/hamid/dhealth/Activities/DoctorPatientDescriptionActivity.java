package com.example.hamid.dhealth.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Doctor;
import com.example.hamid.dhealth.R;

public class DoctorPatientDescriptionActivity extends AppCompatActivity {

    public static final String DOCTOR_DATA = "doctor_data";

    TextView tv_name;

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
            tv_name.setText(doctor.getName());
        }


    }

    private void initLayouts() {

        tv_name = (TextView) findViewById(R.id.tv_name);

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
