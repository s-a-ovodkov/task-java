package ru.otus.ovodkov.hw05;

import ru.otus.ovodkov.hw05.demo.TestLogging;
import ru.otus.ovodkov.hw05.proxy.CustomProxy;

/**
 * @author Ovodkov Sergey
 * created on 04.05.2020
 */
public class Application {
    public static void main(String[] args) {
        TestLogging testLogging = CustomProxy.createTestLogging();

        testLogging.Test1(1);
        testLogging.Test2(2);
        testLogging.Test3(3);
        testLogging.Test4(4);
        testLogging.Test5(5, "5");
        testLogging.Test6(6);
        testLogging.Test7(7);
    }
}
