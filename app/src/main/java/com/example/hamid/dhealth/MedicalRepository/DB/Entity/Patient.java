package com.example.hamid.dhealth.MedicalRepository.DB.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "patient_table")
public class Patient {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String picture;
    private String dob;
    private String disease;
    private String hospital;
    private String doctor;

    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    public Patient(String name, String picture, String dob, String disease, String hospital, String email, String doctor) {
        this.name = name;
        this.picture = picture;
        this.dob = dob;
        this.disease = disease;
        this.hospital = hospital;
        this.email = email;
        this.doctor = doctor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        picture = picture;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

}
