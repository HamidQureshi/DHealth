package com.example.hamid.dhealth.ui.Fragments

import android.Manifest
import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast

import com.example.activeledgersdk.ActiveLedgerSDK
import com.example.activeledgersdk.utility.Utility
import com.example.hamid.dhealth.ActiveLedgerHelper
import com.example.hamid.dhealth.R
import com.example.hamid.dhealth.data.Preference.PreferenceKeys
import com.example.hamid.dhealth.data.Preference.PreferenceManager
import com.example.hamid.dhealth.factory.ViewModelFactory
import com.example.hamid.dhealth.ui.Activities.DashboardScreen
import com.example.hamid.dhealth.ui.Activities.SplashActivity
import com.example.hamid.dhealth.ui.viewmodel.AppViewModel
import com.example.hamid.dhealth.utils.ImageUtils
import com.example.hamid.dhealth.utils.Utils

import java.io.IOException
import java.util.ArrayList
import java.util.Calendar

import javax.inject.Inject

import belka.us.androidtoggleswitch.widgets.ToggleSwitch
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

import android.app.Activity.RESULT_OK
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

class ProfileFragment : Fragment(), View.OnClickListener {
    lateinit var et_name: EditText
    lateinit var et_last_name: EditText
    lateinit var et_email: EditText
    lateinit var et_dob: EditText
    lateinit var et_phone: EditText
    lateinit var et_address: EditText
    lateinit var iv_camera: ImageView
    lateinit var iv_dp: ImageView
    lateinit var btn_submit: Button
    lateinit var btn_logout: Button

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var preferenceManager: PreferenceManager
    private var datePickerDialog: DatePickerDialog? = null
    private var profile_type = PreferenceKeys.LBL_DOCTOR
    private var gender = PreferenceKeys.LBL_MALE
    private var encryption = PreferenceKeys.LBL_RSA
    private var disposable = CompositeDisposable()
    private var progressBar: ProgressBar? = null
    private var mViewModel: AppViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel::class.java)

        initLayouts()
    }


    private fun initLayouts() {

        et_dob = view!!.findViewById<View>(R.id.et_dob) as EditText
        et_dob.setOnClickListener(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            et_dob.showSoftInputOnFocus = false
        }
        prepareDatePickerDialog()

        val dp_toggleSwitch = view!!.findViewById<View>(R.id.dp_toggle) as ToggleSwitch
        val dp_labels = ArrayList<String>()
        dp_labels.add(PreferenceKeys.LBL_DOCTOR)
        dp_labels.add(PreferenceKeys.LBL_PATIENT)
        dp_toggleSwitch.setLabels(dp_labels)
        dp_toggleSwitch.setOnToggleSwitchChangeListener { position, isChecked ->
            if (position == 0)
                profile_type = PreferenceKeys.LBL_DOCTOR
            else
                profile_type = PreferenceKeys.LBL_PATIENT
        }


        val gender_toggleSwitch = view!!.findViewById<View>(R.id.gender_toggle) as ToggleSwitch
        val gender_labels = ArrayList<String>()
        gender_labels.add(PreferenceKeys.LBL_MALE)
        gender_labels.add(PreferenceKeys.LBL_FEMALE)
        gender_toggleSwitch.setLabels(gender_labels)
        gender_toggleSwitch.setOnToggleSwitchChangeListener { position, isChecked ->
            if (position == 0)
                gender = PreferenceKeys.LBL_MALE
            else
                gender = PreferenceKeys.LBL_FEMALE
        }

        val encryption_toggleSwitch = view!!.findViewById<View>(R.id.encryption_toggle) as ToggleSwitch
        val encryption_labels = ArrayList<String>()
        encryption_labels.add(PreferenceKeys.LBL_RSA)
        encryption_labels.add(PreferenceKeys.LBL_EC)
        encryption_toggleSwitch.setLabels(encryption_labels)
        encryption_toggleSwitch.setOnToggleSwitchChangeListener { position, isChecked ->
            if (position == 0)
                encryption = PreferenceKeys.LBL_RSA
            else
                encryption = PreferenceKeys.LBL_EC
        }

        et_name = view!!.findViewById<View>(R.id.et_name) as EditText
        et_last_name = view!!.findViewById<View>(R.id.et_last_name) as EditText
        et_email = view!!.findViewById<View>(R.id.et_email) as EditText
        et_phone = view!!.findViewById<View>(R.id.et_phone) as EditText
        et_address = view!!.findViewById<View>(R.id.et_address) as EditText

        btn_submit = view!!.findViewById<View>(R.id.btn_submit) as Button
        btn_submit.setOnClickListener(this)

        btn_logout = view!!.findViewById<View>(R.id.btn_logout) as Button
        btn_logout.setOnClickListener(this)

        iv_camera = view!!.findViewById<View>(R.id.iv_camera) as ImageView
        iv_camera.setOnClickListener(this)
        iv_dp = view!!.findViewById<View>(R.id.iv_dp) as ImageView
        progressBar = view!!.findViewById<View>(R.id.progressBar) as ProgressBar

        fetchDataFromPref()
    }

    private fun prepareDatePickerDialog() {
        val calendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(activity!!, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            var monthOfYear = monthOfYear
            monthOfYear++
            et_dob.setText(dayOfMonth.toString() + "/" + monthOfYear + "/" + year)
            datePickerDialog!!.dismiss()
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.et_dob -> {
                Utils.hideKeyboard(activity!!)
                if (et_dob.isFocusable && et_dob.isFocusableInTouchMode) {
                    datePickerDialog!!.show()
                }
            }

            R.id.btn_submit -> {
                Utils.hideKeyboard(activity!!)

                progressBar!!.visibility = View.VISIBLE
                updateUserTransaction(et_name.text.toString(), et_last_name.text.toString(), et_dob.text.toString(), et_phone.text.toString(),
                        et_address.text.toString(), preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_PROFILEPIC, ""), et_email.text.toString())
            }

            R.id.btn_logout -> {
                Utils.hideKeyboard(activity!!)
                showAlertDialog()
            }

            R.id.iv_camera -> {
                Utils.hideKeyboard(activity!!)
                takePhoto(v)
            }
        }
    }

    fun fetchDataFromPref() {
        et_name.setText(preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_NAME, "JOHN "))
        et_last_name.setText(preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_LAST_NAME, "DOE"))
        et_email.setText(preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_EMAIL, "JOHN DOE"))
        et_dob.setText(preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_DOB, "JOHN DOE"))
        et_phone.setText(preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_PHONENO, "JOHN DOE"))
        et_address.setText(preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_ADDRESS, "JOHN DOE"))
        if(!preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_PROFILEPIC, "").isNullOrBlank())
        iv_dp.setImageBitmap(Utils.decodeBase64(preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_PROFILEPIC, "")!!))
    }

    private fun updatePref() {
        preferenceManager.writeToPref(activity!!, PreferenceKeys.SP_NAME, et_name.text.toString())
        preferenceManager.writeToPref(activity!!, PreferenceKeys.SP_LAST_NAME, et_last_name.text.toString())
        preferenceManager.writeToPref(activity!!, PreferenceKeys.SP_EMAIL, et_email.text.toString())
        preferenceManager.writeToPref(activity!!, PreferenceKeys.SP_DOB, et_dob.text.toString())
        preferenceManager.writeToPref(activity!!, PreferenceKeys.SP_PHONENO, et_phone.text.toString())
        preferenceManager.writeToPref(activity!!, PreferenceKeys.SP_ADDRESS, et_address.text.toString())
    }


    override fun onPrepareOptionsMenu(menu: Menu?) {
        val inflater = activity!!.menuInflater
        inflater.inflate(R.menu.edit_options_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.itemId

        if (id == R.id.edit) {

            Toast.makeText(activity, "Profile Editing Enabled", Toast.LENGTH_SHORT).show()

            btn_submit.visibility = View.VISIBLE
            //            btn_logout.setVisibility(View.VISIBLE);

            et_name.isFocusable = true
            et_name.isFocusableInTouchMode = true

            et_last_name.isFocusable = true
            et_last_name.isFocusableInTouchMode = true

            et_address.isFocusable = true
            et_address.isFocusableInTouchMode = true

            et_dob.isFocusable = true
            et_dob.isFocusableInTouchMode = true

            et_phone.isFocusable = true
            et_phone.isFocusableInTouchMode = true

            iv_camera.visibility = View.VISIBLE

            return true
        } else if (id == R.id.logout) {
            Utils.hideKeyboard(activity!!)
            showAlertDialog()
        }

        return super.onOptionsItemSelected(item)
    }


    fun takePhoto(view: View) {

        val options = arrayOf<CharSequence>("Take Photo", "Choose From Gallery", "Cancel")
        val builder = android.support.v7.app.AlertDialog.Builder(activity!!)
        builder.setTitle("Select Option")
        builder.setItems(options) { dialog, item ->
            if (options[item] == "Take Photo") {
                dialog.dismiss()

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (activity!!.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(arrayOf(Manifest.permission.CAMERA),
                                MY_CAMERA_PERMISSION_CODE)
                    } else {
                        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(cameraIntent, 0)
                    }
                }

            } else if (options[item] == "Choose From Gallery") {
                dialog.dismiss()

                val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(pickPhoto, 1)
            } else if (options[item] == "Cancel") {
                dialog.dismiss()
            }
        }
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        when (requestCode) {
            0 -> if (resultCode == RESULT_OK) {
                var photo: Bitmap = imageReturnedIntent!!.extras!!.get("data") as Bitmap
                photo = ImageUtils.scaleDownBitmap(photo, activity!!)
                iv_dp.setImageBitmap(photo)
                preferenceManager.writeToPref(activity!!, PreferenceKeys.SP_PROFILEPIC, Utils.encodeTobase64(photo))
            }
            1 -> if (resultCode == RESULT_OK) {
                val selectedImage = imageReturnedIntent!!.data
                try {
                    var photo = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, selectedImage)
                    photo = ImageUtils.scaleDownBitmap(photo, activity!!)
                    preferenceManager.writeToPref(activity!!, PreferenceKeys.SP_PROFILEPIC, Utils.encodeTobase64(photo))
                    iv_dp.setImageBitmap(photo)
                } catch (e: IOException) {
                    iv_dp.setImageURI(selectedImage)
                    e.printStackTrace()
                }

            }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "camera permission granted", Toast.LENGTH_SHORT).show()
                val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, 0)
            } else {
                Toast.makeText(activity, "camera permission denied", Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun showAlertDialog() {
        val builder = AlertDialog.Builder(activity!!)

        builder.setMessage("Are you sure you want to Logout?")
        builder.setCancelable(true)

        builder.setPositiveButton(
                "Yes"
        ) { dialog, id ->
            //delete preferences
            preferenceManager.clearPreferences(activity!!)
            //delete db
            mViewModel!!.nukeDB()

            //back to splash screen
            val intent = Intent(activity, SplashActivity::class.java)
            startActivity(intent)
            activity!!.finish()
            dialog.cancel()
        }

        builder.setNegativeButton(
                "No"
        ) { dialog, id -> dialog.cancel() }

        val logoutAlert = builder.create()

        logoutAlert.show()
    }


    fun updateUserTransaction(first_name: String, last_name: String,
                              date_of_birth: String, phone_number: String, address: String, dp: String?, email: String) {


        ActiveLedgerSDK.KEYNAME = ActiveLedgerHelper.getInstance()!!.keyname
        ActiveLedgerSDK.getInstance().keyType = ActiveLedgerHelper.Companion.getInstance()!!.keyType


        val updateUserTransaction = ActiveLedgerHelper.Companion.getInstance()!!.createUpdateUserTransaction(null, ActiveLedgerHelper.Companion.getInstance()!!.keyType, first_name, last_name,
                date_of_birth, phone_number, address, dp!!, email)

        val transactionString = Utility.getInstance().convertJSONObjectToString(updateUserTransaction)

        disposable.add(
        mViewModel!!.sendTransaction(preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_APP_TOKEN, "null")!!, transactionString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response<String>>() {

                    override fun onError(e: Throwable) {}

                    override fun onSuccess(response: Response<String>) {
                        progressBar!!.visibility = View.GONE
                        Log.e("UpdateUser response--->", response.code().toString() + "")
                        if (response.code() == 200) {
                            updatePref()
                            Utils.Log("UpdateUser response--->", response.body()!! + "")
                            iv_camera.visibility = View.INVISIBLE
                            btn_submit.visibility = View.INVISIBLE
                            //                            btn_logout.setVisibility(View.INVISIBLE);

                            et_name.isFocusable = false
                            et_name.isClickable = false
                            et_name.isFocusableInTouchMode = false

                            et_last_name.isFocusable = false
                            et_last_name.isFocusableInTouchMode = false

                            et_address.isFocusable = false
                            et_address.isFocusableInTouchMode = false

                            et_dob.isFocusable = false
                            et_dob.isFocusableInTouchMode = false

                            et_phone.isFocusable = false
                            et_phone.isFocusableInTouchMode = false

                            (activity as DashboardScreen).refreshDP()

                            Toast.makeText(activity, "User Updated Successfully!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(activity, "User Updated Failed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }))
    }

    override fun onDestroy() {
        super.onDestroy()
            disposable.clear()
    }

        companion object {

        private val MY_CAMERA_PERMISSION_CODE = 100

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }

        }
}
