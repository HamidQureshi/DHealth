package com.example.hamid.dhealth.data.localdb.DAO

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

import com.example.hamid.dhealth.data.localdb.Entity.Doctor
import com.example.hamid.dhealth.data.localdb.Entity.Patient
import com.example.hamid.dhealth.data.localdb.Entity.Report

@Dao
interface DatabaseDAO {

    @get:Query("SELECT * from doctor_table")
    val doctorList: LiveData<List<Doctor>>

    @get:Query("SELECT * from patient_table")
    val patientList: LiveData<List<Patient>>

    @get:Query("SELECT * from report_table")
    val reportList: LiveData<List<Report>>

    @Insert
    fun insertDoctor(doctor: Doctor)

    @Insert
    fun insertDoctorList(doctorList: List<Doctor>)

    @Insert
    fun insertPatientList(patientList: List<Patient>)

    @Insert
    fun insertReportList(reportList: List<Report>)

    @Delete
    fun deleteDoctor(doctor: Doctor)

    @Insert
    fun insertPatient(patient: Patient)

    @Delete
    fun deletePatient(patient: Patient)

    @Insert
    fun insertReport(report: Report)

    @Update
    fun updateReport(report: Report)

    @Delete
    fun deleteReport(report: Report)

    @Query("DELETE FROM  doctor_table")
    fun deleteAllDoctor()

    @Query("DELETE FROM  patient_table")
    fun deleteAllPatient()

    @Query("DELETE FROM  report_table")
    fun deleteAllReport()

    @Query("SELECT * FROM report_table where title LIKE :query")
    fun searchReports(query: String): List<Report>

    @Query("SELECT * FROM doctor_table where first_name LIKE :query")
    fun searchDoctor(query: String): List<Doctor>

    @Query("SELECT * FROM patient_table where first_name LIKE :query")
    fun searchPatient(query: String): List<Patient>

}
