package com.example.hamid.dhealth.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.hamid.dhealth.Adapter.DoctorPatientListAdapter;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Doctor;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Patient;
import com.example.hamid.dhealth.Preference.PreferenceKeys;
import com.example.hamid.dhealth.Preference.PreferenceManager;
import com.example.hamid.dhealth.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class DoctorPatientFragment extends Fragment {

    SearchView searchView;
    private DoctorPatientViewModel mViewModel;
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

        mViewModel = ViewModelProviders.of(this).get(DoctorPatientViewModel.class);

        rv_dplist = (RecyclerView) getView().findViewById(R.id.rv_doctor_patient_list);
        if (PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR).equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {
            patientList = mViewModel.getPatientList().getValue();
            doctorList = new ArrayList<>();
        } else {
            patientList = new ArrayList<>();
            doctorList = mViewModel.getDoctorList().getValue();
        }
        String profileType = PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR);
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


        if (PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR).equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {

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

        populateList();
    }

    private void populateList() {
        progressBar.setVisibility(View.VISIBLE);
        if (PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR).equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {
            mViewModel.getPatientListFromServer(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_APP_TOKEN, "null"));
        } else {
            mViewModel.getDoctorListFromServer(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_APP_TOKEN, "null"));
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("query text submit ==>", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                if (PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR).equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {

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


    }


}



