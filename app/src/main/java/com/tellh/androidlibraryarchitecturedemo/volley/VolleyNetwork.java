package com.tellh.androidlibraryarchitecturedemo.volley;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by tlh on 2016/7/21.
 */
public class VolleyNetwork implements NetworkAccess {
    private NetworkAccessListener listener;

    @Override
    public NetworkAccess setListener(NetworkAccessListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public void get(String url, final NetworkCallback callback) {
        if (listener != null)
            listener.onNetworkAccessStart();
        VolleyUtils.get(url, null, new NetworkCallback() {
            @Override
            public Type getType() {
                return callback.getType();
            }

            @Override
            protected void onResponse(Object response) {
                if (callback != null)
                    callback.onResponse(response);
                if (listener != null)
                    listener.onNetworkAccessFinish();
            }

            @Override
            protected void onError(Exception e) {
                if (callback != null)
                    callback.onError(e);
                if (listener != null)
                    listener.onNetworkAccessFinish();
            }
        });
    }

    @Override
    public void post(String url, Map<String, String> params, final NetworkCallback callback) {
        if (listener != null)
            listener.onNetworkAccessStart();
        VolleyUtils.post(url, params, new NetworkCallback() {
            @Override
            public Type getType() {
                return callback.getType();
            }
            @Override
            protected void onResponse(Object response) {
                if (callback != null)
                    callback.onResponse(response);
                if (listener != null)
                    listener.onNetworkAccessFinish();
            }

            @Override
            protected void onError(Exception e) {
                if (callback != null)
                    callback.onError(e);
                if (listener != null)
                    listener.onNetworkAccessFinish();
            }
        });
    }

}
