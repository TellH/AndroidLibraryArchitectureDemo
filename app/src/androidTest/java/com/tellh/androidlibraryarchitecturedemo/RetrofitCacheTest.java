package com.tellh.androidlibraryarchitecturedemo;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.tellh.androidlibraryarchitecturedemo.retrofit.CacheInterceptor;
import com.tellh.androidlibraryarchitecturedemo.retrofit.GithubApiClient;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tlh on 2016/8/14 :)
 */
public class RetrofitCacheTest extends ApplicationTestCase<Application> {
    public RetrofitCacheTest(Class<Application> applicationClass) {
        super(applicationClass);
//        setUp();
    }

    public void setUp() {
        File cacheFile = new File(getContext().getCacheDir(), "response");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new CacheInterceptor(getContext()))
                .cache(cache)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(GithubApiClient.GITHUB_URL)
                .build();
        GithubApiClient.ReposService reposService = retrofit.create(GithubApiClient.ReposService.class);
        Call<List<GithubApiClient.ReposService.Contributor>> call = reposService.getContributors("square", "retrofit");
        try {
            Response<List<GithubApiClient.ReposService.Contributor>> response = call.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
