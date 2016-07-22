package com.tellh.androidlibraryarchitecturedemo.okhttp.interceptor;

public interface Interceptor {
    Output intercept(Chain chain);
    interface Chain {
        Input input();
        Output proceed(Input input);
    }
}