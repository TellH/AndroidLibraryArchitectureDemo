package com.tellh.androidlibraryarchitecturedemo.network;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class NetworkCallback<ResponseType> {
    private Type mType;

    public Type getType() {
        if (mType==null)
            mType = getSuperclassTypeParameter(getClass());
        return mType;
    }

    public abstract void onResponse(ResponseType response);

    public abstract void onError(Exception e);

    //获得泛型类型
    static public Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return parameterized.getActualTypeArguments()[0];
//        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }
}