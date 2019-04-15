package com.example.hamid.dhealth.Fragments;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Doctor;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Patient;
import com.example.hamid.dhealth.MedicalRepository.DataRepository;
import com.example.hamid.dhealth.Preference.PreferenceKeys;
import com.example.hamid.dhealth.Preference.PreferenceManager;

import java.util.List;

public class DoctorPatientViewModel extends AndroidViewModel {

    private DataRepository repository;
    private LiveData<List<Doctor>> doctor_list;
    private LiveData<List<Patient>> patient_list;

    public DoctorPatientViewModel(Application application) {
        super(application);
        repository = DataRepository.getINSTANCE(application);

        doctor_list = repository.getDoctorList();
        patient_list = repository.getPatientList();
    }

    public LiveData<List<Doctor>> getDoctor_list() {
        if (doctor_list.getValue() == null) {
            getDoctorListFromServer(PreferenceManager.getINSTANCE().readFromPref(getApplication(), PreferenceKeys.SP_APP_TOKEN, "null"));
        }
        return doctor_list;
    }

    public LiveData<List<Patient>> getPatient_list() {
        if (patient_list.getValue() == null) {
            getPatientListFromServer(PreferenceManager.getINSTANCE().readFromPref(getApplication(), PreferenceKeys.SP_APP_TOKEN, "null"));
        }
        return patient_list;
    }

    public void setDoctor_list(LiveData<List<Doctor>> doctor_list) {
        this.doctor_list = doctor_list;
    }

    public LiveData<List<Doctor>> getDoctorList() {
        return doctor_list;
    }

    public LiveData<List<Patient>> getPatientList() {
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


    public void getDoctorListFromServer(String token) {
        repository.getDoctorListFromServer(token);
    }

    public void getPatientListFromServer(String token) {
        repository.getPatientListFromServer(token);
    }

}
