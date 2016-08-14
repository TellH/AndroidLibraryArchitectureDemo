package com.tellh.androidlibraryarchitecturedemo.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.tellh.androidlibraryarchitecturedemo.R;
import com.tellh.androidlibraryarchitecturedemo.network.NetworkCallback;
import com.tellh.androidlibraryarchitecturedemo.network.RequestBuilder;
import com.tellh.androidlibraryarchitecturedemo.volley.WeatherInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {
    private TextView tvReponseStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        tvReponseStr = (TextView) findViewById(R.id.tvResponseStr);
        Map<String, String> params = new HashMap<>();
        params.put("key", "ba8c19698d164e098048dd401ac5ec65");
        params.put("cityid", "CN101280800");
//        getGithubRequest();
        Call<List<GithubApiClient.ReposService.Contributor>> call = GithubApiClient.client().reposService().getContributors("square", "retrofit");
        call.enqueue(new Callback<List<GithubApiClient.ReposService.Contributor>>() {
            @Override
            public void onResponse(Call<List<GithubApiClient.ReposService.Contributor>> call, Response<List<GithubApiClient.ReposService.Contributor>> response) {
                List<GithubApiClient.ReposService.Contributor> list = response.body();
            }

            @Override
            public void onFailure(Call<List<GithubApiClient.ReposService.Contributor>> call, Throwable t) {

            }
        });
    }

    private void getGithubRequest() {
        new RequestBuilder()
                .get()
                .url("https://api.github.com/repos/square/retrofit/contributors")
                .callback(new NetworkCallback<List<GithubApiClient.ReposService.Contributor>>() {
                    @Override
                    public void onResponse(List<GithubApiClient.ReposService.Contributor> response) {
                        tvReponseStr.setText(response.get(3).name);
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(RetrofitActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .buildAndExecute();
    }

    private void getWeather(Map<String, String> params) {
        new RequestBuilder()
                .post()
                .url("https://api.heweather.com/x3/weather")
                .params(params)
                .callback(new NetworkCallback<WeatherInfo>() {
                    @Override
                    public void onResponse(WeatherInfo response) {
                        tvReponseStr.setText(response.getHeWeatherList().get(0).getSuggestion().getComf().getTxt());
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(RetrofitActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .buildAndExecute();
    }

    private void weatherClient(Map<String, String> params) {
//        WeatherApiClient.client().weatherService()
////                .getWeather("ba8c19698d164e098048dd401ac5ec65", "CN101280800")
//                .getWeather(params)
//                .enqueue(new Callback<WeatherInfo>() {
//                    @Override
//                    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
//                        tvReponseStr.setText(response.body().getHeWeatherList().get(0).getSuggestion().getComf().getTxt());
//                    }
//
//                    @Override
//                    public void onFailure(Call<WeatherInfo> call, Throwable t) {
//                        Toast.makeText(RetrofitActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
    }
}
