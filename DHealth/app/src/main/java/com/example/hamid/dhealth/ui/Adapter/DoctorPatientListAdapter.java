package com.example.hamid.dhealth.ui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.data.Preference.PreferenceKeys;
import com.example.hamid.dhealth.data.localdb.Entity.Doctor;
import com.example.hamid.dhealth.data.localdb.Entity.Patient;
import com.example.hamid.dhealth.ui.Activities.DoctorPatientDescriptionActivity;
import com.example.hamid.dhealth.utils.Utils;

import java.util.List;

public class DoctorPatientListAdapter extends RecyclerView.Adapter<DoctorPatientListAdapter.ViewHolder> {

    private Context mContext;
    private List<Doctor> doctorList;
    private List<Patient> patientList;
    private String profile_type;


    public DoctorPatientListAdapter(Context context, List<Doctor> doctorList, List<Patient> patientList, String profile_type) {
        this.mContext = context;
        this.doctorList = doctorList;
        this.patientList = patientList;
        this.profile_type = profile_type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_patient_card, parent, false);

        DoctorPatientListAdapter.ViewHolder viewHolder = new ViewHolder(itemView, new DoctorPatientListAdapter.ViewHolder.IItemClickListener() {

            @Override
            public void onItemClick(View caller, int position) {

                Intent intent = new Intent(mContext, DoctorPatientDescriptionActivity.class);
                if (profile_type.equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {
                    Patient patient = patientList.get(position);
                    intent.putExtra(DoctorPatientDescriptionActivity.DATA, patient);
                } else {
                    Doctor doctor = doctorList.get(position);
                    intent.putExtra(DoctorPatientDescriptionActivity.DATA, doctor);
                }
                mContext.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (profile_type.equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {
            Patient patient = patientList.get(position);
            holder.tv_name.setText(patient.getFirst_name() + " " + patient.getLast_name());
            holder.iv_dp.setImageBitmap(Utils.decodeBase64(patient.getDp()));
        } else {
            Doctor doctor = doctorList.get(position);
            holder.tv_name.setText(doctor.getFirst_name() + " " + doctor.getLast_name());
            holder.iv_dp.setImageBitmap(Utils.decodeBase64(doctor.getDp()));
        }


    }


    @Override
    public int getItemCount() {

        if (profile_type.equalsIgnoreCase(PreferenceKeys.LBL_DOCTOR)) {
            if (patientList != null)
                return patientList.size();
            else return 0;
        } else {
            if (doctorList != null)
                return doctorList.size();
            else return 0;
        }

    }


    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
        notifyDataSetChanged();
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tv_name;
        public ImageView iv_display;
        public ImageView iv_dp;

        private ViewHolder.IItemClickListener itemClickListener;


        public ViewHolder(View view, IItemClickListener itemClickListener) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            iv_display = (ImageView) view.findViewById(R.id.iv_dp);
            iv_dp = (ImageView) view.findViewById(R.id.iv_dp);

            this.itemClickListener = itemClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getLayoutPosition());
        }

        public interface IItemClickListener {
            public void onItemClick(View caller, int position);
        }

    }


}
