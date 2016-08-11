package com.tellh.androidlibraryarchitecturedemo.dagger2;

import com.tellh.androidlibraryarchitecturedemo.dagger2.scope.PerActivity;

import javax.inject.Inject;

/**
 * Created by tlh on 2016/7/29.
 */
public class MvpPresenter {
    MvpView mView;
    private UserManager mUserManager;

    @Inject @PerActivity
    public MvpPresenter(MvpView mView) {
        this.mView = mView;
    }

    public void login(String name,String password){
        if (name == null || name.length() == 0) return;
        if (password == null || password.length() < 6) return;

        mUserManager.performLogin(name, password);
    }

    public void setmUserManager(UserManager mUserManager) {
        this.mUserManager = mUserManager;
    }

    public static class UserManager{
        public void performLogin(String name, String password) {

        }
    }
}
