package com.tellh.androidlibraryarchitecturedemo.rxjava.rxbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tellh.androidlibraryarchitecturedemo.R;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class RxBusDemoActivity extends AppCompatActivity {

    private TextView tv_receive;
    Subscription subscribe;
    RxBusDemoPresenter presenter;
    private String TAG = "RxBusDemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus_demo);
        initView();
        Log.d(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        presenter = new RxBusDemoPresenter();
        subscribe = RxBus.getDefault().toObservable(PresenterEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PresenterEvent>() {
                    @Override
                    public void call(PresenterEvent presenterEvent) {
                        tv_receive.setText(presenterEvent.msg);
                    }
                });
        presenter.run();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        subscribe.unsubscribe();
    }

    private void initView() {
        tv_receive = (TextView) findViewById(R.id.tv_receive);
    }

    public void onClickTV(View view) {
        subscribe.unsubscribe();
    }
}
