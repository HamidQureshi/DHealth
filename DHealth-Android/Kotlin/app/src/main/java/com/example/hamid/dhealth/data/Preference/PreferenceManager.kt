package com.example.hamid.dhealth.data.Preference

import android.content.Context
import android.content.SharedPreferences

import javax.inject.Singleton

@Singleton
class PreferenceManager {

    private val preference_name = "medical_preference"

    fun getSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(preference_name, Context.MODE_PRIVATE)
    }

    fun writeToPref(context: Context, key: String, value: String) {
        val editor = getSharedPreference(context).edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun writeToPref(context: Context, key: String, value: Int) {
        val editor = getSharedPreference(context).edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun writeToPref(context: Context, key: String, value: Boolean?) {
        val editor = getSharedPreference(context).edit()
        editor.putBoolean(key, value!!)
        editor.commit()
    }


    fun readFromPref(context: Context, key: String, defaultValue: String): String? {
        val sharedPreference = getSharedPreference(context)
        return sharedPreference.getString(key, defaultValue)
    }

    fun readFromPref(context: Context, key: String, defaultValue: Boolean?): Boolean? {
        val sharedPreferences = getSharedPreference(context)
        return sharedPreferences.getBoolean(key, defaultValue!!)
    }

    fun readFromPref(context: Context, key: String, defaultValue: Int): Int {
        val sharedPreference = getSharedPreference(context)
        return sharedPreference.getInt(key, defaultValue)
    }


    fun clearPreferences(context: Context) {
        val sharedPreference = getSharedPreference(context)
        sharedPreference.edit().clear().commit()
    }


}
