package com.example.hamid.dhealth.di.module

import com.example.hamid.dhealth.data.Preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Provides
    @Singleton
    fun providePreferenceManager(): PreferenceManager {
        return PreferenceManager()
    }
}
