package com.tellh.androidlibraryarchitecturedemo.rxjava;

import com.tellh.androidlibraryarchitecturedemo.network.NetworkAccessListener;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by tlh on 2016/7/26.
 */
public class RxJavaUtils {
    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    public static <T> Observable.Transformer<T,T> setListener(final NetworkAccessListener listener){
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (listener!=null)
                        listener.onNetworkAccessStart();
                    }
                }).doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        if (listener!=null)
                            listener.onNetworkAccessFinish();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
