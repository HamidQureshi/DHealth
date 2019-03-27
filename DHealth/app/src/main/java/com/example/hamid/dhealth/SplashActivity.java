package com.example.hamid.dhealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hamid.dhealth.Activities.DashboardScreen;
import com.example.hamid.dhealth.Activities.LoginScreen;
import com.example.hamid.dhealth.Activities.ProfileScreen;
import com.example.hamid.dhealth.Preference.PreferenceKeys;
import com.example.hamid.dhealth.Preference.PreferenceManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Intent intent;

        Boolean login = PreferenceManager.getINSTANCE().readFromPref(this, PreferenceKeys.SP_LOGGEDIN, false);

        if (login == false) {
            intent = new Intent(this, LoginScreen.class);
        } else {
            if (!PreferenceManager.getINSTANCE().readFromPref(this, PreferenceKeys.SP_PROFILEFINISHED, false)) {
                intent = new Intent(this, ProfileScreen.class);
            } else {
                intent = new Intent(this, DashboardScreen.class);
            }
        }

        startActivity(intent);

        finish();

    }
}
