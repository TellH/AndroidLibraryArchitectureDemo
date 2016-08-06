package com.tellh.androidlibraryarchitecturedemo.rxjava.frame;

public interface IFun<T, R> {
    R call(T t);
}