package com.tellh.androidlibraryarchitecturedemo.volley;

import java.util.Map;

/**
 * Created by tlh on 2016/7/21.
 */
interface NetworkAccess {
    NetworkAccess setListener(NetworkAccessListener listener);
    void get(String url,NetworkCallback callback);
    void post(String url, Map<String, String> params,NetworkCallback callback);
}
