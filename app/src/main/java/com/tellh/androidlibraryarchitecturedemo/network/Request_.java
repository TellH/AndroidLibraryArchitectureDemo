package com.tellh.androidlibraryarchitecturedemo.network;

import com.tellh.androidlibraryarchitecturedemo.retrofit.RetrofitNetworkAccess;

import java.util.Map;

/**
 * Created by tlh on 2016/7/21.
 */
public class Request_ {
    public interface Method {
        int GET = 0;
        int POST = 1;
        int PUT = 2;
        int DELETE = 3;
        int HEAD = 4;
        int OPTIONS = 5;
        int TRACE = 6;
        int PATCH = 7;
    }

    protected String url;
    protected Object tag;
    protected Map<String, String> headers;
    protected Map<String, String> params;
    protected int method = Method.POST;
    protected NetworkCallback callback;
    protected NetworkAccessListener listener;
    private static NetworkAccess networkAccess;

    public Request_() {
        if (networkAccess == null)
//            networkAccess = VolleyNetworkAccess.getInstance();
            networkAccess = RetrofitNetworkAccess.getInstance();
    }

    public static void setNetworkAccess(NetworkAccess networkAccess) {
        Request_.networkAccess = networkAccess;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public NetworkCallback getCallback() {
        return callback;
    }

    public void setCallback(NetworkCallback callback) {
        this.callback = callback;
    }

    public NetworkAccessListener getListener() {
        return listener;
    }

    public void setListener(NetworkAccessListener listener) {
        this.listener = listener;
    }

    public static void cancelAll(Object tag) {
        networkAccess.cancel(tag);
    }


    public void cancel() {
        networkAccess.cancel(tag);
    }

    public Request_ execute() {
        networkAccess.execute(this);
        return this;
    }
}
