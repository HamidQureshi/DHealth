package com.example.hamid.dhealth.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Report;
import com.example.hamid.dhealth.R;

public class ReportDetailActivity extends AppCompatActivity {

    public static final String REPORT_DATA = "report_data";
    TextView tv_title, tv_name, tv_staus, tv_uploaddate, tv_assigned_to, tv_signeddate, tv_description, tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initLayouts();


        Report report = (Report) getIntent().getSerializableExtra(REPORT_DATA);

        populateLayout(report);


    }

    private void populateLayout(Report report) {

        if (report != null) {
            tv_name.setText(report.getOwnership());
            tv_title.setText(report.getTitle());
            tv_staus.setText(report.getStatus());
            tv_uploaddate.setText(report.getUploadedDate());
            tv_assigned_to.setText(report.getAssignedTo());
            tv_signeddate.setText(report.getSignDate());
            tv_description.setText(report.getDescription());
            tv_content.setText(report.getContent());
        }

    }


    private void initLayouts() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_staus = (TextView) findViewById(R.id.tv_staus);
        tv_uploaddate = (TextView) findViewById(R.id.tv_uploaddate);
        tv_assigned_to = (TextView) findViewById(R.id.tv_assigned_to);
        tv_signeddate = (TextView) findViewById(R.id.tv_signeddate);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_description = (TextView) findViewById(R.id.tv_description);
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
