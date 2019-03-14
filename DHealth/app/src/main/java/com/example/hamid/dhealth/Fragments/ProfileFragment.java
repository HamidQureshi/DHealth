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
import android.widget.Toast;

import com.example.hamid.dhealth.Preference.PreferenceKeys;
import com.example.hamid.dhealth.Preference.PreferenceManager;
import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.Utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    static String lbl_doctor = "Doctor";
    static String lbl_patient = "TablePatient";
    static String lbl_gender_male = "Male";
    static String lbl_gender_female = "Female";
    static String lbl_encryption_RSA = "RSA";
    static String lbl_encryption_EC = "Elliptic Curve";
    EditText et_name, et_email, et_dob, et_phone;
    ImageView iv_camera, iv_dp;
    Button btn_submit;
    private ProfileViewModel mViewModel;
    private DatePickerDialog datePickerDialog;
    private String profession = "Doctor";
    private String gender = "Male";
    private String encryption = "RSA";

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
        prepareDatePickerDialog();

        ToggleSwitch dp_toggleSwitch = (ToggleSwitch) getView().findViewById(R.id.dp_toggle);
        ArrayList<String> dp_labels = new ArrayList<>();
        dp_labels.add(lbl_doctor);
        dp_labels.add(lbl_patient);
        dp_toggleSwitch.setLabels(dp_labels);
        dp_toggleSwitch.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener() {

            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {

                if (position == 0)
                    profession = lbl_doctor;
                else
                    profession = lbl_patient;

            }
        });


        ToggleSwitch gender_toggleSwitch = (ToggleSwitch) getView().findViewById(R.id.gender_toggle);
        ArrayList<String> gender_labels = new ArrayList<>();
        gender_labels.add(lbl_gender_male);
        gender_labels.add(lbl_gender_female);
        gender_toggleSwitch.setLabels(gender_labels);
        gender_toggleSwitch.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener() {

            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {

                if (position == 0)
                    gender = lbl_gender_male;
                else
                    gender = lbl_gender_female;

            }
        });


        ToggleSwitch encryption_toggleSwitch = (ToggleSwitch) getView().findViewById(R.id.encryption_toggle);
        ArrayList<String> encryption_labels = new ArrayList<>();
        encryption_labels.add(lbl_encryption_RSA);
        encryption_labels.add(lbl_encryption_EC);
        encryption_toggleSwitch.setLabels(encryption_labels);
        encryption_toggleSwitch.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener() {

            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {

                if (position == 0)
                    encryption = lbl_encryption_RSA;
                else
                    encryption = lbl_encryption_EC;

            }
        });


        et_name = (EditText) getView().findViewById(R.id.et_name);
        et_email = (EditText) getView().findViewById(R.id.et_email);
        et_phone = (EditText) getView().findViewById(R.id.et_phone);


        btn_submit = (Button) getView().findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        iv_camera = (ImageView) getView().findViewById(R.id.iv_camera);
        iv_camera.setOnClickListener(this);
        iv_dp = (ImageView) getView().findViewById(R.id.iv_dp);


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
                datePickerDialog.show();
                break;

            case R.id.btn_submit:

                updatePref();

                Log.e("===>", "submit");

                //do req to the ledger onboarding and upload data to the ledger

                break;

            case R.id.iv_camera:
                Log.e("===>", "camera");
                takePhoto(v);
                break;
        }
    }

    public void fetchDataFromPref() {

        et_name.setText(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_NAME, "JOHN DOE"));
        et_email.setText(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_EMAIL, "JOHN DOE"));
        et_dob.setText(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_DOB, "JOHN DOE"));
        et_phone.setText(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_PHONENO, "JOHN DOE"));
        iv_dp.setImageBitmap(Utils.decodeBase64(PreferenceManager.getINSTANCE().readFromPref(getActivity(), PreferenceKeys.SP_PROFILEPIC, "")));

    }

    private void updatePref() {
        PreferenceManager.getINSTANCE().writeToPref(getActivity(), PreferenceKeys.SP_NAME, et_name.getText().toString());
        PreferenceManager.getINSTANCE().writeToPref(getActivity(), PreferenceKeys.SP_EMAIL, et_email.getText().toString());
        PreferenceManager.getINSTANCE().writeToPref(getActivity(), PreferenceKeys.SP_DOB, et_dob.getText().toString());
        PreferenceManager.getINSTANCE().writeToPref(getActivity(), PreferenceKeys.SP_PHONENO, et_phone.getText().toString());
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
            Toast.makeText(getActivity(), "Action clicked", Toast.LENGTH_LONG).show();
            et_name.setFocusable(true);
            et_name.setFocusableInTouchMode(true);

//            et_email.setFocusable(true);
//            et_email.setFocusableInTouchMode(true);

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
                    iv_dp.setImageBitmap(photo);
                    PreferenceManager.getINSTANCE().writeToPref(getActivity(), PreferenceKeys.SP_PROFILEPIC, Utils.encodeTobase64(photo));
                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        Bitmap photo = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        iv_dp.setImageBitmap(photo);
                        PreferenceManager.getINSTANCE().writeToPref(getActivity(), PreferenceKeys.SP_PROFILEPIC, Utils.encodeTobase64(photo));
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
                Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 0);
            } else {
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }


}
