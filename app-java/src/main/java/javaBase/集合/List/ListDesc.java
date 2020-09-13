package javaBase.集合.List;

import java.util.*;

/**
 *  链 表 与 泛 型 集 合 之 间 有 一 个 重 要 的 区 别。 链 表 是 一 个 有 序 集 合（ordered
 * collection), 每个对象的位置十分重要。LinkedList.add 方法将对象添加到链表的尾部。但是，
 * 常常需要将元素添加到链表的中间。由于迭代器是描述集合中位置的， 所以这种依赖于位置
 * 的 add 方法将由迭代器负责。只有对自然有序的集合使用迭代器添加元素才有实际意义。例
 * 如， 下一节将要讨论的集 （set ) 类型，其中的元素完全无序。 因此， 在 Iterator 接口中就没有
 * add 方法。相反地，集合类库提供了子接口 Listlterator, 其 中 包 含 add 方 法：
 *     interface ListIterator<E> extends Iterator<E>
 *     {
 *         void add(E element);
 *         ...
 *     }
 * 与 Collection.add 不同， 这个方法不返回 boolean 类型的值， 它假定添加操作总会改变链表。
 * Add 方法在迭代器位置之前添加一个新对象。
 *
 * add 方法只依赖于迭代器的位置， 而 remove 方法依赖于迭代器的状态。
 * 最后需要说明，set 方法用一个新元素取代调用 next 或 previous 方法返回的上一个元素。
 * 例如，下面的代码将用一个新值取代链表的第一个元素：
 * ListIterator<String> iter * list.listlteratorO;
 * String oldValue = iter.next(); // returns first element
 * iter.set(newValue); // sets first element to newValue
 *
 *TODO 可以想象， 如果在某个迭代器修改集合时， 另一个迭代器对其进行遍历， 一定会出现
 *TODO 混乱的状况。例如，一个迭代器指向另一个迭代器刚刚删除的元素前面，现在这个迭代器
 *TODO 就是无效的，并且不应该再使用。链表迭代器的设计使它能够检测到这种修改。 如果迭代
 *TODO 器发现它的集合被另一个迭代器修改了， 或是被该集合自身的方法修改了， 就会抛出一个
 *TODO ConcurrentModificationException 异常。
 *
 *TODO 为了避免发生并发修改的异常，请遵循下述简单规则：可以根据需要给容器附加许多的
 *TODO 迭代器，但是这些迭代器只能读取列表。另外，再单独附加一个既能读又能写的迭代器。
 *
 *TODO 有一种傅单的方法可以检测到并发修改的问题：
 *TODO      集合可以跟踪改写操作（诸如添加或删除元素）的次数。
 *TODO      每个迭代器都维护一个独立的计数值。
 *TODO      在每个迭代器方法的开始处检查自己改写操作的计数值是否与集合的改写操作计数值一致。 如果不一致，
 *TODO      抛出一个 ConcurrentModificationException 异常
 */
public class ListDesc {
    public static void main(String[] args) {
//        List<String> names = Arrays.asList("A«iy", "Bob", "Carl");
//        names.add("李四");
//        System.out.println(names);

//        List<String> settings = Collections.nCopies(10, "DEFAULT") ;
//        settings.set(1,"hello");
//        System.out.println(settings);

//        Set<Object> singleton = Collections.singleton("hell");

        List list = new ArrayList() ;
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        List list1 = list.subList(1, 3);
        System.out.println(list1.get(0));
        System.out.println(list1);
//        list.add("e");
//        System.out.println(list1.get(1));

        List list2 = list.subList(1, 4);

        list1.clear();
        System.out.println(list);

        System.out.println(list2);



    }
}
