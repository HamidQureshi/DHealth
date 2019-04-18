package com.example.hamid.dhealth.MedicalRepository.DB.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Doctor;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Patient;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Report;

import java.util.List;

@Dao
public interface DatabaseDAO {

    @Insert
    void insertDoctor(Doctor doctor);

    @Insert
    void insertDoctorList(List<Doctor> doctorList);

    @Insert
    void insertPatientList(List<Patient> patientList);

    @Insert
    void insertReportList(List<Report> reportList);

    @Delete
    void deleteDoctor(Doctor doctor);

    @Insert
    void insertPatient(Patient patient);

    @Delete
    void deletePatient(Patient patient);

    @Insert
    void insertReport(Report report);

    @Update
    void updateReport(Report report);

    @Delete
    void deleteReport(Report report);

    @Query("DELETE FROM  doctor_table")
    void deleteAllDoctor();

    @Query("DELETE FROM  patient_table")
    void deleteAllPatient();

    @Query("DELETE FROM  report_table")
    void deleteAllReport();

    @Query("SELECT * from doctor_table")
    LiveData<List<Doctor>> getDoctorList();

    @Query("SELECT * from patient_table")
    LiveData<List<Patient>> getPatientList();

    @Query("SELECT * from report_table")
    LiveData<List<Report>> getReportList();

    @Query("SELECT * FROM report_table where title LIKE :query")
    List<Report> searchReports(String query);

    @Query("SELECT * FROM doctor_table where first_name LIKE :query")
    List<Doctor> searchDoctor(String query);

    @Query("SELECT * FROM patient_table where first_name LIKE :query")
    List<Patient> searchPatient(String query);

}
