package com.example.hamid.dhealth.ui.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.hamid.dhealth.R
import com.example.hamid.dhealth.data.localdb.Entity.Report
import com.example.hamid.dhealth.ui.Activities.ReportDetailActivity

class ReportsListAdapter(private val mContext: Context, private var reportList: List<Report>) : RecyclerView.Adapter<ReportsListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsListAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.report_card, parent, false)


        return ViewHolder(itemView, object : ViewHolder.IItemClickListener {

            override fun onItemClick(caller: View, position: Int) {
                val report = reportList[position]
                //TODO remove this line
                val intent = Intent(mContext, ReportDetailActivity::class.java)
                ReportDetailActivity.report = report
                //                intent.putExtra(ReportDetailActivity.REPORT_DATA, report);
                mContext.startActivity(intent)
            }
        })
    }

    override fun onBindViewHolder(holder: ReportsListAdapter.ViewHolder, position: Int) {
        val report = reportList[position]
        holder.tv_name.text = report.ownership
        holder.tv_title.text = report.title
    }


    override fun getItemCount(): Int {
        return reportList.size

    }


    fun setReportList(reportList: List<Report>) {
        this.reportList = reportList
        notifyDataSetChanged()
    }


    class ViewHolder(view: View, private val itemClickListener: ReportsListAdapter.ViewHolder.IItemClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var tv_name: TextView = view.findViewById(R.id.tv_name)
        var tv_title: TextView = view.findViewById(R.id.tv_title)
//        var iv_display: ImageView = view.findViewById(R.id.iv_dp)


        init {
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
