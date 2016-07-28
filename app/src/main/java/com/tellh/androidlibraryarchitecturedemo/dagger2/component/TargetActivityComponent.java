package com.tellh.androidlibraryarchitecturedemo.dagger2.component;

import com.tellh.androidlibraryarchitecturedemo.dagger2.modules.NetworkAccessModule;
import com.tellh.androidlibraryarchitecturedemo.dagger2.scope.PerActivity;
import com.tellh.androidlibraryarchitecturedemo.dagger2.TargetActivity;
import com.tellh.androidlibraryarchitecturedemo.dagger2.modules.ActivityModule;
import com.tellh.androidlibraryarchitecturedemo.dagger2.modules.GsonModule;

import dagger.Component;

/**
 * Created by tlh on 2016/7/28.
 */
//如果依赖的Module有@Singleton，Component也要有@Singleton
@PerActivity
@Component(dependencies = {AppComponent.class}, modules = {GsonModule.class, NetworkAccessModule.class, ActivityModule.class})
public interface TargetActivityComponent extends ActivityComponent {
    void inject(TargetActivity activity);
}
