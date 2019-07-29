package com.example.hamid.dhealth.ui.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.hamid.dhealth.R
import com.example.hamid.dhealth.data.Preference.PreferenceKeys
import com.example.hamid.dhealth.data.Preference.PreferenceManager
import dagger.android.AndroidInjection
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var intent: Intent
        val login = preferenceManager!!.readFromPref(this, PreferenceKeys.SP_LOGGEDIN, false)

        if (login == false) {
            intent = Intent(this, LoginScreen::class.java)
        } else {
            if (preferenceManager.readFromPref(this, PreferenceKeys.SP_PROFILEFINISHED, false)) {
                intent = Intent(this, DashboardScreen::class.java)
            } else {
                intent = Intent(this, ProfileScreen::class.java)

            }
        }

        startActivity(intent)

        finish()

    }
}
