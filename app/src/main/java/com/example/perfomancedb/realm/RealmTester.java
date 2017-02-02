package com.example.perfomancedb.realm;

import android.util.Log;

import com.example.perfomancedb.Generator;
import com.example.perfomancedb.TestLogEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.AbstractList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Date: 29.09.2016
 * Time: 18:01
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

public class RealmTester {
    public static final String FRAMEWORK_NAME = "Realm";
    public static void testTweetsWrite(){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Tweet.class);
            }
        });
        final List<Tweet> tweets = Generator.getRealmTweets();
        long startTime = System.currentTimeMillis();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(tweets);
            }
        });
        realm.close();
        long endTime = System.currentTimeMillis();
        EventBus.getDefault().post(new TestLogEvent(startTime, endTime, FRAMEWORK_NAME, "WRITE"));
    }

    public static RealmResults<Tweet> testTweetRead(){
        Realm realm = Realm.getDefaultInstance();
        long startTime = System.currentTimeMillis();
        RealmResults<Tweet> tweets = realm.where(Tweet.class).findAll();
        verify(tweets);
        realm.close();
        long endTime = System.currentTimeMillis();
        EventBus.getDefault().post(new TestLogEvent(startTime, endTime, FRAMEWORK_NAME, "READ"));
        return tweets;
    }

    private static void verify(AbstractList<Tweet> tweetsList){
        for(Tweet tweet : tweetsList){
            if(!tweet.getAuthor().contains("author Name of #") && !tweet.getContent().contains("Long content or not."))
                Log.e(RealmTester.class.getSimpleName(), "Realm obj is not valid");
        }
    }
}
