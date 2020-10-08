package javaBase.多线程.线程安全的集合04;

/**
 * 从 Java 的初始版本开始，Vector 和 Hashtable 类就提供了线程安全的动态数组和散列表的
 * 实现。现在这些类被弃用了， 取而代之的是 AnayList 和 HashMap 类。这些类不是线程安全
 * 的，而集合库中提供了不同的机制。任何集合类都可以通过使用同步包装器（synchronization
 * wrapper) 变成线程安全的：
 * List<E> synchArrayList = Collections ,synchronizedList(new ArrayList<E>());
 * Map<K, V> synchHashMap = Col1ections.synchroni zedMap(new HashMap<K, V>0)；
 * 结果集合的方法使用锁加以保护，提供了线程安全访问。
 * 应该确保没有任何线程通过原始的非同步方法访问数据结构。最便利的方法是确保不保
 * 存任何指向原始对象的引用， 简单地构造一个集合并立即传递给包装器，像我们的例子中所
 * 做的那样。
 * 如果在另一个线程可能进行修改时要对集合进行迭代，仍然需要使用“ 客户端” 锁定：
 * synchronized (synchHashMap)
 * {
 * Iterator<K> iter = synchHashMap.keySet().iterator();
 * while (iter.hasNextO) . .
 * }
 * 如果使用“ foreach” 循环必须使用同样的代码， 因为循环使用了迭代器。注意：如果在
 * 迭代过程中，别的线程修改集合，迭代器会失效， 抛出 ConcurrentModificationException 异
 * 常。同步仍然是需要的， 因此并发的修改可以被可靠地检测出来。
 *
 * 最好使用 java.Util.COnciirrent 包中定义的集合， 不使用同步包装器中的。特别是， 假如它
 * 们访问的是不同的桶， 由于 ConcurrentHashMap 已经精心地实现了，多线程可以访问它而且
 * 不会彼此阻塞。有一个例外是经常被修改的数组列表。在那种情况下，同步的 ArrayList 可
 * 以胜过 CopyOnWriteArrayList 0
 *
 * TODO java.util.CoIlections 1.2
 * • static <E> Collect1 on<E> synchronizedCollection(Collection<E> c)
 * • static <E> List synchronizedList(List<E> c)
 * • static <E> Set synchronizedSet(Set<E> c)
 * • static <E> SortedSet synchronizedSortedSet(SortedSet <E> c)
 * • static <K, V> Map<K, V> synchronizedMap(Map<K, V> c)
 * • static <K, V> SortedMap<K, V> synchronizedSortedMap(SortedMap<K, V> c)
 * 构建集合视图， 该集合的方法是同步的。
 */
public class 较早的线程安全集合_07 {
}
