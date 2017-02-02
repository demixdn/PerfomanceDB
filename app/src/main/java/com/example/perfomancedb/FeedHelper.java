package com.example.perfomancedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Date: 02.02.2017
 * Time: 15:28
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

public class FeedHelper extends SQLiteOpenHelper {


    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "feedingDB";
    public static final String TABLE = "components";
    public static final String ID = "id";
    //**********************************************************************************************
    public static final String N6 = "N6";
    public static final String U = "U";
    public static final String G = "G";
    public static final String T = "T";
    public static final String B4 = "B4";
    public static final String L6 = "L6";
    public static final String R6 = "R6";
    public static final String P6 = "P6";
    public static final String A6 = "A6";
    public static final String I8 = "I8";
    public static final String C5 = "C5";
    public static final String F = "F";
    public static final String D8 = "D8";
    public static final String H6 = "H6";
    public static final String E6 = "E6";
    public static final String Z = "Z";
    public static final String M6 = "M6";
    public static final String W6 = "W6";
    public static final String m3 = "m3";
    public static final String t3 = "t3";

    //**********************************************************************************************
    public FeedHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE + "(" + ID + " integer primary key," + N6 + " text," + U + " text," + G
                + " text,"  + T  + " text," + B4 + " text," + L6 + " text," + R6 + " text," + P6 + " text,"
                + A6 + " text," + I8 + " text,"
                + C5 + " text," + F + " text," + D8 + " text," + H6 + " text," + E6 + " text," + Z +
                " text," + M6 + " text," + W6 + " text," + m3 + " text," + t3 + " text" + ")" );


    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist " + TABLE);
        onCreate(db);

    }




}
