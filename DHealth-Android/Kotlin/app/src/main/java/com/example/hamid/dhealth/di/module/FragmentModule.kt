package com.example.hamid.dhealth.di.module

import com.example.hamid.dhealth.ui.Fragments.DoctorPatientFragment
import com.example.hamid.dhealth.ui.Fragments.ProfileFragment
import com.example.hamid.dhealth.ui.Fragments.ReportsFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeDoctorPatientFragment(): DoctorPatientFragment

    @ContributesAndroidInjector
    internal abstract fun contributeReportsFragment(): ReportsFragment

    @ContributesAndroidInjector
    internal abstract fun contributeProfileFragment(): ProfileFragment


}