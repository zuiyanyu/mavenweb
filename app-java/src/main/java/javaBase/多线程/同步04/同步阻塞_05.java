package javaBase.多线程.同步04;

/**
 * TODO 1.  每一个 Java 对象有一个锁。 线程可以通过调用同步方法获得锁。
 * TODO 2.  还有另一种机制可以获得锁，通过进入一个同步阻塞。
 *           synchronized (obj) // this is the syntax for a synchronized block
 *           {
 *              critical section
 *           }
 *          于是它获得 obj 的锁。
 *TODO 3. 特殊的锁
 * 有时会发现“ 特殊的” 锁，例如：
 *       public class Bank{
 *           private doublet] accounts;
 *           private Object lock = new Object();
 *           ...
 *           public void transfer(int from, int to, int amount){
 *               synchronized (lock) // an ad-hoc lock
 *               {
 *                   accounts[from] -= amount;
 *                   accounts[to] += amount;
 *               }
 *               System.out.print1n(...)；
 *           }
 *       }
 *在此，lock 对象被创建仅仅是用来使用每个 Java 对象持有的锁。
 *
 *
 *TODO 4. 有时程序员使用一个对象的锁来实现额外的原子操作， 实际上称为客户端锁定（clientside locking) ;
 * TODO   但是， 客户端锁定是非常脆弱的，通常不推荐使用。
 *  例如， 考虑 Vector 类，一个列表， 它的方法是同步的。现在， 假定在 Vector<Double> 中存储银行余额。
 *  这里有一个 transfer 方法的原始实现：
 *       public void transfer(Vector<Double> accounts, int from, int to, int amount)// Error
 *       {
 *           accounts.set(from, accounts.get(from) - amount);
 *           accounts.set(to, accounts.get(to) + amount);
 *           System.out.println(. . .);
 *       }
 *  TODO Vector 类的 get 和 set 方法是同步的， 但是，这对于我们并没有什么帮助。
 *  TODO 在第一次对get 的调用已经完成之后，一个线程完全可能在 transfer 方法中被剥夺运行权。于是，另一个线程可能在相同的存储位置存人不同的值。
 *  但是，我们可以截获这个锁：
 *   public void transfer(Vector<Double> accounts, int from, int to, int amount)
 *  {
 *      synchronized (accounts)
 *      {
 *          accounts.set(from, accounts.get(from) - amount):
 *          accounts.set(to, accounts.get(to) + amount);
 *      }
 *      System.out.print1n(. . .);
 *  }
 *TODO 这个方法可以工作，但是它完全依赖于这样一个事实， Vector 类对自己的所有可修改方法都使用内部锁。
 *
 *
 *
 */
public class 同步阻塞_05 {
}
