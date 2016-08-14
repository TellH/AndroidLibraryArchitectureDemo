package com.tellh.androidlibraryarchitecturedemo.base;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class GetUserListModule {
    @Provides
    public GetUserListContract.Presenter provideGetUserListPresenter(@Named("GetUserList") UseCase useCaseGetUserList) {
        return new GetUserListPresenter(useCaseGetUserList);
    }

    @Provides
    @Named("GetUserList")
    public UseCase provideGetUserListUseCase() {
        return new GetUserListUseCase();
    }
}
