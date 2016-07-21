package com.tellh.androidlibraryarchitecturedemo.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.internal.$Gson$Types;
import com.tellh.androidlibraryarchitecturedemo.MyApplication;

import java.util.Map;

/**
 * Created by tlh on 2016/7/18.
 */
public class VolleyUtils {
    private static RequestQueue mQueue = MyApplication.getInstance().getRequestQueue();

    public static void get(String url, Map<String, String> params) {
        get(url, params, null);
    }

    public static void get(String url, Map<String, String> params, final NetworkCallback listener) {
        //把请求参数组装到url中
        StringBuilder requestUrl = new StringBuilder(url);
        if (params != null) {
            requestUrl.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                requestUrl.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            requestUrl.deleteCharAt(requestUrl.length() - 1);//删除最后一个'&'
        }
        Request request;
        if (listener == null) {
            request = new StringRequest(requestUrl.toString(), null, null);
            mQueue.add(request);
            return;
        }
        if (listener.getType() == String.class) {
            request = new StringRequest(Request.Method.GET, requestUrl.toString(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            listener.onResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            listener.onError(error);
                        }
                    });
        } else {
            request = new GsonRequest(Request.Method.GET, requestUrl.toString(),
                    $Gson$Types.canonicalize(listener.getType()),
                    new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {
                            listener.onResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            listener.onError(error);
                        }
                    });
        }
        mQueue.add(request);
    }

    public static void post(String url, final Map<String, String> params) {
        post(url, params, null);
    }

    public static void post(String url, final Map<String, String> params, final NetworkCallback listener) {
        Request request;
        if (listener == null) {
            request = new StringRequest(url, null, null){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }
            };
            mQueue.add(request);
            return;
        }
        if (listener.getType() == String.class)
            request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                        listener.onResponse(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }
            };
        else
            request = new GsonRequest(Request.Method.POST, url,
                    $Gson$Types.canonicalize(listener.getType()),
                    new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                        listener.onResponse(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }
            };
        mQueue.add(request);
    }
}
