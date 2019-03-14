package com.example.hamid.dhealth.MedicalRepository.DB.Entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "doctor_table")
public class Doctor implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String picture;
    private String dob;
    private String specialist;
    private String hospital;
    @NonNull
    private String email;

    public Doctor(String name, String picture, String dob, String specialist, String hospital, String email) {
        this.name = name;
        this.picture = picture;
        this.dob = dob;
        this.specialist = specialist;
        this.hospital = hospital;
        this.email = email;
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
        this.picture = picture;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
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


}
