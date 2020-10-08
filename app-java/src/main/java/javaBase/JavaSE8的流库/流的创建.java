package javaBase.JavaSE8的流库;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class 流的创建 {
    public static void main(String[] args) {

        ArrayList<Object> objects = new ArrayList<>();

        //方式1
        Stream<Object> stream = objects.stream();

        Object[] objects1 = objects.toArray();
        //方式2
        Stream<Object> objectStream = Stream.of(objects1);
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);

        //方式3 创建不包含任何元素的流
        Stream<Object> empty = Stream.empty();

        //方式4 无限流
        Object[] objects2 = Stream.iterate(1.0, x -> 2 * x)
                .peek(System.out::println)
                .limit(10)
                .toArray();
        System.out.println(Arrays.toString(objects2));

        String a ="anullb" ;
        System.out.println(a.contains("null"));


        Optional<Integer> any = Arrays.asList(1, 2, 3, 4).stream().parallel().filter(x -> x> 5 ).findAny();
        System.out.println("any="+any.orElse(0 ));

        boolean b = Arrays.asList(1, 2, 3, 4).stream().parallel().anyMatch(x -> x > 1);
        boolean b1 = Arrays.asList(1, 2, 3, 4).stream().parallel().allMatch(x -> x > 1);
        boolean b2 = Arrays.asList(1, 2, 3, 4).stream().parallel().noneMatch(x -> x < 1);
        System.out.println(b);
        System.out.println(b1);
        System.out.println(b2);

    }
}
