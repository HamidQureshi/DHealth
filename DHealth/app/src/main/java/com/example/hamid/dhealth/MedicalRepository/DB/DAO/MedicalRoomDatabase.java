package com.example.hamid.dhealth.MedicalRepository.DB.DAO;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Doctor;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Patient;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Report;

@Database(entities = {Doctor.class, Patient.class, Report.class}, version = 2)
public abstract class MedicalRoomDatabase extends RoomDatabase {
    private static volatile MedicalRoomDatabase INSTANCE;

    public static MedicalRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MedicalRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MedicalRoomDatabase.class, "medical_database")
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE doctor_table "
                    +"ADD COLUMN identity TEXT");

            database.execSQL("ALTER TABLE patient_table "
                    +"ADD COLUMN identity TEXT");

        }
    };



    public abstract DatabaseDAO medicalDao();

}
