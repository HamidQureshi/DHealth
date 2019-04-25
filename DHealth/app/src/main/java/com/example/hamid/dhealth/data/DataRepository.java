package com.example.hamid.dhealth.data;


import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.Message;
import android.util.Log;

import com.example.activeledgersdk.ActiveLedgerSDK;
import com.example.hamid.dhealth.ActiveLedgerHelper;
import com.example.hamid.dhealth.data.localdb.DAO.DatabaseDAO;
import com.example.hamid.dhealth.data.localdb.Entity.Doctor;
import com.example.hamid.dhealth.data.localdb.Entity.Patient;
import com.example.hamid.dhealth.data.localdb.Entity.Report;
import com.example.hamid.dhealth.ui.Fragments.ReportsFragment;
import com.example.hamid.dhealth.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import com.example.hamid.dhealth.data.remote.APIService;

@Singleton
public class DataRepository {

    private DatabaseDAO databaseDAO;
    private APIService apiService;
    private LiveData<List<Doctor>> mAllDoctors;
    private LiveData<List<Patient>> mAllPatients;
    private LiveData<List<Report>> mAllReports;

    public DataRepository(APIService apiService, DatabaseDAO databaseDAO) {
        this.databaseDAO = databaseDAO;
        this.apiService = apiService;
        mAllDoctors = databaseDAO.getDoctorList();
        mAllPatients = databaseDAO.getPatientList();
        mAllReports = databaseDAO.getReportList();
    }

    public LiveData<List<Doctor>> getDoctorList() {
        return mAllDoctors;
    }

    public LiveData<List<Patient>> getPatientList() {
        return mAllPatients;
    }

    public LiveData<List<Report>> getReportList() {
        return mAllReports;
    }

    public void getDoctorListFromServer(String token) {

        apiService.getDoctorList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<Response<String>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("doctorlist error", e.getMessage() + "");
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onNext(Response<String> response) {

                        Utils.Log("doctorlist code--->", response.code() + "");

                        if (response.code() == 200) {

                            JSONObject responseObj = null;
                            try {
                                responseObj = new JSONObject(response.body());

                                Utils.Log("doctorlist resp--->", response.body() + "");


                                JSONArray doctorsArray = responseObj.optJSONArray("streams");

                                List<Doctor> doctorList = new ArrayList<>();

                                for (int i = 0; i < doctorsArray.length(); i++) {
                                    JSONObject jsonobject = doctorsArray.getJSONObject(i);

                                    String first_name = jsonobject.optString("first_name");
                                    String last_name = jsonobject.optString("last_name");
                                    String email = jsonobject.optString("email");
                                    String date_of_birth = jsonobject.optString("date_of_birth");
                                    String address = jsonobject.optString("address");
                                    String phone_number = jsonobject.optString("phone_number");
                                    String gender = jsonobject.optString("gender");
                                    String dp = jsonobject.optString("dp");
                                    String identity = jsonobject.optString("_id");

                                    Log.e("doctorlist email--->", email);
                                    Log.e("doctorlist identity--->", identity);

                                    Doctor doctor = new Doctor(first_name, last_name, email, date_of_birth, address, phone_number, gender, dp, identity);
                                    doctorList.add(doctor);
                                }

                                insertDoctorList(doctorList);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                    }
                });

    }

    public void getPatientListFromServer(String token) {

        apiService.getPatientList(token)
//        HttpClientModule.getInstance().getAssignedPatientListFromServer(token)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<Response<String>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onNext(Response<String> response) {

                        Utils.Log("patientlist code--->", response.code() + "");

                        if (response.code() == 200) {
                            JSONObject responseObj = null;
                            try {
                                responseObj = new JSONObject(response.body());

                                JSONArray patientArray = responseObj.optJSONArray("streams");

                                List<Patient> patientList = new ArrayList<>();

                                for (int i = 0; i < patientArray.length(); i++) {
                                    JSONObject jsonobject = patientArray.getJSONObject(i);

                                    String first_name = jsonobject.optString("first_name");
                                    String last_name = jsonobject.optString("last_name");
                                    String email = jsonobject.optString("email");
                                    String date_of_birth = jsonobject.optString("date_of_birth");
                                    String address = jsonobject.optString("address");
                                    String phone_number = jsonobject.optString("phone_number");
                                    String gender = jsonobject.optString("gender");
                                    String dp = jsonobject.optString("dp");
                                    String identity = jsonobject.optString("_id");

                                    Log.e("patientlist email--->", email);
                                    Log.e("patientlist identity-->", identity);

                                    Patient patient = new Patient(first_name, last_name, email, date_of_birth, address, phone_number, gender, dp, identity);
                                    patientList.add(patient);
                                }

                                insertPatientList(patientList);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                });

    }

