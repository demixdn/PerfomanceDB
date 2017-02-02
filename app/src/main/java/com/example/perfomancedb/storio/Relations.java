package com.example.perfomancedb.storio;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.NonNull;

import com.example.perfomancedb.Const;
import com.example.perfomancedb.TestLogEvent;
import com.pushtorefresh.storio.sqlite.Changes;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.PutResults;
import com.pushtorefresh.storio.sqlite.queries.DeleteQuery;
import com.pushtorefresh.storio.sqlite.queries.RawQuery;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Date: 29.09.2016
 * Time: 12:24
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

public class Relations {
    @NonNull
    public static final String QUERY_COLUMN_TWEET_ID = "tweet_id";

    @NonNull
    public static final String QUERY_COLUMN_TWEET_AUTHOR = "tweet_author";

    @NonNull
    public static final String QUERY_COLUMN_TWEET_CONTENT = "tweet_content";

    @NonNull
    public static final String QUERY_COLUMN_USER_ID = "user_id";

    @NonNull
    public static final String QUERY_COLUMN_USER_NICK = "user_nick";

    @NonNull
    private final StorIOSQLite storIOSQLite;

    public Relations(@NonNull StorIOSQLite storIOSQLite) {
        this.storIOSQLite = storIOSQLite;
    }

    @NonNull
    public List<TweetWithUser> getTweetWithUser() {
        return storIOSQLite
                .get()
                .listOfObjects(TweetWithUser.class)
                .withQuery(RawQuery.builder()
                        .query("SELECT "
                                // Unfortunately we have columns with same names, so we need to give them aliases.
                                + TweetsTable.COLUMN_ID_WITH_TABLE_PREFIX + " AS \"" + QUERY_COLUMN_TWEET_ID + "\""
                                + ", "
                                + TweetsTable.COLUMN_AUTHOR_WITH_TABLE_PREFIX + " AS \"" + QUERY_COLUMN_TWEET_AUTHOR + "\""
                                + ", "
                                + TweetsTable.COLUMN_CONTENT_WITH_TABLE_PREFIX + " AS \"" + QUERY_COLUMN_TWEET_CONTENT + "\""
                                + ", "
                                + UsersTable.COLUMN_ID_WITH_TABLE_PREFIX + " AS \"" + QUERY_COLUMN_USER_ID + "\""
                                + ", "
                                + UsersTable.COLUMN_NICK_WITH_TABLE_PREFIX + " AS \"" + QUERY_COLUMN_USER_NICK + "\""
                                + " FROM " + TweetsTable.TABLE
                                + " JOIN " + UsersTable.TABLE
                                + " ON " + TweetsTable.COLUMN_AUTHOR_WITH_TABLE_PREFIX
                                + " = " + UsersTable.COLUMN_NICK_WITH_TABLE_PREFIX)
                        .build())
                .prepare()
                .executeAsBlocking();
    }

    @NonNull
    public List<Tweet> getAllTweets(){
        long startTime = System.currentTimeMillis();
        List<Tweet> results = storIOSQLite.get()
                .listOfObjects(Tweet.class)
                .withQuery(RawQuery.builder().query("SELECT * FROM "+TweetsTable.TABLE).build())
                .prepare()
                .executeAsBlocking();
        long endTime = System.currentTimeMillis();
        EventBus.getDefault().post(new TestLogEvent(startTime, endTime, Const.STORIO, "READ"));
        return results;
    }

    @NonNull
    public List<User> getAllUsers(){
        return storIOSQLite.get()
                .listOfObjects(User.class)
                .withQuery(RawQuery.builder().query("SELECT * FROM "+UsersTable.TABLE).build())
                .prepare()
                .executeAsBlocking();
    }

    public void insertTweets(SQLiteOpenHelper helper, List<Tweet> tweets){
        storIOSQLite.delete().byQuery(DeleteQuery.builder().table(TweetsTable.TABLE).build());
        long startTime = System.currentTimeMillis();
        insertManyTweets(storIOSQLite.lowLevel(), helper, tweets);
        long endTime = System.currentTimeMillis();
        EventBus.getDefault().post(new TestLogEvent(startTime, endTime, Const.STORIO, "WRITE"));
    }

    void insertManyTweets(StorIOSQLite.LowLevel lowLevel, SQLiteOpenHelper helper, List<Tweet> tweets) {
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.beginTransaction();
            String sql = String.format("INSERT INTO %s ('%s', '%s') VALUES(?1, ?2)",
                    TweetsTable.TABLE, TweetsTable.COLUMN_AUTHOR, TweetsTable.COLUMN_CONTENT);
            SQLiteStatement statement = db.compileStatement(sql);
            for (Tweet tweet : tweets) {
                statement.clearBindings();
                statement.bindString(1, tweet.author());
                statement.bindString(2, tweet.content());
                statement.executeInsert();
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        lowLevel.notifyAboutChanges(Changes.newInstance("tweets"));
    }

    public int insertUsers(List<User> users){
        PutResults putResults = storIOSQLite.put().objects(users).prepare().executeAsBlocking();
        return putResults.numberOfInserts();
    }
}
