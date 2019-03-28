package com.example.hamid.dhealth.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Report;
import com.example.hamid.dhealth.MedicalRepository.DataRepository;
import com.example.hamid.dhealth.R;

public class UploadFileActivity extends AppCompatActivity {

    EditText et_name, et_title, et_uploaddate, et_assigned_to, et_signeddate, et_description, et_content;
    TextView tv_staus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        initLayout();
    }

    private void initLayout() {

        et_name = (EditText) findViewById(R.id.et_name);
        et_title = (EditText) findViewById(R.id.et_title);
        et_uploaddate = (EditText) findViewById(R.id.et_uploaddate);
        et_assigned_to = (EditText) findViewById(R.id.et_assigned_to);
        et_signeddate = (EditText) findViewById(R.id.et_signeddate);
        et_description = (EditText) findViewById(R.id.et_description);
        et_content = (EditText) findViewById(R.id.et_content);
        tv_staus = (TextView) findViewById(R.id.tv_staus);

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

        //fetch the data and add it to list and add to activeledger

        String title = et_title.getText().toString();
        String name = et_name.getText().toString();
        String uploaddate = et_uploaddate.getText().toString();
        String assignedto = et_assigned_to.getText().toString();
        String signeddate = et_signeddate.getText().toString();
        String description = et_description.getText().toString();
        String content = et_content.getText().toString();
        String status = tv_staus.getText().toString();
        Report report = new Report(title, description, name, assignedto, uploaddate, signeddate, content, status);

        //add the report to db
        DataRepository dataRepository = DataRepository.getINSTANCE(getApplication());
        dataRepository.insertReport(report);

        finish();

    }
}
