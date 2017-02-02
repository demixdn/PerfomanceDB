package com.example.perfomancedb.sqlv12;

import android.content.Context;
import android.support.annotation.NonNull;

import io.requery.android.database.sqlite.SQLiteDatabase;
import io.requery.android.database.sqlite.SQLiteOpenHelper;

/**
 * Date: 29.09.2016
 * Time: 14:31
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

public class DbOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "tweets_db_3142";

    DbOpenHelper(@NonNull Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(TweetsTable.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