    public void getAssignedPatientListFromServer(String token) {

        apiService.getAssignedPatientList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<Response<String>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onNext(Response<String> response) {

                        Utils.Log("assigned patientlist code--->", response.code() + "");

                        if (response.code() == 200) {
                            JSONObject responseObj = null;
                            try {
                                responseObj = new JSONObject(response.body());

                                JSONArray patientArray = responseObj.optJSONArray("streams");

                                List<Patient> patientList = new ArrayList<>();

                                for (int i = 0; i < patientArray.length(); i++) {
                                    JSONObject jsonobject = patientArray.getJSONObject(i);

                                    String first_name = jsonobject.optString("first_name");
                                    String last_name = jsonobject.optString("last_name");
                                    String email = jsonobject.optString("email");
                                    String date_of_birth = jsonobject.optString("date_of_birth");
                                    String address = jsonobject.optString("address");
                                    String phone_number = jsonobject.optString("phone_number");
                                    String gender = jsonobject.optString("gender");
                                    String dp = jsonobject.optString("dp");
                                    String identity = jsonobject.optString("_id");

                                    Log.e("assigned patient mail", email);
                                    Log.e("assigned patient identi", identity);

                                    Patient patient = new Patient(first_name, last_name, email, date_of_birth, address, phone_number, gender, dp, identity);
                                    patientList.add(patient);

                                    JSONArray reports = jsonobject.optJSONArray("reports");
                                    if (null != reports && reports.length() > 0) {
                                        List<Report> reportList = new ArrayList<>();
                                        for (int j = 0; j < reports.length(); j++) {
                                            JSONObject reportObj = new JSONObject(reports.get(j).toString());

                                            String patientName = reportObj.optString("patientName");
                                            String fileName = reportObj.optString("fileName");
                                            String description = reportObj.optString("description");
                                            String title = reportObj.optString("title");
                                            String content = reportObj.optString("content");
                                            String doctors_list = reportObj.optString("doctors");

                                            Report report = new Report(title, description, patientName, "Jhonny Depp", "", "", content, "", "", doctors_list, fileName);
                                            reportList.add(report);
                                        }
                                        insertReportList(reportList);
                                    }


                                }

                                insertPatientList(patientList);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                });

    }

    public void getReportsListFromServer(String token) {

        ActiveLedgerSDK.KEYNAME = ActiveLedgerHelper.getInstance().getKeyname();
        ActiveLedgerSDK.keyType = ActiveLedgerHelper.getInstance().getKeyType();

        Log.e("getReport token", token);

        apiService.getReport(token)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
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

                        if (null != ReportsFragment.handler) {
                            Message message = new Message();
                            message.what = ReportsFragment.HIDE_PROGRESS;
                            ReportsFragment.handler.sendMessage(message);
                        }

                        Log.e("getReport code--->", response.code() + "");
                        if (response.code() == 200) {

                            Utils.Log("getReport res--->", response.body() + "");

                            try {
                                JSONObject responseJSON = new JSONObject(response.body());

                                JSONArray doctors = responseJSON.optJSONArray("streams").optJSONObject(0).optJSONArray("reports");

                                if (doctors != null) {
                                    List<Report> reportList = new ArrayList<>();

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
                                        reportList.add(report);
                                    }

                                    if (null != ReportsFragment.handler) {
                                        Message message = new Message();
                                        message.what = ReportsFragment.HIDE_NO_REPORTLABEL;
                                        ReportsFragment.handler.sendMessage(message);
                                    }
                                    insertReportList(reportList);

                                } else {
                                    if (null != ReportsFragment.handler) {
                                        Message message = new Message();
                                        message.what = ReportsFragment.SHOW_NO_REPORTLABEL;
                                        ReportsFragment.handler.sendMessage(message);
                                        Message message2 = new Message();
                                        message2.what = ReportsFragment.SHOW_TOAST;
                                        message2.arg1 = ReportsFragment.TXT_NO_REPORTFOUND;
                                        ReportsFragment.handler.sendMessage(message2);
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        } else {
                            if (null != ReportsFragment.handler) {
                                Message message = new Message();
                                message.what = ReportsFragment.SHOW_NO_REPORTLABEL;
                                ReportsFragment.handler.sendMessage(message);
                                Message message2 = new Message();
                                message2.what = ReportsFragment.SHOW_TOAST;
                                message2.arg1 = ReportsFragment.TXT_REPORT_FETCHING_FAILED;
                                ReportsFragment.handler.sendMessage(message2);
                            }
                        }
                    }
                });
    }

