package com.example.hamid.dhealth.ui.Activities

import android.Manifest
import android.app.DatePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import belka.us.androidtoggleswitch.widgets.ToggleSwitch
import com.example.activeledgersdk.ActiveLedgerSDK
import com.example.activeledgersdk.utility.KeyType
import com.example.activeledgersdk.utility.Utility
import com.example.hamid.dhealth.ActiveLedgerHelper
import com.example.hamid.dhealth.R
import com.example.hamid.dhealth.data.Preference.PreferenceKeys
import com.example.hamid.dhealth.data.Preference.PreferenceManager
import com.example.hamid.dhealth.factory.ViewModelFactory
import com.example.hamid.dhealth.ui.viewmodel.AppViewModel
import com.example.hamid.dhealth.utils.ImageUtils
import com.example.hamid.dhealth.utils.Utils
import dagger.android.AndroidInjection
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.security.KeyPair
import java.util.*
import javax.inject.Inject

class ProfileScreen : AppCompatActivity(), View.OnClickListener {
    lateinit var et_dob: EditText
    lateinit var et_name: EditText
    lateinit var et_last_name: EditText
    lateinit var et_email: EditText
    lateinit var et_phone: EditText
    lateinit var et_address: EditText
    lateinit var btn_submit: Button
    lateinit var iv_camera: ImageView
    lateinit var iv_dp: ImageView

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var preferenceManager: PreferenceManager
    private var datePickerDialog: DatePickerDialog? = null
    private var profile_type = PreferenceKeys.LBL_DOCTOR
    private var gender = PreferenceKeys.LBL_MALE
    private var encryption = PreferenceKeys.LBL_RSA
    private val disposable = CompositeDisposable()
    private var progressBar: ProgressBar? = null

