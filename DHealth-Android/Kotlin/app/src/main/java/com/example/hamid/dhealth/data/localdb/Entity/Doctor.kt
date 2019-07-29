package com.example.hamid.dhealth.data.localdb.Entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import java.io.Serializable

@Entity(tableName = "doctor_table")
class Doctor(var first_name: String?, var last_name: String?, var email: String, var date_of_birth: String?, var address: String?, var phone_number: String?, var gender: String?, var dp: String?, var identity: String?) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
