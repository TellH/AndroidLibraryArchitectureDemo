package com.tellh.androidlibraryarchitecturedemo.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.tellh.androidlibraryarchitecturedemo.R;
import com.tellh.androidlibraryarchitecturedemo.network.NetworkAccess;
import com.tellh.androidlibraryarchitecturedemo.network.NetworkAccessListener;
import com.tellh.androidlibraryarchitecturedemo.network.NetworkCallback;
import com.tellh.androidlibraryarchitecturedemo.network.RequestBuilder;

import java.util.HashMap;
import java.util.Map;

public class VolleyDemoActivity extends AppCompatActivity implements NetworkAccessListener {
    private static final String TAG = "VolleyDemoActivity";
    private NetworkAccess networkAccess;
    private static final String KEY = "ba8c19698d164e098048dd401ac5ec65";
    private static final String APIURl = "https://api.heweather.com/x3/weather";
    private static final String FORSHAN_ID = "CN101280800";
    private TextView tvReponseStr;
    private TextView tvBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_demo);
        tvReponseStr = (TextView) findViewById(R.id.tvResponseStr);
        tvBean = (TextView) findViewById(R.id.tvBean);
        Map<String, String> params = new HashMap<>();
        params.put("cityid", FORSHAN_ID);
        params.put("key", KEY);
        RequestBuilder requestBuilder=new RequestBuilder();
        requestBuilder.post()
                .tag(TAG)
                .url(APIURl)
                .params(params)
                .listener(this)
                .callback(new NetworkCallback<WeatherInfo>() {
                    @Override
                    public void onResponse(WeatherInfo response) {
                        tvBean.setText(response.getHeWeatherList().get(0).getSuggestion().getComf().getTxt());
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(VolleyDemoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).buildAndExecute();
    }

    @Override
    public void onNetworkAccessStart() {
        Log.d(TAG, "onNetworkAccessStart() called");
    }

    @Override
    public void onNetworkAccessFinish() {
        Log.d(TAG, "onNetworkAccessFinish() called");
    }
}
