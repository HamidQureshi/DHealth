package com.example.hamid.dhealth.Activities;

import android.app.SearchManager;
import android.content.Intent;
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
import com.example.hamid.dhealth.Fragments.DoctorPatientFragment;
import com.example.hamid.dhealth.Fragments.ProfileFragment;
import com.example.hamid.dhealth.Fragments.ReportsFragment;
import com.example.hamid.dhealth.Fragments.SettingFragment;
import com.example.hamid.dhealth.Preference.PreferenceKeys;
import com.example.hamid.dhealth.Preference.PreferenceManager;
import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.Utils.Utils;

public class DashboardScreen extends AppCompatActivity {

    Fragment fragment = null;
    ImageView iv_dp;
    TextView tv_name;
    private DrawerLayout dl;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView nv;
    private String title = "Profile";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.Open, R.string.Close);

        dl.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //testing
        ActiveLedgerHelper.getInstance().setupALSDK(this);
        ActiveLedgerHelper.getInstance().getTransactionData("a40f9e9520b64cb1d950ef637a59a850dfd64fc5e620c43facd5511133525a7f");

        if (savedInstanceState  == null) {
            Log.e("===>", "on create");

            fragment = ProfileFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
            getSupportActionBar().setTitle(title);
        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }

        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                dl.closeDrawers();
                switch (id) {
                    case R.id.profile:
//                        Toast.makeText(DashboardScreen.this, "My Profile", Toast.LENGTH_SHORT).show();
                        title = "Profile";
                        fragment = ProfileFragment.newInstance();
                        break;

                    case R.id.dplist:
//                        Toast.makeText(DashboardScreen.this, "Doctor/TablePatient List", Toast.LENGTH_SHORT).show();
                        title = "Doctor/TablePatient List";
                        fragment = DoctorPatientFragment.newInstance();
                        break;

                    case R.id.reports:
//                        Toast.makeText(DashboardScreen.this, "Reports", Toast.LENGTH_SHORT).show();
                        title = "Reports";
                        fragment = ReportsFragment.newInstance();
                        break;

                    case R.id.settings:
//                        Toast.makeText(DashboardScreen.this, "Settings", Toast.LENGTH_SHORT).show();
                        title = "Settings";
                        fragment = SettingFragment.newInstance();
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
        tv_name = (TextView) header.findViewById(R.id.name);

        iv_dp.setImageBitmap(Utils.decodeBase64(PreferenceManager.getINSTANCE().readFromPref(this, PreferenceKeys.SP_PROFILEPIC, "")));
        tv_name.setText(PreferenceManager.getINSTANCE().readFromPref(this, PreferenceKeys.SP_NAME, "John Doe"));


        handleIntent(getIntent());


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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.options_menu, menu);
//
//        // Associate searchable configuration with the SearchView
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//
//        return true;
//    }


    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            Log.e("=====>", query);
        }
    }


}