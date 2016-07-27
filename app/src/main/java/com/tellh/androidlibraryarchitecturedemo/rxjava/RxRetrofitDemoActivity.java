package com.tellh.androidlibraryarchitecturedemo.rxjava;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tellh.androidlibraryarchitecturedemo.R;
import com.tellh.androidlibraryarchitecturedemo.network.NetworkAccessListener;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class RxRetrofitDemoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        NetworkAccessListener, FooterLoadMoreAdapter.OnReachFooterListener {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<MovieEntity.SubjectsEntity> mList = new ArrayList<>();
    private FooterLoadMoreAdapter adapter;
    private int updateType;
    private static final int REFRESH = 0;
    private static final int LOAD_MORE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_retrofit_demo);
        initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FooterLoadMoreAdapter(this, mList);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnReachFootreListener(mRecyclerView, this);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        updateType = REFRESH;
        getMovie(0, 50);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
    }

    private void getMovie(int start, int count) {
        GetTopMovieModel.getInstance().getTopMovie(new Action1<List<MovieEntity.SubjectsEntity>>() {
            @Override
            public void call(List<MovieEntity.SubjectsEntity> subjectsEntities) {
                if (updateType == REFRESH)
                    mList.clear();
                mList.addAll(subjectsEntities);
                adapter.notifyDataSetChanged();
            }
        }, start, count, this);
    }

    @Override
    public void onRefresh() {
        updateType = REFRESH;
        getMovie(0, mList.size());
    }

    @Override
    public void onNetworkAccessStart() {
        if (!mSwipeRefreshLayout.isRefreshing() && updateType == REFRESH)
            mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onNetworkAccessFinish() {
        if (mSwipeRefreshLayout.isRefreshing() && updateType == REFRESH) {
            mSwipeRefreshLayout.setRefreshing(false);
            return;
        }
        adapter.setFooterStatus(FooterLoadMoreAdapter.PULL_TO_LOAD_MORE);
    }

    @Override
    public void onReach() {
        int itemCount = adapter.getItemCount();
        if (itemCount == 250) {
            adapter.setFooterStatus(FooterLoadMoreAdapter.NO_MORE);
            return;
        }
        updateType = LOAD_MORE;
        getMovie(itemCount, 50);
    }
}
