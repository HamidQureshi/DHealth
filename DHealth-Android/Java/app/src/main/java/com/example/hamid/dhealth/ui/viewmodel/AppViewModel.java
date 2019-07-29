package com.example.hamid.dhealth.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.hamid.dhealth.data.DataRepository;
import com.example.hamid.dhealth.data.Preference.PreferenceKeys;
import com.example.hamid.dhealth.data.Preference.PreferenceManager;
import com.example.hamid.dhealth.data.localdb.DAO.DatabaseDAO;
import com.example.hamid.dhealth.data.localdb.Entity.Doctor;
import com.example.hamid.dhealth.data.localdb.Entity.Patient;
import com.example.hamid.dhealth.data.localdb.Entity.Report;
import com.example.hamid.dhealth.data.remote.APIService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import retrofit2.Response;

public class AppViewModel extends AndroidViewModel {

    @Inject
    PreferenceManager preferenceManager;
    private DataRepository repository;
    private LiveData<List<Doctor>> doctor_list;
    private LiveData<List<Patient>> patient_list;
    private LiveData<List<Report>> report_list;
    private boolean fetchingDoctorPatientData = false;
    private boolean fetchingReportData = false;


    @Inject
    public AppViewModel(@NonNull Application application, APIService apiService, DatabaseDAO databaseDAO) {
        super(application);
        repository = new DataRepository(apiService, databaseDAO);

        report_list = repository.getReportList();
        doctor_list = repository.getDoctorList();
        patient_list = repository.getPatientList();
    }

    public LiveData<List<Doctor>> getDoctor_list() {
//        if (doctor_list.getValue() == null) {
//            getDoctorListFromServer(preferenceManager.readFromPref(getApplication(), PreferenceKeys.SP_APP_TOKEN, "null"));
//        }
        return doctor_list;
    }

    public void setDoctor_list(LiveData<List<Doctor>> doctor_list) {
        this.doctor_list = doctor_list;
    }

    public LiveData<List<Patient>> getPatient_list() {
//        if (patient_list.getValue() == null) {
////            getPatientListFromServer(preferenceManager.readFromPref(getApplication(), PreferenceKeys.SP_APP_TOKEN, "null"));
//            getAssignedPatientListFromServer(preferenceManager.readFromPref(getApplication(), PreferenceKeys.SP_APP_TOKEN, "null"));
//        }
        return patient_list;
    }

    public LiveData<List<Doctor>> getDoctorList() {
        return doctor_list;
    }

    public LiveData<List<Patient>> getPatientList() {
        return patient_list;
    }

    public void insertDoctor(Doctor doctor) {
        repository.insertDoctor(doctor);
    }

    public void insertPatient(Patient patient) {
        repository.insertPatient(patient);
    }

    public void deleteDctorData() {
        repository.deleteAllDoctor();
    }

    public void deletePatientData() {
        repository.deleteAllPatient();
    }


    public void getDoctorListFromServer(String token) {
        repository.getDoctorListFromServer(token);
    }

    public void getPatientListFromServer(String token) {
        repository.getPatientListFromServer(token);
    }

    public void getAssignedPatientListFromServer(String token) {
        repository.getAssignedPatientListFromServer(token);
    }

    public List<Doctor> searchDoctorsList(String title) {
        return repository.searchDoctorsList(title);
    }

    public List<Patient> searchPatientList(String title) {
        return repository.searchPatientsList(title);
    }


    //report viewmodel methods

    public LiveData<List<Report>> getReportList() {
        return report_list;
    }

    public void insert(Report report) {
        repository.insertReport(report);
    }

    public void deleteReport(int position) {
//        Report report = report_list.get(position);
//        repository.deleteReport(report);

    }

    public void deleteReportData() {
        repository.deleteAllReport();
    }

    public List<Report> searchReportsList(String title) {
        return repository.searchReportList(title);
    }


    public void getReportsListFromServer(String token) {
        repository.getReportsListFromServer(token);
    }

    public void insertReportList(final List<Report> reportList) {
        repository.insertReportList(reportList);
    }

    public Observable<Response<String>> createProfile(String token, String transaction) {
        return repository.createProfile(token, transaction);
    }

    public Observable<Response<String>> loginUser(String user) {
        return repository.loginUser(user);
    }

    public Observable<Response<String>> registerUser(String user) {
        return repository.registerUser(user);
    }

    // this method can be used to send transaction as an HTTP request to the ledger
    public Observable<Response<String>> sendTransaction(String token, String transaction) {
        return repository.sendTransaction(token, transaction);
    }

    public void updateReport(Report report) {
        repository.updateReport(report);
    }

    public void insertReport(Report report) {
        repository.insertReport(report);
    }

    public void nukeDB() {
        repository.deleteAllDoctor();
        repository.deleteAllPatient();
        repository.deleteAllReport();
    }

    public boolean isFetchingDoctorPatientData() {
        return fetchingDoctorPatientData;
    }

    public void setFetchingDoctorPatientData(boolean fetchingDoctorPatientData) {
        this.fetchingDoctorPatientData = fetchingDoctorPatientData;
    }

    public boolean isFetchingReportData() {
        return fetchingReportData;
    }

    public void setFetchingReportData(boolean fetchingReportData) {
        this.fetchingReportData = fetchingReportData;
    }


}
