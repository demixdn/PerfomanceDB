package com.example.perfomancedb.sqlv12;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Date: 29.09.2016
 * Time: 14:31
 *
 * @author Aleks Sander
 *         Project PerfomanceDB
 */

public class Relations {
    private final DbModule dbModule;

    public Relations(Context context) {
        this.dbModule = new DbModule(context);
    }

    public int insertTweets(@NonNull List<Tweet> tweets){
        return dbModule.insertTweets(tweets);
    }

    public List<Tweet> getAllTweets(){
        return dbModule.getAllTweets();
    }
}
