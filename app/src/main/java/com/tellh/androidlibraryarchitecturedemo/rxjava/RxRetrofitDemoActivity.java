package com.tellh.androidlibraryarchitecturedemo.rxjava;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tellh.androidlibraryarchitecturedemo.R;
import com.tellh.androidlibraryarchitecturedemo.network.NetworkAccessListener;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class RxRetrofitDemoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, NetworkAccessListener {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<MovieEntity.SubjectsEntity> mList = new ArrayList<>();
    private BaseRecyclerAdapter<MovieEntity.SubjectsEntity> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_retrofit_demo);
        initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseRecyclerAdapter<MovieEntity.SubjectsEntity>(this, mList) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_movie_list;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, MovieEntity.SubjectsEntity item) {
                holder.setText(R.id.name, item.getTitle())
                        .setText(R.id.year, item.getYear());
                Glide.with(RxRetrofitDemoActivity.this)
                        .load(item.getImages().getMedium())
                        .into(holder.getImageView(R.id.iv_movie));
            }
        };
        mRecyclerView.setAdapter(adapter);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        getMovie();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
    }

    private void getMovie() {
        GetTopMovieModel.getInstance().getTopMovie(new Action1<List<MovieEntity.SubjectsEntity>>() {
            @Override
            public void call(List<MovieEntity.SubjectsEntity> subjectsEntities) {
                mList.clear();
                mList.addAll(subjectsEntities);
                adapter.notifyDataSetChanged();
            }
        }, 0, 50, this);
    }

    @Override
    public void onRefresh() {
        getMovie();
    }

    @Override
    public void onNetworkAccessStart() {
        if (!mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onNetworkAccessFinish() {
        if (mSwipeRefreshLayout.isRefreshing())
            mSwipeRefreshLayout.setRefreshing(false);
    }
}
