package javaBase.多线程.阻塞队列05;

/**
 * TODO 1. 现在，读者已经看到了形成 Java 并发程序设计基础的底层构建块。然而，对于实际编程来说，应该尽可能远离底层结构。
 * 使用由并发处理的专业人士实现的较高层次的结构要方便得多、 要安全得多。
 *
 * TODO 2. 对于许多线程问题， 可以通过使用一个或多个队列以优雅且安全的方式将其形式化.
 * TODO  2.1 生产者线程向队列插人元素， 消费者线程则取出它们。
 * TODO  2.2 使用队列，可以安全地从一个线程向另一个线程传递数据。
 *  例如， 考虑银行转账程序， 转账线程将转账指令对象插入一个队列中，而不是直接访问银行对象。
 *         另一个线程从队列中取出指令执行转账。只有该线程可以访问该银行对象的内部。因此不需要同步。
 *        （当然， 线程安全的队列类的实现者不能不考虑锁和条件，但是， 那是他们的问题而不是你的问题。）
 *TODO 3. 当试图向队列添加元素而队列已满， 或是想从队列移出元素而队列为空的时候， 阻塞队列（ blocking queue ) 导致线程阻塞。
 *         TODO   在协调多个线程之间的合作时， 阻塞队列是一个有用的工具。
 *          TODO  工作者线程可以周期性地将中间结果存储在阻塞队列中。其他的工作者线程移出中间结果并进一步加以修改。
 *TODO 4. 队列会自动地平衡负载。
 * 如果第一个线程集运行得比第二个慢， 第二个线程集在等待结果时会阻塞。
 * 如果第一个线程集运行得快， 它将等待第二个队列集赶上来。
 *
 * TODO 5. 阻塞队列的方法:
 * • add
 *       添加一个元素
 *       如果队列满， 则抛出 IllegalStateException 异常
 * • element
 *       返回队列的头元素
 *       如果队列空，抛出 NoSuchElementException 异常
 * • remove
 *      移出并返回头元素
 *      如果队列空， 则抛出 NoSuchElementException 异常
 *
 * • offer
 *       添加一个元素并返回 true
 *       如果队列满，返回 false
 * • peek
 *       返回队列的头元素
 *       如果队列空， 则返回 null
 * • poll
 *      移出并返回队列的头元素
 *      如果队列空， 则返回 null
 *
 * • put
 *      添加一个元素
 *      如果队列满， 则阻塞
 *• take
 *      移出并返回头元素
 *      如果队列空， 则阻塞
 *
 * TODO 5.1 阻塞队列方法分为以下 3 类， 这取决于当队列满或空时它们的响应方式。
 * TODO （线程管理）如果将队列当作线程管理工具来使用（比如作为线程池）， 将要用到 put 和 take 方法。
 * TODO （单线程用）当试图向满的队列中添加或从空的队列中移出元素时，add、 remove 和 element 操作抛出异常.
 * TODO （多线程用）当然，在一个多线程程序中， 队列会在任何时候空或满， 因此，一定要使用 offer、 poll 和 peek方法作为替代。（任务对列）
 *
 *
 *TODO 5.2 poll和 peek 方法返回空来指示失败。因此，向这些队列中插入 null 值是非法的。
 *
 *TODO 5.3 还有带有超时的 offer 方法和 poll 方法的变体。
 * 例如，下面的调用：
 * boolean success = q.offer(x, 100, TimeUnit.MILLISECONDS);
 *      尝试在 100 毫秒的时间内在队列的尾部插人一个元素。如果成功返回 true ; 否则， 达到超时时，返回 false。类似地，下面的调用：
 * Object head = q.poll(100, TimeUnit.MILLISECONDS)
 *      尝试用 100 毫秒的时间移除队列的头元素；如果成功返回头元素，否则，达到在超时时，返回 null。
 *
 *如果队列满， 则 put 方法阻塞； 如果队列空， 则 take 方法阻塞。在不带超时参数时，offer 和 poll 方法等效。
 *
 *
 *
 * TODO 6. java.util.concurrent 包提供了阻塞队列的几个变种。 默认情况下
 *
 * TODO 6.1 LinkedBlockingQueue
 * LinkedBlockingQueue 的容量是没有上边界的，但是，也可以选择指定最大容量。LinkedBlockingDeque 是一个双端的版本。
 *
 * TODO 6.2 ArrayBlockingQueue
 * ArrayBlockingQueue 在构造时需要指定容量，并且有一个可选的参数来指定是否需要公平性。
 * 若设置了公平参数， 则那么等待了最长时间的线程会优先得到处理。通常，公平性会降低性能，只有在确实非常需要时才使用它。
 *
 *
 * TODO 6.3 PriorityBlockingQueue
 * PriorityBlockingQueue 是一个带优先级的队列， 而不是先进先出队列。元素按照它们的优先级顺序被移出。
 * 该队列是没有容量上限，但是，如果队列是空的， 取元素的操作会阻塞。
 *
 * TODO 6.4 DelayQueue
 * DelayQueue 包含实现 Delayed 接口的对象：
 * interface Delayed extends Comparable<Delayed>
 * {
 *      long getDelay(TimeUnit unit);
 * }
 * getDelay方法返回对象的残留延迟。 负值表示延迟已经结束。元素只有在延迟用完的情况下才能从 DelayQueue 移除。
 * 还必须实现 compareTo 方法。DelayQueue 使用该方法对元素进行排序。
 *
 * TODO 6.5 TransferQueue
 * JavaSE 7增加了一个 TransferQueue 接口，允许生产者线程等待， 直到消费者准备就绪
 * 可以接收一个元素。如果生产者调用
 * q.transfer(item);
 * 这个调用会阻塞， 直到另一个线程将元素（item) 删除。LinkedTransferQueue 类实现了这个接口。
 *
 *
 */
