package ru.otus.ovodkov.hw04;

import java.util.ArrayList;
import java.util.List;

/*
Параметры первого запуска:
-Xms512m
-Xmx512m
-Xlog:gc=debug:file=/logs/1/gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=/logs/1/dump
-XX:+UseG1GC
-XX:MaxGCPauseMillis=100

Параметры второго запуска:
-Xms512m
-Xmx512m
-Xlog:gc=debug:file=/logs/1/gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=/logs/1/dump
-XX:+UseSerialGC
-XX:MaxGCPauseMillis=100


 */


/**
 * @author Ovodkov Sergey
 * created on 02.05.2020
 */
public class HomeworkGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=====Start running application=====");
        createList(10000000, 1000);
        System.out.println("=====End running application=====");
    }

    private static void createList(int elementCounter, int loopCounter) throws InterruptedException {
        for (int i = 0; i < loopCounter; i++) {
            List<Object> list = new ArrayList<>();
            for (int j = 0; j < elementCounter; j++) {
                list.add(new String(new char[0]));
            }
            Thread.sleep(10);
        }
    }
}
