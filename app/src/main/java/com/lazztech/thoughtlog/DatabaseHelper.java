package com.lazztech.thoughtlog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gianlazzarini on 8/9/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "SITUATION";
    public static final String COL_3 = "THOUGHTS";
    public static final String COL_4 = "EMOTIONS";
    public static final String COL_5 = "BEHAVIOR";
    public static final String COL_6 = "DISTORTIONS";
    public static final String COL_7 = "ALTBEHAVIOR";
    public static final String COL_8 = "ALTTHOUGHTS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, SITUATION TEXT, THOUGHTS TEXT, EMOTIONS TEXT, BEHAVIOR TEXT, DISTORTIONS TEXT, ALTBEHAVIOR TEXT, ALTTHOUGHTS TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String situation,String thoughts,String emotions, String behavior, String distortions, String altbehavior, String altthoughts){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,situation);
        contentValues.put(COL_3,thoughts);
        contentValues.put(COL_4,emotions);
        contentValues.put(COL_5,behavior);
        contentValues.put(COL_6,distortions);
        contentValues.put(COL_7,altbehavior);
        contentValues.put(COL_8,altthoughts);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME, null);
        return res;
    }
}