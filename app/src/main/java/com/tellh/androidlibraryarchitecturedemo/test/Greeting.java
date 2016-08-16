package com.tellh.androidlibraryarchitecturedemo.test;

public class Greeting {
    private String name;

    public Greeting(String name) {
        this.name = name;
    }

    public String getGreetings() {
        return "Hello," + name;
    }

}