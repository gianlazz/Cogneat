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
    public static final String COL_2 = "DATETIME";
    public static final String COL_3 = "SITUATION";
    public static final String COL_4 = "THOUGHTS";
    public static final String COL_5 = "EMOTIONS";
    public static final String COL_6 = "BEHAVIOR";
    public static final String COL_7 = "DISTORTIONS";
    public static final String COL_8 = "ALTBEHAVIOR";
    public static final String COL_9 = "ALTTHOUGHTS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, DATETIME TEXT, SITUATION TEXT, THOUGHTS TEXT, EMOTIONS TEXT, BEHAVIOR TEXT, DISTORTIONS TEXT, ALTBEHAVIOR TEXT, ALTTHOUGHTS TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String datetime, String situation,String thoughts,String emotions, String behavior, String distortions, String altbehavior, String altthoughts){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,datetime);
        contentValues.put(COL_3,situation);
        contentValues.put(COL_4,thoughts);
        contentValues.put(COL_5,emotions);
        contentValues.put(COL_6,behavior);
        contentValues.put(COL_7,distortions);
        contentValues.put(COL_8,altbehavior);
        contentValues.put(COL_9,altthoughts);
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

    public boolean updateData(String id, String datetime, String situation,String thoughts,String emotions, String behavior, String distortions, String altbehavior, String altthoughts){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2,datetime);
        contentValues.put(COL_3,situation);
        contentValues.put(COL_4,thoughts);
        contentValues.put(COL_5,emotions);
        contentValues.put(COL_6,behavior);
        contentValues.put(COL_7,distortions);
        contentValues.put(COL_8,altbehavior);
        contentValues.put(COL_9,altthoughts);
        db.update(TABLE_NAME, contentValues, "_id = ?", new String[] {id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "_id = ?", new String[] {id});
    }
}
