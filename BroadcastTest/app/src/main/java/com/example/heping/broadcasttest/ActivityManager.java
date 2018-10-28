package com.example.heping.broadcasttest;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityManager extends Object {
    private static ActivityManager manager = null;

    private List<Activity> activityList;

    public static ActivityManager getManager(){
        if (manager == null){
            synchronized (ActivityManager.class){
                if (manager == null){
                    manager = new ActivityManager();
                }
            }
        }
        return  manager;
    }

    public ActivityManager(){
        activityList = new ArrayList<>();
    }

    public void addActivity(Activity activity){
        activityList.add(activity);
    }

    public void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    public void finishAll()
    {
        for (Activity activity:activityList) {
            activity.finish();
        }
    }
}
