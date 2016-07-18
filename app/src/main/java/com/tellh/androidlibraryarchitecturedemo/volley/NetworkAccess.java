package com.tellh.androidlibraryarchitecturedemo.volley;

import java.util.Map;

/**
 * Created by tlh on 2016/7/18.
 */
public interface NetworkAccess {
    void get(String url);
    void get(String url, Map<String, String> params);
    void post(String url, Map<String, String> params);
}
