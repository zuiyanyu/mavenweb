package javaBase.多线程.同步04;

/**
 * TODO 1. 有时，仅仅为了读写一个或两个实例域就使用同步， 显得开销过大了。
 * 毕竟，什么地方能出错呢？ 遗憾的是， 使用现代的处理器与编译器， 出错的可能性很大。
 *      • 多处理器的计算机能够暂时在寄存器或本地内存缓冲区中保存内存中的值。结果是，运行在不同处理器上的线程可能在同一个内存位置取到不同的值。
 *      • 编译器可以改变指令执行的顺序以使吞吐量最大化。
 *        这种顺序上的变化不会改变代码语义，但是编译器假定内存的值仅仅在代码中有显式的修改指令时才会改变。
 *        然而，内存的值可以被另一个线程改变！
 * TODO 如果你使用锁来保护可以被多个线程访问的代码， 那么可以不考虑这种问题。
 *       编译器被要求通过在必要的时候刷新本地缓存来保持锁的效应，并且不能不正当地重新排序指令。
 *
 * TODO 2.“同步格言”：
 * TODO   “ 如果向一个变量写入值， 而这个变量接下来可能会被另一个线程读取， 或者，
 * TODO      从一个变量读值， 而这个变量可能是之前被另一个线程写入的， 此时必须使用同步”
 *
 * TODO 3. volatile 关键字为实例域的同步访问提供了一种免锁机制。如果声明一个域为 volatile ,那么编译器和虚拟机就知道该域是可能被另一个线程并发更新的。
 *      例如， 假定一个对象有一个布尔标记 done, 它的值被一个线程设置却被另一个线程査询，如同我们讨论过的那样，你可以使用锁：
 *      private boolean done;
 *      public synchronized boolean isDone() { return done; }
 *      public synchronized void setDone(){ done = true; }
 *
 * TODO 或许使用内部锁不是个好主意。如果另一个线程已经对该对象加锁， isDone 和 setDone方法可能阻塞。
 * 如果注意到这个方面，一个线程可以为这一变量使用独立的 Lock。但是，这也会带来许多麻烦。
 * TODO 在这种情况下，将域声明为 volatile 是合理的：
 *      private volatile boolean done;
 *      public boolean isDoneO { return done; }
 *      public void setDoneO { done = true; }
 *
 * TODO 4. Volatile 变量不能提供原子性。例如， 方法
 *        public void flipDoneO { done = !done; } // not atomic
 *       不能确保翻转域中的值。不能保证读取、 翻转和写入不被中断。
 *TODO 5. 假设对共享变量除了赋值之外并不完成其他操作，那么可以将这些共享变量声明为 volatile.
 *
 */
public class Volatile域_07 {
}
