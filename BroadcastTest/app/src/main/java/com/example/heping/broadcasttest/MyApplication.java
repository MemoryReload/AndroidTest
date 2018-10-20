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
        sendMessage("http://192.168.199.118:3000/application?state=create");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        sendMessage("http://192.168.199.118:3000/application?state=terminate");
    }

    void sendMessage(final String urlStr)
    {

        new Thread(){
            HttpURLConnection connection = null;
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(urlStr);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Type","text/json");
                    connection.setRequestProperty("Charset", "utf-8");
                    connection.setUseCaches(false);
                    connection.setConnectTimeout(10 * 1000);
                    connection.setReadTimeout(30 * 1000);
                    connection.connect();
                    if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                        Log.d("START EVENT", "network succeed!");
                    }else {
                        Log.d("START EVENT", "network failed!");
                    }
                } catch (Exception e) {
                    Log.d("START EVENT", "network failed!");
                } finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }.start();
    }
}
