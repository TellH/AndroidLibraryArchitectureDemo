package com.tellh.androidlibraryarchitecturedemo.volley;

import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.internal.$Gson$Types;
import com.tellh.androidlibraryarchitecturedemo.MyApplication;
import com.tellh.androidlibraryarchitecturedemo.network.NetworkAccess;
import com.tellh.androidlibraryarchitecturedemo.network.NetworkAccessListener;
import com.tellh.androidlibraryarchitecturedemo.network.NetworkCallback;
import com.tellh.androidlibraryarchitecturedemo.network.Request_;

import java.util.Map;

/**
 * Created by tlh on 2016/7/21.
 */
public class VolleyNetworkAccess implements NetworkAccess {
    private volatile static VolleyNetworkAccess ourInstance;
    private RequestQueue mQueue;

    public static VolleyNetworkAccess getInstance() {
        if (ourInstance == null) {
            synchronized (VolleyNetworkAccess.class) {
                if (ourInstance == null)
                    ourInstance = new VolleyNetworkAccess();
            }
        }
        return ourInstance;
    }

    private VolleyNetworkAccess() {
        if (mQueue == null)
            mQueue = Volley.newRequestQueue(MyApplication.getInstance().getApplicationContext());
    }

    public RequestQueue getQueue() {
        return mQueue;
    }

    @Override
    public void cancel(Object tag) {
        mQueue.cancelAll(tag);
    }

    @Override
    public void execute(Request_ request_) {
        switch (request_.getMethod()) {
            case Request_.Method.GET:
                handleGET(request_);
                break;
            case Request_.Method.POST:
                handlePOST(request_);
                break;
            default:
                break;
        }
    }

    private void handlePOST(final Request_ request_) {
        Request request = buildRequest(request_);
        if (request == null) return;
        adaptRequest(request, request_);
        mQueue.add(request);
    }

    @Nullable
    private Request buildRequest(final Request_ request_) {
        Request request;
        final String url = request_.getUrl();
        final NetworkCallback callback = request_.getCallback();
        final NetworkAccessListener listener = request_.getListener();
        if (callback == null) {
            request = new StringRequest(request_.getMethod(), url, null, null) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return request_.getParams();
                }
            };
            mQueue.add(request);
            return null;
        }
        if (listener != null)
            listener.onNetworkAccessStart();
        if (callback.getType() == String.class) {
            request = new StringRequest(request_.getMethod(), url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    callback.onResponse(response);
                    if (listener != null)
                        listener.onNetworkAccessFinish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onError(error);
                    if (listener != null)
                        listener.onNetworkAccessFinish();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    if (request_.getMethod() == Method.POST)
                        return request_.getParams();
                    return super.getParams();
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    if (request_.getHeaders() != null)
                        return request_.getHeaders();
                    return super.getHeaders();
                }
            };
        } else {
            request = new GsonRequest(request_.getMethod(), url,
                    $Gson$Types.canonicalize(callback.getType()),
                    new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {
                            callback.onResponse(response);
                            if (listener != null)
                                listener.onNetworkAccessFinish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            callback.onError(error);
                            if (listener != null)
                                listener.onNetworkAccessFinish();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return request_.getParams();
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    if (request_.getHeaders() != null)
                        return request_.getHeaders();
                    return super.getHeaders();
                }
            };
        }
        return request;
    }

    private void adaptRequest(Request request, Request_ request_) {
        if (request_.getTag() != null)
            request.setTag(request_.getTag());
    }

    private void handleGET(final Request_ request_) {
        //把请求参数组装到url中
        StringBuilder requestUrl = new StringBuilder(request_.getUrl());
        if (request_.getParams() != null) {
            requestUrl.append("?");
            for (Map.Entry<String, String> entry : request_.getParams().entrySet()) {
                requestUrl.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            requestUrl.deleteCharAt(requestUrl.length() - 1);//删除最后一个'&'
        }
        request_.setUrl(requestUrl.toString());
        Request request = buildRequest(request_);
        assert request != null;
        adaptRequest(request, request_);
        mQueue.add(request);
    }
}
