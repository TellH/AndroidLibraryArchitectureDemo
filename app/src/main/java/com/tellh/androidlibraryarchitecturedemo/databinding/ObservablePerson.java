package com.tellh.androidlibraryarchitecturedemo.databinding;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by tlh on 2016/7/16.
 * Observable的字段会自动刷新
 * person.age.get();
 * person.name.set("");
 */
public class ObservablePerson {
    public final ObservableBoolean isMale = new ObservableBoolean();
    public final ObservableInt age = new ObservableInt();
    public final ObservableField<String> name = new ObservableField<>();
    //在xml中访问
    //android:text='@{person.info["company"]}'
    public final ObservableArrayMap<String, Object> info = new ObservableArrayMap<>();
    //android:text="@{person.friends[0]}"
    public final ObservableArrayList<String> friends = new ObservableArrayList<>();
}
