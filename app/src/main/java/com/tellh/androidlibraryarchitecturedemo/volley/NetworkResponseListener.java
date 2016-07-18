package com.tellh.androidlibraryarchitecturedemo.volley;

public interface NetworkResponseListener<ResponseType> {
    void onSuccess(ResponseType response);
    void onError();
}