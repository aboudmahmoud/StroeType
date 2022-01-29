package com.example.stroetype;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SameEmailClass extends AppCompatActivity {
    Button BtnSave, BtnStore;
    EditText etEmail, etUser, etPassword;
    public   static  final  String File_name="Users";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_name);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etUser = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPassword);
        BtnSave = (Button) findViewById(R.id.btnSave);
        BtnStore = (Button) findViewById(R.id.btnRes);
        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=etUser.getText().toString();
                String email=etEmail.getText().toString();
                String password=etPassword.getText().toString();
                try {
                    FileOutputStream fileOutputStream = openFileOutput(File_name, MODE_PRIVATE);
                    PrintWriter pw=new PrintWriter(fileOutputStream);
                    pw.println(username+","+email+","+password);
                    pw.close();
                    fileOutputStream.close();
                }
                catch (Exception ex)
                {
                    Toast.makeText(getBaseContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        BtnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fio=openFileInput(File_name);
                    InputStreamReader ISR=new InputStreamReader(fio);
                    BufferedReader br = new BufferedReader(ISR);
                    String alltext="";
                    String temp="";
                    while ((temp=br.readLine())!=null)
                    {
                        alltext=alltext+temp;

                    }
                    br.close();
                    ISR.close();
                    fio.close();
                    Toast.makeText(getBaseContext(),alltext,Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
