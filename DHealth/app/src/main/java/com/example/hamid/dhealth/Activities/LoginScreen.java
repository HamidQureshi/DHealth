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
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginScreen.this, ProfileScreen.class));
            finish();
        }

        setContentView(R.layout.activity_login);


        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreen.this, SignUpScreen.class));
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
                                    JSONObject description = null;
                                    String message = null;
                                    try {
                                        description = new JSONObject(stringResponse.body());
                                        message = description.getString("desc");
                                        Log.e("login description--->", message );

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Log.e("login header--->", stringResponse.headers() + "");
                                    Log.e("login header token--->", token);

                                    Toast.makeText(LoginScreen.this, message,
                                            Toast.LENGTH_SHORT).show();

                                    PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_APP_TOKEN, token);
                                    PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_LOGGEDIN, true);
                                    PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_EMAIL, inputEmail.getText().toString());
                                    Intent intent = new Intent(LoginScreen.this, ProfileScreen.class);
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


                //authenticate user
//                auth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                // If sign in fails, display a message to the user. If sign in succeeds
//                                // the auth state listener will be notified and logic to handle the
//                                // signed in user can be handled in the listener.
//                                progressBar.setVisibility(View.GONE);
//                                if (!task.isSuccessful()) {
//                                    // there was an error
//                                    if (password.length() < 6) {
//                                        inputPassword.setError(getString(R.string.minimum_password));
//                                    } else {
//                                        Toast.makeText(LoginScreen.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
//                                    }
//                                } else {
//
//                                    PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this, PreferenceKeys.SP_LOGGEDIN, true);
//
//                                    PreferenceManager.getINSTANCE().writeToPref(LoginScreen.this,PreferenceKeys.SP_EMAIL,inputEmail.getText().toString());
//
//                                    Intent intent = new Intent(LoginScreen.this, ProfileScreen.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            }
//                        });
            }
        });
    }
}