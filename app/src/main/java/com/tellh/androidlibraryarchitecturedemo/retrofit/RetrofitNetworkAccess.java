package com.tellh.androidlibraryarchitecturedemo.retrofit;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.tellh.androidlibraryarchitecturedemo.network.NetworkAccess;
import com.tellh.androidlibraryarchitecturedemo.network.NetworkAccessListener;
import com.tellh.androidlibraryarchitecturedemo.network.NetworkCallback;
import com.tellh.androidlibraryarchitecturedemo.network.Request_;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by tlh on 2016/7/23.
 */
public class RetrofitNetworkAccess implements NetworkAccess {
    private Call<ResponseBody> call;
    private volatile static RetrofitNetworkAccess ourInstance;
    private static Retrofit retrofit;

    public static RetrofitNetworkAccess getInstance() {
        if (ourInstance == null) {
            synchronized (RetrofitNetworkAccess.class) {
                if (ourInstance == null)
                    ourInstance = new RetrofitNetworkAccess();
            }
        }
        return ourInstance;
    }

    private RetrofitNetworkAccess() {
    }

    @Override
    public void cancel(Object tag) {
        if (call != null)
            call.cancel();
    }

    @Override
    public void execute(Request_ request) {
        String url = request.getUrl();
        if (!url.endsWith("/"))
            url += "/";
        //baseUrl()必须要调用
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();
        RetroService service = retrofit.create(RetroService.class);
        switch (request.getMethod()) {
            case Request_.Method.GET:
                if (request.getHeaders() != null)
                    call = service.get(request.getParams(), url, request.getHeaders());
                else
                    call = service.get(request.getParams(), url);
                break;
            case Request_.Method.POST:
                if (request.getHeaders() != null)
                    call = service.post(request.getParams(), url, request.getHeaders());
                else
                    call = service.post(request.getParams(), url);
                break;
            default:
                break;
        }
        executeCall(request);
    }

    private void executeCall(Request_ request) {
        final NetworkCallback callback = request.getCallback();
        final NetworkAccessListener listener = request.getListener();
        if (listener != null && callback != null) {
            listener.onNetworkAccessStart();
        }
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseStr = null;
                try {
                    responseStr = response.body().string();
                } catch (IOException e) {
                    if (callback != null)
                        callback.onError(e);
                    if (listener != null)
                        listener.onNetworkAccessFinish();
                }
                if (responseStr == null)
                    return;
                if (callback != null) {
                    if (callback.getType() == String.class)
                        callback.onResponse(responseStr);
                    else {
                        Gson gson = new Gson();
                        callback.onResponse(gson.fromJson(responseStr, $Gson$Types.canonicalize(callback.getType())));
                    }
                }
                if (listener != null)
                    listener.onNetworkAccessFinish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callback != null)
                    callback.onError(new Exception(t));
                if (listener != null)
                    listener.onNetworkAccessFinish();
            }
        });
    }

    interface RetroService {
        //Call必须有泛型,默认是ResponseBody
        //如果设置的headers为空，会把默认添加的Headers覆盖掉
        //必须要有url参数
        @GET
        Call<ResponseBody> get(@QueryMap Map<String, String> params, @Url String url, @HeaderMap Map<String, String> headers);

        @GET
        Call<ResponseBody> get(@QueryMap Map<String, String> params, @Url String url);

        @FormUrlEncoded
        @POST
        Call<ResponseBody> post(@FieldMap Map<String, String> params, @Url String url, @HeaderMap Map<String, String> headers);

        @FormUrlEncoded
        @POST
        Call<ResponseBody> post(@FieldMap Map<String, String> params, @Url String url);
    }
}
