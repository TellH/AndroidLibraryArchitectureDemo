package com.tellh.androidlibraryarchitecturedemo.dagger2.modules;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tlh on 2016/7/28.
 */
@Module
public class GsonModule {
    @Provides
    public Gson provideGson(){
        return new Gson();
    }
}
