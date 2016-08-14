package com.tellh.androidlibraryarchitecturedemo.retrofit;

import com.tellh.androidlibraryarchitecturedemo.retrofit.mock.MockInterceptor;
import com.tellh.androidlibraryarchitecturedemo.retrofit.mock.MockService;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.NetworkBehavior;

/**
 * Created by tlh on 2016/8/14 :)
 */
public class ReposServiceTest {
    private NetworkBehavior mNetworkBehavior;
    private MockService mService;
    private Retrofit mRetrofit;

    private void givenNetworkFailurePercentIs(int failurePercent) {
        mNetworkBehavior.setDelay(0, TimeUnit.MILLISECONDS);
        mNetworkBehavior.setVariancePercent(0);
        mNetworkBehavior.setFailurePercent(failurePercent);
    }

    @Before
    public void setUp() {
        MockInterceptor interceptor = MockInterceptor.builder()
                .addUrl("/contributors", "[{\"login\":\"John Doe\",\"contributions\":12},{\"login\":\"Bob Smith\",\"contributions\":2},{\"login\":\"Big Bird\",\"contributions\":40}]")
                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(GithubApiClient.GITHUB_URL)
                .build();
    }

    @Test
    public void testGetContributors() throws Exception {
//        Call<List<GithubApiClient.ReposService.Contributor>> contributors;
//        contributors = mService.getContributors("square", "retrofit");

        GithubApiClient.ReposService reposService = mRetrofit.create(GithubApiClient.ReposService.class);
        Call<List<GithubApiClient.ReposService.Contributor>> contributors = reposService.getContributors("square", "retrofit");
        retrofit2.Response<List<GithubApiClient.ReposService.Contributor>> response = contributors.execute();
        List<GithubApiClient.ReposService.Contributor> list;
        list = response.body();

        MockService mockService = new MockService();
        Call<List<GithubApiClient.ReposService.Contributor>> call = mockService.getContributors("square", "retrofit");
        Response<List<GithubApiClient.ReposService.Contributor>> execute = call.execute();
        List<GithubApiClient.ReposService.Contributor> contributorList;
        contributorList = execute.body();
    }
}