package com.tellh.androidlibraryarchitecturedemo.dagger2.component;

import com.tellh.androidlibraryarchitecturedemo.dagger2.MvpActivity;
import com.tellh.androidlibraryarchitecturedemo.dagger2.modules.MvpModule;

import dagger.Component;

/**
 * Created by tlh on 2016/7/29.
 */
@Component(modules = {MvpModule.class})
public interface MvpComponent {
    void inject(MvpActivity activity);
}
