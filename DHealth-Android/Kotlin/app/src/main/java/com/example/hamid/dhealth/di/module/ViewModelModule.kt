package com.example.hamid.dhealth.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import com.example.hamid.dhealth.factory.ViewModelFactory
import com.example.hamid.dhealth.ui.viewmodel.AppViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    /*
     * This method basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  ViewModel.class as key,
     * and a Provider that will build a ViewModel
     * object.
     *
     * */

    @Binds
    @IntoMap
    @ViewModelKey(AppViewModel::class)
    protected abstract fun appViewModel(appViewModel: AppViewModel): ViewModel

}
