package com.tellh.androidlibraryarchitecturedemo.base;

import javax.inject.Named;

public class GetUserListPresenter extends BasePresenter<GetUserListContract.View> implements GetUserListContract.Presenter {
    private static final int GetUserList = 0;

    public GetUserListPresenter(@Named("GetUserList") UseCase useCaseGetUserList) {
        registerUserCase(GetUserList, useCaseGetUserList);
    }
}