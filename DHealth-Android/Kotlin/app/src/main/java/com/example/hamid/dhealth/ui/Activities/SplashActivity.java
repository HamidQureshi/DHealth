package com.example.hamid.dhealth.ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.data.Preference.PreferenceKeys;
import com.example.hamid.dhealth.data.Preference.PreferenceManager;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SplashActivity extends AppCompatActivity {

    @Inject
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent;
        Boolean login = preferenceManager.readFromPref(this, PreferenceKeys.INSTANCE.getSP_LOGGEDIN(), false);

        if (login == false) {
            intent = new Intent(this, LoginScreen.class);
        } else {
            if (!preferenceManager.readFromPref(this, PreferenceKeys.INSTANCE.getSP_PROFILEFINISHED(), false)) {
                intent = new Intent(this, ProfileScreen.class);
            } else {
                intent = new Intent(this, DashboardScreen.class);
            }
        }

        startActivity(intent);

        finish();

    }
}
