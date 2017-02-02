package com.example.perfomancedb.storio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;


/**
 * Date: 29.09.2016
 * Time: 11:44
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "tweets_db_storio";

    DbOpenHelper(@NonNull Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(TweetsTable.getCreateTableQuery());
        db.execSQL(UsersTable.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
