package com.example.stroetype;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stroetype.Database.MyDataBase;
import com.example.stroetype.Moudle.MouldeCar;

import java.util.ArrayList;

public class SQLITEeMAIL extends AppCompatActivity {
    Button BtnSave, BtnStore;
    EditText etNamr, etColor, etDistance;
    MyDataBase MydataBase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_insert);
        etNamr = (EditText) findViewById(R.id.etCarName);
        etColor = (EditText) findViewById(R.id.etColor);
        etDistance = (EditText) findViewById(R.id.etDistanc);
        BtnSave = (Button) findViewById(R.id.btnSave);
        BtnStore = (Button) findViewById(R.id.btnRes);
        MydataBase= new MyDataBase(this);

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNumeric(etDistance.getText().toString()))
                {
                    String name=etNamr.getText().toString();
                    String Color=etColor.getText().toString();
                    double dpl=Double.parseDouble(etDistance.getText().toString());
                    MouldeCar car = new MouldeCar(name,Color,dpl);
                    boolean res= MydataBase.InsertCar(car);
                    if (res) {
                        Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_LONG).show();
                        etNamr.setText(null);
                        etColor.setText(null);
                        etDistance.setText(null);

                    } else {
                        Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();
                        etDistance.setText(null);
                    }

                }
                else {
                    Toast.makeText(getBaseContext(),"plase inset Number in Dasternce Field",Toast.LENGTH_LONG).show();

                }


            }
        });
        BtnStore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    ArrayList<MouldeCar> cars= MydataBase.getAllCarData();
                    Toast.makeText(getBaseContext(), "Number of Columen is " + MydataBase.GetNumberOfColumen()+" and that it mean index is " +cars.size(), Toast.LENGTH_LONG).show();
                    for (MouldeCar speiccar:cars)
                    {
                        Log.d("car info",speiccar.getName());
                       // System.out.println("car info"+speiccar.getName());
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });
    }


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}