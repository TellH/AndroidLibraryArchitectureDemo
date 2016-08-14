package com.tellh.androidlibraryarchitecturedemo.base;

import dagger.Component;

@Component(modules = {GetUserListModule.class}, dependencies = {})
public interface GetUserListComponent {
    void inject(GetUserListActivity activity);
}