    private var appViewModel: AppViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)
        appViewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel::class.java)

        initLayouts()

    }


    private fun initLayouts() {

        et_dob = findViewById<View>(R.id.et_dob) as EditText
        et_dob.setOnClickListener(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            et_dob.showSoftInputOnFocus = false
        }
        prepareDatePickerDialog()

        et_name = findViewById<View>(R.id.et_name) as EditText
        et_last_name = findViewById<View>(R.id.et_last_name) as EditText
        et_email = findViewById<View>(R.id.et_email) as EditText
        et_email.setText(preferenceManager!!.readFromPref(this, PreferenceKeys.SP_EMAIL, "null"))
        et_phone = findViewById<View>(R.id.et_phone) as EditText
        et_address = findViewById<View>(R.id.et_address) as EditText
        btn_submit = findViewById<View>(R.id.btn_submit) as Button
        btn_submit.setOnClickListener(this)

        iv_camera = findViewById<View>(R.id.iv_camera) as ImageView
        iv_camera.setOnClickListener(this)
        iv_dp = findViewById<View>(R.id.iv_dp) as ImageView
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar


        val dp_toggleSwitch = findViewById<View>(R.id.dp_toggle) as ToggleSwitch
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

        val gender_toggleSwitch = findViewById<View>(R.id.gender_toggle) as ToggleSwitch
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


        val encryption_toggleSwitch = findViewById<View>(R.id.encryption_toggle) as ToggleSwitch
        val encryption_labels = ArrayList<String>()
        encryption_labels.add(PreferenceKeys.LBL_RSA)
        encryption_labels.add(PreferenceKeys.LBL_EC)
        encryption_toggleSwitch.setLabels(encryption_labels)
        encryption_toggleSwitch.setOnToggleSwitchChangeListener { position, isChecked ->
            if (position == 0) {
                encryption = PreferenceKeys.LBL_RSA
                ActiveLedgerHelper.getInstance()!!.keyType = KeyType.RSA
            } else {
                encryption = PreferenceKeys.LBL_EC
                ActiveLedgerHelper.getInstance()!!.keyType = KeyType.EC
            }
        }

        btn_submit = findViewById<View>(R.id.btn_submit) as Button
        btn_submit.setOnClickListener(this)

        iv_camera = findViewById<View>(R.id.iv_camera) as ImageView
        iv_camera.setOnClickListener(this)
        iv_dp = findViewById<View>(R.id.iv_dp) as ImageView


    }

    private fun prepareDatePickerDialog() {
        val calendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            var monthOfYear = monthOfYear

            monthOfYear++
            et_dob.setText(dayOfMonth.toString() + "/" + monthOfYear + "/" + year)
            datePickerDialog!!.dismiss()
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.et_dob -> {
                Utils.hideKeyboard(this)
                datePickerDialog!!.show()
            }

            R.id.btn_submit -> {
                Utils.hideKeyboard(this)
                ActiveLedgerHelper.getInstance()!!.setupALSDK(applicationContext, preferenceManager)

                progressBar!!.visibility = View.VISIBLE

                generateKeys(et_name.text.toString(), et_last_name.text.toString(), et_email.text.toString(),
                        et_dob.text.toString(), et_phone.text.toString(), et_address.text.toString(), encryption, profile_type,
                        gender,
                        preferenceManager!!.readFromPref(this, PreferenceKeys.SP_PROFILEPIC, "null"))
            }

            R.id.iv_camera -> takePhoto(v)
        }
    }


    fun submitProfile() {

        updatePref()
        val intent = Intent(this, DashboardScreen::class.java)
        startActivity(intent)
        finish()

    }

    private fun updatePref() {
        preferenceManager!!.writeToPref(this, PreferenceKeys.SP_NAME, et_name.text.toString())
        preferenceManager!!.writeToPref(this, PreferenceKeys.SP_LAST_NAME, et_last_name.text.toString())
        preferenceManager!!.writeToPref(this, PreferenceKeys.SP_EMAIL, et_email.text.toString())
        preferenceManager!!.writeToPref(this, PreferenceKeys.SP_DOB, et_dob.text.toString())
        preferenceManager!!.writeToPref(this, PreferenceKeys.SP_PHONENO, et_phone.text.toString())
        preferenceManager!!.writeToPref(this, PreferenceKeys.SP_ADDRESS, et_address.text.toString())
        preferenceManager!!.writeToPref(this, PreferenceKeys.SP_GENDER, gender)
        preferenceManager!!.writeToPref(this, PreferenceKeys.SP_ENCRYPTION, encryption)
        preferenceManager!!.writeToPref(this, PreferenceKeys.SP_PROFILE_TYPE, profile_type)
        preferenceManager!!.writeToPref(this, PreferenceKeys.SP_PROFILEFINISHED, true)
    }


    fun takePhoto(view: View) {

        val options = arrayOf<CharSequence>("Take Photo", "Choose From Gallery", "Cancel")
        val builder = android.support.v7.app.AlertDialog.Builder(this)
        builder.setTitle("Select Option")
        builder.setItems(options) { dialog, item ->
            if (options[item] == "Take Photo") {
                dialog.dismiss()


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        when (requestCode) {
            0 -> if (resultCode == RESULT_OK) {
                var photo: Bitmap = imageReturnedIntent.extras!!.get("data") as Bitmap

                photo = ImageUtils.scaleDownBitmap(photo, this)

                iv_dp.setImageBitmap(photo)

                preferenceManager!!.writeToPref(this, PreferenceKeys.SP_PROFILEPIC, Utils.encodeTobase64(photo))
            }
            1 -> if (resultCode == RESULT_OK) {
                val selectedImage = imageReturnedIntent.data
                try {
                    var photo = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
                    photo = ImageUtils.scaleDownBitmap(photo, this)
                    iv_dp.setImageBitmap(photo)
                    preferenceManager!!.writeToPref(this, PreferenceKeys.SP_PROFILEPIC, Utils.encodeTobase64(photo))

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
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show()
                val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, 0)
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show()
            }

        }
    }


    private fun generateKeys(first_name: String, last_name: String, email: String,
                             date_of_birth: String, phone_number: String, address: String, security: String, profile_type: String, gender: String, dp: String?) {

        ActiveLedgerSDK.getInstance().generateAndSetKeyPair(ActiveLedgerHelper.getInstance()!!.keyType, true, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<KeyPair> {

                    override fun onSubscribe(d: Disposable) {}

                    override fun onError(e: Throwable) {}

                    override fun onComplete() {
                        Log.d("MainActivity", "onComplete")

                        try {
                            ActiveLedgerSDK.getInstance().keyType = ActiveLedgerHelper.getInstance()!!.keyType
                            ActiveLedgerHelper.getInstance()!!.setPublickey(ActiveLedgerSDK.readFileAsString(Utility.getPublicKeyFileName(email)))
                            ActiveLedgerHelper.getInstance()!!.setPrivatekey(ActiveLedgerSDK.readFileAsString(Utility.getPrivateKeyFileName(email)))

                            if (ActiveLedgerHelper.getInstance()!!.key_Pair != null) {
                                onboardKeys(first_name, last_name, email,
                                        date_of_birth, phone_number, address, security, profile_type, gender, dp)

                            } else {
                                Toast.makeText(this@ProfileScreen, "Generate Keys First", Toast.LENGTH_SHORT)
                            }


                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                    }

                    override fun onNext(keyPair: KeyPair) {
                        ActiveLedgerHelper.getInstance()!!.key_Pair = keyPair
                    }
                })

    }

    fun onboardKeys(first_name: String, last_name: String, email: String,
                    date_of_birth: String, phone_number: String, address: String, security: String, profile_type: String, gender: String, dp: String?) {

        ActiveLedgerSDK.KEYNAME = ActiveLedgerHelper.getInstance()!!.keyname

        val onboardTransaction = ActiveLedgerHelper.getInstance()!!.onboardTransaction(ActiveLedgerSDK.getInstance().keyType, first_name, last_name, email,
                date_of_birth, phone_number, address, security, profile_type, gender, dp!!)

        val transactionString = Utility.getInstance().convertJSONObjectToString(onboardTransaction)

        Utils.Log("Onboard Transaction", transactionString)
        Log.e("Onboard token", preferenceManager!!.readFromPref(this, PreferenceKeys.SP_APP_TOKEN, "null"))

        disposable.add(
                appViewModel!!.createProfile(preferenceManager!!.readFromPref(this, PreferenceKeys.SP_APP_TOKEN, "null")!!, transactionString)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<Response<String>>() {

                            override fun onError(e: Throwable) {}

                            override fun onSuccess(response: Response<String>) {
                                progressBar!!.visibility = View.GONE
                                Log.e("onboard response--->", response.code().toString() + "")
                                if (response.code() == 200) {

                                    try {
                                        Utils.Log("onboard response--->", response.body()!! + "")

                                        val responseJSON = JSONObject(response.body())

                                        val stream = responseJSON.optJSONObject("streams")
                                        if (stream != null) {
                                            val identity = stream.optString("_id")
                                            preferenceManager!!.writeToPref(this@ProfileScreen, PreferenceKeys.SP_IDENTITY, identity)
                                        }
                                        submitProfile()

                                    } catch (e: JSONException) {
                                        e.printStackTrace()
                                    }


                                }
                            }
                        }))


    }

    override fun onDestroy() {
        super.onDestroy()
        if (disposable != null && !disposable.isDisposed)
            disposable.dispose()
    }

    companion object {

        private val MY_CAMERA_PERMISSION_CODE = 100
    }
}
