package com.tellh.androidlibraryarchitecturedemo.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tellh.androidlibraryarchitecturedemo.BR;

import java.util.List;

/**
 * Created by tlh on 2016/7/16.
 */
abstract public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.BindingViewHolder> {
    private List items;

    public CommonAdapter(List items) {
        this.items = items;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(getItemLayoutId(viewType), parent, false);
        return new BindingViewHolder(itemView);
    }

    protected abstract int getItemLayoutId(int viewType);

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        holder.bind(items.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class BindingViewHolder extends RecyclerView.ViewHolder{
        private ViewDataBinding binding;
        public BindingViewHolder(View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
        public void bind(@NonNull Object item){
            binding.setVariable(BR.item,item);
        }
    }
}
