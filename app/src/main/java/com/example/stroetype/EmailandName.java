package com.example.stroetype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailandName extends AppCompatActivity {

    Button BtnSave,BtnStore;
    EditText etEmail,etUser,etPassword;
    SharedPreferences.Editor edit;
    SharedPreferences sp;
    public final String User_key="username", Email_key="email",Password_key="password",ErrorMessg="Not Found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_name);
        etEmail= (EditText) findViewById(R.id.etEmail);
        etUser= (EditText) findViewById(R.id.etUser);
        etPassword= (EditText) findViewById(R.id.etPassword);
        BtnSave= (Button) findViewById(R.id.btnSave);
        BtnStore=(Button) findViewById(R.id.btnRes);
        sp= PreferenceManager.getDefaultSharedPreferences(this);
        edit=sp.edit();

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveDatae();
            }
        });

        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdata();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SaveDatae();

    }



    public  void SaveDatae()
    {
        String username=etUser.getText().toString();
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();
        edit.putString(User_key,username);
        edit.putString(Email_key,email);
        edit.putString(Password_key,password);
        edit.apply();

    }
    public void  showdata()
    {
        etEmail.setText(sp.getString(Email_key,ErrorMessg));
        etUser.setText(sp.getString(User_key,ErrorMessg));
        etPassword.setText(sp.getString(Password_key,ErrorMessg));
    }
}