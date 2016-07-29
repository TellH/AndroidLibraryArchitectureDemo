package com.tellh.androidlibraryarchitecturedemo.dagger2.modules;

import com.tellh.androidlibraryarchitecturedemo.dagger2.MvpView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tlh on 2016/7/29.
 */
@Module
public class MvpModule {
    MvpView mvpView;

    public MvpModule(MvpView mvpView) {
        this.mvpView = mvpView;
    }

    @Provides
    public MvpView provideMvpView(){
        return mvpView;
    }
}
