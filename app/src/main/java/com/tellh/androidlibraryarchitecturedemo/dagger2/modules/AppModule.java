package com.tellh.androidlibraryarchitecturedemo.dagger2.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tlh on 2016/7/28.
 */
@Module
public class AppModule {
    private final Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public Context provideAppContext() {
        return mContext;
    }
}
