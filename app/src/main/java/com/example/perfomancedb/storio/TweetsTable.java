package com.example.perfomancedb.storio;

import android.support.annotation.NonNull;

/**
 * Date: 29.09.2016
 * Time: 12:02
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

public class TweetsTable {

    @NonNull
    public static final String TABLE = "tweets";

    @NonNull
    public static final String COLUMN_ID = "_id";

    @NonNull
    public static final String COLUMN_AUTHOR = "author";

    @NonNull
    public static final String COLUMN_CONTENT = "content";

    public static final String COLUMN_ID_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_ID;
    public static final String COLUMN_AUTHOR_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_AUTHOR;
    public static final String COLUMN_CONTENT_WITH_TABLE_PREFIX = TABLE + "." + COLUMN_CONTENT;

    // This is just class with Meta Data, we don't need instances
    public TweetsTable() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_AUTHOR + " TEXT NOT NULL, "
                + COLUMN_CONTENT + " TEXT NOT NULL"
                + ");";
    }
}
