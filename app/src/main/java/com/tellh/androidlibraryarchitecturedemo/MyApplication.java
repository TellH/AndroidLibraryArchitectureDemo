package com.tellh.androidlibraryarchitecturedemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.tellh.androidlibraryarchitecturedemo.dagger2.component.AppComponent;
import com.tellh.androidlibraryarchitecturedemo.dagger2.component.DaggerAppComponent;
import com.tellh.androidlibraryarchitecturedemo.dagger2.modules.AppModule;
import com.tellh.androidlibraryarchitecturedemo.volley.OkHttpStack;

/**
 * Created by tlh on 2016/7/18.
 */
public class MyApplication extends Application {
    private static MyApplication sInstance;
    private RequestQueue mRequestQueue;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(this,new OkHttpStack());
        sInstance = this;
        mAppComponent= DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public synchronized static MyApplication getInstance() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
