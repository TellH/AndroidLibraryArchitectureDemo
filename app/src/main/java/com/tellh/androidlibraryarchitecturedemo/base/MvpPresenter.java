package com.tellh.androidlibraryarchitecturedemo.base;

public interface MvpPresenter<V extends BaseView> {
    void attachView(V view);

    void detachView();
}