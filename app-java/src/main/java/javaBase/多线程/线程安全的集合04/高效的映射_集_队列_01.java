package javaBase.多线程.线程安全的集合04;

/**
 * TODO 1. 如果多线程要并发地修改一个数据结构， 例如散列表， 那么很容易会破坏这个数据结构
 * 例如，   一个线程可能要开始向表中插入一个新元素。假
 *          定在调整散列表各个桶之间的链接关系的过程中， 被剥夺了控制权。如果另一个线程也开始
 *          遍历同一个链表， 可能使用无效的链接并造成混乱， 会抛出异常或者陷人死循环
 * TODO 2. 可以通过提供锁来保护共享数据结构， 但是选择线程安全的实现作为替代可能更容易.
 * TODO 3. 阻塞队列就是线程安全的集合
 *
 * TODO 4. java.util.concurrent 包提供了映射、 有序集和队列的高效实现：
 *          ConcurrentHashMap、ConcurrentSkipListMap > ConcurrentSkipListSet 和 ConcurrentLinkedQueue。
 *          TODO 这些集合使用复杂的算法，通过允许并发地访问数据结构的不同部分来使竞争极小化。
 *          TODO 与大多数集合不同，size 方法不必在常量时间内操作。确定这样的集合当前的大小通常需要遍历。
 *
 *TODO 5.  注： 有些应用使用庞大的并发散列映射，这些映射太过庞大， 以至于无法用 size 方法得到它的大小， 因为这个方法只能返回 int。
 * 对于一个包含超过 20 亿条目的映射该如何处理？ JavaSE 8 引入了一个 mappingCount 方法可以把大小作为 long 返回。
 *
 * TODO 6. 集合返回弱一致性（ weakly consistent) 的迭代器。
 * 这意味着迭代器不一定能反映出它们被构造之后的所有的修改，但是，它们不会将同一个值返回两次，也不会拋出 ConcurrentModificationException 异常。
 * 与之形成对照的是， 集合如果在迭代器构造之后发生改变，java.util 包中的迭代器将抛出一个 ConcurrentModificationException 异常。
 *
 * TODO 7.并发的散列映射表， 可高效地支持大量的读者和一定数量的写者。
 * 默认情况下， 假定可以有多达 16 个写者线程同时执行。
 * 可以有更多的写者线程，但是， 如果同一时间多于 16个，其他线程将暂时被阻塞。可以指定更大数目的构造器，然而， 恐怕没有这种必要。
 */
public class 高效的映射_集_队列_01 {
    /**
     *TODO java.util.concurrent.ConcurrentLinkedQueue<E> 5.0
     * • ConcurrentLinkedQueue<E>()
     *   构造一个可以被多线程安全访问的无边界非阻塞的队列。
     *
     *TODO java.util.concurrent.ConcurrentSkipListSet<E> 6
     * • ConcurrentSkipListSet<E>()
     * • ConcurrentSkipListSet<E>(Comparator<? super E> comp)
     *   构造一个可以被多线程安全访问的有序集。第一个构造器要求元素实现 Comparable接口。
     *
     * TODO java.util.concurrent.ConcurrentHashMap<K, V> 5.0
     * • ConcurrentHashMap<K, V>()
     * • ConcurrentHashMap<K, V>(1nt 1n1t1 alCapacity)
     * • ConcurrentHashMapCK, V>(int initialCapacity, float 1oadFactor, 1nt concurrencyLevel)
     *    构造一个可以被多线程安全访问的散列映射表。
     *    参数: initialCapacity 集合的初始容量。默认值为 16。
     *          loadFactor 控制调整： 如果每一个桶的平均负载超过这个因子，表的大小会被重新调整。默认值为 0.75。
     *          concurrencyLevel 并发写者线程的估计数目。
     *
     * TODO java.util.concurrent.ConcurrentSkipListMap<K, V> 6
     * • ConcurrentSkipListMap<K, V>()
     * • ConcurrentSkipListSet<K, V>(Comparator<? super K> comp)
     *   构造一个可以被多线程安全访问的有序的映像表。 第一个构造器要求键实现Comparable 接口
     *
     */
}
