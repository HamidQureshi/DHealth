package com.example.hamid.dhealth.Fragments;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Report;
import com.example.hamid.dhealth.MedicalRepository.DataRepository;

import java.util.List;

public class ReportsViewModel extends AndroidViewModel {

    private DataRepository repository;
    private LiveData<List<Report>> report_list;

    public ReportsViewModel(Application application) {
        super(application);
        repository = new DataRepository(application);
        report_list = repository.getReportList();
    }

    public LiveData<List<Report>> getReportList() {
        return report_list;
    }

    public void insert(Report report) {
        repository.insertReport(report);
    }

    public void deleteReport( int position){
//        Report report = report_list.get(position);
//        repository.deleteReport(report);

    }

    public void deleteReportData() {
        repository.deleteAllReport();
    }


}
