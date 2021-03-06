package com.tellh.androidlibraryarchitecturedemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tellh.androidlibraryarchitecturedemo.dagger2.component.AppComponent;
import com.tellh.androidlibraryarchitecturedemo.dagger2.component.DaggerAppComponent;
import com.tellh.androidlibraryarchitecturedemo.dagger2.component.DaggerTargetActivityComponent;
import com.tellh.androidlibraryarchitecturedemo.dagger2.component.TargetActivityComponent;
import com.tellh.androidlibraryarchitecturedemo.dagger2.modules.ActivityModule;
import com.tellh.androidlibraryarchitecturedemo.dagger2.modules.AppModule;
import com.tellh.androidlibraryarchitecturedemo.volley.OkHttpStack;

/**
 * Created by tlh on 2016/7/18.
 */
public class MyApplication extends Application {
    private static MyApplication sInstance;
    private RequestQueue mRequestQueue;
    private AppComponent mAppComponent;
    private TargetActivityComponent mTargetActivityComponent;
    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack());
        sInstance = this;
        refWatcher = LeakCanary.install(this);
    }

    public synchronized static MyApplication getInstance() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(getInstance()))
                    .build();
        }
        return mAppComponent;
    }

    public TargetActivityComponent getTargetActivityComponent(Activity activity) {
        if (mTargetActivityComponent == null) {
            mTargetActivityComponent = DaggerTargetActivityComponent.builder()
                    .appComponent(getAppComponent())
                    .activityModule(new ActivityModule(activity))//有无参构造方法的Module可以显式构造
                    .build();
        }
        return mTargetActivityComponent;
    }
    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }
}
