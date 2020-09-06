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
 */




public class QueueDesc {
}
