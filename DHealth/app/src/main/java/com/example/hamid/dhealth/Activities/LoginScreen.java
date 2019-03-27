package com.example.hamid.dhealth.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hamid.dhealth.MedicalRepository.HTTP.HttpClient;
import com.example.hamid.dhealth.Preference.PreferenceKeys;
import com.example.hamid.dhealth.Preference.PreferenceManager;
import com.example.hamid.dhealth.R;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class LoginScreen extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreen.this, SignUpScreen.class));
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreen.this, ResetPasswordScreen.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                String base = email + ":" + password;
                Log.e("login base --->", base);

                String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
                Log.e("login auth header --->", authHeader);


                HttpClient.getInstance().loginUser(authHeader)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Response<String>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Response<String> stringResponse) {
                                int status_code = stringResponse.code();
                                Log.e("login status code --->", status_code + "");

                                if (status_code == 200) {

                                    //hit the service if the response is 200 go for it
                                    String token = stringResponse.headers().get("Token");
                                    JSONObject response = null;
                                    Intent intent = null;
                                    String message = null;
                                    try {
                                        response = new JSONObject(stringResponse.body());
                                        JSONObject res = response.optJSONObject("resp");

                                        message = res.optString("desc");
                                        Log.e("login description--->", message + "");

                                        JSONObject stream = response.optJSONObject("stream");
                                        Log.e("login stream--->", stream + "");

                                        if (stream != null) {

                                            String first_name = stream.optString("first_name");
                                            String last_name = stream.optString("last_name");
                                            String email = stream.optString("email");
                                            String date_of_birth = stream.optString("date_of_birth");
                                            String phone_number = stream.optString("phone_number");
                                            String address = stream.optString("address");
                                            String security = stream.optString("security");
                                            String profile_type = stream.optString("profile_type");
                                            String gender = stream.optString("gender");
                                            String dp = stream.optString("dp");

                                            Log.e("login stream--->", first_name + "");

                                            intent = new Intent(LoginScreen.this, DashboardScreen.class);

                                            PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_NAME, first_name);
                                            PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_LAST_NAME, last_name);
                                            PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_EMAIL, email);
                                            PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_DOB, date_of_birth);
                                            PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_PHONENO, phone_number);
                                            PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_ADDRESS, address);
                                            PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_GENDER, gender);
                                            PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_ENCRYPTION, security);
                                            PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_PROFILE_TYPE, profile_type);
                                            PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_PROFILEPIC, dp);
                                            PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_PROFILEFINISHED, true);

                                        } else {
                                            intent = new Intent(LoginScreen.this, ProfileScreen.class);
                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Log.e("login header--->", stringResponse.headers() + "");
                                    Log.e("login header token--->", token + "");

                                    Toast.makeText(LoginScreen.this, message,
                                            Toast.LENGTH_SHORT).show();

                                    PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_APP_TOKEN, token);
                                    PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_LOGGEDIN, true);
                                    PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_EMAIL, inputEmail.getText().toString());
                                    startActivity(intent);
                                    finish();

                                } else if (status_code == 400) {
                                    Toast.makeText(LoginScreen.this, "User already exist." + status_code,
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginScreen.this, "Authentication failed." + status_code,
                                            Toast.LENGTH_SHORT).show();
                                }

                            }


                            @Override
                            public void onError(Throwable e) {
                                Log.e("login --->", e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                progressBar.setVisibility(View.GONE);

                            }
                        });


            }
        });
    }
}