package com.tellh.androidlibraryarchitecturedemo.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.tellh.androidlibraryarchitecturedemo.MyApplication;

import java.util.Map;

/**
 * Created by tlh on 2016/7/18.
 */
public class VolleyNetworkAccess<ResponseType> implements NetworkAccess {
    private RequestQueue mQueue = MyApplication.getInstance().getRequestQueue();
    private Class<ResponseType> mClass;
    private NetworkResponseListener<ResponseType> mListener;
    private Request request;

    public VolleyNetworkAccess(NetworkResponseListener listener) {
        mListener = listener;
    }

    public VolleyNetworkAccess(Class<ResponseType> clz, NetworkResponseListener listener) {
        mClass = clz;
        mListener = listener;
    }


    @Override
    public void get(String url) {
        get(url, null);
    }

    @Override
    public void get(String url, Map<String, String> params) {
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
        if (mClass == null)
            request = new StringRequest(Request.Method.GET, requestUrl.toString(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (mListener != null)
                        mListener.onSuccess((ResponseType) response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (mListener != null)
                        mListener.onError();
                }
            });
        else
            request = new GsonRequest<ResponseType>(Request.Method.POST, requestUrl.toString(), mClass, new Response.Listener<ResponseType>() {
                @Override
                public void onResponse(ResponseType response) {
                    if (mListener != null)
                        mListener.onSuccess((ResponseType) response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (mListener != null)
                        mListener.onError();
                }
            });
        mQueue.add(request);
    }

    @Override
    public void post(String url, final Map<String, String> params) {
        if (mClass == null)
            request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (mListener != null)
                        mListener.onSuccess((ResponseType) response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (mListener != null)
                        mListener.onError();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }
            };
        else
            request = new GsonRequest<ResponseType>(Request.Method.POST, url, mClass, new Response.Listener<ResponseType>() {
                @Override
                public void onResponse(ResponseType response) {
                    if (mListener != null)
                        mListener.onSuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (mListener != null)
                        mListener.onError();
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
