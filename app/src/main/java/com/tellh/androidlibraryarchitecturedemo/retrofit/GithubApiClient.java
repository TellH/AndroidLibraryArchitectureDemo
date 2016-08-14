package com.tellh.androidlibraryarchitecturedemo.retrofit;

import com.google.gson.annotations.SerializedName;
import com.tellh.androidlibraryarchitecturedemo.MyApplication;

import java.io.File;
import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tlh on 2016/7/23.
 */
public class GithubApiClient {
    private static GithubApiClient mClient;
    public static final String GITHUB_URL = "https://api.github.com/";
    private Retrofit retrofit;

    public static GithubApiClient client() {
//        File cacheFile = new File(MyApplication.getInstance().getCacheDir(), "response");
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(new CacheInterceptor(MyApplication.getInstance()))
//                .cache(cache)
//                .build();
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .baseUrl(GithubApiClient.GITHUB_URL)
//                .build();
//        GithubApiClient.ReposService reposService = retrofit.create(GithubApiClient.ReposService.class);
        if (mClient == null)
            mClient = new GithubApiClient();
        if (mClient.retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            File cacheFile = new File(MyApplication.getInstance().getCacheDir(), "response");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
            OkHttpClient client = new OkHttpClient.Builder()
//                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(new CacheInterceptor(MyApplication.getInstance()))
                    .cache(cache)
                    .build();
            mClient.retrofit = new Retrofit.Builder()
                    .baseUrl(GITHUB_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mClient;
    }

    public ReposService reposService() {
        return retrofit.create(ReposService.class);
    }

    public interface ReposService {
        @GET("repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> getContributors(@Path("owner") String owner, @Path("repo") String repo);

        class Contributor {
            @SerializedName("login")
            public final String name;
            public final int contributions;

            public Contributor(String name, int contributions) {
                this.name = name;
                this.contributions = contributions;
            }
        }
    }

    public static void main(String[] args) {
        GithubApiClient.client().reposService()
                .getContributors("square", "retrofit")
//                .execute();
                .enqueue(new Callback<List<ReposService.Contributor>>() {
                    @Override
                    public void onResponse(Call<List<ReposService.Contributor>> call, Response<List<ReposService.Contributor>> response) {
                        List<ReposService.Contributor> contributors = response.body();
                        for (ReposService.Contributor contributor : contributors) {
                            System.out.println(contributor.name + ": " + contributor.contributions);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ReposService.Contributor>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
    }
}
