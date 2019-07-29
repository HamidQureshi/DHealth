package com.example.hamid.dhealth.ui.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.hamid.dhealth.R
import com.example.hamid.dhealth.data.Preference.PreferenceKeys
import com.example.hamid.dhealth.data.Preference.PreferenceManager
import com.example.hamid.dhealth.data.localdb.Entity.Doctor
import com.example.hamid.dhealth.data.localdb.Entity.Patient
import com.example.hamid.dhealth.utils.Utils
import dagger.android.AndroidInjection
import javax.inject.Inject

class DoctorPatientDescriptionActivity : AppCompatActivity() {

    lateinit var iv_dp: ImageView
    lateinit var iv_dp_bg: ImageView
    lateinit var et_name: TextView
    lateinit var et_last_name: TextView
    lateinit var et_email: TextView
    lateinit var et_dob: TextView
    lateinit var et_phone: TextView
    lateinit var et_address: TextView

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_patient_description)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        initLayouts()

        if (preferenceManager == null) {
            preferenceManager = PreferenceManager()
        }
        if (preferenceManager!!.readFromPref(this, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)!!.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {
            val patient = intent.getSerializableExtra(DATA) as Patient
            if (patient != null) {
                et_name.text = patient.first_name
                et_last_name.text = patient.last_name
                et_email.text = patient.email
                et_phone.text = patient.phone_number
                et_address.text = patient.address
                et_dob.text = patient.date_of_birth
                if (patient.dp!!.length > 4)
                    iv_dp.setImageBitmap(Utils.decodeBase64(patient.dp!!))
                //                createPaletteAsync(Utils.decodeBase64(patient.getDp()));
            }
        } else {
            val doctor = intent.getSerializableExtra(DATA) as Doctor
            if (doctor != null) {
                et_name.text = doctor.first_name
                et_last_name.text = doctor.last_name
                et_email.text = doctor.email
                et_phone.text = doctor.phone_number
                et_address.text = doctor.address
                et_dob.text = doctor.date_of_birth
                if (doctor.dp!!.length > 4)
                    iv_dp.setImageBitmap(Utils.decodeBase64(doctor.dp!!))
                //                createPaletteAsync(Utils.decodeBase64(doctor.getDp()));
            }
        }

        title = et_name.text.toString() + " " + et_last_name.text

    }

    private fun initLayouts() {
        iv_dp = findViewById(R.id.iv_dp)
        iv_dp_bg = findViewById(R.id.iv_dp_bg)
        et_name = findViewById(R.id.et_name)
        et_last_name = findViewById(R.id.et_last_name)
        et_email = findViewById(R.id.et_email)
        et_phone = findViewById(R.id.et_phone)
        et_address = findViewById(R.id.et_address)
        et_dob = findViewById(R.id.et_dob)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {

        val DATA = "data"
    }

}
