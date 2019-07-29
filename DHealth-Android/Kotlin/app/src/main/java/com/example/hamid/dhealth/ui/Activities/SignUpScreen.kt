package com.example.hamid.dhealth.ui.Activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.activeledgersdk.utility.Utility
import com.example.hamid.dhealth.R
import com.example.hamid.dhealth.data.Preference.PreferenceKeys
import com.example.hamid.dhealth.data.Preference.PreferenceManager
import com.example.hamid.dhealth.factory.ViewModelFactory
import com.example.hamid.dhealth.ui.viewmodel.AppViewModel
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


class SignUpScreen : AppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var preferenceManager: PreferenceManager
    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var btnSignIn: Button? = null
    private var btnSignUp: Button? = null
    private var btnResetPassword: Button? = null
    private var progressBar: ProgressBar? = null

    private var appViewModel: AppViewModel? = null
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)
        appViewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel::class.java)

        btnSignIn = findViewById<View>(R.id.sign_in_button) as Button
        btnSignUp = findViewById<View>(R.id.sign_up_button) as Button
        inputEmail = findViewById<View>(R.id.email) as EditText
        inputPassword = findViewById<View>(R.id.password) as EditText
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar
        btnResetPassword = findViewById<View>(R.id.btn_reset_password) as Button

        btnResetPassword!!.setOnClickListener {
            startActivity(Intent(this@SignUpScreen, ResetPasswordScreen::class.java))
            finish()
        }

        btnSignIn!!.setOnClickListener {
            startActivity(Intent(this@SignUpScreen, LoginScreen::class.java))
            finish()
        }

        btnSignUp!!.setOnClickListener(View.OnClickListener {
            val email = inputEmail!!.text.toString().trim { it <= ' ' }
            val password = inputPassword!!.text.toString().trim { it <= ' ' }

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

            if (password.length < 6) {
                Toast.makeText(applicationContext, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            progressBar!!.visibility = View.VISIBLE

            val userJSON = JSONObject()

            try {
                userJSON.put("username", email)
                userJSON.put("password", password)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            val userString = Utility.getInstance().convertJSONObjectToString(userJSON)

            disposable.add(
                    appViewModel!!.registerUser(userString)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(object : DisposableSingleObserver<Response<String>>() {

                                override fun onSuccess(stringResponse: Response<String>) {
                                    progressBar!!.visibility = View.GONE

                                    val status_code = stringResponse.code()
                                    Log.e("register --->", status_code.toString() + "")

                                    if (status_code == 200) {

                                        //hit the service if the response is 200 go for it
                                        val token = stringResponse.headers().get("Token")
                                        var description: JSONObject? = null
                                        var message: String? = null
                                        try {
                                            description = JSONObject(stringResponse.body())
                                            message = description.getString("desc")
                                            Log.e("register description", message)

                                        } catch (e: JSONException) {
                                            e.printStackTrace()
                                        }

                                        Log.e("register header--->", stringResponse.headers().toString() + "")
                                        Log.e("register header token", token)

                                        Toast.makeText(this@SignUpScreen, message,
                                                Toast.LENGTH_SHORT).show()

                                        preferenceManager!!.writeToPref(this@SignUpScreen, PreferenceKeys.SP_APP_TOKEN, token!!)
                                        preferenceManager!!.writeToPref(this@SignUpScreen, PreferenceKeys.SP_LOGGEDIN, true)
                                        preferenceManager!!.writeToPref(this@SignUpScreen, PreferenceKeys.SP_EMAIL, inputEmail!!.text.toString())
                                        startActivity(Intent(this@SignUpScreen, ProfileScreen::class.java))
                                        finish()
                                    } else if (status_code == 400) {
                                        Toast.makeText(this@SignUpScreen, "User already exist.",
                                                Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(this@SignUpScreen, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show()
                                    }

                                }

                                override fun onError(e: Throwable) {
                                    Log.e("register --->", e.message)
                                }

                            }))
        })
    }

    override fun onResume() {
        super.onResume()
        progressBar!!.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
