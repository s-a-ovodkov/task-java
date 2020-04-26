package ru.otus.ovodkov.hw03;

import ru.otus.ovodkov.hw03.annotations.After;
import ru.otus.ovodkov.hw03.annotations.Before;
import ru.otus.ovodkov.hw03.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * "Запускатор" тестов
 *
 * @author Ovodkov Sergey
 * created on 22.04.2020
 */
public class TestRunner {
    private int testSuccessfulCount;
    private int countTest;
    private int testExceptionCount;

    private Constructor constructor;
    private List<Method> beforeMethods;
    private List<Method> afterMethods;

    private final String classString;

    public TestRunner(String classString) {
        this.classString = classString;
    }

    public void runTests() {
        try {
            Class<?> clazz = Class.forName(this.classString);
            this.constructor = clazz.getDeclaredConstructor();
            Method[] methods = clazz.getDeclaredMethods();
            List<Method> testMethods = getMethodsWithSpecificAnnotation(methods, Test.class);
            this.beforeMethods = getMethodsWithSpecificAnnotation(methods, Before.class);
            this.afterMethods = getMethodsWithSpecificAnnotation(methods, After.class);
            for (Method testMethod : testMethods) {
                this.countTest++;
                runTest(testMethod);
            }
        } catch (ClassNotFoundException | NoSuchMethodException exc) {
            System.out.println("Указанный тест не найден.");
            this.testExceptionCount++;
        }
    }

    public void showResult() {
        System.out.println("==========Result==========");
        System.out.println("Count: " + this.countTest);
        System.out.println("Successful: " + this.testSuccessfulCount);
        System.out.println("Exceptions: " + this.testExceptionCount);
        System.out.println("==========================");
    }

    private List<Method> getMethodsWithSpecificAnnotation(Method[] methods, Class<? extends Annotation> annotationClass) {
        return Arrays.stream(methods)
                .filter(x -> x.isAnnotationPresent(annotationClass))
                .collect(Collectors.toList());
    }

    private void runTest(Method method) {
        try {
            Object object = this.constructor.newInstance();
            try {
                for (Method beforeMethod : this.beforeMethods) {
                    beforeMethod.invoke(object);
                }
                method.invoke(object);
                this.testSuccessfulCount++;
            } catch (Exception exc) {
                this.testExceptionCount++;
            } finally {
                try {
                    for (Method afterMethod : this.afterMethods) {
                        afterMethod.invoke(object);
                    }
                } catch (IllegalAccessException | InvocationTargetException exc) {
                    // падение при работе after методов не учитываем
                }
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            this.testExceptionCount++;
        }
    }
}
