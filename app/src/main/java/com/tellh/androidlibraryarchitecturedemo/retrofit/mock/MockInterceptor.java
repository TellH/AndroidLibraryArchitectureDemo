package com.tellh.androidlibraryarchitecturedemo.retrofit.mock;

import com.tellh.androidlibraryarchitecturedemo.BuildConfig;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by tlh on 2016/8/14 :)
 */
public class MockInterceptor implements Interceptor {
    private HashMap<String, String> urlMap;

    private MockInterceptor() {
        urlMap = new HashMap<>();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = null;
        if (!BuildConfig.DEBUG)
            return chain.proceed(chain.request());
        String responseString = null;
        String path = chain.request().url().uri().getPath();
        for (String url : urlMap.keySet()) {
            if (!path.contains(url))
                continue;
            responseString = urlMap.get(url);
            break;
        }

        if (responseString != null) {
            response = new Response.Builder()
                    .code(200)
                    .message(responseString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                    .addHeader("content-type", "application/json")
                    .build();
        } else {
            response = chain.proceed(chain.request());
        }
        return response;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MockInterceptor interceptor;

        public Builder() {
            this.interceptor = new MockInterceptor();
        }

        public Builder addUrl(String url, String responseString) {
            interceptor.urlMap.put(url, responseString);
            return this;
        }

        public MockInterceptor build() {
            return interceptor;
        }
    }
}
