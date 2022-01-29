package com.example.stroetype;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class SDCardEmail extends AppCompatActivity {
    Button BtnSave, BtnStore;
    EditText etEmail, etUser, etPassword;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_name);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etUser = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPassword);
        BtnSave = (Button) findViewById(R.id.btnSave);
        BtnStore = (Button) findViewById(R.id.btnRes);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
//الصلاحية لم يتم الحصول عليها
            Toast.makeText(getBaseContext(),"الصلاحية لم يتم الحصول عليها",Toast.LENGTH_LONG).show();

            String[] Permtions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this,Permtions,1);
        }

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkIfSdcardWritable())
                {
                    try {
                        File fileinPublic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                        File file = new File(fileinPublic, SameEmailClass.File_name);
                        file.createNewFile();
                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(getBaseContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }


    public boolean checkIfSdcardWritable()
    {
       String state= Environment.getExternalStorageState();
       if (state.equals(Environment.MEDIA_MOUNTED))
       {
           return  true;
       }
       return false;

    }

    public boolean checkIfSdcardRead()
    {
        String state= Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)|| state.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
        {
            return  true;
        }
        return false;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getBaseContext(),"تــــم الحصول علي الصلاحية",Toast.LENGTH_LONG).show();
                }
                return;
        }
    }
}
