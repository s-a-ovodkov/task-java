package ru.otus.ovodkov.hw03;

/**
 * @author Ovodkov Sergey
 * created on 22.04.2020
 */
public class Application {
    public static void main(String[] args) {
        TestRunner runner = new TestRunner("ru.otus.ovodkov.hw03.TestClass");
        runner.runTests();
        runner.showResult();
    }
}
