package com.example.stroetype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btn_save,btn_stroe;
    SharedPreferences.Editor edit;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_save=(Button)findViewById(R.id.btn_save);
        btn_stroe=(Button)findViewById(R.id.Store);
        //this frist way
        // sp= PreferenceManager.getDefaultSharedPreferences(this);
        sp=getSharedPreferences("Names",MODE_PRIVATE);
         edit=sp.edit();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.putString("Name","aboud mahmoud elsyed");
                edit.apply();
            }
        });

        btn_stroe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = sp.getString("Name","Not Found");
                Toast.makeText(getBaseContext(),name,Toast.LENGTH_LONG).show();

            }
        });


    }
}