package ru.otus.ovodkov.hw03;

import ru.otus.ovodkov.hw03.annotations.*;

import java.util.stream.IntStream;

/**
 * "Тесты"
 *
 * @author Ovodkov Sergey
 * created on 22.04.2020
 */
public class TestClass {

    @Before
    public void setUp() {
        System.out.println("setUp");
    }

    @Test
    public void shouldCreateIntStream() {
        IntStream intStream = IntStream.rangeClosed(1, 5);
        System.out.println("shouldCreateIntStream");
    }

    @Test
    public void shouldCreateDoubleStream() {
        System.out.println("shouldCreateDoubleStream");
    }

    @Test
    public void testException() {
        System.out.println("testException");
        int temp = 12/0;
    }

    @After
    public void setDown() {
        System.out.println("setDown");
    }
}
