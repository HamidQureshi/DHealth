package com.example.hamid.dhealth.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.Message
import android.util.Log
import com.example.hamid.dhealth.data.DataRepository
import com.example.hamid.dhealth.data.Preference.PreferenceManager
import com.example.hamid.dhealth.data.localdb.DAO.DatabaseDAO
import com.example.hamid.dhealth.data.localdb.Entity.Doctor
import com.example.hamid.dhealth.data.localdb.Entity.Patient
import com.example.hamid.dhealth.data.localdb.Entity.Report
import com.example.hamid.dhealth.data.remote.APIService
import com.example.hamid.dhealth.ui.Fragments.ReportsFragment
import com.example.hamid.dhealth.utils.Utils
import io.reactivex.Single
import io.reactivex.annotations.NonNull
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

class AppViewModel @Inject
constructor(@NonNull application: Application, apiService: APIService, databaseDAO: DatabaseDAO, preferenceManager: PreferenceManager) : AndroidViewModel(application) {

    private val repository: DataRepository = DataRepository(apiService, databaseDAO, this)
    var doctor_list: LiveData<List<Doctor>> = repository.doctorList
    val patient_list: LiveData<List<Patient>> = repository.patientList
    val reportList: LiveData<List<Report>> =  repository.reportList
    var isFetchingDoctorPatientData = false
    var isFetchingReportData = false


    init {
//        reportList = repository.reportList
//        doctor_list = repository.doctorList
//        patient_list = repository.patientList
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

    fun createProfile(token: String, transaction: String): Single<Response<String>> {
        return repository.createProfile(token, transaction)
    }

    fun loginUser(user: String): Single<Response<String>> {
        return repository.loginUser(user)
    }

    fun registerUser(user: String): Single<Response<String>> {
        return repository.registerUser(user)
    }

    // this method can be used to send transaction as an HTTP request to the ledger
    fun sendTransaction(token: String, transaction: String): Single<Response<String>> {
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

    fun parseDoctorListResponse(response: Response<String>): List<Doctor>{

        Utils.Log("doctorlist code--->", response.code().toString() + "")

        var doctorList = ArrayList<Doctor>()

        if (response.code() == 200) {

            lateinit var responseObj: JSONObject

            try {
                responseObj = JSONObject(response.body())

            } catch (e: JSONException) {
                e.printStackTrace()
            }

                val doctorsArray = responseObj.optJSONArray("streams")

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

                    val doctor = Doctor(first_name, last_name, email, date_of_birth, address, phone_number, gender, dp, identity)
                    doctorList.add(doctor)
                }
        }

        return doctorList

    }

    fun parsePatientListResponse(response: Response<String>): List<Patient>{

        Utils.Log("patientlist code--->", response.code().toString() + "")

        var patientList = ArrayList<Patient>()

        if (response.code() == 200) {
            var responseObj: JSONObject?
            try {
                responseObj = JSONObject(response.body())

                val patientArray = responseObj.optJSONArray("streams")

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

                    val patient = Patient(first_name, last_name, email, date_of_birth, address, phone_number, gender, dp, identity)
                    patientList.add(patient)
                }

            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }

        return patientList
    }

    fun parseAssignedPatientListResponse(response: Response<String>): List<Patient>{

        Utils.Log("assigned patientlist code--->", response.code().toString() + "")

        val patientList = ArrayList<Patient>()

        if (response.code() == 200) {
            var responseObj: JSONObject?
            try {
                responseObj = JSONObject(response.body())

                val patientArray = responseObj.optJSONArray("streams")

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

                    extractReports(reports)

                }

            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }
        return patientList

    }

    fun extractReports(reports: JSONArray) {
        if (reports.length() > 0) {
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

    fun parseReportsListResponse(response: Response<String>): List<Report>{

        val reportList = ArrayList<Report>()

        Log.e("getReport code--->", response.code().toString() + "")
        if (response.code() == 200) {

            try {
                val responseJSON = JSONObject(response.body())

                val doctors = responseJSON.optJSONArray("streams").optJSONObject(0).optJSONArray("reports")

                if (doctors != null) {

                    var doctor: JSONObject
                    for (i in 0 until doctors.length()) {
                        doctor = doctors.optJSONObject(i)

                        val patientName = doctor.optString("patientName")
                        val fileName = doctor.optString("fileName")
                        val description = doctor.optString("description")
                        val title = doctor.optString("title")
                        val content = doctor.optString("content")
                        val doctors_list = doctor.optString("doctors")

                        val report = Report(title, description, patientName, "Jhonny Depp", "", "", content, "", "", doctors_list, fileName)
                        reportList.add(report)
                    }

                    sendMessageToHandler(ReportsFragment.HIDE_NO_REPORTLABEL,null)

                } else {
                    sendMessageToHandler(ReportsFragment.SHOW_NO_REPORTLABEL,null)
                    sendMessageToHandler(ReportsFragment.SHOW_TOAST,ReportsFragment.TXT_NO_REPORTFOUND)
                }

            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }else {
            sendMessageToHandler(ReportsFragment.SHOW_NO_REPORTLABEL,null)
            sendMessageToHandler(ReportsFragment.SHOW_TOAST,ReportsFragment.TXT_REPORT_FETCHING_FAILED)
        }

            return reportList
    }

    fun sendMessageToHandler(messageCode: Int, arg1: Int?){
        if (null != ReportsFragment.handler) {
            val message = Message()
            message.what = messageCode
            if(arg1!= null)
            message.arg1 = arg1
            ReportsFragment.handler!!.sendMessage(message)
        }
    }

}
