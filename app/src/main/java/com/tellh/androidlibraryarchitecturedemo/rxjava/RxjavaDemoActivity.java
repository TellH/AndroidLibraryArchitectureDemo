package com.tellh.androidlibraryarchitecturedemo.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tellh.androidlibraryarchitecturedemo.R;

import rx.Observable;
import rx.Subscriber;

public class RxjavaDemoActivity extends AppCompatActivity {

    private static final String TAG = "RxjavaDemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_demo);
        observableCreate2();
    }

    private void observableCreate2(){
        final String[] strings = new String[]{"hehe", "haha", "xixi"};
//        Observable.from(strings)
        Observable.just("hehe", "haha", "xixi")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Subscriber is informed that task is completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "Subscriber receive " + s);
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.d(TAG, "Subscriber is informed that task is started");
                    }
                });
    }
    private void observableCreate() {
        final String[] strings = new String[]{"hehe", "haha", "xixi"};
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onStart();
                for (String string : strings) {
                    subscriber.onNext(string);
                }
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Subscriber is informed that task is completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Subscriber receive " + s);
            }

            @Override
            public void onStart() {
                super.onStart();
                Log.d(TAG, "Subscriber is informed that task is started");
            }
        });
    }

}
