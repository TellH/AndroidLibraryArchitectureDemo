package com.tellh.androidlibraryarchitecturedemo.rxjava;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.tellh.androidlibraryarchitecturedemo.R;

import java.util.List;

public class FooterLoadMoreAdapter extends BaseRecyclerAdapter<MovieEntity.SubjectsEntity> {
    public static final int PULL_TO_LOAD_MORE = 0;
    public static final int LOADING = 1;
    public static final int NO_MORE = 2;
    private int mFooterStatus = PULL_TO_LOAD_MORE;

    public FooterLoadMoreAdapter(Context ctx, List<MovieEntity.SubjectsEntity> list) {
        super(ctx, list);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_movie_list;
    }

    @Override
    protected void bindData(RecyclerViewHolder holder, int position, MovieEntity.SubjectsEntity item) {
        holder.setText(R.id.name, item.getTitle())
                .setText(R.id.year, item.getYear());
        Glide.with(mContext)
                .load(item.getImages().getMedium())
                .into(holder.getImageView(R.id.iv_movie));
    }

    @Override
    protected int getFooterLayoutId() {
        return R.layout.footer_load_more;
    }

    @Override
    protected void bindFooter(RecyclerViewHolder holder, int position) {
        View itemView = holder.getItemView();
        if (mItems.size() == 0) {
            itemView.setVisibility(View.INVISIBLE);
            return;
        }
        if (itemView.getVisibility() == View.INVISIBLE)
            itemView.setVisibility(View.VISIBLE);
        ProgressBar progressBar = (ProgressBar) holder.getView(R.id.progressBar);
        switch (mFooterStatus) {
            case PULL_TO_LOAD_MORE:
                progressBar.setVisibility(View.VISIBLE);
                holder.setText(R.id.tv_footer, "上拉加载更多...");
                break;
            case LOADING:
                progressBar.setVisibility(View.VISIBLE);
                holder.setText(R.id.tv_footer, "正在拼命加载...");
                break;
            case NO_MORE:
                holder.setText(R.id.tv_footer, "没有更多了！");
                progressBar.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void setFooterStatus(int status) {
        mFooterStatus = status;
        notifyDataSetChanged();
    }

    public void setOnReachFootreListener(RecyclerView recyclerView, final OnReachFooterListener listener) {
        if (recyclerView == null || listener == null)
            return;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isReachBottom(recyclerView, newState) && mFooterStatus != LOADING) {
                    setFooterStatus(LOADING);
                    listener.onReach();
                }
            }
        });
    }

    public boolean isReachBottom(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            return ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition()
                    == recyclerView.getAdapter().getItemCount() - 1;
        }
        return false;
    }

    interface OnReachFooterListener {
        void onReach();
    }

    @Override
    public int getItemCount() {
        return mItems.size() + 1;//算上footer
    }
}