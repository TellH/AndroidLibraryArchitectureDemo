package com.tellh.androidlibraryarchitecturedemo.dagger2.modules;

import android.app.Activity;

import com.tellh.androidlibraryarchitecturedemo.dagger2.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tlh on 2016/7/28.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }
    @Provides @PerActivity
    public Activity provideActivity(){
        return activity;
    }
}
