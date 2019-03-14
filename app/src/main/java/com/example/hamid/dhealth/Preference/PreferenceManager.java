package com.example.hamid.dhealth.Preference;


import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    private static final String preference_name = "medical_preference";
    private static volatile PreferenceManager INSTANCE;

    public static PreferenceManager getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (PreferenceManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PreferenceManager();
                }
            }
        }
        return INSTANCE;
    }

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

    public Boolean readFromPref(Context context, String key, Boolean defaultValue){
        SharedPreferences sharedPreferences = getSharedPreference(context);
        return sharedPreferences.getBoolean(key,defaultValue);
    }

    public int readFromPref(Context context, String key, int defaultValue) {
        SharedPreferences sharedPreference = getSharedPreference(context);
        return sharedPreference.getInt(key, defaultValue);
    }

}
