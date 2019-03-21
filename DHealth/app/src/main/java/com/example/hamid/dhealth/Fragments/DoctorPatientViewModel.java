package com.example.hamid.dhealth.Fragments;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Doctor;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Patient;
import com.example.hamid.dhealth.MedicalRepository.DataRepository;

import java.util.List;

public class DoctorPatientViewModel extends AndroidViewModel {

    private DataRepository repository;
    private LiveData<List<Doctor>> doctor_list;

    private LiveData<List<Patient>> patient_list;

    public DoctorPatientViewModel(Application application) {
        super(application);
        repository = new DataRepository(application);
        doctor_list = repository.getDoctorList();
        patient_list = repository.getPatientList();
    }

    LiveData<List<Doctor>> getDoctorList() {
        return doctor_list;
    }

    LiveData<List<Patient>> getPatientList() {
        return patient_list;
    }

    public void insertDoctor(Doctor doctor) {
        repository.insertDoctor(doctor);
    }

    public void insertPatient(Patient patient) {
        repository.insertPatient(patient);
    }

    public void deleteDctorData() {
        repository.deleteAllDoctor();
    }

    public void deletePatientData() {
        repository.deleteAllPatient();
    }

}