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
    public static final String DATABASE_NAME = "Cogneat.db";
    public static final String TABLE_NAME = "thoughtlog_table";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "DATETIME";
    public static final String COL_3 = "SITUATION";
    public static final String COL_4 = "THOUGHTS";
    public static final String COL_5 = "EMOTIONS";
    public static final String COL_6 = "BEHAVIOR";
    public static final String COL_7 = "DISTORTIONS";
    public static final String COL_8 = "ALTBEHAVIOR";
    public static final String COL_9 = "ALTTHOUGHTS";

    public static final String TABLE_GAD7 = "gad7_table";
    public static final String GAD7_COL_1 = "_id";
    public static final String GAD7_COL_2 = "DATETIME";
    public static final String GAD7_COL_3 = "SCORE";
    public static final String GAD7_COL_4 = "DIAGNOSIS";

    public static final String TABLE_PHQ9 = "phq9_table";
    public static final String PHQ9_COL_1 = "_id";
    public static final String PHQ9_COL_2 = "DATETIME";
    public static final String PHQ9_COL_3 = "SCORE";
    public static final String PHQ9_COL_4 = "DIAGNOSIS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, DATETIME TEXT, SITUATION TEXT, THOUGHTS TEXT, EMOTIONS TEXT, BEHAVIOR TEXT, DISTORTIONS TEXT, ALTBEHAVIOR TEXT, ALTTHOUGHTS TEXT) ");
        db.execSQL("create table " + TABLE_GAD7 + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, DATETIME TEXT, SCORE TEXT, DIAGNOSIS TEXT) ");
        db.execSQL("create table " + TABLE_PHQ9 + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, DATETIME TEXT, SCORE TEXT, DIAGNOSIS TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 2) {
         //   db.execSQL("DROP TABLE IF EXIST " + TABLE_GAD7);
         //   onCreate(db);
        }
    }

    public boolean insertThoughtLogData(String datetime, String situation, String thoughts, String emotions, String behavior, String distortions, String altbehavior, String altthoughts){
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

    public boolean insertGAD7Data(String datetime, String score, String diagnosis){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GAD7_COL_2, datetime);
        contentValues.put(GAD7_COL_3, score);
        contentValues.put(GAD7_COL_4, diagnosis);
        long result = db.insert(TABLE_GAD7, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertPHQ9Data(String datetime, String score, String diagnosis){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PHQ9_COL_2, datetime);
        contentValues.put(PHQ9_COL_3, score);
        contentValues.put(PHQ9_COL_4, diagnosis);
        long result = db.insert(TABLE_PHQ9, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllThoughtLogData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME, null);
        return res;
    }

    public Cursor getAllGAD7Data(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_GAD7, null);
        return res;
    }

    public Cursor getAllPHQ9Data(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_PHQ9, null);
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
