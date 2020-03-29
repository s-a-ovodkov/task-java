package ru.otus.ovodkov.hw01;

import com.google.common.base.Splitter;

public class Application {
    public static void main(String[] args) {
        String str = "word,   test, , otus,     java";
        Iterable<String> words = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split(str);
        words.forEach(System.out::println);
    }
}