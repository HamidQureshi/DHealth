package com.example.hamid.dhealth.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid.dhealth.Activities.DoctorPatientDescriptionActivity;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Doctor;
import com.example.hamid.dhealth.R;

import java.util.List;

public class DoctorPatientListAdapter extends RecyclerView.Adapter<DoctorPatientListAdapter.ViewHolder> {

    private Context mContext;
    private List<Doctor> doctorList;


    public DoctorPatientListAdapter(Context context, List<Doctor> doctorList) {
        this.mContext = context;
        this.doctorList = doctorList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_patient_card, parent, false);

        DoctorPatientListAdapter.ViewHolder viewHolder = new ViewHolder(itemView, new DoctorPatientListAdapter.ViewHolder.IItemClickListener() {

            @Override
            public void onItemClick(View caller, int position) {
                Doctor doctor = doctorList.get(position);
                Intent intent = new Intent(mContext, DoctorPatientDescriptionActivity.class);
                intent.putExtra(DoctorPatientDescriptionActivity.DOCTOR_DATA, doctor);
                mContext.startActivity(intent);
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.tv_name.setText(doctor.getName());

        // loading album cover using Glide library
//        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

    }


    @Override
    public int getItemCount() {
        if (doctorList != null)
            return doctorList.size();
        else return 0;

    }


    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tv_name;
        public ImageView iv_display;

        private ViewHolder.IItemClickListener itemClickListener;


        public ViewHolder(View view, IItemClickListener itemClickListener) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            iv_display = (ImageView) view.findViewById(R.id.iv_dp);

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
