package com.tellh.androidlibraryarchitecturedemo.rxjava;

public class AppInfo implements Comparable<Object> {

    long lastUpdateTime;

    String name;

    String icon;

    public AppInfo(String name, String icon,long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        this.name = name;
        this.icon = icon;
    }

    public long getLastUpdateTime() {

        return lastUpdateTime;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public int compareTo(Object another) {
        AppInfo f = (AppInfo) another;
        return getName().compareTo(f.getName());
    }
}
