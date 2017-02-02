package com.example.perfomancedb;

import android.annotation.SuppressLint;

import com.example.perfomancedb.sqlv12.Tweet;

import java.util.ArrayList;
import java.util.List;


/**
 * Date: 29.09.2016
 * Time: 16:51
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

public class Generator {
    private static final int SIMPLE_COUNT = 10000;
    private static List<Tweet> sqlTweets;
    private static List<com.example.perfomancedb.storio.Tweet> storTweets;
    private static List<com.example.perfomancedb.realm.Tweet> realmTweets;

    private static void generateTweets(){
        sqlTweets = new ArrayList<>(SIMPLE_COUNT);
        for(int i=0; i < SIMPLE_COUNT; i++){
            sqlTweets.add(generateRandomTweet(i));
        }
    }

    @SuppressLint("DefaultLocale")
    private static Tweet generateRandomTweet(int number){
        return Tweet.newTweet(String.format("author Name of #%d",number),
                String.format("Long content or not. Maybe it's big story #%d", number));
    }

    public static List<Tweet> getTweets() {
        if(sqlTweets == null)
            generateTweets();
        return sqlTweets;
    }

    private static void generateStorTweets(){
        storTweets = new ArrayList<>(SIMPLE_COUNT);
        for(int i=0; i < SIMPLE_COUNT; i++){
            storTweets.add(generateRandomStorTweet(i));
        }
    }

    @SuppressLint("DefaultLocale")
    private static com.example.perfomancedb.storio.Tweet generateRandomStorTweet(int number){
        return com.example.perfomancedb.storio.Tweet.newTweet(String.format("author Name of #%d",number),
                String.format("Long content or not. Maybe it's big story #%d", number));
    }

    public static List<com.example.perfomancedb.storio.Tweet> getStorTweets() {
        if(storTweets == null)
            generateStorTweets();
        return storTweets;
    }

    public static List<com.example.perfomancedb.realm.Tweet> getRealmTweets(){
        if(realmTweets == null)
            generateRealmTweets();
        return realmTweets;
    }

    private static void generateRealmTweets() {
        realmTweets = new ArrayList<>(SIMPLE_COUNT);
        for(int i=0; i < SIMPLE_COUNT; i++){
            realmTweets.add(generateRandomRealmTweet(i));
        }
    }

    @SuppressLint("DefaultLocale")
    private static com.example.perfomancedb.realm.Tweet generateRandomRealmTweet(int number) {
        return com.example.perfomancedb.realm.Tweet.newTweet(Long.parseLong(String.valueOf(number)), String.format("author Name of #%d",number),
                String.format("Long content or not. Maybe it's big story #%d", number));
    }
}
