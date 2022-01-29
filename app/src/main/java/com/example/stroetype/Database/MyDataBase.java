package com.example.stroetype.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.stroetype.Moudle.MouldeCar;

import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper {

    static  public final String DatabaseName="CaresDB";
     static  public final int DatabaseVersion=1;
    static public final String NameOfTable="CareInfo";
     static public  final String ColumnID="CarID";
     static public  final String ColumnName="NameOfCar";
    static public  final String ColumnColor="ColoOfCar";
    static public  final String ColumnDistance="DistanceTerval";
    public  MyDataBase(Context context)
    {
        super(context,DatabaseName,null,DatabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Qurey="CREATE TABLE "+ NameOfTable +" (" +ColumnID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ ColumnName+ " TEXT, "+ ColumnColor + " TEXT ,"+ ColumnDistance +" REAL);";
        sqLiteDatabase.execSQL(Qurey);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String Qurey="DROP TABLE IF EXISTS car";
        sqLiteDatabase.execSQL(Qurey);
        onCreate(sqLiteDatabase);

    }

    public boolean InsertCar(MouldeCar car)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ColumnName,car.getName());
        values.put(ColumnColor,car.getColor());
        values.put(ColumnDistance,car.getDpl());
        //long couse insert method return long number if insert done but if not return -1
       long result= sqLiteDatabase.insert(NameOfTable,null,values);
       return result!=-1;
    }

    public boolean UpdateCar(MouldeCar car)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ColumnName,car.getName());
        values.put(ColumnColor,car.getColor());
        values.put(ColumnDistance,car.getDpl());
        String Args[]= {""+car.getId()};
        //int cause update Method retrung number of update Columens if there no Columen Update that means Return 0
        int result= sqLiteDatabase.update(NameOfTable,values,ColumnID+"=?",Args);
        return result>0;
    }

    public long GetNumberOfColumen()
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
       return DatabaseUtils.queryNumEntries(sqLiteDatabase,NameOfTable);
    }
    public boolean DeleteCar(MouldeCar car)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();


        String Args[]= {""+car.getId()};
        //int cause update Method retrung number of update Columens if there no Columen Update that means Return 0
        int result= sqLiteDatabase.delete(NameOfTable,ColumnID+"=?",Args);
        return result>0;
    }

    public ArrayList<MouldeCar> getAllCarData(){
        ArrayList<MouldeCar> cars= new ArrayList<MouldeCar>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String Sql="SELECT * FROM "+NameOfTable +" ;";
        Cursor cursor= sqLiteDatabase.rawQuery(Sql,null);

        if(cursor.moveToFirst())
        {
            do {
            @SuppressLint("Range") int id=cursor.getInt(cursor.getColumnIndex(ColumnID));
                @SuppressLint("Range") String Name=cursor.getString(cursor.getColumnIndex(ColumnName));
               @SuppressLint("Range") String Color=cursor.getString(cursor.getColumnIndex(ColumnColor));
                @SuppressLint("Range") double dpl=cursor.getDouble(cursor.getColumnIndex(ColumnDistance));
                cars.add(
                        new MouldeCar(id, Name, Color,dpl )
                );
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return  cars;

    }
}
