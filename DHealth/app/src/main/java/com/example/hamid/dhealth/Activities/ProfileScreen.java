package com.example.hamid.dhealth.Activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.activeledgersdk.utility.KeyType;
import com.example.hamid.dhealth.ActiveLedgerHelper;
import com.example.hamid.dhealth.Preference.PreferenceKeys;
import com.example.hamid.dhealth.Preference.PreferenceManager;
import com.example.hamid.dhealth.R;
import com.example.hamid.dhealth.Utils.ImageUtils;
import com.example.hamid.dhealth.Utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

public class ProfileScreen extends AppCompatActivity implements View.OnClickListener {

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    EditText et_dob, et_name, et_last_name, et_email, et_phone, et_address;
    Button btn_submit;
    ImageView iv_camera, iv_dp;
    private DatePickerDialog datePickerDialog;
    private String profile_type = PreferenceKeys.LBL_DOCTOR;
    private String gender = PreferenceKeys.LBL_MALE;
    private String encryption = PreferenceKeys.LBL_RSA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        initLayouts();

    }


    private void initLayouts() {

        et_dob = (EditText) findViewById(R.id.et_dob);
        et_dob.setOnClickListener(this);
        prepareDatePickerDialog();

        et_name = (EditText) findViewById(R.id.et_name);
        et_last_name = (EditText) findViewById(R.id.et_last_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_email.setText(PreferenceManager.getINSTANCE().readFromPref(this, PreferenceKeys.SP_EMAIL, "null"));
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_address = (EditText) findViewById(R.id.et_address);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        iv_camera = (ImageView) findViewById(R.id.iv_camera);
        iv_camera.setOnClickListener(this);
        iv_dp = (ImageView) findViewById(R.id.iv_dp);


        ToggleSwitch dp_toggleSwitch = (ToggleSwitch) findViewById(R.id.dp_toggle);
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

        ToggleSwitch gender_toggleSwitch = (ToggleSwitch) findViewById(R.id.gender_toggle);
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


        ToggleSwitch encryption_toggleSwitch = (ToggleSwitch) findViewById(R.id.encryption_toggle);
        ArrayList<String> encryption_labels = new ArrayList<>();
        encryption_labels.add(PreferenceKeys.LBL_RSA);
        encryption_labels.add(PreferenceKeys.LBL_EC);
        encryption_toggleSwitch.setLabels(encryption_labels);
        encryption_toggleSwitch.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener() {

            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {

                if (position == 0) {
                    encryption = PreferenceKeys.LBL_RSA;
                    ActiveLedgerHelper.getInstance().setKeyType(KeyType.RSA);
                } else {
                    encryption = PreferenceKeys.LBL_EC;
                    ActiveLedgerHelper.getInstance().setKeyType(KeyType.EC);
                }
            }
        });

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        iv_camera = (ImageView) findViewById(R.id.iv_camera);
        iv_camera.setOnClickListener(this);
        iv_dp = (ImageView) findViewById(R.id.iv_dp);


    }

    private void prepareDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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
                //do req to the ledger onboarding and upload data to the ledger
                ActiveLedgerHelper.getInstance().setupALSDK(getApplicationContext());

//                ActiveLedgerHelper.getInstance().generatekeys("hamid", "mehmood", "hamid@agilitysciences.com",
//                        "24/07/1994", "07400633866", "queen mary", "RSA", "Doctor", "Male","dp");

                ActiveLedgerHelper.getInstance().generatekeys(this, et_name.getText().toString(), et_last_name.getText().toString(), et_email.getText().toString(),
                        et_dob.getText().toString(), et_phone.getText().toString(), et_address.getText().toString(), encryption, profile_type,
                        gender,
                        PreferenceManager.getINSTANCE().readFromPref(this, PreferenceKeys.SP_PROFILEPIC, "null"),true,false);

                break;

            case R.id.iv_camera:
                takePhoto(v);
                break;
        }
    }

    public void submitProfile() {

        updatePref();
        Intent intent = new Intent(this, DashboardScreen.class);
        startActivity(intent);
        finish();

    }

    private void updatePref() {
        PreferenceManager.getINSTANCE().writeToPref(this, PreferenceKeys.SP_NAME, et_name.getText().toString());
        PreferenceManager.getINSTANCE().writeToPref(this, PreferenceKeys.SP_LAST_NAME, et_last_name.getText().toString());
        PreferenceManager.getINSTANCE().writeToPref(this, PreferenceKeys.SP_EMAIL, et_email.getText().toString());
        PreferenceManager.getINSTANCE().writeToPref(this, PreferenceKeys.SP_DOB, et_dob.getText().toString());
        PreferenceManager.getINSTANCE().writeToPref(this, PreferenceKeys.SP_PHONENO, et_phone.getText().toString());
        PreferenceManager.getINSTANCE().writeToPref(this, PreferenceKeys.SP_ADDRESS, et_address.getText().toString());
        PreferenceManager.getINSTANCE().writeToPref(this, PreferenceKeys.SP_GENDER, gender);
        PreferenceManager.getINSTANCE().writeToPref(this, PreferenceKeys.SP_ENCRYPTION, encryption);
        PreferenceManager.getINSTANCE().writeToPref(this, PreferenceKeys.SP_PROFILE_TYPE, profile_type);
        PreferenceManager.getINSTANCE().writeToPref(this, PreferenceKeys.SP_PROFILEFINISHED, true);
    }


    public void takePhoto(View view) {

        final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("Select Option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    dialog.dismiss();


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA)
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
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");

                    photo = ImageUtils.scaleDownBitmap(photo, this);

                    iv_dp.setImageBitmap(photo);

                    PreferenceManager.getINSTANCE().writeToPref(this, PreferenceKeys.SP_PROFILEPIC, Utils.encodeTobase64(photo));
                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        Bitmap photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        photo = ImageUtils.scaleDownBitmap(photo, this);
                        iv_dp.setImageBitmap(photo);
                        PreferenceManager.getINSTANCE().writeToPref(this, PreferenceKeys.SP_PROFILEPIC, Utils.encodeTobase64(photo));

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
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 0);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }

}
