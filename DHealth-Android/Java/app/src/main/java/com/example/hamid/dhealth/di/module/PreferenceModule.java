package com.example.hamid.dhealth.di.module;

import com.example.hamid.dhealth.data.Preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferenceModule {

    @Provides
    @Singleton
    PreferenceManager providePreferenceManager() {
        return new PreferenceManager();
    }
}
