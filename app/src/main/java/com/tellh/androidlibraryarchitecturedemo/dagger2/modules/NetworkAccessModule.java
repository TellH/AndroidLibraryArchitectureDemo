package com.tellh.androidlibraryarchitecturedemo.dagger2.modules;

import com.tellh.androidlibraryarchitecturedemo.network.NetworkAccess;
import com.tellh.androidlibraryarchitecturedemo.retrofit.RetrofitNetworkAccess;
import com.tellh.androidlibraryarchitecturedemo.volley.VolleyNetworkAccess;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tlh on 2016/7/28.
 */
@Module
public class NetworkAccessModule {
    //@Singleton与@Named()不能同时用
    @Provides
//    @Singleton
    @Named("Volley")
    public NetworkAccess provideVolleyNetworkAccess(){
        return VolleyNetworkAccess.getInstance();
    }
    @Provides
//    @Singleton
    @Named("Retrofit")
    public NetworkAccess provideRetrofitNetworkAccess(){
        return RetrofitNetworkAccess.getInstance();
    }

}
