package com.example.hamid.dhealth.ui.Activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast

import com.example.hamid.dhealth.R
import com.example.hamid.dhealth.data.Preference.PreferenceKeys
import com.example.hamid.dhealth.data.Preference.PreferenceManager
import com.example.hamid.dhealth.factory.ViewModelFactory
import com.example.hamid.dhealth.ui.viewmodel.AppViewModel

import org.json.JSONException
import org.json.JSONObject

import javax.inject.Inject

import dagger.android.AndroidInjection
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response


class LoginScreen : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var preferenceManager: PreferenceManager
    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var progressBar: ProgressBar? = null
    private var btnSignup: Button? = null
    private var btnLogin: Button? = null
    private var btnReset: Button? = null

    private var appViewModel: AppViewModel? = null
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        appViewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel::class.java)

        inputEmail = findViewById<View>(R.id.email) as EditText
        inputPassword = findViewById<View>(R.id.password) as EditText
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        btnSignup = findViewById<View>(R.id.btn_signup) as Button
        btnLogin = findViewById<View>(R.id.btn_login) as Button
        btnReset = findViewById<View>(R.id.btn_reset_password) as Button

        btnSignup!!.setOnClickListener {
            startActivity(Intent(this@LoginScreen, SignUpScreen::class.java))
            finish()
        }

        btnReset!!.setOnClickListener { startActivity(Intent(this@LoginScreen, ResetPasswordScreen::class.java)) }

        btnLogin!!.setOnClickListener(View.OnClickListener {
            val email = inputEmail!!.text.toString()
            val password = inputPassword!!.text.toString()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(applicationContext, "Enter valid email address!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }


            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            progressBar!!.visibility = View.VISIBLE


            val base = "$email:$password"
            Log.e("login base --->", base)

            val authHeader = "Basic " + Base64.encodeToString(base.toByteArray(), Base64.NO_WRAP)
            Log.e("login auth header --->", authHeader)

            disposable.add(
                    appViewModel!!.loginUser(authHeader)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(object : DisposableSingleObserver<Response<String>>() {

                                override fun onSuccess(stringResponse: Response<String>) {
                                    progressBar!!.visibility = View.GONE

                                    val status_code = stringResponse.code()
                                    Log.e("login status code --->", status_code.toString() + "")

                                    if (status_code == 200) {

                                        //hit the service if the response is 200 go for it
                                        val token = stringResponse.headers().get("Token")
                                        var response: JSONObject? = null
                                        var intent: Intent? = null
                                        var message: String? = null
                                        try {
                                            response = JSONObject(stringResponse.body())
                                            val res = response.optJSONObject("resp")

                                            message = res.optString("desc")
                                            Log.e("login description--->", message!! + "")

                                            val stream = response.optJSONObject("streams")
                                            Log.e("login streams--->", stream!!.toString() + "")

                                            if (stream != null) {

                                                val first_name = stream.optString("first_name")
                                                val last_name = stream.optString("last_name")
                                                val email = stream.optString("email")
                                                val date_of_birth = stream.optString("date_of_birth")
                                                val phone_number = stream.optString("phone_number")
                                                val address = stream.optString("address")
                                                val security = stream.optString("security")
                                                val profile_type = stream.optString("profile_type")
                                                val gender = stream.optString("gender")
                                                val dp = stream.optString("dp")
                                                val identity = stream.optString("_id")

                                                Log.e("login stream--->", first_name + "")

                                                intent = Intent(this@LoginScreen, DashboardScreen::class.java)

                                                preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_IDENTITY, identity)
                                                preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_NAME, first_name)
                                                preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_LAST_NAME, last_name)
                                                preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_EMAIL, email)
                                                preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_DOB, date_of_birth)
                                                preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_PHONENO, phone_number)
                                                preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_ADDRESS, address)
                                                preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_GENDER, gender)
                                                preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_ENCRYPTION, security)
                                                preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_PROFILE_TYPE, profile_type)
                                                preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_PROFILEPIC, dp)
                                                preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_PROFILEFINISHED, true)

                                            } else {
                                                intent = Intent(this@LoginScreen, ProfileScreen::class.java)
                                            }


                                        } catch (e: JSONException) {
                                            e.printStackTrace()
                                        }

                                        Log.e("login header--->", stringResponse.headers().toString() + "")
                                        Log.e("login header token--->", token!! + "")

                                        Toast.makeText(this@LoginScreen, message,
                                                Toast.LENGTH_SHORT).show()

                                        preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_APP_TOKEN, token)
                                        preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_LOGGEDIN, true)
                                        preferenceManager!!.writeToPref(this@LoginScreen, PreferenceKeys.SP_EMAIL, inputEmail!!.text.toString())
                                        startActivity(intent)
                                        finish()

                                    } else if (status_code == 400) {
                                        Toast.makeText(this@LoginScreen, "User already exist.$status_code",
                                                Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(this@LoginScreen, "Authentication failed.$status_code",
                                                Toast.LENGTH_SHORT).show()
                                    }

                                }


                                override fun onError(e: Throwable) {
                                    Log.e("login --->", e.message)
                                }

                            }))
        })
    }
}