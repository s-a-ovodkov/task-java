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

    private String classString;

    public TestRunner(String classString) {
        this.classString = classString;
    }

    public void runTests() {
        try {
            Class<?> clazz = Class.forName(this.classString);
            Constructor constructor = clazz.getDeclaredConstructor();
            Method[] methods = clazz.getDeclaredMethods();
            List<Method> testMethods = getMethodsWithSpecificAnnotation(methods, Test.class);
            List<Method> beforeMethods = getMethodsWithSpecificAnnotation(methods, Before.class);
            List<Method> afterMethods = getMethodsWithSpecificAnnotation(methods, After.class);
            for (Method testMethod : testMethods) {
                this.countTest++;
                runTest(testMethod, constructor, beforeMethods, afterMethods);
                this.testSuccessfulCount++;
            }
        } catch (ClassNotFoundException | NoSuchMethodException exc) {
            System.out.println("Указанный тест не найден.");
            this.testSuccessfulCount++;
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException exc) {
            System.out.println("У теста отсутствует конструктор по умолчанию.");
            this.testExceptionCount++;
        } catch (Exception exc) {
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

    private void runTest(Method method,
                         Constructor constructor,
                         List<Method> beforeMethods,
                         List<Method> afterMethods)
            throws IllegalAccessException, InvocationTargetException, InstantiationException {

        Object object = constructor.newInstance();
        for (Method beforeMethod : beforeMethods) {
            beforeMethod.invoke(object);
        }
        method.invoke(object);
        for (Method afterMethod : afterMethods) {
            afterMethod.invoke(object);
        }
    }
}
