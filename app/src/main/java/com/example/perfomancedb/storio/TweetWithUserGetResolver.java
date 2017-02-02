package com.example.perfomancedb.storio;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.example.perfomancedb.storio.Relations;
import com.example.perfomancedb.storio.Tweet;
import com.example.perfomancedb.storio.TweetWithUser;
import com.example.perfomancedb.storio.User;
import com.pushtorefresh.storio.sqlite.operations.get.DefaultGetResolver;

/**
 * Date: 29.09.2016
 * Time: 12:23
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

public final class TweetWithUserGetResolver extends DefaultGetResolver<TweetWithUser> {

    // We expect that cursor will contain both Tweet and User: SQL JOIN
    @NonNull
    @Override
    public TweetWithUser mapFromCursor(@NonNull Cursor cursor) {
        final Tweet tweet = Tweet.newTweet(
                cursor.getLong(cursor.getColumnIndexOrThrow(Relations.QUERY_COLUMN_TWEET_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(Relations.QUERY_COLUMN_TWEET_AUTHOR)),
                cursor.getString(cursor.getColumnIndexOrThrow(Relations.QUERY_COLUMN_TWEET_CONTENT))
        );

        final User user = User.newUser(
                cursor.getLong(cursor.getColumnIndexOrThrow(Relations.QUERY_COLUMN_USER_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(Relations.QUERY_COLUMN_USER_NICK))
        );

        return new TweetWithUser(tweet, user);
    }
}
