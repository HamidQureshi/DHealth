package com.example.hamid.dhealth.ui.Activities

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
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
import java.io.IOException
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

class UploadFileActivity : AppCompatActivity() {
    private var uri: Uri? = null
    lateinit var et_title: EditText
    lateinit var et_uploaddate: EditText
    lateinit var et_signeddate: EditText
    lateinit var et_description: EditText
    lateinit var et_content: EditText
    lateinit var et_assigned_to: MultiSelectSpinner
    lateinit var tv_staus: TextView
    lateinit var tv_document: TextView
    lateinit var et_name: TextView
    internal var report: Report? = null
    private lateinit var base64File: String

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var preferenceManager: PreferenceManager
    private val disposable = CompositeDisposable()
    private var progressBar: ProgressBar? = null
    private var mViewModel: AppViewModel? = null
    private val doctors_array = ArrayList<String>()
    private val obj_doctors_array = JSONArray()
    private val patients_array = ArrayList<String>()
    private val obj_patients_array = JSONArray()

    private var appViewModel: AppViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_file)
        appViewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel::class.java)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        title = "File Upload"
        mViewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        initLayout()
        setUpSpinner()

    }

    private fun setUpSpinner() {


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


                val adapter = ArrayAdapter(this@UploadFileActivity, android.R.layout.simple_list_item_multiple_choice, options)

                et_assigned_to
                        .setListAdapter(adapter)
                        .setListener<BaseMultiSelectSpinner> { selected ->
                            patients_array.clear()
                            for (i in selected.indices) {
                                if (selected[i]) {
                                    patients_array.add("" + patients[i].identity!!)
                                    obj_patients_array.put("" + patients[i].identity!!)
                                }
                            }
                        }
//                        .setAllCheckedText("All")
//                        .setAllUncheckedText("none selected")
//                        .setSelectAll(false)
//                        .setMinSelectedItems(1)
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


                val adapter = ArrayAdapter(this@UploadFileActivity, android.R.layout.simple_list_item_multiple_choice, options)

                et_assigned_to
                        .setListAdapter(adapter)
                        .setListener<BaseMultiSelectSpinner> { selected ->
                            doctors_array.clear()
                            for (i in selected.indices) {
                                if (selected[i]) {
                                    doctors_array.add("" + doctors[i].identity!!)
                                    obj_doctors_array.put("" + doctors[i].identity!!)
                                }
                            }

                        }
//                        .setAllCheckedText("All")
//                        .setAllUncheckedText("none selected")
//                        .setSelectAll(false)
//                        .setMinSelectedItems(1)
            })
        }

    }

    private fun initLayout() {

        et_name = findViewById(R.id.et_name)
        val firstName = preferenceManager!!.readFromPref(this, PreferenceKeys.SP_NAME, "")
        val lastName = preferenceManager!!.readFromPref(this, PreferenceKeys.SP_LAST_NAME, "")
        et_name.text = "$firstName $lastName"
        et_title = findViewById(R.id.et_title)
        et_uploaddate = findViewById(R.id.et_uploaddate)
        et_assigned_to = findViewById(R.id.et_assigned_to)
        et_signeddate = findViewById(R.id.et_signeddate)
        et_description = findViewById(R.id.et_description)
        et_content = findViewById(R.id.et_content)
        tv_staus = findViewById(R.id.tv_staus)
        tv_document = findViewById(R.id.tv_document)
        progressBar = findViewById(R.id.progressBar)

    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    fun AddReport(view: View) {


        if (uri == null) {
            Toast.makeText(this, "Select a File first", Toast.LENGTH_SHORT).show()
            return
        }
        progressBar!!.visibility = View.VISIBLE

        val title = et_title.text.toString()
        val name = et_name.text.toString()
        val uploaddate = et_uploaddate.text.toString()
        val signeddate = et_signeddate.text.toString()
        val description = et_description.text.toString()
        val status = tv_staus.text.toString()

        //convert the contents to base 64
        base64File = FileUtils.getBase64FromURI(this, uri!!)

        val fileName = FileUtils.getFileName(this, uri!!)

        if (preferenceManager!!.readFromPref(this, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)!!.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {
            report = Report(title, description, name, "", uploaddate, signeddate, base64File, status, FileUtils.uriToString(uri!!), obj_patients_array.toString(), fileName)
            //send the report to ledger
            uploadReport(name, title, status, uploaddate, "", signeddate, description, base64File, fileName, patients_array, preferenceManager.readFromPref(this, PreferenceKeys.SP_EMAIL, ""))

        } else {
            report = Report(title, description, name, "", uploaddate, signeddate, base64File, status, FileUtils.uriToString(uri!!), obj_doctors_array.toString(), fileName)
            //send the report to ledger
            uploadReport(name, title, status, uploaddate, "", signeddate, description, base64File, fileName, doctors_array, preferenceManager.readFromPref(this, PreferenceKeys.SP_EMAIL, ""))
        }


    }

    fun uploadFILE(view: View) {

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        //        intent.setType("image/*");
        intent.type = "application/pdf"
        //        intent.setType("*/*");

        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int,
                                         resultData: Intent?) {

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            if (resultData != null) {
                uri = resultData.data
                Log.i(TAG, "Uri: " + uri!!.toString())

                if (null != uri) {

                    try {
                        tv_document.text = FileUtils.getFileName(this, uri!!)

//                        Log.i(TAG, "FileContent: " + FileUtils.readTextFromUri(this, uri!!))
                        openPDF(FileUtils.getFileName(this, uri!!), uri!!)

                        //TODO give read permissions already given at some point


                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }


    private fun openPDF(fileName: String, uri: Uri) {
        val intent = Intent(this, PDFViewActivity::class.java)
        val bundle = Bundle()
        bundle.putString("URI", uri.toString())
        bundle.putString("filename", fileName)
        bundle.putBoolean("showAttachButton", true)
        intent.putExtras(bundle)
        //TODO should be start activity for result
        startActivity(intent)
    }


    fun uploadReport(name: String, title: String, status: String, uploaddate: String, assignedto: String,
                     signeddate: String, description: String, base64document: String, documentName: String, selected_array: ArrayList<String>, email: String?) {

        ActiveLedgerSDK.KEYNAME = ActiveLedgerHelper.getInstance()!!.keyname
        ActiveLedgerSDK.keyType = ActiveLedgerHelper.getInstance()!!.keyType

        val uploadReportTransaction = ActiveLedgerHelper.getInstance()!!.createUploadReportTransaction(null, ActiveLedgerSDK.getInstance().keyType, name, title,
                status, uploaddate, assignedto, signeddate, description, base64document, documentName, selected_array, email!!)

        val transactionString = Utility.getInstance().convertJSONObjectToString(uploadReportTransaction)

        Log.e("UploadReport token", preferenceManager!!.readFromPref(this, PreferenceKeys.SP_APP_TOKEN, "null"))

        disposable.add(
                appViewModel!!.sendTransaction(preferenceManager!!.readFromPref(this, PreferenceKeys.SP_APP_TOKEN, "null")!!, transactionString)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<Response<String>>() {

                            override fun onError(e: Throwable) {}

                            override fun onSuccess(response: Response<String>) {
                                progressBar!!.visibility = View.GONE
                                Log.e("UploadReport code--->", response.code().toString() + "")
                                if (response.code() == 200) {
                                    Utils.Log("UploadReport res--->", response.body()!! + "")

                                    Toast.makeText(this@UploadFileActivity, "Report Uploaded Successfully!", Toast.LENGTH_SHORT).show()

                                    if (report != null)
                                        appViewModel!!.insertReport(report!!)
                                    finish()

                                } else {
                                    Toast.makeText(this@UploadFileActivity, "Report Uploading  Failed!", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }))
    }

    public override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    companion object {

        private val TAG = "UploadFileActivity"
        private val READ_REQUEST_CODE = 42
        private val WRITE_REQUEST_CODE = 43
    }

}
