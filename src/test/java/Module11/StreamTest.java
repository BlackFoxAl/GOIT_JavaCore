package Module11;

import java.util.*;
import java.util.stream.*;

public class StreamTest {
    public static List<String> oddList(List<String> listName) { //task1
        return IntStream
                .range(0, listName.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(num ->Integer.toString(num + 1)
                                        .concat(". ")
                                        .concat(listName.get(num)))
                .collect(Collectors.toList());
    }
    public static List<String> sortUpperList(List<String> listName) { //task2
        return listName
                .stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .toList();
    }
    public static List<String> sortNumbersFromArray(String[] stringNumbers) { //task3

        return Arrays.asList(Arrays.asList(stringNumbers)
                                .stream()
                                .collect(Collectors.joining(", "))
                                .split(", "))
                .stream()
                .sorted()
                .toList();
    }
    public static LongStream streamRandom(int seed, long a, int c, int m) { //task4
        return LongStream.iterate(seed,n -> 1 * (a * n + c) % m);
    }
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) { //task5
        Iterator<T> iteratorFirst = first.iterator();
        Iterator<T> iteratorSecond = second.iterator();
        List<T> elements = new LinkedList<>();
        while (iteratorFirst.hasNext() && iteratorSecond.hasNext()) {
            elements.add(iteratorFirst.next());
            elements.add(iteratorSecond.next());
        }
        return elements.stream();
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ivan", "Peter", "Alex","John", "Bill");
        String[] numbers = {"1, 2, 0", "4, 5"};
        Stream<Integer> first = Stream.of(9, 8, 7, 6);
        Stream<Integer> second = Stream.of(0, 1, 2, 3, 4, 5);
        System.out.println("Task1 " + oddList(names));                             //test task1
        System.out.println("Task2 " + sortUpperList(names));                       //test task2
        System.out.println("Task3 " + sortNumbersFromArray(numbers));              //test task3
        System.out.println("Task4 ");
        streamRandom(0,25_214_903_917L,11,2^48)                     //test task4
                .limit(10)
                .forEach(System.out::println);
        System.out.println("Task5 ");
        zip(first,second).forEach(System.out::println);                           //test task5
    }
}
