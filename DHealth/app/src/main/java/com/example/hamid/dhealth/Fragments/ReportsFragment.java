package com.example.hamid.dhealth.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import android.widget.SearchView;

import com.example.hamid.dhealth.Activities.UploadFileActivity;
import com.example.hamid.dhealth.Adapter.ReportsListAdapter;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Report;
import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.Utils.SwipeController;
import com.example.hamid.dhealth.Utils.SwipeControllerActions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReportsFragment extends Fragment implements View.OnClickListener {


    public List<Report> reports;
    SearchView searchView;
    private FloatingActionButton fab_create_file;
    private ReportsViewModel mViewModel;
    private RecyclerView rv_report_list;
    private ReportsListAdapter reportsListAdapter;

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

//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
//        itemTouchHelper.attachToRecyclerView(rv_report_list);

        mViewModel.getReportList().observe(this, new Observer<List<Report>>() {
            @Override
            public void onChanged(@Nullable final List<Report> reports) {
                reportsListAdapter.setReportList(reports);
                //update reports list
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
        mViewModel.deleteReportData();
        Report report = new Report("Diabetic Report", "abc", "john", "Jhonny Depp", "10/20/2012", "10/20/2012", "report diabetic etc", "Pending");
        mViewModel.insert(report);
    }


}
