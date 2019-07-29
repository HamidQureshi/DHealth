package com.example.hamid.dhealth.ui.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.hamid.dhealth.ActiveLedgerHelper
import com.example.hamid.dhealth.R
import com.example.hamid.dhealth.data.Preference.PreferenceKeys
import com.example.hamid.dhealth.data.Preference.PreferenceManager
import com.example.hamid.dhealth.factory.ViewModelFactory
import com.example.hamid.dhealth.ui.Fragments.DoctorPatientFragment
import com.example.hamid.dhealth.ui.Fragments.ProfileFragment
import com.example.hamid.dhealth.ui.Fragments.ReportsFragment
import com.example.hamid.dhealth.ui.viewmodel.AppViewModel
import com.example.hamid.dhealth.utils.Utils
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class DashboardScreen : AppCompatActivity(), HasSupportFragmentInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    internal var fragment: Fragment? = null
    internal lateinit var iv_dp: ImageView
    internal lateinit var tv_first_name: TextView
    internal lateinit var tv_last_name: TextView

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var preferenceManager: PreferenceManager
    private var dl: DrawerLayout? = null
    private var drawerToggle: ActionBarDrawerToggle? = null
    private var nv: NavigationView? = null
    private var title = "Profile"
    private var toolbar: Toolbar? = null

    private var appViewModel: AppViewModel? = null

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment>? {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_screen)
        initialiseViewModel()

        ActiveLedgerHelper.getInstance()!!.setupALSDK(applicationContext, preferenceManager)

        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        dl = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawerToggle = ActionBarDrawerToggle(this, dl, toolbar, R.string.Open, R.string.Close)

        dl!!.addDrawerListener(drawerToggle!!)
        drawerToggle!!.syncState()

        //getPatientList for doctor profile only
        if (preferenceManager!!.readFromPref(this@DashboardScreen, PreferenceKeys.SP_PROFILE_TYPE, "Doctor").equals("Doctor", ignoreCase = true)) {
            appViewModel!!.getAssignedPatientListFromServer(preferenceManager!!.readFromPref(this, PreferenceKeys.SP_APP_TOKEN, "null")!!)
        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        if (savedInstanceState == null) {
            fragment = ProfileFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit()
            supportActionBar!!.setTitle(title)
        } else {
            Log.e("MainActivity", "Error in creating fragment")
        }

        nv = findViewById<View>(R.id.nav_view) as NavigationView
        if (preferenceManager!!.readFromPref(this@DashboardScreen, PreferenceKeys.SP_PROFILE_TYPE, "Doctor").equals("Doctor", ignoreCase = true)) {
            nv!!.menu.getItem(1).title = "Patient List"
        } else {
            nv!!.menu.getItem(1).title = "Doctor List"
        }
        nv!!.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            val id = item.itemId
            dl!!.closeDrawers()
            when (id) {
                R.id.profile -> {
                    title = "Profile"
                    fragment = ProfileFragment.newInstance()
                }

                R.id.dplist -> {
                    if (preferenceManager!!.readFromPref(this@DashboardScreen, PreferenceKeys.SP_PROFILE_TYPE, "Doctor").equals("Doctor", ignoreCase = true)) {
                        title = "Patient List"
                        item.title = "Patient List"
                    } else {
                        title = "Doctor List"
                        item.title = "Doctor List"
                    }
                    fragment = DoctorPatientFragment.newInstance()
                }

                R.id.reports -> {
                    title = "Reports"
                    fragment = ReportsFragment.newInstance()
                }


                else ->

                    return@OnNavigationItemSelectedListener true
            }

            if (fragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit()
                supportActionBar!!.title = title
                item.isChecked = true
                dl!!.closeDrawers()
            } else {
                Log.e("MainActivity", "Error in creating fragment")
            }


            true
        })


        val header = nv!!.getHeaderView(0)
        iv_dp = header.findViewById<View>(R.id.imageView) as ImageView
        tv_first_name = header.findViewById<View>(R.id.first_name) as TextView
        tv_last_name = header.findViewById<View>(R.id.last_name) as TextView
        tv_first_name.text = preferenceManager!!.readFromPref(this, PreferenceKeys.SP_NAME, "John")
        tv_last_name.text = preferenceManager!!.readFromPref(this, PreferenceKeys.SP_LAST_NAME, "Doe")

        refreshDP()

        getDataFromServer()


        if (preferenceManager!!.readFromPref(this, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)!!.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {

            appViewModel!!.getPatientList()!!.observe(this, Observer { appViewModel!!.isFetchingDoctorPatientData = false })
        } else {

            appViewModel!!.getDoctorList()!!.observe(this, Observer { appViewModel!!.isFetchingDoctorPatientData = false })
        }

        appViewModel!!.reportList!!.observe(this, Observer { appViewModel!!.isFetchingReportData = false })


    }

    private fun getDataFromServer() {

        appViewModel!!.isFetchingDoctorPatientData = true
        appViewModel!!.isFetchingReportData = true
        if (preferenceManager!!.readFromPref(this, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)!!.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {
            appViewModel!!.getAssignedPatientListFromServer(preferenceManager!!.readFromPref(this, PreferenceKeys.SP_APP_TOKEN, "null")!!)

        } else {
            appViewModel!!.getDoctorListFromServer(preferenceManager!!.readFromPref(this, PreferenceKeys.SP_APP_TOKEN, "null")!!)
            appViewModel!!.getReportsListFromServer(preferenceManager!!.readFromPref(this, PreferenceKeys.SP_APP_TOKEN, "null")!!)

        }


    }


    private fun initialiseViewModel() {
        appViewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel::class.java)
    }

    fun refreshDP() {
        if (!preferenceManager.readFromPref(this, PreferenceKeys.SP_PROFILEPIC, "").isNullOrBlank())
            iv_dp.setImageBitmap(Utils.decodeBase64(preferenceManager.readFromPref(this, PreferenceKeys.SP_PROFILEPIC, "")!!))

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return if (drawerToggle!!.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle!!.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Pass any configuration change to the drawer toggles
        drawerToggle!!.onConfigurationChanged(newConfig)
    }


}