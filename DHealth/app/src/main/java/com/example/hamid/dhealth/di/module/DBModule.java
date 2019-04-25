package com.example.hamid.dhealth.di.module;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.example.hamid.dhealth.data.localdb.DAO.DatabaseDAO;
import com.example.hamid.dhealth.data.localdb.DAO.MedicalRoomDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DBModule {

    /*
     * The method returns the Database object
     * */
    @Provides
    @Singleton
    MedicalRoomDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                MedicalRoomDatabase.class, "medical_database.db")
                //                            .addMigrations(MIGRATION_3_4)
                .allowMainThreadQueries().build();
    }


        static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE doctor_table "
//                    + "ADD COLUMN identity TEXT");
//
//            database.execSQL("ALTER TABLE patient_table "
//                    + "ADD COLUMN identity TEXT");
//
//            database.execSQL("ALTER TABLE report_table "
//                    + "ADD COLUMN uri TEXT");

            database.execSQL("ALTER TABLE report_table "
                    + "ADD COLUMN doctors TEXT");

            database.execSQL("ALTER TABLE report_table "
                    + "ADD COLUMN fileName TEXT");

        }
    };

    /*
     * We need the MovieDao module.
     * For this, We need the AppDatabase object
     * So we will define the providers for this here in this module.
     * */

    @Provides
    @Singleton
    DatabaseDAO provideMovieDao(@NonNull MedicalRoomDatabase appDatabase) {
        return appDatabase.medicalDao();
    }

}
