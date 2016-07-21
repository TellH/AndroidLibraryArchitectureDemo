package com.tellh.androidlibraryarchitecturedemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.tellh.androidlibraryarchitecturedemo.volley.OkHttpStack;

/**
 * Created by tlh on 2016/7/18.
 */
public class MyApplication extends Application {
    private static MyApplication sInstance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(this,new OkHttpStack());
        sInstance = this;
    }

    public synchronized static MyApplication getInstance() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
