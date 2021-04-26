package javaBase.多线程.同步器_08;

import java.util.ArrayList;

/**
 * 同步队列是一种将生产者与消费者线程配对的机制。当一个线程调用 SynchronousQueue的 put 方法时，
 * 它会阻塞直到另一个线程调用 take 方法为止， 反之亦然。
 *
 * 与 Exchanger 的情况不同， 数据仅仅沿一个方向传递，从生产者到消费者。
 * 即使 SynchronousQueue 类实现了 BlockingQueue 接口， 概念上讲， 它依然是一个队列。但是它没有包含任何元素，它的 size 方法总是返回 0。
 */
public class 同步队列 {
    public static void main(String[] args) {
        ArrayList<String> objects = new ArrayList<>();
        long count = objects.stream().filter(x -> x.length() > 2).count();

        Type[] values = Type.values();
        for (Type value : values) {
            System.out.println(value);
        }
    }
}
enum Type {
    /**
     * The flag is associated with spliterator characteristics.
     */
    SPLITERATOR,

    /**
     * The flag is associated with stream flags.
     */
    STREAM,

    /**
     * The flag is associated with intermediate operation flags.
     */
    OP,

    /**
     * The flag is associated with terminal operation flags.
     */
    TERMINAL_OP,

    /**
     * The flag is associated with terminal operation flags that are
     * propagated upstream across the last stateful operation boundary
     */
    UPSTREAM_TERMINAL_OP
}
