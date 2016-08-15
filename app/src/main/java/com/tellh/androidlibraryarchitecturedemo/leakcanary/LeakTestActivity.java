package com.tellh.androidlibraryarchitecturedemo.leakcanary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tellh.androidlibraryarchitecturedemo.R;

public class LeakTestActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        textView = (TextView) findViewById(R.id.tvResponseStr);
    }

    @Override
    protected void onDestroy() {
        TestDataModel.getInstance().setRetainedTextView(textView);
        super.onDestroy();
//        MyApplication.getRefWatcher(this).watch(this);
    }
}
