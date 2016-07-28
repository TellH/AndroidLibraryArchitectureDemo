package com.tellh.androidlibraryarchitecturedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tellh.androidlibraryarchitecturedemo.dagger2.TargetActivity;
import com.tellh.androidlibraryarchitecturedemo.databinding.DataBindPrimaryPracticeActivity;
import com.tellh.androidlibraryarchitecturedemo.databinding.DataBindingRVActivity;
import com.tellh.androidlibraryarchitecturedemo.glide.GlideDemoActivity;
import com.tellh.androidlibraryarchitecturedemo.okhttp.OkExampleActivity;
import com.tellh.androidlibraryarchitecturedemo.retrofit.RetrofitActivity;
import com.tellh.androidlibraryarchitecturedemo.rxjava.RxRetrofitDemoActivity;
import com.tellh.androidlibraryarchitecturedemo.rxjava.RxjavaAppListActivity;
import com.tellh.androidlibraryarchitecturedemo.volley.VolleyDemoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    public void onClickDataBindingPrimaryPractice(View view) {
        startActivity(new Intent(MainActivity.this, DataBindPrimaryPracticeActivity.class));
    }

    public void onClickDataBindingRecyclerView(View view) {
        startActivity(new Intent(MainActivity.this, DataBindingRVActivity.class));
    }

    public void onClickGlideDemo(View view) {
        startActivity(new Intent(MainActivity.this, GlideDemoActivity.class));
    }

    public void onClickVolleyDemo(View view) {
        startActivity(new Intent(MainActivity.this, VolleyDemoActivity.class));
    }

    public void onClickOkHttpDemo(View view) {
        startActivity(new Intent(MainActivity.this, OkExampleActivity.class));
    }

    public void onClickRetrofitDemo(View view) {
        startActivity(new Intent(MainActivity.this, RetrofitActivity.class));
    }

    public void onClickRxJavaDemo(View view) {
        startActivity(new Intent(MainActivity.this, RxjavaAppListActivity.class));
    }

    public void onClickRxRetrofitDemo(View view) {
        startActivity(new Intent(MainActivity.this, RxRetrofitDemoActivity.class));
    }

    public void onClickDagger2Demo(View view) {
        startActivity(new Intent(MainActivity.this, TargetActivity.class));
    }
}
