package com.example.hamid.dhealth.data.localdb.DAO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.hamid.dhealth.data.localdb.Entity.Doctor;
import com.example.hamid.dhealth.data.localdb.Entity.Patient;
import com.example.hamid.dhealth.data.localdb.Entity.Report;

@Database(entities = {Doctor.class, Patient.class, Report.class}, version = 4)
public abstract class MedicalRoomDatabase extends RoomDatabase {

    public abstract DatabaseDAO medicalDao();

}
