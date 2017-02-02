package com.example.perfomancedb.storio;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;


/**
 * Date: 29.09.2016
 * Time: 12:29
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

public class DbModule {
    private final StorIOSQLite storIOSQLite;
    private final SQLiteOpenHelper sqLiteOpenHelper;

    public DbModule(@NonNull Context context) {
        sqLiteOpenHelper = new DbOpenHelper(context);
        this.storIOSQLite = DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .addTypeMapping(Tweet.class, new TweetSQLiteTypeMapping())
                .addTypeMapping(User.class, new UserSQLiteTypeMapping())
                .addTypeMapping(TweetWithUser.class, SQLiteTypeMapping.<TweetWithUser>builder()
                        .putResolver(new TweetWithUserPutResolver())
                        .getResolver(new TweetWithUserGetResolver())
                        .deleteResolver(new TweetWithUserDeleteResolver())
                        .build())
                .build();
    }

    @NonNull
    public StorIOSQLite getStorIOSQLite() {
        return storIOSQLite;
    }

    public SQLiteOpenHelper getSqLiteOpenHelper() {
        return sqLiteOpenHelper;
    }
}
