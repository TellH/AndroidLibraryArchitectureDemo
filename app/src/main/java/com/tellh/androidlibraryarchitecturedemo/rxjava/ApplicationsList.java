package com.tellh.androidlibraryarchitecturedemo.rxjava;

import java.util.List;

public class ApplicationsList {

    private static ApplicationsList ourInstance = new ApplicationsList();

    private List<AppInfo> mList;

    private ApplicationsList() {
    }

    public List<AppInfo> getList() {
        return mList;
    }

    public void setList(List<AppInfo> mList) {
        this.mList = mList;
    }

    public static ApplicationsList getInstance() {
        return ourInstance;
    }
}