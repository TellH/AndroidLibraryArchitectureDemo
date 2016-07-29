package com.tellh.androidlibraryarchitecturedemo.dagger2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tellh.androidlibraryarchitecturedemo.R;
import com.tellh.androidlibraryarchitecturedemo.dagger2.component.DaggerMvpComponent;
import com.tellh.androidlibraryarchitecturedemo.dagger2.modules.MvpModule;

import javax.inject.Inject;

public class MvpActivity extends AppCompatActivity implements MvpView {

    @Inject MvpPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        DaggerMvpComponent.builder().mvpModule(new MvpModule(this)).build();
    }
}
