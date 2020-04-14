package ru.otus.ovodkov.hw02;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Ovodkov Sergey on 14.04.2020
 */
public class Application {
    public static void main(String[] args) {
        DIYArrayList<Integer> diyArrayList = new DIYArrayList<>();
        IntStream.range(1, 26)
                .boxed()
                .forEach(diyArrayList::add);

        System.out.println("=== Collections.addAll(Collection<? super T> c, T... elements) ===");
        System.out.println("Source collection: ");
        System.out.println(diyArrayList.toString());
        Collections.addAll(diyArrayList, 26, 27, 28, 29, 30);
        System.out.println("Change collection: ");
        System.out.println(diyArrayList.toString());
        System.out.println();

        DIYArrayList<Integer> src = new DIYArrayList<>();
        IntStream.generate(() -> new Random().nextInt(100))
                .limit(30)
                .forEach(src::add);

        System.out.println("=== Collections.static <T> void copy(List<? super T> dest, List<? extends T> src) ===");
        System.out.println("Destination collection:");
        System.out.println(diyArrayList.toString());
        System.out.println("Source collection:");
        System.out.println(src.toString());

        Collections.copy(diyArrayList, src);

        System.out.println("Result:");
        System.out.println(diyArrayList.toString());
        System.out.println();

        System.out.println("=== Collections.static <T> void sort(List<T> list, Comparator<? super T> c) ===");
        DIYArrayList<Integer> list = new DIYArrayList<>();
        IntStream.generate(() -> new Random().nextInt(100))
                .limit(30)
                .forEach(list::add);
        System.out.println("Source collection:");
        System.out.println(list.toString());

        Collections.sort(list, Comparator.naturalOrder());

        System.out.println("Sorted collection:");
        System.out.println(list.toString());
        System.out.println();
    }
}
