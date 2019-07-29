package com.example.hamid.dhealth.di.component

import android.app.Application

import com.example.hamid.dhealth.data.remote.HttpClientModule
import com.example.hamid.dhealth.di.AppController
import com.example.hamid.dhealth.di.module.ActivityModule
import com.example.hamid.dhealth.di.module.DBModule
import com.example.hamid.dhealth.di.module.FragmentModule
import com.example.hamid.dhealth.di.module.PreferenceModule
import com.example.hamid.dhealth.di.module.ViewModelModule

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [HttpClientModule::class, DBModule::class, ViewModelModule::class, ActivityModule::class, AndroidSupportInjectionModule::class, FragmentModule::class, PreferenceModule::class])
interface AppComponent {

    /*
     * This is our custom Application class
     * */
    fun inject(appController: AppController)


    /* We will call this builder interface from our custom Application class.
     * This will set our application object to the AppComponent.
     * So inside the AppComponent the application instance is available.
     * So this application instance can be accessed by our modules
     * such as ApiModule when needed
     * */
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}
