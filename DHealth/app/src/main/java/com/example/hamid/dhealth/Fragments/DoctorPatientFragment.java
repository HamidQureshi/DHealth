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
import com.example.hamid.dhealth.R;

import java.util.ArrayList;
import java.util.List;


public class DoctorPatientFragment extends Fragment {

    private DoctorPatientViewModel mViewModel;
    private RecyclerView rv_dplist;
    private DoctorPatientListAdapter doctorPatientListAdapter;
    private List<Doctor> doctorList;

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
        doctorPatientListAdapter = new DoctorPatientListAdapter(getContext(), doctorList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        rv_dplist.setLayoutManager(mLayoutManager);
        rv_dplist.setItemAnimator(new DefaultItemAnimator());
        rv_dplist.setAdapter(doctorPatientListAdapter);

        mViewModel.getDoctorList().observe(this, new Observer<List<Doctor>>() {
            @Override
            public void onChanged(@Nullable final List<Doctor> doctors) {
                // Update the cached copy of the words in the adapter.
                doctorPatientListAdapter.setDoctorList(doctors);
            }
        });
        populateList();
    }

    private void populateList() {

        mViewModel.deleteDctorData();

        Doctor doctor = new Doctor("Johnny Depp", "null", "10/20/1994", "eye", "east london", "abc@gmail.com");

        mViewModel.insertDoctor(doctor);
        doctor = new Doctor("Tom Cruise", "null", "10/20/1994", "eye", "east london", "abc@gmail.com");
        mViewModel.insertDoctor(doctor);

    }


}



