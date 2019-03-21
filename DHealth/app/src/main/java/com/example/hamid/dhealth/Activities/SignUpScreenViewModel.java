package com.example.hamid.dhealth.Activities;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.activeledgersdk.utility.Utility;
import com.example.hamid.dhealth.MedicalRepository.HTTP.HttpClient;
import com.example.hamid.dhealth.Preference.PreferenceKeys;
import com.example.hamid.dhealth.Preference.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SignUpScreenViewModel extends AndroidViewModel {

    public SignUpScreenViewModel(Application application) {
        super(application);
    }

    public void signUp(String email, String password){

        JSONObject userJSON = new JSONObject();

        try {
            userJSON.put("username", email);
            userJSON.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String userString = Utility.getInstance().convertJSONObjectToString(userJSON);

        HttpClient.getInstance().registerUser(userString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        int status_code = stringResponse.code();
                        Log.e("register --->", status_code + "");

                        if (status_code == 200) {
                            //hit the service if the response is 200 go for it
//                            PreferenceManager.getINSTANCE().writeToPref(SignUpScreen.this, PreferenceKeys.SP_EMAIL, inputEmail.getText().toString());
//                            startActivity(new Intent(SignUpScreen.this, ProfileScreen.class));
//                            finish();
                        } else if (status_code == 400) {
//                            Toast.makeText(SignUpScreen.this, "User already exist." + status_code,
//                                    Toast.LENGTH_SHORT).show();
                        } else {
//                            Toast.makeText(SignUpScreen.this, "Authentication failed." + status_code,
//                                    Toast.LENGTH_SHORT).show();
                        }

                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.e("login --->", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
