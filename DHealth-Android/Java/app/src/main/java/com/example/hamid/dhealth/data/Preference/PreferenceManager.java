package com.example.hamid.dhealth.data.Preference;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

@Singleton
public class PreferenceManager {

    private static final String preference_name = "medical_preference";

    public SharedPreferences getSharedPreference(Context context) {

        return context.getSharedPreferences(preference_name, Context.MODE_PRIVATE);
    }

    public void writeToPref(Context context, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void writeToPref(Context context, String key, int value) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void writeToPref(Context context, String key, Boolean value) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }


    public String readFromPref(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreference = getSharedPreference(context);
        return sharedPreference.getString(key, defaultValue);
    }

    public Boolean readFromPref(Context context, String key, Boolean defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public int readFromPref(Context context, String key, int defaultValue) {
        SharedPreferences sharedPreference = getSharedPreference(context);
        return sharedPreference.getInt(key, defaultValue);
    }


    public void clearPreferences(Context context) {
        SharedPreferences sharedPreference = getSharedPreference(context);
        sharedPreference.edit().clear().commit();
    }

}
