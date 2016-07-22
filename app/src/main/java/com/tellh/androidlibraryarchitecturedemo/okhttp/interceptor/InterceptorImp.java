package com.tellh.androidlibraryarchitecturedemo.okhttp.interceptor;

/**
 * Created by tlh on 2016/7/22.
 * 责任链设计模式
 */
public class InterceptorImp implements Interceptor {
    @Override
    public Output intercept(Chain chain) {
        //before intercept
        Output output = chain.proceed(chain.input());
        //pass interceptor and return here
        return output;
    }
}
