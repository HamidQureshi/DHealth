package com.example.hamid.dhealth.ui.Fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import com.example.hamid.dhealth.R
import com.example.hamid.dhealth.data.Preference.PreferenceKeys
import com.example.hamid.dhealth.data.Preference.PreferenceManager
import com.example.hamid.dhealth.data.localdb.Entity.Doctor
import com.example.hamid.dhealth.data.localdb.Entity.Patient
import com.example.hamid.dhealth.factory.ViewModelFactory
import com.example.hamid.dhealth.ui.Adapter.DoctorPatientListAdapter
import com.example.hamid.dhealth.ui.viewmodel.AppViewModel
import com.example.hamid.dhealth.utils.Utils
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


class DoctorPatientFragment : Fragment() {

    lateinit var searchView: SearchView

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var preferenceManager: PreferenceManager
    private var mViewModel: AppViewModel? = null
    private var rv_dplist: RecyclerView? = null
    private var doctorPatientListAdapter: DoctorPatientListAdapter? = null
    private var doctorList: List<Doctor>? = null
    private var patientList: List<Patient>? = null
    private var progressBar: ProgressBar? = null
    internal var pullToRefresh: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidSupportInjection.inject(this)
        setHasOptionsMenu(true)

    }

    companion object {
        fun newInstance(): DoctorPatientFragment {
            return DoctorPatientFragment()
        }
    }

    override fun onCreateView(@NonNull inflater: LayoutInflater, @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.doctor_patient_fragment, container, false)
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(AppViewModel::class.java)

        rv_dplist = view!!.findViewById<View>(R.id.rv_doctor_patient_list) as RecyclerView
        if (preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)!!.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {
            patientList = mViewModel!!.getPatientList()!!.value
            doctorList = ArrayList()
        } else {
            patientList = ArrayList()
            doctorList = mViewModel!!.getDoctorList()!!.value
        }
        val profileType = preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)
        doctorPatientListAdapter = DoctorPatientListAdapter(context!!, doctorList, patientList, profileType!!)

        val mLayoutManager = GridLayoutManager(context, 1)
        rv_dplist!!.layoutManager = mLayoutManager
        rv_dplist!!.itemAnimator = DefaultItemAnimator()
        rv_dplist!!.adapter = doctorPatientListAdapter
        progressBar = view!!.findViewById<View>(R.id.progressBar) as ProgressBar

        pullToRefresh = view!!.findViewById(R.id.pullToRefresh)
        pullToRefresh!!.setOnRefreshListener {
            pullToRefresh!!.isRefreshing = true
            populateList()
        }


        if (preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)!!.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {

            mViewModel!!.getPatientList()!!.observe(this, Observer { patients ->
                progressBar!!.visibility = View.GONE
                // Update the cached copy of the words in the adapter.
                doctorPatientListAdapter!!.setPatientList(patients!!)
                pullToRefresh!!.isRefreshing = false
            })
        } else {

            mViewModel!!.getDoctorList()!!.observe(this, Observer { doctors ->
                progressBar!!.visibility = View.GONE
                // Update the cached copy of the words in the adapter.
                doctorPatientListAdapter!!.setDoctorList(doctors!!)
                pullToRefresh!!.isRefreshing = false
            })
        }

        //        if (mViewModel.isFetchingDoctorPatientData()){
        //            pullToRefresh.setRefreshing(true);
        //        }
        //        else{
        //            pullToRefresh.setRefreshing(false);
        //
        //        }

        //        populateList();
    }

    private fun populateList() {
        progressBar!!.visibility = View.VISIBLE
        if (preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)!!.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {
            mViewModel!!.getAssignedPatientListFromServer(preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_APP_TOKEN, "null")!!)
        } else {
            mViewModel!!.getDoctorListFromServer(preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_APP_TOKEN, "null")!!)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        val inflater = activity!!.menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        searchView = menu!!.findItem(R.id.search).actionView as SearchView

        searchView.queryHint = "Search by name"


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.e("query text submit ==>", query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {


                if (preferenceManager.readFromPref(activity!!, PreferenceKeys.SP_PROFILE_TYPE, PreferenceKeys.LBL_DOCTOR)!!.equals(PreferenceKeys.LBL_DOCTOR, ignoreCase = true)) {

                    Observable.fromCallable { mViewModel!!.searchPatientList(newText) }
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe { result -> doctorPatientListAdapter!!.setPatientList(result) }

                } else {

                    Observable.fromCallable { mViewModel!!.searchDoctorsList(newText) }
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe { result -> doctorPatientListAdapter!!.setDoctorList(result) }

                }


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


    override fun onResume() {
        super.onResume()

        if (pullToRefresh == null) {
            return
        }
        pullToRefresh!!.isRefreshing = mViewModel!!.isFetchingDoctorPatientData

    }


}



