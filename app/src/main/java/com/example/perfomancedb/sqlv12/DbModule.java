package com.example.perfomancedb.sqlv12;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.example.perfomancedb.Const;
import com.example.perfomancedb.TestLogEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.requery.android.database.sqlite.SQLiteDatabase;
import io.requery.android.database.sqlite.SQLiteOpenHelper;
import io.requery.android.database.sqlite.SQLiteStatement;

/**
 * Date: 29.09.2016
 * Time: 14:53
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

public class DbModule {
    private SQLiteOpenHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    public DbModule(@NonNull Context context){
        sqLiteHelper = new DbOpenHelper(context);
    }

    private void openReadable(){
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
    }

    private void openWriteable(){
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
    }

    private void close(){
        sqLiteDatabase.close();
        sqLiteHelper.close();
    }

    public int insertTweets(@NonNull List<Tweet> tweets){
        openWriteable();
        sqLiteDatabase.execSQL("DELETE FROM "+TweetsTable.TABLE);

        long startTime = System.currentTimeMillis();

        SQLiteStatement stmt = sqLiteDatabase.compileStatement(
                String.format("INSERT INTO %s ('%s', '%s') VALUES(?1, ?2)",
                        TweetsTable.TABLE, TweetsTable.COLUMN_AUTHOR, TweetsTable.COLUMN_CONTENT));

        int insertedCount = 0;
        sqLiteDatabase.beginTransaction();
        try {
            for (int i=0; i < tweets.size(); i++){
                stmt.clearBindings();
                Tweet tweet = tweets.get(i);
                stmt.bindString(1, tweet.author());
                stmt.bindString(2, tweet.content());
                stmt.executeInsert();
            }
            sqLiteDatabase.setTransactionSuccessful();
        }finally {
            sqLiteDatabase.endTransaction();
            close();
        }
        EventBus.getDefault().post(new TestLogEvent(startTime, System.currentTimeMillis(), Const.SQLV12, "WRITE"));
        return insertedCount;
    }

    @NonNull
    public List<Tweet> getAllTweets(){
        openReadable();
        long startTime = System.currentTimeMillis();
        Cursor cursor = sqLiteDatabase.rawQuery(String.format("SELECT * FROM %s", TweetsTable.TABLE), null);
        cursor.moveToFirst();
        List<Tweet> tweetsItems = new ArrayList<>();
        do {
            Tweet tweet = Tweet.newTweet(cursor.getLong(cursor.getColumnIndex(TweetsTable.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(TweetsTable.COLUMN_AUTHOR)),
                    cursor.getString(cursor.getColumnIndex(TweetsTable.COLUMN_CONTENT)));
            tweetsItems.add(tweet);
        }while (cursor.moveToNext());
        cursor.close();
        close();
        long endTime = System.currentTimeMillis();
        EventBus.getDefault().post(new TestLogEvent(startTime, endTime, Const.SQLV12, "READ"));
        return tweetsItems;
    }
}
