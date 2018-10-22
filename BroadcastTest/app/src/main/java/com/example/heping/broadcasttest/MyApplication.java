package com.example.heping.broadcasttest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"Application created, babe!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Toast.makeText(this,"Application terminated, babe!",Toast.LENGTH_SHORT).show();
    }
}
