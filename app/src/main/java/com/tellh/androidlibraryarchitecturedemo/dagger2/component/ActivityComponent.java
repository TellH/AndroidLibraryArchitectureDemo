package com.tellh.androidlibraryarchitecturedemo.dagger2.component;

import android.app.Activity;

import com.tellh.androidlibraryarchitecturedemo.dagger2.scope.PerActivity;
import com.tellh.androidlibraryarchitecturedemo.dagger2.modules.ActivityModule;

import dagger.Component;

/**
 * Created by tlh on 2016/7/28.
 */
@PerActivity
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity activity();
}
