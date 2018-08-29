package com.example.heping.listviewtest;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import java.net.MalformedURLException;
import java.net.URL;

public class Fruit extends Object {

    private URL     mImageURL;
    private int     mImageResource;
    private Bitmap  mImageCache;
    private String  mName;


    public Fruit(@DrawableRes int image, @NonNull String name)
    {
        this.mImageResource = image;
        this.mName = name;
    }

    public Fruit(String url, @NonNull String name){
        try {
            this.mImageURL = new URL(url);
        } catch (MalformedURLException e) {
            this.mImageURL = null;
        }
        this.mName = name;
    }

    public String getName()
    {
        return this.mName;
    }

    public URL getImageURL() {
        return mImageURL;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public Bitmap getImageCache() {
        return mImageCache;
    }

    public void setImageCache(Bitmap mImageCache) {
        this.mImageCache = mImageCache;
    }
}
