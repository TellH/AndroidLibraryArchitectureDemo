package com.tellh.androidlibraryarchitecturedemo.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tellh.androidlibraryarchitecturedemo.R;
import com.tellh.androidlibraryarchitecturedemo.volley.WeatherInfo;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkExampleActivity extends AppCompatActivity {
    private static OkHttpClient client= new OkHttpClient();
    private TextView tvGet;
    private TextView tvPost;
    private static final String TAG = "OkExampleActivity";
    private static final String URL_GET = "https://raw.githubusercontent.com/square/okhttp/master/samples/guide/src/main/java/okhttp3/guide/GetExample.java";
    private static final String URL_POST = "https://api.heweather.com/x3/weather";

    private static final String KEY = "ba8c19698d164e098048dd401ac5ec65";
    private static final String FORSHAN_ID = "CN101280800";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_example);
        tvGet = (TextView) findViewById(R.id.tvGet);
        tvPost = (TextView) findViewById(R.id.tvPost);
        new Thread(new Runnable() {
            @Override
            public void run() {
                get();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                post();
            }
        }).start();
    }
    private WeatherInfo gsonParse(Response response){
        Gson gson=new Gson();
        return gson.fromJson(response.body().charStream(),WeatherInfo.class);
    }
    private void post(){
        RequestBody formBody=new FormBody.Builder()
                .add("cityid",FORSHAN_ID)
                .add("key",KEY)
                .build();
        Request request=new Request.Builder()
                .url(URL_POST)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d(TAG, "onFailure: " + e.getMessage());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            //每次调用string()后,IO流会关闭。
//                final String s = response.body().string();
            final WeatherInfo weatherInfo = gsonParse(response);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvPost.setText(weatherInfo.getHeWeatherList().get(0).getSuggestion().getComf().getTxt());
                }
            });
        }
    });
}
    private void get() {
        Request request = new Request.Builder()
                .url(URL_GET)
                .tag(TAG)
                .build();
//            Response response = client.newCall(request).execute();//Synchronous
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Toast.makeText(OkExampleActivity.this, "network access failed", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String s = response.body().string();
                Log.d(TAG, s);
                //不能在非UI线程进行UI操作
//                tvGet.setText(response.body().string());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //这种写法也是错误的，response是非UI线程中。
//                tvGet.setText(response.body().string());
                        tvGet.setText(s);
                    }
                });
            }
        });
    }
    private void uploadFile(){
        final MediaType MEDIA_TYPE_MULTIPART = MediaType.parse("multipart/form-data");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("token", "Square Logo")
                .addFormDataPart("key", "logo-square.png",
                        RequestBody.create(MEDIA_TYPE_MULTIPART, new File("website/static/logo-square.png")))
                .build();
    }
}
