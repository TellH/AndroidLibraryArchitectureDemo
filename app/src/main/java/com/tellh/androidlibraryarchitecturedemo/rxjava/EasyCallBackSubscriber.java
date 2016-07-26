package com.tellh.androidlibraryarchitecturedemo.rxjava;

import com.tellh.androidlibraryarchitecturedemo.network.NetworkAccessListener;

import rx.Subscriber;

/**
 * Created by tlh on 2016/7/26.
 */
public abstract class EasyCallBackSubscriber<T> extends Subscriber<T> {
    private NetworkAccessListener mListener;

    public EasyCallBackSubscriber(NetworkAccessListener listener) {
        mListener = listener;
    }

    @Override
    public void onCompleted() {
        if (mListener!=null)
            mListener.onNetworkAccessFinish();
    }

    @Override
    public void onError(Throwable e) {

    }

    public abstract void onNext(T t) ;

}
