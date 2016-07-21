package com.tellh.androidlibraryarchitecturedemo.network;

import java.util.LinkedHashMap;
import java.util.Map;

public class RequestBuilder {
    private Request_ request;

    public RequestBuilder() {
        request = new Request_();
    }

    public RequestBuilder get() {
        request.method = Request_.Method.GET;
        return this;
    }

    public RequestBuilder post() {
        request.method = Request_.Method.POST;
        return this;
    }

    public RequestBuilder url(String url) {
        request.url = url;
        return this;
    }


    public RequestBuilder tag(Object tag) {
        request.tag = tag;
        return this;
    }

    public RequestBuilder headers(Map<String, String> headers) {
        request.headers = headers;
        return this;
    }

    public RequestBuilder addHeader(String key, String val) {
        if (request.headers == null) {
            request.headers = new LinkedHashMap<>();
        }
        request.headers.put(key, val);
        return this;
    }

    public RequestBuilder params(Map<String, String> params) {
        if (request.params == null)
            request.params = params;
        else request.params.putAll(params);
        return this;
    }

    public RequestBuilder addParam(String key, String val) {
        if (request.params == null) {
            request.params = new LinkedHashMap<>();
        }
        request.params.put(key, val);
        return this;
    }

    public RequestBuilder callback(NetworkCallback callback) {
        if (callback != null) {
            request.callback = callback;
        }
        return this;
    }

    public RequestBuilder listener(NetworkAccessListener listener) {
        if (listener != null) {
            request.listener = listener;
        }
        return this;
    }

    public Request_ build() {
        return request;
    }

    public Request_ buildAndExecute() {
        request.execute();
        return request;
    }
}