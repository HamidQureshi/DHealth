package com.example.hamid.dhealth.ui.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.hamid.dhealth.R
import com.example.hamid.dhealth.data.Preference.PreferenceKeys
import com.example.hamid.dhealth.data.localdb.Entity.Doctor
import com.example.hamid.dhealth.data.localdb.Entity.Patient
import com.example.hamid.dhealth.ui.Activities.DoctorPatientDescriptionActivity
import com.example.hamid.dhealth.utils.Utils

class DoctorPatientListAdapter(private val mContext: Context, private var doctorList: List<Doctor>?, private var patientList: List<Patient>?, private val profile_type: String) : RecyclerView.Adapter<DoctorPatientListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.doctor_patient_card, parent, false)

        return ViewHolder(itemView, object : ViewHolder.IItemClickListener {

            override fun onItemClick(caller: View, position: Int) {

                val intent = Intent(mContext, DoctorPatientDescriptionActivity::class.java)
                if (profile_type.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {
                    val patient = patientList!![position]
                    intent.putExtra(DoctorPatientDescriptionActivity.DATA, patient)
                } else {
                    val doctor = doctorList!![position]
                    intent.putExtra(DoctorPatientDescriptionActivity.DATA, doctor)
                }
                mContext.startActivity(intent)
            }
        })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (profile_type.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {
            val patient = patientList!![position]
            holder.tv_name.text = patient.first_name + " " + patient.last_name
            if (patient.dp!!.length > 4)
                holder.iv_dp.setImageBitmap(Utils.decodeBase64(patient.dp!!))
        } else {
            val doctor = doctorList!![position]
            holder.tv_name.text = doctor.first_name + " " + doctor.last_name
            if (doctor.dp!!.length > 4)
                holder.iv_dp.setImageBitmap(Utils.decodeBase64(doctor.dp!!))
        }


    }


    override fun getItemCount(): Int {

        return if (profile_type.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {
            if (patientList != null)
                patientList!!.size
            else
                0
        } else {
            if (doctorList != null)
                doctorList!!.size
            else
                0
        }

    }


    fun setDoctorList(doctorList: List<Doctor>) {
        this.doctorList = doctorList
        notifyDataSetChanged()
    }

    fun setPatientList(patientList: List<Patient>) {
        this.patientList = patientList
        notifyDataSetChanged()
    }


    class ViewHolder(view: View, itemClickListener: IItemClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var tv_name: TextView
        var iv_display: ImageView
        var iv_dp: ImageView

        private val itemClickListener: ViewHolder.IItemClickListener


        init {

            tv_name = view.findViewById(R.id.tv_name)
            iv_display = view.findViewById(R.id.iv_dp)
            iv_dp = view.findViewById(R.id.iv_dp)

            this.itemClickListener = itemClickListener
            view.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            itemClickListener.onItemClick(view, layoutPosition)
        }

        interface IItemClickListener {
            fun onItemClick(caller: View, position: Int)
        }

    }


}
