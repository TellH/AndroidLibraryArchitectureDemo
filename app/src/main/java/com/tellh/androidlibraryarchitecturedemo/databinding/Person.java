package com.tellh.androidlibraryarchitecturedemo.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.tellh.androidlibraryarchitecturedemo.BR;

/**
 * Created by tlh on 2016/7/16.
 */
public class Person extends BaseObservable {
    private boolean isMale;
    private int age;
    private String name;

    public Person(boolean isMale, int age, String name) {
        this.isMale = isMale;
        this.age = age;
        this.name = name;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
