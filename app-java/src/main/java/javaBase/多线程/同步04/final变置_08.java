package javaBase.多线程.同步04;

/**
 * TODO 1.除非使用锁或 volatile 修饰符， 否则无法从多个线程安全地读取一个域。
 * TODO 2.还有一种情况可以安全地访问一个共享域， 即这个域声明为 final 时。
 *        考虑以下声明：
 *        final Map<String, Double〉accounts = new HashKap<>();
 *        其他线程会在构造函数完成构造之后才看到这个 accounts 变量。
 *        如果不使用 final，就不能保证其他线程看到的是 accounts 更新后的值，它们可能都只是看到 null, 而不是新构造的 HashMap.
 *        当然，对这个映射表的操作并不是线程安全的。如果多个线程在读写这个映射表，仍然需要进行同步。
 *
 *
 *
 *
 *
 */
public class final变置_08 {
}
