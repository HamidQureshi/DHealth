package com.example.hamid.dhealth.ui.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.data.Preference.PreferenceKeys;
import com.example.hamid.dhealth.data.Preference.PreferenceManager;
import com.example.hamid.dhealth.data.localdb.Entity.Doctor;
import com.example.hamid.dhealth.data.localdb.Entity.Patient;
import com.example.hamid.dhealth.factory.ViewModelFactory;
import com.example.hamid.dhealth.ui.Adapter.DoctorPatientListAdapter;
import com.example.hamid.dhealth.ui.viewmodel.AppViewModel;
import com.example.hamid.dhealth.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.schedulers.Schedulers;


public class DoctorPatientFragment extends Fragment {

    SearchView searchView;

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    PreferenceManager preferenceManager;
    private AppViewModel mViewModel;
    private RecyclerView rv_dplist;
    private DoctorPatientListAdapter doctorPatientListAdapter;
    private List<Doctor> doctorList;
    private List<Patient> patientList;
    private ProgressBar progressBar;

    public static DoctorPatientFragment newInstance() {
        return new DoctorPatientFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidSupportInjection.inject(this);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.doctor_patient_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(AppViewModel.class);

        rv_dplist = (RecyclerView) getView().findViewById(R.id.rv_doctor_patient_list);
        if (preferenceManager.readFromPref(getActivity(), PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR).equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {
            patientList = mViewModel.getPatientList().getValue();
            doctorList = new ArrayList<>();
        } else {
            patientList = new ArrayList<>();
            doctorList = mViewModel.getDoctorList().getValue();
        }
        String profileType = preferenceManager.readFromPref(getActivity(), PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR);
        doctorPatientListAdapter = new DoctorPatientListAdapter(getContext(), doctorList, patientList, profileType);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        rv_dplist.setLayoutManager(mLayoutManager);
        rv_dplist.setItemAnimator(new DefaultItemAnimator());
        rv_dplist.setAdapter(doctorPatientListAdapter);
        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);

        SwipeRefreshLayout pullToRefresh = getView().findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefresh.setRefreshing(true);
                populateList();
            }
        });


        if (preferenceManager.readFromPref(getActivity(), PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR).equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {

            mViewModel.getPatientList().observe(this, new Observer<List<Patient>>() {
                @Override
                public void onChanged(@Nullable final List<Patient> patients) {
                    progressBar.setVisibility(View.GONE);
                    // Update the cached copy of the words in the adapter.
                    doctorPatientListAdapter.setPatientList(patients);
                    pullToRefresh.setRefreshing(false);

                }
            });
        } else {

            mViewModel.getDoctorList().observe(this, new Observer<List<Doctor>>() {
                @Override
                public void onChanged(@Nullable final List<Doctor> doctors) {
                    progressBar.setVisibility(View.GONE);
                    // Update the cached copy of the words in the adapter.
                    doctorPatientListAdapter.setDoctorList(doctors);
                    pullToRefresh.setRefreshing(false);

                }
            });
        }

        if (mViewModel.isFetchingDoctorPatientData()){
            pullToRefresh.setRefreshing(true);
        }
        else{
            pullToRefresh.setRefreshing(false);

        }

//        populateList();
    }

    private void populateList() {
        progressBar.setVisibility(View.VISIBLE);
        if (preferenceManager.readFromPref(getActivity(), PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR).equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {
            mViewModel.getAssignedPatientListFromServer(preferenceManager.readFromPref(getActivity(), PreferenceKeys.SP_APP_TOKEN, "null"));
        } else {
            mViewModel.getDoctorListFromServer(preferenceManager.readFromPref(getActivity(), PreferenceKeys.SP_APP_TOKEN, "null"));
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setQueryHint("Search by name");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("query text submit ==>", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                if (preferenceManager.readFromPref(getActivity(), PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR).equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {

                    Observable.fromCallable(() -> {
                        return mViewModel.searchPatientList(newText);
                    })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe((result) -> {
                                doctorPatientListAdapter.setPatientList(result);
                            });

                } else {

                    Observable.fromCallable(() -> {
                        return mViewModel.searchDoctorsList(newText);
                    })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe((result) -> {
                                doctorPatientListAdapter.setDoctorList(result);
                            });

                }


                Log.e("query text change ==>", newText);
                return false;
            }
        });

        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.search), new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
//                Utils.showKeyboard(getActivity());
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                searchView.setQuery("", true);
                Utils.hideKeyboard(getActivity());
                return true;
            }
        });


    }


}



