package com.tellh.androidlibraryarchitecturedemo.test;

import junit.framework.TestCase;


/**
 * Created by tlh on 2016/8/16 :)
 */
public class GreetingTest extends TestCase {
    Greeting greeting;

    public void setUp() throws Exception {
        greeting = new Greeting("World");
    }

    public void tearDown() throws Exception {
        System.out.println("Test is over! ");
    }

    public void testGetGreetings() throws Exception {
        assertEquals("Hello,World", greeting.getGreetings());
    }
}