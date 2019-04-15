package com.example.hamid.dhealth.Fragments;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.activeledgersdk.ActiveLedgerSDK;
import com.example.hamid.dhealth.ActiveLedgerHelper;
import com.example.hamid.dhealth.Activities.UploadFileActivity;
import com.example.hamid.dhealth.Adapter.ReportsListAdapter;
import com.example.hamid.dhealth.FileUtils;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Report;
import com.example.hamid.dhealth.MedicalRepository.HTTP.HttpClient;
import com.example.hamid.dhealth.Preference.PreferenceKeys;
import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.Utils.SwipeController;
import com.example.hamid.dhealth.Utils.SwipeControllerActions;
import com.example.hamid.dhealth.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ReportsFragment extends Fragment implements View.OnClickListener {


    private static final int WRITE_REQUEST_CODE = 43;
    public List<Report> reports;
    SearchView searchView;
    String fileName;
    String base64File;
    private FloatingActionButton fab_create_file;
    private ReportsViewModel mViewModel;
    private RecyclerView rv_report_list;
    private ReportsListAdapter reportsListAdapter;
    private ProgressBar progressBar;


    public static ReportsFragment newInstance() {
        return new ReportsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.reports_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ReportsViewModel.class);

        initLayouts();

        reports = new ArrayList<>();
        reportsListAdapter = new ReportsListAdapter(getContext(), reports);
        setupRecyclerView();

        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);

        SwipeRefreshLayout pullToRefresh = getView().findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefresh.setRefreshing(true);
                populateList();
            }
        });


        mViewModel.getReportList().observe(this, new Observer<List<Report>>() {
            @Override
            public void onChanged(@Nullable final List<Report> reports) {
                reportsListAdapter.setReportList(reports);
                //update reports list
                pullToRefresh.setRefreshing(false);
            }
        });
        populateList();
    }


    public void setupRecyclerView() {

        rv_report_list = (RecyclerView) getView().findViewById(R.id.rv_report_list);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_report_list.setLayoutManager(mLayoutManager);
        rv_report_list.setItemAnimator(new DefaultItemAnimator());
        rv_report_list.setAdapter(reportsListAdapter);

        SwipeController swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                reportsListAdapter.reportList.remove(position);
                reportsListAdapter.notifyItemRemoved(position);
                reportsListAdapter.notifyItemRangeChanged(position, reportsListAdapter.getItemCount());
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(rv_report_list);

        rv_report_list.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });


    }


//    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
//
//        @Override
//        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//            Toast.makeText(getActivity(), "on Move", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        @Override
//        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
//            Toast.makeText(getActivity(), "on Swiped ", Toast.LENGTH_SHORT).show();
//            //Remove swiped item from list and notify the RecyclerView
//            int position = viewHolder.getAdapterPosition();
//
//
//
//            mViewModel.deleteReport(position);
//
//
//        }
//    };

    private void initLayouts() {
        fab_create_file = (FloatingActionButton) getView().findViewById(R.id.fab_create_file);
        fab_create_file.setOnClickListener(this);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("query text submit ==>", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Observable.fromCallable(() -> {
                    return mViewModel.searchReportsList(newText);
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((result) -> {
                            reportsListAdapter.setReportList(result);
                        });

                Log.e("query text change ==>", newText);
                return false;
            }
        });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_create_file:
                Intent intent = new Intent(getActivity(), UploadFileActivity.class);
                startActivity(intent);
                break;
        }
    }


    private void populateList() {
        progressBar.setVisibility(View.VISIBLE);
        //get reports from http
        getReports();
    }


    public void getReports() {


        ActiveLedgerSDK.KEYNAME = ActiveLedgerHelper.getInstance().getKeyname();
        ActiveLedgerSDK.keyType = ActiveLedgerHelper.getInstance().getKeyType();

        Log.e("getReport token", com.example.hamid.dhealth.Preference.PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_APP_TOKEN, "null"));

        HttpClient.getInstance().getReport(com.example.hamid.dhealth.Preference.PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_APP_TOKEN, "null"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<Response<String>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
//                        disposable = d;
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onNext(Response<String> response) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("getReport code--->", response.code() + "");
                        if (response.code() == 200) {
                            Utils.Log("getReport res--->", response.body() + "");

                            //delete all reports in db
                            mViewModel.deleteReportData();

                            //add all reports to db

                            try {
                                JSONObject responseJSON = new JSONObject(response.body());

                                JSONArray doctors = responseJSON.optJSONArray("streams").optJSONObject(0).optJSONArray("reports");

                                if (doctors != null) {
                                    JSONObject doctor = new JSONObject();
                                    for (int i = 0; i < doctors.length(); i++) {
                                        doctor = doctors.optJSONObject(i);

                                        String patientName = doctor.optString("patientName");
                                        String fileName = doctor.optString("fileName");
                                        String description = doctor.optString("description");
                                        String title = doctor.optString("title");
                                        String content = doctor.optString("content");
                                        String doctors_list = doctor.optString("doctors");

                                        Log.e("------->", doctors_list);

                                        Report report = new Report(title, description, patientName, "Jhonny Depp", "", "", content, "", "", doctors_list, fileName);
                                        mViewModel.insert(report);
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "NO Reports Found in Ledger", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        } else {
                            Toast.makeText(getActivity(), "Report Fetching  Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void saveFiletoStrorage() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_REQUEST_CODE);
        } else {
            FileUtils.saveFile(fileName, base64File);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case WRITE_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    FileUtils.saveFile(fileName, base64File);

                } else {
                    Toast.makeText(getActivity(), "Writing permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }


}
