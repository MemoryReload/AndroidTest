package com.example.heping.broadcasttest;

import android.app.Application;
import android.widget.Toast;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"Application created, babe!",Toast.LENGTH_SHORT);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Toast.makeText(this,"Application terminated, babe!",Toast.LENGTH_SHORT);
    }
}
