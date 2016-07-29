package com.tellh.androidlibraryarchitecturedemo.dagger2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.tellh.androidlibraryarchitecturedemo.MyApplication;
import com.tellh.androidlibraryarchitecturedemo.R;
import com.tellh.androidlibraryarchitecturedemo.network.NetworkAccess;

import javax.inject.Inject;
import javax.inject.Named;

public class TargetActivity extends AppCompatActivity {

    @Inject
    Gson gson;
    @Inject @Named("Volley")
    NetworkAccess networkAccess;
    @Inject
    Context mCtx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        MyApplication.getInstance().getTargetActivityComponent(this).inject(this);
        //do something here after all valuable injected. :)
    }


}
