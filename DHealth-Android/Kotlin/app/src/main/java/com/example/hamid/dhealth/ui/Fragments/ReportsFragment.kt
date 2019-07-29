package com.example.hamid.dhealth.ui.Fragments

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast

import com.example.hamid.dhealth.R
import com.example.hamid.dhealth.data.Preference.PreferenceKeys
import com.example.hamid.dhealth.data.Preference.PreferenceManager
import com.example.hamid.dhealth.data.localdb.Entity.Report
import com.example.hamid.dhealth.factory.ViewModelFactory
import com.example.hamid.dhealth.ui.Activities.UploadFileActivity
import com.example.hamid.dhealth.ui.Adapter.ReportsListAdapter
import com.example.hamid.dhealth.ui.viewmodel.AppViewModel
import com.example.hamid.dhealth.utils.FileUtils
import com.example.hamid.dhealth.utils.SwipeController
import com.example.hamid.dhealth.utils.SwipeControllerActions
import com.example.hamid.dhealth.utils.Utils

import java.util.ArrayList

import javax.inject.Inject

import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ReportsFragment : Fragment(), View.OnClickListener {
    var reports: List<Report>? = null
    lateinit var searchView: SearchView
    internal var fileName: String? = null
    internal var base64File: String? = null
    lateinit var txt_no_report: TextView

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var preferenceManager: PreferenceManager
    private var fab_create_file: FloatingActionButton? = null
    private var mViewModel: AppViewModel? = null
    private var rv_report_list: RecyclerView? = null
    private var reportsListAdapter: ReportsListAdapter? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)
        setHasOptionsMenu(true)

    }

    override fun onResume() {
        super.onResume()
        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.e("Message Received", "" + msg.what)
                if (msg.what == HIDE_PROGRESS) {
                    hideProgressbar()
                } else if (msg.what == HIDE_NO_REPORTLABEL) {
                    hideNoReportLabel()
                } else if (msg.what == SHOW_NO_REPORTLABEL) {
                    showNoReportLabel()
                }

                if (msg.what == SHOW_TOAST) {

                    if (msg.arg1 == TXT_NO_REPORTFOUND) {
                        showToast("NO Reports Found in Ledger")
                    } else if (msg.arg1 == TXT_NO_REPORTFOUND) {
                        showToast("Report Fetching  Failed!")
                    }
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.reports_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(AppViewModel::class.java)

        initLayouts()

        if (mViewModel!!.reportList == null) {
            txt_no_report.visibility = View.VISIBLE
        }

        reports = ArrayList()
        reports = mViewModel!!.reportList!!.value

        reportsListAdapter = ReportsListAdapter(context!!, reports!!)
        setupRecyclerView()

        progressBar = view!!.findViewById<View>(R.id.progressBar) as ProgressBar

        val pullToRefresh = view!!.findViewById<SwipeRefreshLayout>(R.id.pullToRefresh)
        pullToRefresh.setOnRefreshListener {
            pullToRefresh.isRefreshing = true
            populateList()
        }


        mViewModel!!.reportList!!.observe(this, Observer { reports ->
            reportsListAdapter!!.setReportList(reports!!)
            //update reports list
            pullToRefresh.isRefreshing = false
        })

        pullToRefresh.isRefreshing = mViewModel!!.isFetchingReportData


        //        populateList();
    }


    fun setupRecyclerView() {

        rv_report_list = view!!.findViewById<View>(R.id.rv_report_list) as RecyclerView

        val mLayoutManager = GridLayoutManager(context, 1)
        //        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_report_list!!.layoutManager = mLayoutManager
        rv_report_list!!.itemAnimator = DefaultItemAnimator()
        rv_report_list!!.adapter = reportsListAdapter

//        val swipeController = SwipeController(object : SwipeControllerActions() {
//            override fun onRightClicked(position: Int) {
//                reportsListAdapter!!.reportList.removeAt(position)
//                reportsListAdapter!!.notifyItemRemoved(position)
//                reportsListAdapter!!.notifyItemRangeChanged(position, reportsListAdapter!!.itemCount)
//            }
//        })

//        val itemTouchhelper = ItemTouchHelper(swipeController)
//        itemTouchhelper.attachToRecyclerView(rv_report_list)

//        rv_report_list!!.addItemDecoration(object : RecyclerView.ItemDecoration() {
//            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
//                swipeController.onDraw(c)
//            }
//        })


    }


    private fun initLayouts() {
        fab_create_file = view!!.findViewById<View>(R.id.fab_create_file) as FloatingActionButton
        if (preferenceManager!!.readFromPref(activity!!, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)!!.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {
            fab_create_file!!.visibility = View.GONE
        }
        fab_create_file!!.setOnClickListener(this)

        txt_no_report = view!!.findViewById<View>(R.id.txt_no_report) as TextView
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        val inflater = activity!!.menuInflater
        inflater.inflate(R.menu.options_menu, menu)


        searchView = menu!!.findItem(R.id.search).actionView as SearchView

        searchView.queryHint = "Search by title"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.e("query text submit ==>", query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                Observable.fromCallable { mViewModel!!.searchReportsList(newText) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { result -> reportsListAdapter!!.setReportList(result) }

                Log.e("query text change ==>", newText)
                return false
            }
        })

        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.search), object : MenuItemCompat.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                //                Utils.showKeyboard(getActivity());

                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                searchView.setQuery("", true)
                Utils.hideKeyboard(activity!!)
                return true
            }
        })


    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.fab_create_file -> {
                val intent = Intent(activity, UploadFileActivity::class.java)
                startActivity(intent)
            }
        }
    }


    private fun populateList() {
        progressBar!!.visibility = View.VISIBLE
        if (preferenceManager!!.readFromPref(activity!!, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)!!.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {
            mViewModel!!.getAssignedPatientListFromServer(preferenceManager!!.readFromPref(activity!!, PreferenceKeys.SP_APP_TOKEN, "null")!!)
        } else {
            mViewModel!!.getReportsListFromServer(preferenceManager!!.readFromPref(activity!!, PreferenceKeys.SP_APP_TOKEN, "null")!!)
        }

    }


    fun hideProgressbar() {
        progressBar!!.visibility = View.GONE
    }

    fun hideNoReportLabel() {
        txt_no_report.visibility = View.GONE
    }

    fun showNoReportLabel() {
        txt_no_report.visibility = View.VISIBLE
    }

    fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun saveFiletoStrorage() {
        if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(activity!!,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    WRITE_REQUEST_CODE)
        } else {
            FileUtils.saveFile(fileName!!, base64File!!)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            WRITE_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    FileUtils.saveFile(fileName!!, base64File!!)

                } else {
                    Toast.makeText(activity, "Writing permission denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }



    companion object {

        private val WRITE_REQUEST_CODE = 43
        var handler: Handler? = null
        var HIDE_PROGRESS = 0
        var HIDE_NO_REPORTLABEL = 1
        var SHOW_NO_REPORTLABEL = 2
        var SHOW_TOAST = 3
        var TXT_NO_REPORTFOUND = 0
        var TXT_REPORT_FETCHING_FAILED = 1

        fun newInstance(): ReportsFragment {
            return ReportsFragment()
        }
    }


}
