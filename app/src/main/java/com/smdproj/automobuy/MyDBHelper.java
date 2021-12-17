package com.smdproj.automobuy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    String CREATE_USER_TABLE="CREATE TABLE " +
            MyUsers.User.TABLENAME + "(" +
            MyUsers.User._EMAIL+" TEXT PRIMARY KEY, "+
            MyUsers.User._PASSWORD+" TEXT, "+
            MyUsers.User._IS_MGR+" TEXT, "+
            MyUsers.User._IS_DLR+" TEXT)";

    String CREATE_EMP_TABLE="CREATE TABLE " +
            MyUsers.Employee.TABLENAME + "(" +
            MyUsers.Employee._EMAIL+" TEXT, "+
            MyUsers.Employee._CNIC+" TEXT, "+
            MyUsers.Employee._FNAME+" TEXT, "+
            MyUsers.Employee._LNAME+" TEXT, "+
            MyUsers.Employee._PHONENO+" TEXT, "+
            MyUsers.Employee._ADDRESS+" TEXT, "+
            MyUsers.Employee._TYPE+" TEXT, "+
            MyUsers.Employee._SALARY+" TEXT, "+
            MyUsers.Employee._SALARY+" TEXT, "+
            MyUsers.Employee._ISMGR+" TEXT)";

    String DELETE_USER_TABLE="DROP TABLE IF EXISTS "+ MyUsers.User.TABLENAME;
    String DELETE_EMP_TABLE="DROP TABLE IF EXISTS "+ MyUsers.Employee.TABLENAME;

    public MyDBHelper(@Nullable Context context) {
        super(context,MyUsers.DB_NAME,null,MyUsers.DB_VERSION);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_EMP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_USER_TABLE);
        sqLiteDatabase.execSQL(DELETE_EMP_TABLE);

        onCreate(sqLiteDatabase);
    }

    public Boolean insertData(String email, String pw, String is_mgr, String is_dlr){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MyUsers.User._EMAIL, email);
        cv.put(MyUsers.User._PASSWORD, pw);
        cv.put(MyUsers.User._IS_MGR, is_mgr);
        cv.put(MyUsers.User._IS_DLR, is_dlr);

        long result = database.insert(MyUsers.User.TABLENAME,null,cv);
        //database.close();
        return result != -1;
    }

    public Boolean insertEmp(String email, String cnic, String is_mgr, String fn, String ln, String phone,String addr,String sal,String type,String sales){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MyUsers.Employee._EMAIL, email);
        cv.put(MyUsers.Employee._CNIC, cnic);
        cv.put(MyUsers.Employee._ISMGR, is_mgr);
        cv.put(MyUsers.Employee._FNAME, fn);
        cv.put(MyUsers.Employee._LNAME, ln);
        cv.put(MyUsers.Employee._PHONENO, phone);
        cv.put(MyUsers.Employee._ADDRESS, addr);
        cv.put(MyUsers.Employee._SALARY, sal);
        cv.put(MyUsers.Employee._SALES, sales);
        cv.put(MyUsers.Employee._TYPE, type);

        long result = database.insert(MyUsers.Employee.TABLENAME,null,cv);
        //database.close();
        return result != -1;
    }


    public String checkUser(){
        String value = "";
        SQLiteDatabase database = this.getReadableDatabase();
        long NoOfRows = DatabaseUtils.queryNumEntries(database,MyUsers.User.TABLENAME);
        if (NoOfRows == 0){
            return "";
        }

        Cursor res = database.rawQuery( "select * from "+MyUsers.User.TABLENAME, null );
        res.moveToFirst();
        String mgr = res.getString(2);
        if(mgr.equals("1")) {
            value = "mgr";
        }
        else{
            value = "dlr";
        }

        return value;
    }

    public void logout(){
        SQLiteDatabase database = this.getReadableDatabase();
        database.delete(MyUsers.User.TABLENAME, null, null);
    }
}
