package com.example.hamid.dhealth.di.module;

import com.example.hamid.dhealth.ui.Fragments.DoctorPatientFragment;
import com.example.hamid.dhealth.ui.Fragments.ProfileFragment;
import com.example.hamid.dhealth.ui.Fragments.ReportsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract DoctorPatientFragment contributeDoctorPatientFragment();

    @ContributesAndroidInjector
    abstract ReportsFragment contributeReportsFragment();

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();
}