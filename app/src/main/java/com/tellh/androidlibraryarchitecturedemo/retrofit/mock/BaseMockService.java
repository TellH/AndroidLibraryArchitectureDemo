package com.tellh.androidlibraryarchitecturedemo.retrofit.mock;

import com.tellh.androidlibraryarchitecturedemo.retrofit.GithubApiClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

/**
 * Created by tlh on 2016/8/14 :)
 */
public abstract class BaseMockService<T> {
    protected final BehaviorDelegate<GithubApiClient.ReposService> delegate;
    protected NetworkBehavior networkBehavior;

    public BaseMockService(Class<T> clz,String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
        networkBehavior = NetworkBehavior.create();
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(networkBehavior)
                .build();
        delegate = mockRetrofit.create(GithubApiClient.ReposService.class);
    }
}
