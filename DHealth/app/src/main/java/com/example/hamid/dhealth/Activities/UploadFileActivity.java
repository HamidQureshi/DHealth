package com.example.hamid.dhealth.Activities;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hamid.dhealth.MedicalRepository.DB.Entity.Report;
import com.example.hamid.dhealth.MedicalRepository.DataRepository;
import com.example.hamid.dhealth.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UploadFileActivity extends AppCompatActivity {

    private static final String TAG = "UploadFileActivity";
    EditText et_name, et_title, et_uploaddate, et_assigned_to, et_signeddate, et_description, et_content;
    TextView tv_staus, tv_document;
    private static final int READ_REQUEST_CODE = 42;
    private static final int WRITE_REQUEST_CODE = 43;
    static Uri uri = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initLayout();
    }

    private void initLayout() {

        et_name = (EditText) findViewById(R.id.et_name);
        et_title = (EditText) findViewById(R.id.et_title);
        et_uploaddate = (EditText) findViewById(R.id.et_uploaddate);
        et_assigned_to = (EditText) findViewById(R.id.et_assigned_to);
        et_signeddate = (EditText) findViewById(R.id.et_signeddate);
        et_description = (EditText) findViewById(R.id.et_description);
        et_content = (EditText) findViewById(R.id.et_content);
        tv_staus = (TextView) findViewById(R.id.tv_staus);
        tv_document = (TextView) findViewById(R.id.tv_document);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void AddReport(View view) {

        //fetch the data and add it to list and add to activeledger

        String title = et_title.getText().toString();
        String name = et_name.getText().toString();
        String uploaddate = et_uploaddate.getText().toString();
        String assignedto = et_assigned_to.getText().toString();
        String signeddate = et_signeddate.getText().toString();
        String description = et_description.getText().toString();
        String content = et_content.getText().toString();
        String status = tv_staus.getText().toString();
        Report report = new Report(title, description, name, assignedto, uploaddate, signeddate, content, status);

        //add the report to db
        DataRepository dataRepository = DataRepository.getINSTANCE(getApplication());
        dataRepository.insertReport(report);

        finish();

    }

    public void uploadFILE(View view) {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

//        intent.setType("image/*");
        intent.setType("application/pdf");
//        intent.setType("*/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);


        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            if (resultData != null) {
                uri = resultData.getData();
                Log.i(TAG, "Uri: " + uri.toString());

                if (null != uri) {

                    try {
                        tv_document.setText(getFileName(uri));

                        Log.i(TAG, "FileContent: " + readTextFromUri(uri));
                        openPDF(uri);

                        //convert the contents to base 64
                        String base64File = com.example.hamid.dhealth.FileUtils.getBase64FromURI(this, uri);
                        Log.e(TAG, "file--> " + base64File);

                        //convert back from base 64
                        //create a pdf file with diff name
                        saveFile(base64File);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public String getPDFPath(Uri uri) {

        final String id = DocumentsContract.getDocumentId(uri);
        final Uri contentUri = ContentUris.withAppendedId(
                Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void saveFile(String base64File) {

        Log.e("--------->", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/testing" + ".pdf");

        final File pdfFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/testing" + ".pdf");
        byte[] pdfAsBytes = Base64.decode(base64File, 0);
        FileOutputStream os;
        try {
            os = new FileOutputStream(pdfFile, false);
            os.write(pdfAsBytes);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void openPDF(Uri uri) {
        Intent intent = new Intent(this, PDFViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("URI", uri.toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }


    private String readTextFromUri(Uri uri) throws IOException {

        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        FileInputStream fileInputStream =
                new FileInputStream(parcelFileDescriptor.getFileDescriptor());

        InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        fileInputStream.close();
        parcelFileDescriptor.close();
        return stringBuilder.toString();
    }


}
