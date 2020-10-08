package javaBase.多线程.线程安全的集合04;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 假设你想要的是一个大的线程安全的集而不是映射。并没有一个 ConcurrentHashSet 类，
 * 而且你肯定不想自己创建这样一个类。当然，可以使用 ConcurrentHashMap (包含“ 假” 值),
 * 不过这会得到一个映射而不是集， 而且不能应用 Set 接口的操作。
 * 静态 newKeySet 方法会生成一个 Set<K>, 这实际上是 ConcurrentHashMap<K, Boolean〉
 * 的一个包装器。（所有映射值都为 Boolean.TRUE, 不过因为只是要把它用作一个集，所以并
 * 不关心具体的值。）
 * Set<String> words = ConcurrentHashMap.<String>newKeySet();
 * 当然， 如果原来有一个映射，keySet 方法可以生成这个映射的键集。这个集是可变的。
 * 如果删除这个集的元素，这个键（以及相应的值）会从映射中删除。不过， 不能向键集增加
 * 元素，因为没有相应的值可以增加。Java SE 8 为 ConcurrentHashMap 增加了第二个 keySet 方
 * 法，包含一个默认值，可以在为集增加元素时使用：
 * Set<String> words = map.keySet(1L);
 * words.add("]'ava”）；
 * 如果 "Java” 在 words 中不存在， 现在它会有一个值 10
 */
public class 并发集视图_04 {
    public static void main(String[] args) {
        ConcurrentHashMap.KeySetView<String, Boolean> strings = ConcurrentHashMap.<String>newKeySet();
        strings.add("hello");
        System.out.println(strings);
    }
}
