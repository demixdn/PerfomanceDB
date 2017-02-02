package com.example.perfomancedb;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.perfomancedb.FeedHelper.T;

public class DBActivity extends AppCompatActivity {

    public FeedHelper feedData;
    public String set_N6, set_U, set_G, set_T, set_B4, set_L6, set_R6, set_P6, set_A6, set_I8, set_C5, set_F, set_D8, set_H6, set_E6, set_Z, set_M6, set_W6, set_m3, set_t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        ButterKnife.bind(this);
        feedData = new FeedHelper(this);
    }

    @OnClick({R.id.button4, R.id.button5, R.id.button6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button4:
                insert();
                break;
            case R.id.button5:
                read();
                break;
            case R.id.button6:
                feedData.getWritableDatabase().delete("components", null, null);
                feedData.close();
                break;
        }
    }

    private void read() {
        Cursor cursor = feedData.getReadableDatabase().query("components", null, null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Log.d("log", "id " + cursor.getInt(cursor.getColumnIndex(FeedHelper.ID))
                            + " N6 " + cursor.getString(cursor.getColumnIndex(FeedHelper.N6))
                            + " U " + cursor.getString(cursor.getColumnIndex(FeedHelper.U))
                            + " G " + cursor.getString(cursor.getColumnIndex(FeedHelper.G))
                            + " T " + cursor.getString(cursor.getColumnIndex(FeedHelper.T))
                            + " B4 " + cursor.getString(cursor.getColumnIndex(FeedHelper.B4))
                            + " L6 " + cursor.getString(cursor.getColumnIndex(FeedHelper.L6))
                            + " R6 " + cursor.getString(cursor.getColumnIndex(FeedHelper.R6))
                            + " P6 " + cursor.getString(cursor.getColumnIndex(FeedHelper.P6))
                            + " A6 " + cursor.getString(cursor.getColumnIndex(FeedHelper.A6))
                            + " I8 " + cursor.getString(cursor.getColumnIndex(FeedHelper.I8))
                            + " C5 " + cursor.getString(cursor.getColumnIndex("С5"))
                            + " F " + cursor.getString(cursor.getColumnIndex(FeedHelper.F))
                            + " D8 " + cursor.getString(cursor.getColumnIndex(FeedHelper.D8))
                            + " H6 " + cursor.getString(cursor.getColumnIndex(FeedHelper.E6))
                            + " Z " + cursor.getString(cursor.getColumnIndex(FeedHelper.Z))
                            + " M6 " + cursor.getString(cursor.getColumnIndex(FeedHelper.M6))
                            + " W6 " + cursor.getString(cursor.getColumnIndex(FeedHelper.W6))
                            + " m3 " + cursor.getString(cursor.getColumnIndex(FeedHelper.m3))
                            + " t3 " + cursor.getString(cursor.getColumnIndex(FeedHelper.t3))

                    );
                    cursor.moveToNext();
                }
            }
            cursor.close();
        } else {
            Log.d("lod", "Table not found");
        }
        feedData.close();
    }

    private void insert() {
        ContentValues contentValues = new ContentValues();
        set_N6 = "02";
        set_U = "02";
        set_G = "02";
        set_T = "02";
        set_B4 = "02";
        set_L6 = "02";
        set_R6 = "02";
        set_P6 = "02";
        set_A6 = "02";
        set_I8 = "02";
        set_C5 = "02";
        set_F = "02";
        set_D8 = "02";
        set_H6 = "02";
        set_E6 = "02";
        set_Z = "02";
        set_M6 = "02";
        set_W6 = "02";
        set_m3 = "02";
        set_t3 = "02";

        contentValues.put(FeedHelper.N6, set_N6);
        contentValues.put(FeedHelper.U, set_U);
        contentValues.put(FeedHelper.G, set_G);
        contentValues.put(T, set_T);
        contentValues.put(FeedHelper.B4, set_B4);
        contentValues.put(FeedHelper.L6, set_L6);
        contentValues.put(FeedHelper.R6, set_R6);
        contentValues.put(FeedHelper.P6, set_P6);
        contentValues.put(FeedHelper.A6, set_A6);
        contentValues.put(FeedHelper.I8, set_I8);
        contentValues.put(FeedHelper.C5, set_C5);
        contentValues.put(FeedHelper.F, set_F);
        contentValues.put(FeedHelper.D8, set_D8);
        contentValues.put(FeedHelper.H6, set_H6);
        contentValues.put(FeedHelper.E6, set_E6);
        contentValues.put(FeedHelper.Z, set_Z);
        contentValues.put(FeedHelper.M6, set_M6);
        contentValues.put(FeedHelper.W6, set_W6);
        contentValues.put(FeedHelper.m3, set_m3);
        contentValues.put(FeedHelper.t3, set_t3);


        feedData.getWritableDatabase().insert("components", null, contentValues);
        Log.d("log", "Data inserted");
        feedData.close();
    }
}
