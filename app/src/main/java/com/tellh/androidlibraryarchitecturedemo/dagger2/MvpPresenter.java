package com.tellh.androidlibraryarchitecturedemo.dagger2;

import com.tellh.androidlibraryarchitecturedemo.dagger2.scope.PerActivity;

import javax.inject.Inject;

/**
 * Created by tlh on 2016/7/29.
 */
public class MvpPresenter {
    MvpView mView;

    @Inject @PerActivity
    public MvpPresenter(MvpView mView) {
        this.mView = mView;
    }
}
