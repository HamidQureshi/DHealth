package com.example.hamid.dhealth.ui.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid.dhealth.ActiveLedgerHelper;
import com.example.hamid.dhealth.data.Preference.PreferenceKeys;
import com.example.hamid.dhealth.data.Preference.PreferenceManager;
import com.example.hamid.dhealth.ui.Fragments.DoctorPatientFragment;
import com.example.hamid.dhealth.ui.Fragments.ProfileFragment;
import com.example.hamid.dhealth.ui.Fragments.ReportsFragment;
import com.example.hamid.dhealth.ui.viewmodel.AppViewModel;
import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.utils.Utils;
import com.example.hamid.dhealth.factory.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class DashboardScreen extends AppCompatActivity implements HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    Fragment fragment = null;
    ImageView iv_dp;
    TextView tv_first_name, tv_last_name;

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    PreferenceManager preferenceManager;
    private DrawerLayout dl;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView nv;
    private String title = "Profile";
    private Toolbar toolbar;

    private AppViewModel appViewModel;

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);
        initialiseViewModel();

        ActiveLedgerHelper.getInstance().setupALSDK(getApplicationContext());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.Open, R.string.Close);

        dl.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //getPatientList for doctor profile only
        if ((preferenceManager.readFromPref(DashboardScreen.this, PreferenceKeys.SP_PROFILE_TYPE, "Doctor")).equalsIgnoreCase("Doctor")) {
            appViewModel.getAssignedPatientListFromServer(preferenceManager.readFromPref(this, PreferenceKeys.SP_APP_TOKEN, "null"));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (savedInstanceState == null) {
            fragment = ProfileFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
            getSupportActionBar().setTitle(title);
        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }

        nv = (NavigationView) findViewById(R.id.nav_view);
        if ((preferenceManager.readFromPref(DashboardScreen.this, PreferenceKeys.SP_PROFILE_TYPE, "Doctor")).equalsIgnoreCase("Doctor")) {
            nv.getMenu().getItem(1).setTitle("Patient List");
        } else {
            nv.getMenu().getItem(1).setTitle("Doctor List");
        }
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                dl.closeDrawers();
                switch (id) {
                    case R.id.profile:
                        title = "Profile";
                        fragment = ProfileFragment.newInstance();
                        break;

                    case R.id.dplist:
                        if ((preferenceManager.readFromPref(DashboardScreen.this, PreferenceKeys.SP_PROFILE_TYPE, "Doctor")).equalsIgnoreCase("Doctor")) {
                            title = "Patient List";
                            item.setTitle("Patient List");
                        } else {
                            title = "Doctor List";
                            item.setTitle("Doctor List");
                        }
                        fragment = DoctorPatientFragment.newInstance();
                        break;

                    case R.id.reports:
                        title = "Reports";
                        fragment = ReportsFragment.newInstance();
                        break;


                    default:

                        return true;
                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                    getSupportActionBar().setTitle(title);
                    item.setChecked(true);
                    dl.closeDrawers();
                } else {
                    Log.e("MainActivity", "Error in creating fragment");
                }


                return true;
            }

        });


        View header = nv.getHeaderView(0);
        iv_dp = (ImageView) header.findViewById(R.id.imageView);
        tv_first_name = (TextView) header.findViewById(R.id.first_name);
        tv_last_name = (TextView) header.findViewById(R.id.last_name);
        tv_first_name.setText(preferenceManager.readFromPref(this, PreferenceKeys.SP_NAME, "John"));
        tv_last_name.setText(preferenceManager.readFromPref(this, PreferenceKeys.SP_LAST_NAME, "Doe"));

        refreshDP();
    }


    private void initialiseViewModel() {
        appViewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
    }

    public void refreshDP() {
        iv_dp.setImageBitmap(Utils.decodeBase64(preferenceManager.readFromPref(this, PreferenceKeys.SP_PROFILEPIC, "")));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }


}