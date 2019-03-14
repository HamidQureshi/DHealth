package com.example.hamid.dhealth.MedicalRepository.DB.DAO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Doctor;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Patient;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Report;

@Database(entities = {Doctor.class, Patient.class, Report.class}, version = 1)
public abstract class MedicalRoomDatabase extends RoomDatabase {
    private static volatile MedicalRoomDatabase INSTANCE;

    public static MedicalRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MedicalRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MedicalRoomDatabase.class, "medical_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract DatabaseDAO medicalDao();

}
