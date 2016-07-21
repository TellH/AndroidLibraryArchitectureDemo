package com.tellh.androidlibraryarchitecturedemo.network;

/**
 * Created by tlh on 2016/7/21.
 */
public interface NetworkAccess {
    void cancel(Object tag);
    void execute(Request_ request);
}
