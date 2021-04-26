package javaBase.多线程.同步04;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * TODO 1. 前面几节中， 我们讨论了在线程间共享变量的风险。 有时可能要避免共享变量， 使用ThreadLocal 辅助类为各个线程提供各自的实例。
 * 例如，SimpleDateFormat 类不是线程安全的。
 * 假设有一个静态变量：
 *      public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd")；
 *      如果两个线程都执行以下操作：
 *      String dateStamp = dateFormat.format(new Date());
 *      结果可能很混乱， 因为 dateFormat 使用的内部数据结构可能会被并发的访问所破坏。
 *      TODO 虽然可以使用同步，但开销很大；
 *      TODO 或者也可以在需要时构造一个局部 SimpleDateFormat 对象，不过这也太浪费了。
 *
 * TODO 2. 要为每个线程构造一个实例，可以使用以下代码：
 *  public static final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
 * 要访问具体的格式化方法，可以调用：
 * String dateStamp = dateFormat.get().format(new Date());
 *在一个给定线程中首次调用 get 时， 会调用 initialValue 方法。在此之后， get 方法会返回属于当前线程的那个实例。
 *
 *TODO 3.一个应用场景
 * 在多个线程中生成随机数也存在类似的问题。java..util.Random 类是线程安全的。但是如
 * 果多个线程需要等待一个共享的随机数生成器， 这会很低效。可以使用 ThreadLocal 辅助类为各个线程提供一个单独的生成器，
 *
 * TODO 不过 Java SE 7 还另外提供了一个便利类。只需要做以下调用：
 * int random = ThreadLocalRandom.current().nextlnt(upperBound);
 * ThreadLocalRandom.current() 调用会返回特定于当前线程的 Random 类实例。
 *
 *TODO java.lang.ThreadLocal<T>  1.2
 * • T get( )
 *      得到这个线程的当前值。如果是首次调用 get, 会调用 initialize 来得到这个值。
 * •protected initialize( )
 *      应覆盖这个方法来提供一个初始值。默认情况下，这个方法返回 null。
 * • void set( T t)
 *      为这个线程设置一个新值。
 * • void remove( )
 *      删除对应这个线程的值。
 * •static <S> ThreadLocal <S> withlnitial ( Supplier < ? extends S> supplier) 8
 *      创建一个线程局部变量， 其初始值通过调用给定的 supplier 生成。
 *
 *TODO java.util.concurrent.ThreadLocalRandom  7
 *•static ThreadLocalRandom current( )
 *      返回特定于当前线程的 Random 类实例。
 */
public class 线程局部变量_11 {
    public static final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    public static void main(String[] args) {
        String dateStamp  = dateFormat.get().format(new Date());

        int random = ThreadLocalRandom.current().nextInt(20);

    }
}
