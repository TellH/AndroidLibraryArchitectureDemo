package com.tellh.androidlibraryarchitecturedemo.rxjava;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tellh.androidlibraryarchitecturedemo.MyApplication;
import com.tellh.androidlibraryarchitecturedemo.R;
import com.tellh.androidlibraryarchitecturedemo.Utils;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxjavaAppListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ApplicationAdapter mAdapter;

    private File mFilesDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_app_list);
        initView();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ApplicationAdapter(new ArrayList<AppInfo>(), R.layout.item_app_list);
        mRecyclerView.setAdapter(mAdapter);

        //调整下拉圆的位置
        mSwipeRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        // Progress
        mSwipeRefreshLayout.setEnabled(false);
        mSwipeRefreshLayout.setRefreshing(true);
        mRecyclerView.setVisibility(View.GONE);

        getFileDir()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<File>() {
                    @Override
                    public void call(File file) {
                        mFilesDir = file;
                        refreshTheList();
                    }
                });
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
    }

    private Observable<File> getFileDir() {
        return Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                subscriber.onNext(MyApplication.getInstance().getApplicationContext().getFilesDir());
                subscriber.onCompleted();
            }
        });
    }

    private void refreshTheList() {
        getApps()
//                .compose(RxJavaUtils.<AppInfo>applySchedulers())
                .compose(SchedulersCompat.<AppInfo>applyIoSchedulers())
                .toSortedList()
                .flatMap(new Func1<List<AppInfo>, Observable<AppInfo>>() {
                    @Override
                    public Observable<AppInfo> call(List<AppInfo> appInfos) {
                        return Observable.from(appInfos);
                    }
                })
                //过滤null对象
                .filter(new Func1<AppInfo,Boolean>(){
                    @Override
                    public Boolean call(AppInfo appInfo){
                        return appInfo != null;
                    }
                })
//                .filter(new Func1<AppInfo, Boolean>() {
//                    @Override
//                    public Boolean call(AppInfo appInfo) {
//                        return appInfo.getName().startsWith("C");
//                    }
//                })
//                .take(3)
                .subscribe(new Observer<AppInfo>() {
                    @Override
                    public void onCompleted() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mSwipeRefreshLayout.setEnabled(true);
                        Toast.makeText(RxjavaAppListActivity.this, "Here is the list!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxjavaAppListActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(AppInfo appInfos) {
                        mRecyclerView.setVisibility(View.VISIBLE);
                        mAdapter.addApplication(mAdapter.getItemCount(),appInfos);
                    }
                });
    }

    private void storeList(final List<AppInfo> appInfos) {
        ApplicationsList.getInstance().setList(appInfos);

        Schedulers.io().createWorker().schedule(new Action0() {
            @Override
            public void call() {
                SharedPreferences sharedPref = RxjavaAppListActivity.this.getPreferences(Context.MODE_PRIVATE);
                Type appInfoType = new TypeToken<List<AppInfo>>(){}.getType();
                sharedPref.edit().putString("APPS", new Gson().toJson(appInfos, appInfoType)).apply();
            }
        });
    }

    private Observable<AppInfo> getApps() {
        return Observable.create(new Observable.OnSubscribe<AppInfo>() {
            @Override
            public void call(Subscriber<? super AppInfo> subscriber) {
                List<AppInfoRich> apps = new ArrayList<>();

                final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

                List<ResolveInfo> infos = RxjavaAppListActivity.this.getPackageManager().queryIntentActivities(mainIntent, 0);
                for (ResolveInfo info : infos) {
                    apps.add(new AppInfoRich(RxjavaAppListActivity.this, info));
                }

                for (AppInfoRich appInfo : apps) {
                    Bitmap icon = Utils.drawableToBitmap(appInfo.getIcon());
                    String name = appInfo.getName();
                    String iconPath = mFilesDir + "/" + name;
                    Utils.storeBitmap(MyApplication.getInstance(), icon, name);

                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    subscriber.onNext(new AppInfo(name, iconPath, appInfo.getLastUpdateTime()));
                }
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        refreshTheList();
    }
}
