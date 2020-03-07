package com.gdg.bhopal.studentadmissiongdgform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databasehelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="student_table";
    public static final String NAME_COL="Name";
    public static final String EMAIL_COL="Email";
    public static final String DOB_COL="DOB";
    public static final String GEN_COL="Gender";
    public static final String MOB_COL="Mobile";
    public static final String BRANCH_COL="Branch";
    public static final String HS_COL="High_School";
    public static final String SS_COL="Senior_School";

        public databasehelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAME+"(Name text,Email text,DOB text,Gender text,Mobile text,Branch text,High_School real,Senior_School real)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
            onCreate(db);
    }
    public boolean insertdata(String Name,String Email,String DOB,String Gender,String Mobile,String Branch,Float High_School,Float Senior_School){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME_COL,Name);
        cv.put(EMAIL_COL,Email);
        cv.put(DOB_COL,DOB);
        cv.put(GEN_COL,Gender);
        cv.put(MOB_COL,Mobile);
        cv.put(BRANCH_COL,Branch);
        cv.put(HS_COL,High_School);
        cv.put(SS_COL,Senior_School);
        long result=db.insert(TABLE_NAME,null,cv);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Cursor getalldata(){
            SQLiteDatabase db=this.getWritableDatabase();
            Cursor res=db.rawQuery("Select * from "+TABLE_NAME,null);
            return res;
    }
}
