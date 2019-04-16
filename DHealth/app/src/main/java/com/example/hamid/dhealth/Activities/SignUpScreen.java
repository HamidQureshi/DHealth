package com.example.hamid.dhealth.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.activeledgersdk.utility.Utility;
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


public class SignUpScreen extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private SignUpScreenViewModel signUpScreenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);


        signUpScreenViewModel = ViewModelProviders.of(this).get(SignUpScreenViewModel.class);

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpScreen.this, ResetPasswordScreen.class));
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getApplicationContext(), "Enter valid email address!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

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
                                    String token = stringResponse.headers().get("Token");
                                    JSONObject description = null;
                                    String message = null;
                                    try {
                                        description = new JSONObject(stringResponse.body());
                                        message = description.getString("desc");
                                        Log.e("register description", message);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Log.e("register header--->", stringResponse.headers() + "");
                                    Log.e("register header token", token);

                                    Toast.makeText(SignUpScreen.this, message,
                                            Toast.LENGTH_SHORT).show();

                                    PreferenceManager.getINSTANCE().writeToPref(SignUpScreen.this, PreferenceKeys.SP_APP_TOKEN, token);
                                    PreferenceManager.getINSTANCE().writeToPref(SignUpScreen.this, PreferenceKeys.SP_LOGGEDIN, true);
                                    PreferenceManager.getINSTANCE().writeToPref(SignUpScreen.this, PreferenceKeys.SP_EMAIL, inputEmail.getText().toString());
                                    startActivity(new Intent(SignUpScreen.this, ProfileScreen.class));
                                    finish();
                                } else if (status_code == 400) {
                                    Toast.makeText(SignUpScreen.this, "User already exist." ,
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignUpScreen.this, "Authentication failed." ,
                                            Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("register --->", e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                progressBar.setVisibility(View.GONE);

                            }
                        });


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
