package ru.otus.ovodkov.hw05.demo;

import ru.otus.ovodkov.hw05.anotations.Log;

/**
 * @author Ovodkov Sergey
 * created on 05.05.2020
 */
public class TestLoggingImpl implements TestLogging {
    @Override
    @Log
    public void Test1(int i) {
        System.out.println("Test 1");
    }

    @Override
    @Log
    public void Test2(int i) {
        System.out.println("Test 2");
    }

    @Override
    @Log
    public void Test3(int i) {
        System.out.println("Test 3");
    }

    @Override
    public void Test4(int i) {
        System.out.println("Test 4");
    }

    @Override
    @Log
    public void Test5(int i, String str) {
        System.out.println("Test 5");
    }

    @Override
    @Log
    public void Test6(int i) {
        System.out.println("Test 6");
    }

    @Override
    public void Test7(int i) {
        System.out.println("Test 7");
    }
}
