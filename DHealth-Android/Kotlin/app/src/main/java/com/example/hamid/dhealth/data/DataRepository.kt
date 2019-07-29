package com.example.hamid.dhealth.data


import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.os.Message
import android.util.Log

import com.example.activeledgersdk.ActiveLedgerSDK
import com.example.hamid.dhealth.ActiveLedgerHelper
import com.example.hamid.dhealth.data.localdb.DAO.DatabaseDAO
import com.example.hamid.dhealth.data.localdb.Entity.Doctor
import com.example.hamid.dhealth.data.localdb.Entity.Patient
import com.example.hamid.dhealth.data.localdb.Entity.Report
import com.example.hamid.dhealth.data.remote.APIService
import com.example.hamid.dhealth.ui.Fragments.ReportsFragment
import com.example.hamid.dhealth.utils.Utils

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

import javax.inject.Singleton

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

@Singleton
class DataRepository(private val apiService: APIService, private val databaseDAO: DatabaseDAO) {
    var doctorList: LiveData<List<Doctor>>? = null
        private set
    var patientList: LiveData<List<Patient>>? = null
        private set
    var reportList: LiveData<List<Report>>? = null
        private set

    init {
        doctorList = databaseDAO.doctorList
        patientList = databaseDAO.patientList
        reportList = databaseDAO.reportList
    }

