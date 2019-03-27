package com.example.hamid.dhealth.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hamid.dhealth.Adapter.DoctorPatientListAdapter;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Doctor;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Patient;
import com.example.hamid.dhealth.Preference.PreferenceKeys;
import com.example.hamid.dhealth.Preference.PreferenceManager;
import com.example.hamid.dhealth.R;

import java.util.ArrayList;
import java.util.List;


public class DoctorPatientFragment extends Fragment {

    private DoctorPatientViewModel mViewModel;
    private RecyclerView rv_dplist;
    private DoctorPatientListAdapter doctorPatientListAdapter;
    private List<Doctor> doctorList;
    private List<Patient> patientList;

    public static DoctorPatientFragment newInstance() {
        return new DoctorPatientFragment();
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
        doctorList = new ArrayList<>();
        patientList = new ArrayList<>();
        doctorPatientListAdapter = new DoctorPatientListAdapter(getContext(), doctorList, patientList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        rv_dplist.setLayoutManager(mLayoutManager);
        rv_dplist.setItemAnimator(new DefaultItemAnimator());
        rv_dplist.setAdapter(doctorPatientListAdapter);


        if (PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR).equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {

            mViewModel.getPatientList().observe(this, new Observer<List<Patient>>() {
                @Override
                public void onChanged(@Nullable final List<Patient> patients) {
                    // Update the cached copy of the words in the adapter.
                    doctorPatientListAdapter.setP(patients);
                }
            });
        } else {

            mViewModel.getDoctorList().observe(this, new Observer<List<Doctor>>() {
                @Override
                public void onChanged(@Nullable final List<Doctor> doctors) {
                    // Update the cached copy of the words in the adapter.
                    doctorPatientListAdapter.setDoctorList(doctors);
                }
            });
        }


        populateList();
    }

    private void populateList() {
        if (PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR).equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {
            mViewModel.getPatientListFromServer(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_APP_TOKEN, "null"));

        } else {
            mViewModel.getDoctorListFromServer(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_APP_TOKEN, "null"));
        }
    }


}



