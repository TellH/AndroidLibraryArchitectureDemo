package com.tellh.androidlibraryarchitecturedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tellh.androidlibraryarchitecturedemo.databinding.DataBindPrimaryPracticeActivity;
import com.tellh.androidlibraryarchitecturedemo.databinding.DataBindingRVActivity;
import com.tellh.androidlibraryarchitecturedemo.glide.GlideDemoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
