package com.example.hamid.dhealth.ui.Activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import com.example.activeledgersdk.ActiveLedgerSDK
import com.example.activeledgersdk.utility.Utility
import com.example.hamid.dhealth.ActiveLedgerHelper
import com.example.hamid.dhealth.R
import com.example.hamid.dhealth.data.Preference.PreferenceKeys
import com.example.hamid.dhealth.data.Preference.PreferenceManager

import com.example.hamid.dhealth.data.localdb.Entity.Report
import com.example.hamid.dhealth.factory.ViewModelFactory
import com.example.hamid.dhealth.ui.viewmodel.AppViewModel
import com.example.hamid.dhealth.utils.FileUtils
import com.example.hamid.dhealth.utils.Utils

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

import javax.inject.Inject

import dagger.android.AndroidInjection
import io.apptik.widget.multiselectspinner.BaseMultiSelectSpinner
import io.apptik.widget.multiselectspinner.MultiSelectSpinner
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class ReportDetailActivity : AppCompatActivity() {
    lateinit var et_title: EditText
    lateinit var et_name: EditText
    lateinit var et_description: EditText
    lateinit var et_document: EditText
    lateinit var et_assigned_to: MultiSelectSpinner
    lateinit var tv_lbl_assigned_to: TextView
    lateinit var btn_update: Button
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var preferenceManager: PreferenceManager

    private val disposable = CompositeDisposable()
    private val doctors_array = ArrayList<String>()
    private val patients_array = ArrayList<String>()
    private var mViewModel: AppViewModel? = null
    private var progressBar: ProgressBar? = null

    private var appViewModel: AppViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        appViewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel::class.java)
        setContentView(R.layout.activity_report_detail)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        title = report!!.title

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        initLayouts()

        populateLayout(report)

        mViewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        val options = ArrayList<String>()


        if (preferenceManager!!.readFromPref(this, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)!!.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {


            val patientList = mViewModel!!.patient_list!!.value
            if (patientList != null) {
                for (i in patientList.indices) {
                    options.add(patientList[i].email)
                }

            }

            mViewModel!!.getPatientList()!!.observe(this, android.arch.lifecycle.Observer { patients ->
                options.clear()

                for (i in patients!!.indices) {
                    options.add(patients[i].first_name + " " + patients[i].last_name)
                }

                val adapter = ArrayAdapter(this@ReportDetailActivity, android.R.layout.simple_list_item_multiple_choice, options)

                et_assigned_to
                        .setListAdapter(adapter)
                        .setListener<BaseMultiSelectSpinner> { selected ->
                            patients_array.clear()
                            for (i in selected.indices) {
                                if (selected[i]) {
                                    patients_array.add("" + patients[i].identity!!)
                                }
                            }
                        }
//                        .setAllCheckedText("All")
//                        .setAllUncheckedText("none selected")
//                        .setSelectAll(false)
//                        .setMinSelectedItems(1)

                for (i in patients.indices) {
                    for (j in patients_array.indices)
                        if (patients[i].identity!!.equals(patients_array[j], ignoreCase = true)) {
                            Log.e("patients selected", i.toString() + "= " + patients[i].first_name)
                            et_assigned_to.selectItem<BaseMultiSelectSpinner>(i, true)
                        }
                }
            })


        } else {

            val doctorList = mViewModel!!.doctor_list!!.value
            if (doctorList != null) {
                for (i in doctorList.indices) {
                    options.add(doctorList[i].email)
                }

            }

            mViewModel!!.getDoctorList()!!.observe(this, android.arch.lifecycle.Observer { doctors ->
                options.clear()

                for (i in doctors!!.indices) {
                    options.add(doctors[i].first_name + " " + doctors[i].last_name)
                }

                val adapter = ArrayAdapter(this@ReportDetailActivity, android.R.layout.simple_list_item_multiple_choice, options)

                et_assigned_to
                        .setListAdapter(adapter)
                        .setListener<BaseMultiSelectSpinner> { selected ->
                            doctors_array.clear()
                            for (i in selected.indices) {
                                if (selected[i]) {
                                    doctors_array.add("" + doctors[i].identity!!)
                                }
                            }
                        }
//                        .setAllCheckedText("All")
//                        .setAllUncheckedText("none selected")
//                        .setSelectAll(false)
//                        .setMinSelectedItems(1)

                for (i in doctors.indices) {
                    for (j in doctors_array.indices)
                        if (doctors[i].identity!!.equals(doctors_array[j], ignoreCase = true)) {
                            Log.e("doctors selected", i.toString() + "= " + doctors[i].first_name)
                            et_assigned_to.selectItem<BaseMultiSelectSpinner>(i, true)
                        }
                }
            })


        }


        if (preferenceManager!!.readFromPref(this, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)!!.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {
            tv_lbl_assigned_to.visibility = View.GONE
            et_assigned_to.visibility = View.GONE
            btn_update.visibility = View.GONE
        }


    }

    private fun populateLayout(report: Report?) {

        if (report != null) {
            et_name.setText(report.ownership)
            et_title.setText(report.title)
            et_assigned_to = findViewById<View>(R.id.et_assigned_to) as MultiSelectSpinner
            et_description.setText(report.description)
            et_document.setText(report.fileName)

            if (preferenceManager!!.readFromPref(this, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)!!.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {
                if (report.doctors!!.isEmpty()) {
                    et_assigned_to.visibility = View.GONE
                    tv_lbl_assigned_to.visibility = View.GONE
                    btn_update.visibility = View.GONE
                } else {
                    et_assigned_to.visibility = View.VISIBLE
                    tv_lbl_assigned_to.visibility = View.VISIBLE
                    btn_update.visibility = View.VISIBLE
                    extractIDPatients(report.doctors)
                }
            } else {
                extractIDDoctors(report.doctors)

            }
        }

    }


    private fun initLayouts() {
        et_name = findViewById<View>(R.id.et_name) as EditText
        et_title = findViewById<View>(R.id.et_title) as EditText
        et_description = findViewById<View>(R.id.et_description) as EditText
        et_document = findViewById<View>(R.id.et_document) as EditText
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        tv_lbl_assigned_to = findViewById<View>(R.id.tv_lbl_assigned_to) as TextView
        btn_update = findViewById<View>(R.id.btn_update) as Button

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }


        return super.onOptionsItemSelected(item)
    }

    fun showPDF(view: View) {
        openPDF(report!!.fileName, FileUtils.saveFile(report!!.fileName!!, report!!.content!!))
    }

    fun updateReport(view: View) {
        progressBar!!.visibility = View.VISIBLE


        ActiveLedgerSDK.KEYNAME = ActiveLedgerHelper.getInstance()!!.keyname
        ActiveLedgerSDK.keyType = ActiveLedgerHelper.getInstance()!!.keyType

        val name = et_name.text.toString()
        val title = et_title.text.toString()
        val status = ""
        val uploaddate = ""
        val assignedto = ""
        val signeddate = ""
        val description = et_description.text.toString()
        val base64document = report!!.content
        val documentName = report!!.fileName
        val email = preferenceManager!!.readFromPref(this, PreferenceKeys.SP_EMAIL, "")
        val updateReportTransaction: JSONObject

        if (preferenceManager!!.readFromPref(this, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)!!.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {
            updateReportTransaction = ActiveLedgerHelper.getInstance()!!.createUpdateReportTransaction(null!!, ActiveLedgerSDK.getInstance().keyType, name, title,
                    status, uploaddate, assignedto, signeddate, description, base64document!!, documentName!!, patients_array, email!!)
            report!!.doctors = patients_array.toString()

        } else {
            updateReportTransaction = ActiveLedgerHelper.getInstance()!!.createUpdateReportTransaction(null!!, ActiveLedgerSDK.getInstance().keyType, name, title,
                    status, uploaddate, assignedto, signeddate, description, base64document!!, documentName!!, doctors_array, email!!)
            report!!.doctors = doctors_array.toString()

        }


        val transactionString = Utility.getInstance().convertJSONObjectToString(updateReportTransaction)

        Utils.Log("UpdateReport Transaction", transactionString)
        Log.e("UpdateReport token", preferenceManager!!.readFromPref(this, PreferenceKeys.SP_APP_TOKEN, "null"))

        disposable.add(
                appViewModel!!.sendTransaction(preferenceManager!!.readFromPref(this, PreferenceKeys.SP_APP_TOKEN, "null")!!, transactionString)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<Response<String>>() {

                            override fun onError(e: Throwable) {
                                e.printStackTrace()
                            }

                            override fun onSuccess(response: Response<String>) {
                                progressBar!!.visibility = View.GONE
                                Log.e("UpdateReport code--->", response.code().toString() + "")
                                if (response.code() == 200) {
                                    Utils.Log("UpdateReport res--->", response.body()!! + "")

                                    Toast.makeText(this@ReportDetailActivity, "Report Update Successfully!", Toast.LENGTH_SHORT).show()

                                    if (report != null)
                                        appViewModel!!.updateReport(report!!)
                                    finish()

                                } else {
                                    Toast.makeText(this@ReportDetailActivity, "Report Update  Failed!", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }))
    }


    public override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }


    fun extractIDDoctors(doctors: String?) {
        try {
            val jsonArray = JSONArray(doctors)
            for (i in 0 until jsonArray.length()) {
                doctors_array.add("" + jsonArray.get(i))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    fun extractIDPatients(patients: String?) {
        try {
            val jsonArray = JSONArray(patients)
            for (i in 0 until jsonArray.length()) {
                patients_array.add("" + jsonArray.get(i))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    private fun openPDF(filename: String?, uri: Uri) {
        val intent = Intent(this, PDFViewActivity::class.java)
        val bundle = Bundle()
        bundle.putString("URI", uri.toString())
        bundle.putString("filename", filename)
        bundle.putBoolean("showAttachButton", false)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    companion object {

        val REPORT_DATA = "report_data"
        var report: Report? = null
    }

}
