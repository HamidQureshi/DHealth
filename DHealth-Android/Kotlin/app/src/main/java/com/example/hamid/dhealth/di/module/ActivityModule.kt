package com.example.hamid.dhealth.di.module

import com.example.hamid.dhealth.ui.Activities.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun contributeDashboardActivity(): DashboardScreen

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun contributeLoginActivity(): LoginScreen

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun contributeSignUpActivity(): SignUpScreen

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun contributeProfileActivity(): ProfileScreen

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun contributeReportDetailActivity(): ReportDetailActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun contributeUploadFileActivity(): UploadFileActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun contributeDoctorPatientDescriptionActivity(): DoctorPatientDescriptionActivity

}
