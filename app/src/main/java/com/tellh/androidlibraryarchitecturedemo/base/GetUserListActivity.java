package com.tellh.androidlibraryarchitecturedemo.base;

import android.os.Bundle;

import com.tellh.androidlibraryarchitecturedemo.R;

import javax.inject.Inject;

public class GetUserListActivity extends BaseActivity implements GetUserListContract.View {
    @Inject
    GetUserListContract.Presenter presenter;

    @Override
    protected void initData(Bundle savedInstanceState) {
        initDagger();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_empty;
    }

    private void initDagger() {
        DaggerGetUserListComponent.builder()
                .build().inject(this);
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}