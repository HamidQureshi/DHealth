package com.example.hamid.dhealth.Fragments;

import android.Manifest;
import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.activeledgersdk.ActiveLedgerSDK;
import com.example.activeledgersdk.utility.Utility;
import com.example.hamid.dhealth.ActiveLedgerHelper;
import com.example.hamid.dhealth.Activities.DashboardScreen;
import com.example.hamid.dhealth.MedicalRepository.DataRepository;
import com.example.hamid.dhealth.MedicalRepository.HTTP.HttpClient;
import com.example.hamid.dhealth.Preference.PreferenceKeys;
import com.example.hamid.dhealth.Preference.PreferenceManager;
import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.SplashActivity;
import com.example.hamid.dhealth.Utils.ImageUtils;
import com.example.hamid.dhealth.Utils.Utils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import belka.us.androidtoggleswitch.widgets.BaseToggleSwitch;
import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    EditText et_name, et_last_name, et_email, et_dob, et_phone, et_address;
    ImageView iv_camera, iv_dp;
    Button btn_submit, btn_logout;
    private ProfileViewModel mViewModel;
    private DatePickerDialog datePickerDialog;
    private String profile_type = PreferenceKeys.LBL_DOCTOR;
    private String gender = PreferenceKeys.LBL_MALE;
    private String encryption = PreferenceKeys.LBL_RSA;
    private Disposable disposable;
    private ProgressBar progressBar;


    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);


        initLayouts();
    }


    private void initLayouts() {

        et_dob = (EditText) getView().findViewById(R.id.et_dob);
        et_dob.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            et_dob.setShowSoftInputOnFocus(false);
        }
        prepareDatePickerDialog();

        ToggleSwitch dp_toggleSwitch = (ToggleSwitch) getView().findViewById(R.id.dp_toggle);
        ArrayList<String> dp_labels = new ArrayList<>();
        dp_labels.add(PreferenceKeys.LBL_DOCTOR);
        dp_labels.add(PreferenceKeys.LBL_PATIENT);
        dp_toggleSwitch.setLabels(dp_labels);
        dp_toggleSwitch.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener() {

            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {

                if (position == 0)
                    profile_type = PreferenceKeys.LBL_DOCTOR;
                else
                    profile_type = PreferenceKeys.LBL_PATIENT;
            }
        });


        ToggleSwitch gender_toggleSwitch = (ToggleSwitch) getView().findViewById(R.id.gender_toggle);
        ArrayList<String> gender_labels = new ArrayList<>();
        gender_labels.add(PreferenceKeys.LBL_MALE);
        gender_labels.add(PreferenceKeys.LBL_FEMALE);
        gender_toggleSwitch.setLabels(gender_labels);
        gender_toggleSwitch.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener() {

            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {

                if (position == 0)
                    gender = PreferenceKeys.LBL_MALE;
                else
                    gender = PreferenceKeys.LBL_FEMALE;
            }
        });

        ToggleSwitch encryption_toggleSwitch = (ToggleSwitch) getView().findViewById(R.id.encryption_toggle);
        ArrayList<String> encryption_labels = new ArrayList<>();
        encryption_labels.add(PreferenceKeys.LBL_RSA);
        encryption_labels.add(PreferenceKeys.LBL_EC);
        encryption_toggleSwitch.setLabels(encryption_labels);
        encryption_toggleSwitch.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener() {

            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {

                if (position == 0)
                    encryption = PreferenceKeys.LBL_RSA;
                else
                    encryption = PreferenceKeys.LBL_EC;
            }
        });

        et_name = (EditText) getView().findViewById(R.id.et_name);
        et_last_name = (EditText) getView().findViewById(R.id.et_last_name);
        et_email = (EditText) getView().findViewById(R.id.et_email);
        et_phone = (EditText) getView().findViewById(R.id.et_phone);
        et_address = (EditText) getView().findViewById(R.id.et_address);

        btn_submit = (Button) getView().findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        btn_logout = (Button) getView().findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);

        iv_camera = (ImageView) getView().findViewById(R.id.iv_camera);
        iv_camera.setOnClickListener(this);
        iv_dp = (ImageView) getView().findViewById(R.id.iv_dp);
        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);

        fetchDataFromPref();
    }

    private void prepareDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear++;
                et_dob.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                datePickerDialog.dismiss();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_dob:
                Utils.hideKeyboard(getActivity());
                if(et_dob.isFocusable() && et_dob.isFocusableInTouchMode()) {
                    datePickerDialog.show();
                }
                break;

            case R.id.btn_submit:
                Utils.hideKeyboard(getActivity());

                progressBar.setVisibility(View.VISIBLE);
                updateUserTransaction(et_name.getText().toString(), et_last_name.getText().toString(), et_dob.getText().toString(), et_phone.getText().toString(),
                        et_address.getText().toString(), PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_PROFILEPIC, ""), et_email.getText().toString());

                break;

            case R.id.btn_logout:
                Utils.hideKeyboard(getActivity());
                showAlertDialog();
                break;

            case R.id.iv_camera:
                Utils.hideKeyboard(getActivity());
                takePhoto(v);
                break;
        }
    }

    public void fetchDataFromPref() {
        et_name.setText(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_NAME, "JOHN "));
        et_last_name.setText(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_LAST_NAME, "DOE"));
        et_email.setText(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_EMAIL, "JOHN DOE"));
        et_dob.setText(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_DOB, "JOHN DOE"));
        et_phone.setText(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_PHONENO, "JOHN DOE"));
        et_address.setText(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_ADDRESS, "JOHN DOE"));
        iv_dp.setImageBitmap(Utils.decodeBase64(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_PROFILEPIC, "")));
    }

    private void updatePref() {
        PreferenceManager.getINSTANCE().writeToPref(getActivity(), PreferenceKeys.SP_NAME, et_name.getText().toString());
        PreferenceManager.getINSTANCE().writeToPref(getActivity(), PreferenceKeys.SP_LAST_NAME, et_last_name.getText().toString());
        PreferenceManager.getINSTANCE().writeToPref(getActivity(), PreferenceKeys.SP_EMAIL, et_email.getText().toString());
        PreferenceManager.getINSTANCE().writeToPref(getActivity(), PreferenceKeys.SP_DOB, et_dob.getText().toString());
        PreferenceManager.getINSTANCE().writeToPref(getActivity(), PreferenceKeys.SP_PHONENO, et_phone.getText().toString());
        PreferenceManager.getINSTANCE().writeToPref(getActivity(), PreferenceKeys.SP_ADDRESS, et_address.getText().toString());
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.edit_options_menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.edit) {

            Toast.makeText(getActivity(), "Profile Editing Enabled", Toast.LENGTH_SHORT).show();

            btn_submit.setVisibility(View.VISIBLE);
            btn_logout.setVisibility(View.VISIBLE);

            et_name.setFocusable(true);
            et_name.setFocusableInTouchMode(true);

            et_last_name.setFocusable(true);
            et_last_name.setFocusableInTouchMode(true);

            et_address.setFocusable(true);
            et_address.setFocusableInTouchMode(true);

            et_dob.setFocusable(true);
            et_dob.setFocusableInTouchMode(true);

            et_phone.setFocusable(true);
            et_phone.setFocusableInTouchMode(true);

            iv_camera.setVisibility(View.VISIBLE);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void takePhoto(View view) {

        final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Select Option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    dialog.dismiss();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA},
                                    MY_CAMERA_PERMISSION_CODE);
                        } else {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, 0);
                        }
                    }

                } else if (options[item].equals("Choose From Gallery")) {
                    dialog.dismiss();

                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    photo = ImageUtils.scaleDownBitmap(photo, getActivity());
                    iv_dp.setImageBitmap(photo);
                    PreferenceManager.getINSTANCE().writeToPref(getActivity(), PreferenceKeys.SP_PROFILEPIC, Utils.encodeTobase64(photo));
                    ((DashboardScreen) getActivity()).updateDrawerDP();
                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        Bitmap photo = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        photo = ImageUtils.scaleDownBitmap(photo, getActivity());
                        PreferenceManager.getINSTANCE().writeToPref(getActivity(), PreferenceKeys.SP_PROFILEPIC, Utils.encodeTobase64(photo));
                        ((DashboardScreen) getActivity()).updateDrawerDP();
                        iv_dp.setImageBitmap(photo);
                    } catch (IOException e) {
                        iv_dp.setImageURI(selectedImage);
                        e.printStackTrace();
                    }
                }
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_SHORT).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 0);
            } else {
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Are you sure you want to Logout?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //delete preferences
                        PreferenceManager.getINSTANCE().clearPreferences(getActivity());
                        //delete db
                        DataRepository repository = DataRepository.getINSTANCE(getActivity().getApplication());
                        repository.deleteAllDoctor();
                        repository.deleteAllPatient();
                        repository.deleteAllReport();

                        //back to splash screen
                        Intent intent = new Intent(getActivity(), SplashActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        dialog.cancel();
                    }
                });

        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog logoutAlert = builder.create();

        logoutAlert.show();
    }


    public void updateUserTransaction(String first_name, String last_name,
                                      String date_of_birth, String phone_number, String address, String dp, String email) {


        ActiveLedgerSDK.KEYNAME = ActiveLedgerHelper.getInstance().getKeyname();
        ActiveLedgerSDK.getInstance().setKeyType(ActiveLedgerHelper.getInstance().getKeyType());


        JSONObject updateUserTransaction = ActiveLedgerHelper.getInstance().createUpdateUserTransaction(null, ActiveLedgerHelper.getInstance().getKeyType(), first_name, last_name,
                date_of_birth, phone_number, address, dp, email);

        String transactionString = Utility.getInstance().convertJSONObjectToString(updateUserTransaction);

        Utils.Log("UpdateUser Transaction", transactionString);
        Log.e("UpdateUser token", com.example.hamid.dhealth.Preference.PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_APP_TOKEN, "null"));

        HttpClient.getInstance().sendTransaction(com.example.hamid.dhealth.Preference.PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_APP_TOKEN, "null"), transactionString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onNext(Response<String> response) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("UpdateUser response--->", response.code() + "");
                        if (response.code() == 200) {
                            updatePref();
                            Utils.Log("UpdateUser response--->", response.body() + "");
                            iv_camera.setVisibility(View.INVISIBLE);
                            btn_submit.setVisibility(View.INVISIBLE);
//                            btn_logout.setVisibility(View.INVISIBLE);

                            et_name.setFocusable(false);
                            et_name.setClickable(false);
                            et_name.setFocusableInTouchMode(false);

                            et_last_name.setFocusable(false);
                            et_last_name.setFocusableInTouchMode(false);

                            et_address.setFocusable(false);
                            et_address.setFocusableInTouchMode(false);

                            et_dob.setFocusable(false);
                            et_dob.setFocusableInTouchMode(false);

                            et_phone.setFocusable(false);
                            et_phone.setFocusableInTouchMode(false);

                            ((DashboardScreen) getActivity()).refreshDP();

                            Toast.makeText(getActivity(), "User Updated Successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "User Updated Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
