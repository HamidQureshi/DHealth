package com.example.hamid.dhealth.MedicalRepository;


import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.hamid.dhealth.MedicalRepository.DB.DAO.DatabaseDAO;
import com.example.hamid.dhealth.MedicalRepository.DB.DAO.MedicalRoomDatabase;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Doctor;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Patient;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Report;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class DataRepository {

    private DatabaseDAO databaseDAO;
    private LiveData<List<Doctor>> mAllDoctors;
    private LiveData<List<Patient>> mAllPatients;
    private LiveData<List<Report>> mAllReports;

    public DataRepository(Application application) {
        MedicalRoomDatabase roomDatabase = MedicalRoomDatabase.getDatabase(application);
        databaseDAO = roomDatabase.medicalDao();
        mAllDoctors = databaseDAO.getDoctorList();
        mAllPatients = databaseDAO.getPatientList();
        mAllReports = databaseDAO.getReportList();
    }


    public LiveData<List<Doctor>> getDoctorList() {
        return mAllDoctors;
    }

    public LiveData<List<Patient>> getPatientList() {
        return mAllPatients;
    }

    public LiveData<List<Report>> getReportList() {
        return mAllReports;
    }

    public List<Report> searchReportList(String name) {
        return databaseDAO.searchReports(name + '%');
    }


    @SuppressLint("CheckResult")
    public void insertDoctor(final Doctor doctor) {

        Observable.fromCallable(() -> {
            databaseDAO.insertDoctor(doctor);
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });

    }

    public void insertPatient(final Patient patient) {

        Observable.fromCallable(() -> {
            databaseDAO.insertPatient(patient);
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });

    }

    public void insertReport(Report report) {

        Observable.fromCallable(() -> {
            databaseDAO.insertReport(report);
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });


    }


    public void deleteReport(Report report) {
        Observable.fromCallable(() -> {
            databaseDAO.deleteReport(report);
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });
    }


    public void deleteAllDoctor() {

        Observable.fromCallable(() -> {
            databaseDAO.deleteAllDoctor();
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });

    }

    public void deleteAllPatient() {

        Observable.fromCallable(() -> {
            databaseDAO.deleteAllPatient();
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });

    }

    public void deleteAllReport() {

        Observable.fromCallable(() -> {
            databaseDAO.deleteAllReport();
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });

    }


}
