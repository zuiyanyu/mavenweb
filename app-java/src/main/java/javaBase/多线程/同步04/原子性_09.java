package javaBase.多线程.同步04;

import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO 1.假设对共享变量除了赋值之外并不完成其他操作，那么可以将这些共享变量声明为 volatile.
 * TODO 2.java.util.concurrent.atomic 包中有很多类使用了很高效的机器级指令（而不是使用锁）来保证其他操作的原子性。
 *      2.1例如， Atomiclnteger 类提供了方法 incrementAndGet 和 decrementAndGet, 它们分别以原子方式将一个整数自增或自减。
 *      2.2例如，可以安全地生成一个数值序列，如下所示：
 *           public static AtomicLong nextNumber = new AtomicLong() ;
 *           // In some thread...
 *           long id = nextNumber.increinentAndGet();
 *
 *      TODO incrementAndGet 方法以原子方式将 AtomicLong 自增， 并返回自增后的值。也就是说，
 *      TODO 获得值、 增 1 并设置然后生成新值的操作不会中断。
 *      TODO 可以保证即使是多个线程并发地访问同一个实例，也会计算并返回正确的值。
 *
 *TODO 3. compareAndSet 方法
 *      TODO 有很多方法可以以原子方式设置和增减值，不过， 如果希望完成更复杂的更新，就必须使用 compareAndSet 方法。
 *      例如， 假设希望跟踪不同线程观察的最大值。下面的代码是不可行的：
 *          public static AtonicLong largest = new AtomicLongO；
 *          // In some thread...
 *          largest.set(Math.max(largest.get(), observed)); // Error race condition!
 *      这个更新不是原子的。实际上，应当在一个循环中计算新值和使用 compareAndSet:
 *           do {
 *                oldValue = largest.get();
 *                newValue = Math.max (oldValue , observed) ;
 *            } while (!largest.compareAndSet(oldValue, newValue)) ;
 *      如果另一个线程也在更新 largest，就可能阻止这个线程更新。这样一来， compareAndSet
 *      会返回 false, 而不会设置新值。在这种情况下，循环会再次尝试.
 * TODO compareAndSet 方法会映射到一个处理器操作， 比使用锁速度更快。
 *
 * TODO 在 Java SE8 中，不再需要编写这样的循环样板代码。实际上， 可以提供一个 lambda 表达式更新变量，它会为你完成更新。
 * 对于这个例子，我们可以调用：
 * largest.updateAndGet(x -> Math.max(x, observed)) ;
 * 或
 * largest.accumulateAndCet(observed , Math::max);
 * accumulateAndGet 方法利用一个二元操作符来合并原子值和所提供的参数。
 * 还有 getAndUpdate 和 getAndAccumulate 方法可以返回原值。
 *
 *TODO 4.注释：
 *  类 Atomiclnteger、AtomicIntegerArray、AtomicIntegerFieldUpdater、AtomicLongArray、
 * AtomicLongFieldUpdater、AtomicReference、 AtomicReferenceArray 和 AtomicReferenceFieldUpdater 也提供了这些方法。
 *
 * TODO 5.如果有大量线程要访问相同的原子值， 性能会大幅下降， 因为乐观更新需要太多次重试。
 * Java SE 8 提供了 LongAdder 和 LongAccumulator 类来解决这个问题。
 *TODO  LongAdder 包括多个变量（加数，) 其总和为当前值。可以有多个线程更新不同的加数，线程个数增加时会自动提供新的加数。
 *TODO  通常情况下， 只有当所有工作都完成之后才需要总和的值， 对于这种情况,这种方法会很高效。性能会有显著的提升。
 *
 * TODO 6.如果认为可能存在大量竞争， 只需要使用 LongAdder 而不是 AtomicLong。
 * 方法名稍有区别。调用 increment 让计数器自增，或者调用 add 来增加一个量， 或者调用 sum 来获取总和。
 *final LongAdder adder = new LongAdder()；
 * for (. . .){
 *      pool .submit(() -> {
 *          while (. . .) {
 *               ...
 *              if (. . .) adder.increment();
 *          }
 *      });
 *  }
 * ...
 * long total = adder.sum());
 * TODO increment 方法不会返回原值。这样做会消除将求和分解到多个加数所带来的性能提升。
 *
 *
 * TODO 7. LongAccumulator 将这种思想推广到任意的累加操作。在构造器中， 可以提供这个操作以及它的零元素。
 * TODO  要加入新的值，可以调用 accumulate() 。调用 get() 来获得当前值。下面的代
 * TODO  码可以得到与 LongAdder() 同样的效果：
 * TODO       LongAccumulator adder = new LongAccumulator(Long::sum, 0) ;
 * TODO       // In some thread...
 * TODO       adder.accumulate(value) ;
 * TODO
 * TODO  在内部，这个累加器包含变量 A1, A2，A3 … An。每个变量初始化为零元素（这个例子中零元素为 0。)
 * TODO  调用 accumulate 并提供值 v 时，其中一个变量会以原子方式更新为： Ai= Ai op v, 这里 op是中缀形式的累加操作。
 * TODO  在我们这个例子中，调用 accumulate 会对某个 i 计算 Ai= Ai + v。
 * TODO  get 的结果是 A1 op A2 op A3 op...op An 在我们的例子中， 这就是累加器的总和：A1 + A2 + A3 + … + An。
 * TODO  如果选择一个不同的操作，可以计算最小值或最大值。一般地， 这个操作必须满足结合律和交换律。这说明， 最终结果必须独立于所结合的中间值的顺序。
 * TODO  另外 DoubleAdder 和 DoubleAccumulator 也采用同样的方式， 只不过处理的是 double 值。
 *
 */
public class 原子性_09 {
    public static AtomicLong nextNumber = new AtomicLong() ;

    public static void main(String[] args) {
        long l = nextNumber.incrementAndGet();
        System.out.println(l);

        long l1 = nextNumber.updateAndGet(x -> x + 1);
    }
}
