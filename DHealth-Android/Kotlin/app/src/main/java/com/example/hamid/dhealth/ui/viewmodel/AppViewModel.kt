package com.example.hamid.dhealth.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import com.example.hamid.dhealth.data.DataRepository
import com.example.hamid.dhealth.data.Preference.PreferenceKeys
import com.example.hamid.dhealth.data.Preference.PreferenceManager
import com.example.hamid.dhealth.data.localdb.DAO.DatabaseDAO
import com.example.hamid.dhealth.data.localdb.Entity.Doctor
import com.example.hamid.dhealth.data.localdb.Entity.Patient
import com.example.hamid.dhealth.data.localdb.Entity.Report
import com.example.hamid.dhealth.data.remote.APIService

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.annotations.NonNull
import retrofit2.Response

class AppViewModel @Inject
constructor(@NonNull application: Application, apiService: APIService, databaseDAO: DatabaseDAO, preferenceManager: PreferenceManager) : AndroidViewModel(application) {

//    @Inject
//    lateinit var preferenceManager: PreferenceManager
    private val repository: DataRepository = DataRepository(apiService, databaseDAO)
    //        if (doctor_list.getValue() == null) {
    //            getDoctorListFromServer(preferenceManager.readFromPref(getApplication(), PreferenceKeys.SP_APP_TOKEN, "null"));
    //        }
    var doctor_list: LiveData<List<Doctor>>? = null
    //        if (patient_list.getValue() == null) {
    ////            getPatientListFromServer(preferenceManager.readFromPref(getApplication(), PreferenceKeys.SP_APP_TOKEN, "null"));
    //            getAssignedPatientListFromServer(preferenceManager.readFromPref(getApplication(), PreferenceKeys.SP_APP_TOKEN, "null"));
    //        }
    val patient_list: LiveData<List<Patient>>?
    //report viewmodel methods

    val reportList: LiveData<List<Report>>?
    var isFetchingDoctorPatientData = false
    var isFetchingReportData = false


    init {
        reportList = repository.reportList
        doctor_list = repository.doctorList
        patient_list = repository.patientList
    }

    fun getDoctorList(): LiveData<List<Doctor>>? {
        return doctor_list
    }

    fun getPatientList(): LiveData<List<Patient>>? {
        return patient_list
    }

    fun insertDoctor(doctor: Doctor) {
        repository.insertDoctor(doctor)
    }

    fun insertPatient(patient: Patient) {
        repository.insertPatient(patient)
    }

    fun deleteDctorData() {
        repository.deleteAllDoctor()
    }

    fun deletePatientData() {
        repository.deleteAllPatient()
    }


    fun getDoctorListFromServer(token: String) {
        repository.getDoctorListFromServer(token)
    }

    fun getPatientListFromServer(token: String) {
        repository.getPatientListFromServer(token)
    }

    fun getAssignedPatientListFromServer(token: String) {
        repository.getAssignedPatientListFromServer(token)
    }

    fun searchDoctorsList(title: String): List<Doctor> {
        return repository.searchDoctorsList(title)
    }

    fun searchPatientList(title: String): List<Patient> {
        return repository.searchPatientsList(title)
    }

    fun insert(report: Report) {
        repository.insertReport(report)
    }

    fun deleteReport(position: Int) {
        //        Report report = report_list.get(position);
        //        repository.deleteReport(report);

    }

    fun deleteReportData() {
        repository.deleteAllReport()
    }

    fun searchReportsList(title: String): List<Report> {
        return repository.searchReportList(title)
    }


    fun getReportsListFromServer(token: String) {
        repository.getReportsListFromServer(token)
    }

    fun insertReportList(reportList: List<Report>) {
        repository.insertReportList(reportList)
    }

    fun createProfile(token: String, transaction: String): Observable<Response<String>> {
        return repository.createProfile(token, transaction)
    }

    fun loginUser(user: String): Observable<Response<String>> {
        return repository.loginUser(user)
    }

    fun registerUser(user: String): Observable<Response<String>> {
        return repository.registerUser(user)
    }

    // this method can be used to send transaction as an HTTP request to the ledger
    fun sendTransaction(token: String, transaction: String): Observable<Response<String>> {
        return repository.sendTransaction(token, transaction)
    }

    fun updateReport(report: Report) {
        repository.updateReport(report)
    }

    fun insertReport(report: Report) {
        repository.insertReport(report)
    }

    fun nukeDB() {
        repository.deleteAllDoctor()
        repository.deleteAllPatient()
        repository.deleteAllReport()
    }


}
