package com.example.hamid.dhealth.data.localdb.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import java.io.Serializable

@Entity(tableName = "report_table")
class Report(@field:ColumnInfo(name = "title")
             var title: String, var description: String?, var ownership: String?, var assignedTo: String?, var uploadedDate: String?, var signDate: String?, var content: String?, var status: String?, var uri: String?, var doctors: String?, var fileName: String?) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}