    fun getDoctorListFromServer(token: String) {

        apiService.getDoctorList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(object : Observer<Response<String>> {

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        Log.e("doctorlist error", e.message + "")
                    }

                    override fun onComplete() {}

                    override fun onNext(response: Response<String>) {

                        Utils.Log("doctorlist code--->", response.code().toString() + "")

                        if (response.code() == 200) {

                            var responseObj: JSONObject?
                            try {
                                responseObj = JSONObject(response.body())

//                                Utils.Log("doctorlist resp--->", response.body()!! + "")


                                val doctorsArray = responseObj.optJSONArray("streams")

                                val doctorList = ArrayList<Doctor>()

                                for (i in 0 until doctorsArray.length()) {
                                    val jsonobject = doctorsArray.getJSONObject(i)

                                    val first_name = jsonobject.optString("first_name")
                                    val last_name = jsonobject.optString("last_name")
                                    val email = jsonobject.optString("email")
                                    val date_of_birth = jsonobject.optString("date_of_birth")
                                    val address = jsonobject.optString("address")
                                    val phone_number = jsonobject.optString("phone_number")
                                    val gender = jsonobject.optString("gender")
                                    val dp = jsonobject.optString("dp")
                                    val identity = jsonobject.optString("_id")

                                    Log.e("doctorlist email--->", email)
                                    Log.e("doctorlist identity--->", identity)

                                    val doctor = Doctor(first_name, last_name, email, date_of_birth, address, phone_number, gender, dp, identity)
                                    doctorList.add(doctor)
                                }

                                insertDoctorList(doctorList)

                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }


                        }

                    }
                })

    }

    fun getPatientListFromServer(token: String) {

        apiService.getPatientList(token)
                //        HttpClientModule.getInstance().getAssignedPatientListFromServer(token)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(object : Observer<Response<String>> {

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                    override fun onComplete() {}

                    override fun onNext(response: Response<String>) {

                        Utils.Log("patientlist code--->", response.code().toString() + "")

                        if (response.code() == 200) {
                            var responseObj: JSONObject?
                            try {
                                responseObj = JSONObject(response.body())

                                val patientArray = responseObj.optJSONArray("streams")

                                val patientList = ArrayList<Patient>()

                                for (i in 0 until patientArray.length()) {
                                    val jsonobject = patientArray.getJSONObject(i)

                                    val first_name = jsonobject.optString("first_name")
                                    val last_name = jsonobject.optString("last_name")
                                    val email = jsonobject.optString("email")
                                    val date_of_birth = jsonobject.optString("date_of_birth")
                                    val address = jsonobject.optString("address")
                                    val phone_number = jsonobject.optString("phone_number")
                                    val gender = jsonobject.optString("gender")
                                    val dp = jsonobject.optString("dp")
                                    val identity = jsonobject.optString("_id")

                                    Log.e("patientlist email--->", email)
                                    Log.e("patientlist identity-->", identity)

                                    val patient = Patient(first_name, last_name, email, date_of_birth, address, phone_number, gender, dp, identity)
                                    patientList.add(patient)
                                }

                                insertPatientList(patientList)

                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }

                        }

                    }
                })

    }

    fun getAssignedPatientListFromServer(token: String) {

        apiService.getAssignedPatientList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(object : Observer<Response<String>> {

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                    override fun onComplete() {}

                    override fun onNext(response: Response<String>) {

                        Utils.Log("assigned patientlist code--->", response.code().toString() + "")

                        if (response.code() == 200) {
                            var responseObj: JSONObject?
                            try {
                                responseObj = JSONObject(response.body())

                                val patientArray = responseObj.optJSONArray("streams")

                                val patientList = ArrayList<Patient>()

                                for (i in 0 until patientArray.length()) {
                                    val jsonobject = patientArray.getJSONObject(i)

                                    val first_name = jsonobject.optString("first_name")
                                    val last_name = jsonobject.optString("last_name")
                                    val email = jsonobject.optString("email")
                                    val date_of_birth = jsonobject.optString("date_of_birth")
                                    val address = jsonobject.optString("address")
                                    val phone_number = jsonobject.optString("phone_number")
                                    val gender = jsonobject.optString("gender")
                                    val dp = jsonobject.optString("dp")
                                    val identity = jsonobject.optString("_id")

                                    Log.e("assigned patient mail", email)
                                    Log.e("assigned patient identi", identity)

                                    val patient = Patient(first_name, last_name, email, date_of_birth, address, phone_number, gender, dp, identity)
                                    patientList.add(patient)

                                    val reports = jsonobject.optJSONArray("reports")
                                    if (null != reports && reports.length() > 0) {
                                        val reportList = ArrayList<Report>()
                                        for (j in 0 until reports.length()) {
                                            val reportObj = JSONObject(reports.get(j).toString())

                                            val patientName = reportObj.optString("patientName")
                                            val fileName = reportObj.optString("fileName")
                                            val description = reportObj.optString("description")
                                            val title = reportObj.optString("title")
                                            val content = reportObj.optString("content")
                                            val doctors_list = reportObj.optString("doctors")

                                            val report = Report(title, description, patientName, "Jhonny Depp", "", "", content, "", "", doctors_list, fileName)
                                            reportList.add(report)
                                        }
                                        insertReportList(reportList)
                                    }


                                }

                                insertPatientList(patientList)

                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }

                        }

                    }
                })

    }

    fun getReportsListFromServer(token: String) {

        ActiveLedgerSDK.KEYNAME = ActiveLedgerHelper.getInstance()!!.keyname
        ActiveLedgerSDK.keyType = ActiveLedgerHelper.getInstance()!!.keyType

        Log.e("getReport token", token)

        apiService.getReport(token)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(object : io.reactivex.Observer<Response<String>> {

                    override fun onSubscribe(d: Disposable) {
                        //                        disposable = d;
                    }

                    override fun onError(e: Throwable) {}

                    override fun onComplete() {}

                    override fun onNext(response: Response<String>) {

//                        if (null != ReportsFragment.handler) {
//                            val message = Message()
//                            message.what = ReportsFragment.HIDE_PROGRESS
//                            ReportsFragment.handler!!.sendMessage(message)
//                        }

                        Log.e("getReport code--->", response.code().toString() + "")
                        if (response.code() == 200) {

//                            Utils.Log("getReport res--->", response.body()!! + "")

                            try {
                                val responseJSON = JSONObject(response.body())

                                val doctors = responseJSON.optJSONArray("streams").optJSONObject(0).optJSONArray("reports")

                                if (doctors != null) {
                                    val reportList = ArrayList<Report>()

                                    var doctor: JSONObject
                                    for (i in 0 until doctors.length()) {
                                        doctor = doctors.optJSONObject(i)

                                        val patientName = doctor.optString("patientName")
                                        val fileName = doctor.optString("fileName")
                                        val description = doctor.optString("description")
                                        val title = doctor.optString("title")
                                        val content = doctor.optString("content")
                                        val doctors_list = doctor.optString("doctors")

                                        Log.e("------->", doctors_list)

                                        val report = Report(title, description, patientName, "Jhonny Depp", "", "", content, "", "", doctors_list, fileName)
                                        reportList.add(report)
                                    }

//                                    if (null != ReportsFragment.handler) {
//                                        val message = Message()
//                                        message.what = ReportsFragment.HIDE_NO_REPORTLABEL
//                                        ReportsFragment.handler!!.sendMessage(message)
//                                    }
                                    insertReportList(reportList)

                                } else {
//                                    if (null != ReportsFragment.handler) {
//                                        val message = Message()
//                                        message.what = ReportsFragment.SHOW_NO_REPORTLABEL
//                                        ReportsFragment.handler!!.sendMessage(message)
//                                        val message2 = Message()
//                                        message2.what = ReportsFragment.SHOW_TOAST
//                                        message2.arg1 = ReportsFragment.TXT_NO_REPORTFOUND
//                                        ReportsFragment.handler!!.sendMessage(message2)
//                                    }
                                }

                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }


                        } else {
//                            if (null != ReportsFragment.handler) {
//                                val message = Message()
//                                message.what = ReportsFragment.SHOW_NO_REPORTLABEL
//                                ReportsFragment.handler!!.sendMessage(message)
//                                val message2 = Message()
//                                message2.what = ReportsFragment.SHOW_TOAST
//                                message2.arg1 = ReportsFragment.TXT_REPORT_FETCHING_FAILED
//                                ReportsFragment.handler!!.sendMessage(message2)
//                            }
                        }
                    }
                })
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


    fun registerUser(user: String): Observable<Response<String>> {
        return apiService.registerUser(user)
    }

    fun loginUser(user: String): Observable<Response<String>> {
        return apiService.loginUser(user)
    }

    // this method can be used to send transaction as an HTTP request to the ledger
    fun createProfile(token: String, transaction: String): Observable<Response<String>> {
        return apiService.createProfile(token, transaction)
    }

    // this method can be used to send transaction as an HTTP request to the ledger
    fun sendTransaction(token: String, transaction: String): Observable<Response<String>> {
        return apiService.sendTransaction(token, transaction)
    }

}
