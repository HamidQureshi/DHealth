package com.example.hamid.dhealth.MedicalRepository;


import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.hamid.dhealth.MedicalRepository.DB.DAO.DatabaseDAO;
import com.example.hamid.dhealth.MedicalRepository.DB.DAO.MedicalRoomDatabase;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Doctor;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Patient;
import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Report;
import com.example.hamid.dhealth.MedicalRepository.HTTP.HttpClient;
import com.example.hamid.dhealth.Preference.PreferenceManager;
import com.example.hamid.dhealth.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class DataRepository {

    private DatabaseDAO databaseDAO;
    private LiveData<List<Doctor>> mAllDoctors;
    private LiveData<List<Patient>> mAllPatients;
    private LiveData<List<Report>> mAllReports;

    private static volatile DataRepository INSTANCE;

    public static DataRepository getINSTANCE(Application application) {
        if (INSTANCE == null) {
            synchronized (PreferenceManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    public DataRepository(Application application) {
        MedicalRoomDatabase roomDatabase = MedicalRoomDatabase.getDatabase(application);
        databaseDAO = roomDatabase.medicalDao();
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

        Utils.Log("doctorlist--->", "");
        //fetch data from server
        HttpClient.getInstance().getDoctorListFromServer(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onError(Throwable e) {
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

                                    Log.e("doctorlist code--->", email);

                                    Doctor doctor = new Doctor(first_name, last_name, email, date_of_birth, address, phone_number, gender, dp);
                                    doctorList.add(doctor);
                                }

                                deleteAllDoctor();
                                insertDoctorList(doctorList);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                    }
                });

        //update database and everything should update automatically
    }

    public void getPatientListFromServer(String token) {

        Utils.Log("patientlist--->", "");
        //fetch data from server
        HttpClient.getInstance().getPatientListFromServer(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onError(Throwable e) {
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

                                    Patient patient = new Patient(first_name, last_name, email, date_of_birth, address, phone_number, gender, dp);
                                    patientList.add(patient);
                                }

                                deleteAllPatient();
                                insertPatientList(patientList);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                });

        //update database and everything should update automatically
    }

    public List<Report> searchReportList(String name) {
        return databaseDAO.searchReports(name + '%');
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


}
