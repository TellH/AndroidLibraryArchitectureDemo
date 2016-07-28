package com.tellh.androidlibraryarchitecturedemo.dagger2.component;

import android.content.Context;

import com.tellh.androidlibraryarchitecturedemo.dagger2.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tlh on 2016/7/28.
 */
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    Context getContext();
}
