package com.example.hamid.dhealth.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.example.hamid.dhealth.data.localdb.DAO.DatabaseDAO
import com.example.hamid.dhealth.data.localdb.DAO.MedicalRoomDatabase
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.NonNull
import javax.inject.Singleton

@Module
class DBModule {

    /*
     * The method returns the Database object
     * */
    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): MedicalRoomDatabase {
        return Room.databaseBuilder(application,
                MedicalRoomDatabase::class.java, "medical_database.db")
                //                            .addMigrations(MIGRATION_3_4)
                .allowMainThreadQueries().build()
    }

    /*
     * We need the MovieDao module.
     * For this, We need the AppDatabase object
     * So we will define the providers for this here in this module.
     * */

    @Provides
    @Singleton
    fun provideMovieDao(@NonNull appDatabase: MedicalRoomDatabase): DatabaseDAO {
        return appDatabase.medicalDao()
    }

//    companion object {
//
//        internal val MIGRATION_3_4: Migration = object : Migration(3, 4) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                //            database.execSQL("ALTER TABLE doctor_table "
//                //                    + "ADD COLUMN identity TEXT");
//                //
//                //            database.execSQL("ALTER TABLE patient_table "
//                //                    + "ADD COLUMN identity TEXT");
//                //
//                //            database.execSQL("ALTER TABLE report_table "
//                //                    + "ADD COLUMN uri TEXT");
//
//                database.execSQL("ALTER TABLE report_table " + "ADD COLUMN doctors TEXT")
//
//                database.execSQL("ALTER TABLE report_table " + "ADD COLUMN fileName TEXT")
//
//            }
//        }
//    }

}
