package javaBase.集合.map;

import javaBase.domain.Employee;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 弱散列映射
 *
 * TODO 1.设计 WeakHashMap 类是为了解决一个有趣的问题。
 * TODO 如果有一个值，对应的键已经不再使用了， 将会出现什么情况呢？
 * 假定对某个键的最后一次引用已经消亡，不再有任何途径引用这个值的对象了。
 * 但是，由于在程序中的任何部分没有再出现这个键， 所以， 这个键/值 对无法从映射中删除。
 * 为什么垃圾回收器不能够删除它呢？ 难道删除无用的对象不是垃圾回收器的工作吗？
 *
 * TODO 遗憾的是， 事情没有这样简单。 垃圾回收器跟踪活动的对象。
 * 只要映射对象是活动的，其中的所有桶也是活动的， 它们不能被回收。
 * 因此， 需要由程序负责从长期存活的映射表中删除那些无用的值。（或者使用 WeakHashMap 完成这件事情。）
 *
 * TODO 当对键的唯一引用来自散列条目时， 这一数据结构将与垃圾回收器协同工作一起删除键/值对。
 *
 * TODO 2. WeakHashMap 这种机制的内部运行情况
 * WeakHashMap 使用弱引用 （weak references) 保存键。
 * WeakReference 对象将引用保存到另外一个对象中， 在这里， 就是散列键。对于这种类型的
 * 对象， 垃圾回收器用一种特有的方式进行处理。通常， 如果垃圾回收器发现某个特定的对象
 * 已经没有他人引用了， 就将其回收。然而， 如果某个对象只能由 WeakReference 引用， 垃圾
 * 回收器仍然回收它，但要将引用这个对象的弱引用放人队列中。WeakHashMap 将周期性地检
 * 查队列， 以便找出新添加的弱引用。一个弱引用进人队列意味着这个键不再被他人使用， 并
 * 且已经被收集起来。于是， WeakHashMap 将删除对应的条目。
 * =========================================================
 *
 * TODO 上面的理解 + 源码解析
 WeakHashMap的内部机制

 1. 看下 WeakHashMap 的整体结构
 属性
 private int size;
 private int threshold = 16;
 private final float loadFactor = 0.75;

 private final ReferenceQueue<Object> queue= new ReferenceQueue();  //弱引用队列
 WeakHashMap.Entry<K, V>[] table; //元素存储位置

 节点定义：只定义了V,却不见K,
 private static class Entry<K,V> extends WeakReference<Object> implements Map.Entry<K,V> {
     V value;
     final int hash;
     Entry<K,V> next;

     Entry(Object key, V value, ReferenceQueue<Object> queue, int hash, Entry<K,V> next) {
         super(key, queue);
         this.value = value;
         this.hash  = hash;
         this.next  = next;
     }
 }
 public class WeakReference<T> extends Reference<T> {
     public WeakReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
     }
 }
 public abstract class Reference<T> {
        private T referent;    // Treated specially by GC
        volatile ReferenceQueue<? super T> queue;

        // super(key, queue); 最终调用的地方
        Reference(T referent, ReferenceQueue<? super T> queue) {
            this.referent = referent;
            this.queue = (queue == null) ? ReferenceQueue.NULL : queue;
        }
}

2. 从 WeakHashMap 的整体结构可以看出：
    每一个（key,value）键值对都存储在一个Entry节点中;
    而每个Entry 是一个WeakReference类实例;
    而WeakReference又继承了Reference接口;
    所以 每一个节点即为一个 弱引用对象。
2. 每个键值对的key都存储在Reference弱引用对象中。
3. 每个WeakHashMap的Entry节点都会共享一个 弱引用队列 ReferenceQueue ；
    并且每个弱引用队列 ReferenceQueue在每个 Entry节点中都会保存一份副本。
4. 弱引用队列 ReferenceQueue 的 作用：
    4.1 对于WeakReference 对象，垃圾回收器用一种特有的方式进行处理。
    4.1.1 通常， 如果垃圾回收器发现某个特定的对象已经没有他人引用了， 就将其回收。
    4.1.2 然而， 如果某个对象只能由 WeakReference 引用， 垃圾回收器仍然回收它，但要将引用这个对象所在的弱引用对象放人队列中。
4.2 WeakHashMap 将周期性地检查队列， 以便找出新添加的弱引用。一个弱引用进人队列意味着这个键不再被他人使用， 并且已经被收集起来。于是， WeakHashMap 将删除对应的条目。

5. 从上可以看出，这一数据结构将与垃圾回收器协同工作一起删除键/值对
简单协作执行流程模拟如下：

ReferenceQueue<Reference> queue = new ReferenceQueue();
tab[
    reference01 WeakHashMap.Entry{ k1,v1} ,   // private T referent;
    reference02 WeakHashMap.Entry{ k2,v2} ,
    reference03 WeakHashMap.Entry{ k3,v3} ,
    reference04 WeakHashMap.Entry{ k4,v4}
]

=>如果在外界设置 k2 = null ; 则表示对k2 这个键的最后一次引用已经消亡，不再有任何途径引用这个值的对象了。
=>垃圾回收器发现 k2 除了在 reference02 这个弱引用对象中还被引用外，外界已无途径引用这个k2值了
=>在reference02中，垃圾回收器将k2继续进行回收，并将引用k2的变量referent[private T referent; ]设置为null, 同时将 k2的引用对象reference02 放到弱引用队列queue中,
(垃圾回收器可以将k2进行回收，但是不能将k2的引用进行回收(因为还在被tab引用着))
=>WeakHashMap检查队列queue中存在一个值：reference02，然后将reference02这个对象从tab中删除
=>tab更新后的值为：
tab[
    reference01 WeakHashMap.Entry{ k1,v1} ,
    reference03 WeakHashMap.Entry{ k3,v3} ,
    reference04 WeakHashMap.Entry{ k4,v4}
]
=>从而可以看出 GC 如何和Reference一起协作工作的了吧。

6. 其实 WeakHashMap检查队列queue 是在 resize(int newTabLength) ,即tab扩容的时候有进行扫描。
7. WeakHashMap在进行扩容时候，将oldTab中的值拷贝到newTab中的时候，会获取每一个 节点对应的key[即 T referent],
如果 referent 值为null, 也认为此节点的key已经被GC回收，应该将此节点从集合中删除。
8. 源码中，WeakHashMap就是单纯的 数组+单链表结构 ，不存在红黑树这一结构。
 */
public class WeakHashMapDesc {
    public static void main(String[] args) {

    }
    /**
     * TODO WeakHashMap 要解决的问题
     */
    @Test
    public  void  strongHashMap(){
        Map<Employee,String> map = new HashMap();
        Employee employee1 = new Employee("张三");
        Employee employee2 = new Employee("李四");

        map.put(employee1,"abc");
        map.put(employee2,"dddd");

        System.out.println("map = "+map);
        System.out.println("==========================");
        //TODO 一个值，对应的键已经不再使用了,不再有任何途径引用这个值的对象了。
        //TODO 但是，由于在程序中的任何部分没有再出现这个键， 所以， 这个键/值 对无法从映射中删除。
        //employee1 对应的元素无法被删除，也无法被垃圾回收器回收。必须程序进行手工删除这个元素了。
        employee1 = null ;
        System.out.println("map = "+map);

    }
}
