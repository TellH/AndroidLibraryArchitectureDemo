package com.tellh.androidlibraryarchitecturedemo.retrofit;

import com.tellh.androidlibraryarchitecturedemo.volley.WeatherInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by tlh on 2016/7/23.
 */
public class WeatherApiClient {
    private static WeatherApiClient mClient;
    private static final String WEATHER_URL = "https://api.heweather.com/";
    private Retrofit retrofit;

    public static WeatherApiClient client() {
        if (mClient == null)
            mClient = new WeatherApiClient();
        if (mClient.retrofit == null)
            mClient.retrofit = new Retrofit.Builder()
                    .baseUrl(WEATHER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return mClient;
    }

    public WeatherService weatherService() {
        return retrofit.create(WeatherService.class);
    }

    public interface WeatherService {
        @GET("x3/weather")
        Call<WeatherInfo> getWeather(@Query("key") String key, @Query("cityid") String cityid);

        @GET("x3/weather")
        Call<WeatherInfo> getWeather(@QueryMap Map<String, String> params);
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getId());
        WeatherApiClient.client().weatherService().getWeather("ba8c19698d164e098048dd401ac5ec65", "CN101280800")
                .enqueue(new Callback<WeatherInfo>() {
                    @Override
                    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                        System.out.println(Thread.currentThread().getId());
                        System.out.println(response.body().getHeWeatherList().get(0).getSuggestion().getComf().getTxt());
                    }

                    @Override
                    public void onFailure(Call<WeatherInfo> call, Throwable t) {
                        System.out.println(Thread.currentThread().getId());
                        System.out.println(t.getMessage());
                    }
                });
    }
}
