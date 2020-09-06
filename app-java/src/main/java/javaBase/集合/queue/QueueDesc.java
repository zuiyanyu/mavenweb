package javaBase.集合.queue;

/**
 * TODO 队列接口指出可以在队列的尾部添加元素， 在队列的头部删除元素，并且可以査找队列中元素的个数。 (先进先出)
 * TODO 队列的使用场景：
 *      当需要收集对象， 并按照“ 先进先出” 的规则检索对象时就应该使用队列
 *
 * TODO 队列通常有两种实现方式： 一种是使用循环数组； 另一种是使用链表
 * TODO 如果需要一个循环数组队列， 就可以使用 ArrayDeque 类。
 * TODO 如果需要一个链表队列， 就直接使用 LinkedList类， 这个类实现了 Queue 接口。
 *
 * TODO a.循环数组要比链表更高效，但是循环数组是一个有界集合， 即容量有限
 * TODO b.如果程序中要收集的对象数量没有上限， 就最好使用链表来实现
 *
 *
 * 在研究 API 文档时， 会发现另外一组名字以 Abstract 开头的类， 例如， AbstractQueue。
 * 这些类是为类库实现者而设计的。 如果想要实现自己的队列类（也许不太可能，) 会发现扩展
 * AbstractQueue 类要比实现 Queue 接口中的所有方法轻松得多。
 *
 *
 * 在 Java SE 6 中引人了 Deque 接口， 并由 ArrayDeque 和
 * LinkedList 类实现。这两个类都提供了双端队列，而且在必要时可以增加队列的长度。
 *
 * TODO java.utii.Queue<E>
 * •boolean add(E element )
 * •boolean offer(E element )
 *      如果队列没有满，将给定的元素添加到这个双端队列的尾部并返回 true。如果队列满了，第一个方法将拋出一个 IllegalStateException,
 *      而第二个方法返回 false。
 * • E remove()
 * • E poll ()
 *      假如队列不空，删除并返回这个队列头部的元素。如果队列是空的，第一个方法抛出NoSuchElementException,
 *      而第二个方法返回 null。
 * • E element()
 * • E peek( )
 *      如果队列不空，返回这个队列头部的元素， 但不删除。如果队列空，第一个方法将拋出一个 NoSuchElementException,
 *      而第二个方法返回 null。
 *      • void addFirst( E element )
 * • void addLast(E element )
 * • boolean offerFirst(E element )
 * • boolean offerLast( E element )
 *      将给定的对象添加到双端队列的头部或尾部。如果队列满了，前面两个方法将拋出一
 *      个 IllegalStateException， 而后面两个方法返回 false。
 * • E removeFirst( )
 * • E removeLast( )
 * • E pollFirst( )
 * • E pollLast( )
 *      如果队列不空，删除并返回队列头部的元素。 如果队列为空，前面两个方法将拋出一
 *      个 NoSuchElementException, 而后面两个方法返回 null。
 * • E getFirst( )
 * • E getLast( )
 * • E peekFirst( )
 * • E peekLast( )
 *      如果队列非空，返回队列头部的元素， 但不删除。 如果队列空，前面两个方法将拋出
 *      一个 NoSuchElementException, 而后面两个方法返回 null。
 * TODO java.util.ArrayDeque<E> 6
 * • ArrayDeque( )
 * • ArrayDeque( 1nt initialCapacity)
 *   用初始容量 16 或给定的初始容量构造一个无限双端队列。
 */




public class QueueDesc {
}