public class 阻塞队列 {
    /**
     * TODO java.util.concurrent.ArrayBlockingQueue<E> 5.0
     * • ArrayBlockingQueue( int capacity)
     * • ArrayBlockingQueue(int capacity, boolean fair )
     * 构造一个带有指定的容量和公平性设置的阻塞队列。该队列用循环数组实现。
     *
     *TODO java,util.concurrent.LinkedBlockingQueue<E> 5.0
     *TODO java.util.concurrent.LinkedBlockingDeque<E> 6
     * • LinkedBlockingQueue( )
     * • LinkedBlockingDeque( )
     * 构造一个无上限的阻塞队列或双向队列，用链表实现。
     *
     * • LinkedBlockingQueue(int capacity )
     * • LinkedBlockingDeque(int capacity )
     * 根据指定容量构建一个有限的阻塞队列或双向队列，用链表实现。
     *
     * TODO java.util.concurrent.DelayQueue<E extends Delayed〉5.0
     * • DelayQueue( )
     * 构造一个包含 Delayed 元素的无界的阻塞时间有限的阻塞队列。只有那些延迟已经超
     * 过时间的元素可以从队列中移出。
     *
     * TODO java.util.concurrent.Delayed 5.0
     * • long getDelay( Timellnit unit )
     * 得到该对象的延迟，用给定的时间单位进行度量。
     *
     *TODO  java.util.concurrent.PriorityBlockingQueue<E> 5.0
     * • PriorityBlockingQueue( )
     * • Priori tyBl ockingQueiie(int initi alCapaci ty)
     * • PriorityBlockingQueue( int initialCapacity, Comparator ? super E> comparator )
     * 构造一个无边界阻塞优先队列，用堆实现。
     * 参数：initialCapacity 优先队列的初始容量。默认值是 11。
     *       comparator 用来对元素进行比较的比较器， 如果没有指定， 则元素必须实现 Comparable 接口。
     *
     * TODO java.util.concurrent.BlockingQueue<E> 5.0
     * • void put( E element )
     * 添加元素， 在必要时阻塞
     * • E take( )
     * 移除并返回头元素， 必要时阻塞。
     * • boolean offer( E element , long time , Timellnit unit )
     * 添加给定的元素， 如果成功返回 true， 如果必要时阻塞， 直至元素已经被添加或超时。
     * • E poll ( long time , Timellnit unit )
     * 移除并返回头元素， 必要时阻塞， 直至元素可用或超时用完。失败时返回 null。
     *
     * TODO java.util.concurrent.BiockingDeque<E> 6
     * • void putFirst( E element )
     * • void putLast( E element )
     * 添加元素， 必要时阻塞。
     * • E takeFirst( )
     * • E takeLast( )
     * 移除并返回头元素或尾元素， 必要时阻塞。
     * • boolean offerFirst( E element , long time, Timellnit unit )
     * • boolean offerLast( E element , long time , TimeUnit unit )
     * 添加给定的元素， 成功时返回 true， 必要时阻塞直至元素被添加或超时。
     * • E pol 1First( 1 ong time , TimeUnit unit )
     * • E pol 1Last( 1 ong time , Timellnit unit )
     * 移动并返回头元素或尾元素，必要时阻塞， 直至元素可用或超时。失败时返回 mill。
     *
     * TODO java.util.concurrent.Transfer Queue<E> 7
     * • void transfer( E element )
     * • boolean tryTransfer( E element , long time , TimeUnit unit )
     * 传输一个值， 或者尝试在给定的超时时间内传输这个值， 这个调用将阻塞，直到另一
     * 个线程将元素删除。第二个方法会在调用成功时返回 true。
     *
     */


}
