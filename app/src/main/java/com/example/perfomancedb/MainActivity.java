package com.example.perfomancedb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.perfomancedb.realm.RealmTester;
import com.example.perfomancedb.sqlv12.Relations;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private Thread runTestThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(this).build());
        ButterKnife.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogEvent(TestLogEvent event){
        Log.e(MainActivity.class.getSimpleName(), event.toString());
        System.gc();
    }

    @OnClick(R.id.button)
    public void runSimpleTests(View v){
        runTestThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Context applicationContext = MainActivity.this.getApplicationContext();
                testSqlWrite(applicationContext);
                testStorIOWrite(applicationContext);
                RealmTester.testTweetsWrite();
            }
        });
        runTestThread.start();
        System.gc();
    }

    @OnClick(R.id.button2)
    public void runSimpleReadTest(){
        runTestThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Context applicationContext = MainActivity.this.getApplicationContext();
                testSqlRead(applicationContext);
                testStorIORead(applicationContext);
                RealmTester.testTweetRead();
            }
        });
        runTestThread.start();
        System.gc();
    }

    @OnClick(R.id.button3)
    public void onNextScreen(){
        startActivity(new Intent(MainActivity.this, DBActivity.class));
    }

    public void testSqlWrite(Context context){
        Relations sqlTest = new Relations(context);
        sqlTest.insertTweets(Generator.getTweets());
    }

    public void testStorIOWrite(Context context){
        com.example.perfomancedb.storio.DbModule dbModule = new com.example.perfomancedb.storio.DbModule(context);
        com.example.perfomancedb.storio.Relations storTest = new com.example.perfomancedb.storio.Relations(dbModule.getStorIOSQLite());
        storTest.insertTweets(dbModule.getSqLiteOpenHelper(), Generator.getStorTweets());
    }

    public void testSqlRead(Context context){
        Relations sqlTest = new Relations(context);
        sqlTest.getAllTweets();
    }

    public void testStorIORead(Context context){
        com.example.perfomancedb.storio.DbModule dbModule = new com.example.perfomancedb.storio.DbModule(context);
        com.example.perfomancedb.storio.Relations storTest = new com.example.perfomancedb.storio.Relations(dbModule.getStorIOSQLite());
        storTest.getAllTweets();
    }
}
