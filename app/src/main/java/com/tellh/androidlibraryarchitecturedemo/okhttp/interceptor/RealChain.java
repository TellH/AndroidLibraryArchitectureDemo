package com.tellh.androidlibraryarchitecturedemo.okhttp.interceptor;

import java.util.List;

/**
 * Created by tlh on 2016/7/22.
 */
public class RealChain implements Interceptor.Chain {
    private final int index;
    private final List<Interceptor> interceptors;
    private final Input input;

    public RealChain(List<Interceptor> interceptors, int index, Input input) {
        this.index=index;
        this.interceptors=interceptors;
        this.input=input;
    }

    @Override
    public Input input() {
        return this.input;
    }

    @Override
    public Output proceed(Input input) {
        if (index >= interceptors.size()) throw new AssertionError();
        // Call the next interceptor in the chain.
        RealChain next = new RealChain(
                interceptors, index + 1, input);
        Interceptor interceptor = interceptors.get(index);
        return interceptor.intercept(next);
    }
}
