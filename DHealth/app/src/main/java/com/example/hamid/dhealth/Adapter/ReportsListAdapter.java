package com.example.hamid.dhealth.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid.dhealth.Activities.ReportDetailActivity;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Report;
import com.example.hamid.dhealth.R;

import java.util.List;

public class ReportsListAdapter extends RecyclerView.Adapter<ReportsListAdapter.ViewHolder> {

    public List<Report> reportList;
    private Context mContext;

    public ReportsListAdapter(Context context, List<Report> reports) {
        this.mContext = context;
        this.reportList = reports;
    }


    @Override
    public ReportsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_card, parent, false);

        ReportsListAdapter.ViewHolder viewHolder = new ReportsListAdapter.ViewHolder(itemView, new ReportsListAdapter.ViewHolder.IItemClickListener() {

            @Override
            public void onItemClick(View caller, int position) {
                Report report = reportList.get(position);
                //TODO remove this line
                Intent intent = new Intent(mContext, ReportDetailActivity.class);
                ReportDetailActivity.report = report;
//                intent.putExtra(ReportDetailActivity.REPORT_DATA, report);
                mContext.startActivity(intent);
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ReportsListAdapter.ViewHolder holder, int position) {
        Report report = reportList.get(position);
        holder.tv_name.setText(report.getOwnership());
        holder.tv_title.setText(report.getTitle());

    }


    @Override
    public int getItemCount() {
        if (reportList != null)
            return reportList.size();
        else return 0;
    }


    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tv_name, tv_title;
        public ImageView iv_display;

        private ReportsListAdapter.ViewHolder.IItemClickListener itemClickListener;


        public ViewHolder(View view, ReportsListAdapter.ViewHolder.IItemClickListener itemClickListener) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            iv_display = (ImageView) view.findViewById(R.id.iv_dp);
            tv_title = (TextView) view.findViewById(R.id.tv_title);

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
