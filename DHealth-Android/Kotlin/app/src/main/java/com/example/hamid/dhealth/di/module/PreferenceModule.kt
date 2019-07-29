package com.example.hamid.dhealth.di.module

import com.example.hamid.dhealth.data.Preference.PreferenceManager

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class PreferenceModule {

    @Provides
    @Singleton
    fun providePreferenceManager(): PreferenceManager {
        return PreferenceManager()
    }
}
