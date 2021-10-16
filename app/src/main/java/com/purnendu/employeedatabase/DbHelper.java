package com.purnendu.employeedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NewsDatabase";
    private final Context context;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table Employee "+
                        "(_id integer primary key autoincrement,_no integer , _name text,_age integer,_designation text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Employee" );
        onCreate(db);
    }


    public boolean insertEmployeeDetails (int employeeNo,String name,int age,String designation) {

        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("_no", employeeNo);
            contentValues.put("_name", name);
            contentValues.put("_age", age);
            contentValues.put("_designation", designation);
            db.insert("Employee", null, contentValues);
        }
        catch (SQLiteException e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    ArrayList<Model>getAllData()
    {
        ArrayList<Model> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from "+"Employee", null );
        if (res.moveToFirst())
        {
            do {
                int id = Integer.parseInt(res.getString(0));
                int employeeNo = res.getInt(1);
                String name= res.getString(2);
                int age=res.getInt(3);
                String designation=res.getString(4);
                array_list.add(new Model(name,designation,id,employeeNo,age));
            } while (res.moveToNext());
        }
        res.close();
        return array_list;
    }

    public boolean deleteData(int id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM "+"Employee"+ " WHERE " +"_id ="+id);
            return true;
        }
        catch (SQLiteException e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public ArrayList<Model> searchData(int id) {
        ArrayList<Model> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from "+"Employee where _id="+id, null );
        if (res.moveToFirst())
        {
            do {
                int iD = Integer.parseInt(res.getString(0));
                int employeeNo = res.getInt(1);
                String name= res.getString(2);
                int age=res.getInt(3);
                String designation=res.getString(4);
                array_list.add(new Model(name,designation,iD,employeeNo,age));
            } while (res.moveToNext());
        }
        res.close();
        return array_list;
    }

    public  int update(int id,int employeeNo,String name,int age,String designation)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id",id);
        contentValues.put("_no", employeeNo);
        contentValues.put("_name", name);
        contentValues.put("_age", age);
        contentValues.put("_designation", designation);
        int temp=db.update("Employee",contentValues, "_id=?", new String[]{String.valueOf(id)});
        db.close();
        return temp;
    }

    }
