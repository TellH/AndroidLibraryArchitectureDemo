package com.tellh.androidlibraryarchitecturedemo.retrofit;

import com.tellh.androidlibraryarchitecturedemo.MyApplication;
import com.tellh.androidlibraryarchitecturedemo.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tlh on 2016/8/15 :)
 */
public class RemoveEtagInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (NetworkUtil.isNetworkAvailable(MyApplication.getInstance())) {
            request = request.newBuilder()
                    .removeHeader("If-None-Match")
                    .build();
        }
        return chain.proceed(request);
    }
}
