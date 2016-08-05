package com.tellh.androidlibraryarchitecturedemo.rxjava.rxbus;

/**
 * Created by tlh on 2016/8/5.
 */
public class RxBusDemoPresenter {
    int i = 0;
    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int j = 0; j < 20; j++) {
                        Thread.sleep(1000);
                        RxBus.getDefault().post(new PresenterEvent(String.valueOf(++i)));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
