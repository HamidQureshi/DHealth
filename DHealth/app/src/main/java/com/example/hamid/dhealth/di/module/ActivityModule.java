package com.example.hamid.dhealth.di.module;

import com.example.hamid.dhealth.ActiveLedgerHelper;
import com.example.hamid.dhealth.ui.Activities.DashboardScreen;
import com.example.hamid.dhealth.ui.Activities.DoctorPatientDescriptionActivity;
import com.example.hamid.dhealth.ui.Activities.LoginScreen;
import com.example.hamid.dhealth.ui.Activities.ProfileScreen;
import com.example.hamid.dhealth.ui.Activities.ReportDetailActivity;
import com.example.hamid.dhealth.ui.Activities.SignUpScreen;
import com.example.hamid.dhealth.ui.Activities.SplashActivity;
import com.example.hamid.dhealth.ui.Activities.UploadFileActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract DashboardScreen contributeDashboardActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LoginScreen contributeLoginActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract SignUpScreen contributeSignUpActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ProfileScreen contributeProfileActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract ReportDetailActivity contributeReportDetailActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract UploadFileActivity contributeUploadFileActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract SplashActivity contributeSplashActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract DoctorPatientDescriptionActivity contributeDoctorPatientDescriptionActivity();

//    @ContributesAndroidInjector(modules = FragmentModule.class)
//    abstract ActiveLedgerHelper contributeActiveLedgerHelper();
}
