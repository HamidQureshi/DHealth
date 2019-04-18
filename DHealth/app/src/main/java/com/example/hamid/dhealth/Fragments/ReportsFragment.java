package com.example.hamid.dhealth.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
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
    TextView txt_no_report;
    private FloatingActionButton fab_create_file;
    private ReportsViewModel mViewModel;
    private RecyclerView rv_report_list;
    private ReportsListAdapter reportsListAdapter;
    private ProgressBar progressBar;
    public static Handler handler= null;

    public static int HIDE_PROGRESS = 0;
    public static int HIDE_NO_REPORTLABEL = 1;
    public static int SHOW_NO_REPORTLABEL = 2;
    public static int SHOW_TOAST = 3;

    public static int TXT_NO_REPORTFOUND = 0;
    public static int TXT_REPORT_FETCHING_FAILED = 1;

    public static ReportsFragment newInstance() {
        return new ReportsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @SuppressLint("HandlerLeak")
    @Override
    public void onResume() {
        super.onResume();
       handler = new Handler(){
           @Override
           public void handleMessage(Message msg) {
               super.handleMessage(msg);
               Log.e("Message Received",""+msg.what);
               if (msg.what == HIDE_PROGRESS){
                   hideProgressbar();
               }
               else if(msg.what == HIDE_NO_REPORTLABEL){
                   hideNoReportLabel();
               }
               else if(msg.what == SHOW_NO_REPORTLABEL){
                   showNoReportLabel();
               }

               if (msg.what == SHOW_TOAST){

                   if (msg.arg1 == TXT_NO_REPORTFOUND){
                       showToast("NO Reports Found in Ledger");
                   }
                   else if (msg.arg1 == TXT_NO_REPORTFOUND){
                       showToast("Report Fetching  Failed!");
                   }


               }

           }
       };
       
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

        if (mViewModel.getReportList() == null) {
            txt_no_report.setVisibility(View.VISIBLE);
        }

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


    private void initLayouts() {
        fab_create_file = (FloatingActionButton) getView().findViewById(R.id.fab_create_file);
        fab_create_file.setOnClickListener(this);

        txt_no_report = (TextView) getView().findViewById(R.id.txt_no_report);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);


        searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setQueryHint("Search by title");

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

        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.search), new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
//                Utils.showKeyboard(getActivity());

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                searchView.setQuery("", true);
                Utils.hideKeyboard(getActivity());
                return true;
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
        mViewModel.getReportsListFromServer(com.example.hamid.dhealth.Preference.PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_APP_TOKEN, "null"));
    }


    public void hideProgressbar(){
        progressBar.setVisibility(View.GONE);
    }

    public void hideNoReportLabel(){
        txt_no_report.setVisibility(View.GONE);
    }

    public void showNoReportLabel(){
        txt_no_report.setVisibility(View.VISIBLE);
    }

    public void showToast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
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
