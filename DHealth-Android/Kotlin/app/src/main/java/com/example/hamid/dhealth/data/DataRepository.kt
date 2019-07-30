package com.example.hamid.dhealth.data

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.activeledgersdk.ActiveLedgerSDK
import com.example.hamid.dhealth.ActiveLedgerHelper
import com.example.hamid.dhealth.data.localdb.DAO.DatabaseDAO
import com.example.hamid.dhealth.data.localdb.Entity.Doctor
import com.example.hamid.dhealth.data.localdb.Entity.Patient
import com.example.hamid.dhealth.data.localdb.Entity.Report
import com.example.hamid.dhealth.data.remote.APIService
import com.example.hamid.dhealth.ui.Fragments.ReportsFragment
import com.example.hamid.dhealth.ui.viewmodel.AppViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class DataRepository(private val apiService: APIService, private val databaseDAO: DatabaseDAO, private val viewModel: AppViewModel) {
    var doctorList: LiveData<List<Doctor>> = databaseDAO.doctorList
        private set
    var patientList: LiveData<List<Patient>> = databaseDAO.patientList
        private set
    var reportList: LiveData<List<Report>> = databaseDAO.reportList
        private set
    private var disposable = CompositeDisposable()

    init {
//        doctorList = databaseDAO.doctorList
//        patientList = databaseDAO.patientList
//        reportList = databaseDAO.reportList
    }

    fun getDoctorListFromServer(token: String) {
        disposable.add(
                apiService.getDoctorList(token)
                        .map { viewModel.parseDoctorListResponse(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribeWith(object : DisposableSingleObserver<List<Doctor>>() {

                            override fun onError(e: Throwable) {
                                Log.e("doctorlist error", e.message + "")
                            }

                            override fun onSuccess(doctorList: List<Doctor>) {
                                insertDoctorList(doctorList)
                            }
                        }))

    }

    fun getPatientListFromServer(token: String) {
        disposable.add(
                apiService.getPatientList(token)
                        .map { viewModel.parsePatientListResponse(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribeWith(object : DisposableSingleObserver<List<Patient>>() {

                            override fun onError(e: Throwable) {
                                e.printStackTrace()
                            }

                            override fun onSuccess(patientList: List<Patient>) {
                                insertPatientList(patientList)
                            }
                        }))
    }

    fun getAssignedPatientListFromServer(token: String) {
        disposable.add(
                apiService.getAssignedPatientList(token)
                        .map { viewModel.parseAssignedPatientListResponse(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribeWith(object : DisposableSingleObserver<List<Patient>>() {

                            override fun onError(e: Throwable) {
                                e.printStackTrace()
                            }

                            override fun onSuccess(patientList: List<Patient>) {
                                insertPatientList(patientList)
                            }
                        }))

    }

    fun getReportsListFromServer(token: String) {

        ActiveLedgerSDK.KEYNAME = ActiveLedgerHelper.getInstance()!!.keyname
        ActiveLedgerSDK.keyType = ActiveLedgerHelper.getInstance()!!.keyType

        Log.e("getReport token", token)

        disposable.add(
                apiService.getReport(token)
                        .map { viewModel.parseReportsListResponse(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribeWith(object : DisposableSingleObserver<List<Report>>() {

                            override fun onError(e: Throwable) {
                                viewModel.sendMessageToHandler(ReportsFragment.HIDE_PROGRESS, null)
                                viewModel.sendMessageToHandler(ReportsFragment.SHOW_NO_REPORTLABEL, null)
                                viewModel.sendMessageToHandler(ReportsFragment.SHOW_TOAST, ReportsFragment.TXT_REPORT_FETCHING_FAILED)
                            }

                            override fun onSuccess(reportList: List<Report>) {
                                viewModel.sendMessageToHandler(ReportsFragment.HIDE_PROGRESS, null)
                                insertReportList(reportList)
                            }
                        }))
    }

    fun searchReportList(title: String): List<Report> {
        return databaseDAO.searchReports("$title%")
    }

    fun searchDoctorsList(name: String): List<Doctor> {
        return databaseDAO.searchDoctor("$name%")
    }

    fun searchPatientsList(name: String): List<Patient> {
        return databaseDAO.searchPatient("$name%")
    }

    @SuppressLint("CheckResult")
    fun insertDoctor(doctor: Doctor) {

        Observable.fromCallable {
            databaseDAO.insertDoctor(doctor)
            true
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ ->
                    //Use result for something
                }

    }

    @SuppressLint("CheckResult")
    fun insertDoctorList(doctorList: List<Doctor>) {

        Observable.fromCallable {
            databaseDAO.deleteAllDoctor()
            databaseDAO.insertDoctorList(doctorList)

            true
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ -> this.doctorList = databaseDAO.doctorList }

    }

    @SuppressLint("CheckResult")
    fun insertPatientList(patientList: List<Patient>) {

        Observable.fromCallable {
            databaseDAO.deleteAllPatient()
            databaseDAO.insertPatientList(patientList)

            true
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ -> this.patientList = databaseDAO.patientList }

    }

    fun insertPatient(patient: Patient) {

        Observable.fromCallable {
            databaseDAO.insertPatient(patient)
            true
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ ->
                    //Use result for something
                }

    }

    fun insertReport(report: Report) {

        Observable.fromCallable {
            databaseDAO.insertReport(report)
            true
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ ->
                    //Use result for something
                }
    }

    @SuppressLint("CheckResult")
    fun insertReportList(reportList: List<Report>) {

        Observable.fromCallable {
            databaseDAO.deleteAllReport()
            databaseDAO.insertReportList(reportList)

            true
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ -> this.reportList = databaseDAO.reportList }
    }

    fun updateReport(report: Report) {

        Observable.fromCallable {
            databaseDAO.updateReport(report)
            true
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ ->
                    //Use result for something
                }
    }

    fun deleteReport(report: Report) {
        Observable.fromCallable {
            databaseDAO.deleteReport(report)
            true
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ ->
                    //Use result for something
                }
    }

    fun deleteAllDoctor() {

        Observable.fromCallable {
            databaseDAO.deleteAllDoctor()
            true
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ ->
                    //Use result for something
                }

    }

    fun deleteAllPatient() {

        Observable.fromCallable {
            databaseDAO.deleteAllPatient()
            true
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ ->
                    //Use result for something
                }

    }

    fun deleteAllReport() {

        Observable.fromCallable {
            databaseDAO.deleteAllReport()
            true
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ ->
                    //Use result for something
                }

    }


    fun registerUser(user: String): Single<Response<String>> {
        return apiService.registerUser(user)
    }

    fun loginUser(user: String): Single<Response<String>> {
        return apiService.loginUser(user)
    }

    // this method can be used to send transaction as an HTTP request to the ledger
    fun createProfile(token: String, transaction: String): Single<Response<String>> {
        return apiService.createProfile(token, transaction)
    }

    // this method can be used to send transaction as an HTTP request to the ledger
    fun sendTransaction(token: String, transaction: String): Single<Response<String>> {
        return apiService.sendTransaction(token, transaction)
    }

}