    public List<Report> searchReportList(String title) {
        return databaseDAO.searchReports(title + '%');
    }

    public List<Doctor> searchDoctorsList(String name) {
        return databaseDAO.searchDoctor(name + '%');
    }

    public List<Patient> searchPatientsList(String name) {
        return databaseDAO.searchPatient(name + '%');
    }

    @SuppressLint("CheckResult")
    public void insertDoctor(final Doctor doctor) {

        Observable.fromCallable(() -> {
            databaseDAO.insertDoctor(doctor);
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });

    }

    @SuppressLint("CheckResult")
    public void insertDoctorList(final List<Doctor> doctorList) {

        Observable.fromCallable(() -> {
            databaseDAO.deleteAllDoctor();
            databaseDAO.insertDoctorList(doctorList);

            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    mAllDoctors = databaseDAO.getDoctorList();
                });

    }

    @SuppressLint("CheckResult")
    public void insertPatientList(final List<Patient> patientList) {

        Observable.fromCallable(() -> {
            databaseDAO.deleteAllPatient();
            databaseDAO.insertPatientList(patientList);

            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    mAllPatients = databaseDAO.getPatientList();
                });

    }

    public void insertPatient(final Patient patient) {

        Observable.fromCallable(() -> {
            databaseDAO.insertPatient(patient);
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });

    }

    public void insertReport(Report report) {

        Observable.fromCallable(() -> {
            databaseDAO.insertReport(report);
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });
    }

    @SuppressLint("CheckResult")
    public void insertReportList(final List<Report> reportList) {

        Observable.fromCallable(() -> {
            databaseDAO.deleteAllReport();
            databaseDAO.insertReportList(reportList);

            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    mAllReports = databaseDAO.getReportList();
                });
    }

    public void updateReport(Report report) {

        Observable.fromCallable(() -> {
            databaseDAO.updateReport(report);
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });
    }

    public void deleteReport(Report report) {
        Observable.fromCallable(() -> {
            databaseDAO.deleteReport(report);
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });
    }

    public void deleteAllDoctor() {

        Observable.fromCallable(() -> {
            databaseDAO.deleteAllDoctor();
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });

    }

    public void deleteAllPatient() {

        Observable.fromCallable(() -> {
            databaseDAO.deleteAllPatient();
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });

    }

    public void deleteAllReport() {

        Observable.fromCallable(() -> {
            databaseDAO.deleteAllReport();
            return true;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    //Use result for something
                });

    }


    public Observable<Response<String>> registerUser(String user) {
        return apiService.registerUser(user);
    }

    public Observable<Response<String>> loginUser(String user) {
        return apiService.loginUser(user);
    }

    // this method can be used to send transaction as an HTTP request to the ledger
    public Observable<Response<String>> createProfile(String token, String transaction) {
        return apiService.createProfile(token, transaction);
    }

    // this method can be used to send transaction as an HTTP request to the ledger
    public Observable<Response<String>> sendTransaction(String token, String transaction) {
        return apiService.sendTransaction(token, transaction);
    }

}
