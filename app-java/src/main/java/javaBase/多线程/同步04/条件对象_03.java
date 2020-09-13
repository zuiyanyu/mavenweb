package javaBase.多线程.同步04;

/**
 * TODO 条件对象
 * TODO 1. 通常， 线程进人临界区，却发现在某一条件满足之后它才能执行。
 * TODO 2. 要使用一个条件对象来管理那些已经获得了一个锁但是却不能做有用工作的线程。
 * TODO 3. 条件对象经常被称为条件变量（conditionalvariable )
 * TODO  4. 条件对象 存在的意义：
 * 现在来细化银行的模拟程序。我们避免选择没有足够资金的账户作为转出账户。注意不
 * 能使用下面这样的代码：
 * if (bank.getBalance(fron) >= amount){
 *     bank.transfer(fro«, to, amount) ;
 * }
 *
 * 当前线程完全有可能在成功地完成测试，且在调用 transfer 方法之前将被中断。
 * if (bank.getBalance(from) >= amount){
 *     // thread night be deactivated at this point(线程可能在此处中断)
 *     bank.transfer(from, to, amount);
 * }
 *TODO 在线程再次运行前， 账户余额可能已经低于提款金额。必须确保没有其他线程在本检査余额
 *TODO 与转账活动之间修改余额。通过使用锁来保护检査与转账动作来做到这一点：
 *TODO   public void transfer(int from, int to, int amount)
 *TODO   {
 *TODO        bankLock.1ock();
 *TODO        try{
 *TODO             while (accounts[from] < amount){
 *TODO                  // wait
 *TODO                  ...
 *TODO             }
 *TODO              // transfer funds
 *TODO              ...
 *TODO         }finally{
 *TODO             bankLock.unlockO；
 *TODO         }
 *TODO   }
 *TODO  现在， 当账户中没有足够的余额时， 应该做什么呢？ 等待直到另一个线程向账户中注入了资金。
 *TODO  但是，这一线程刚刚获得了对 bankLock 的排它性访问， 因此别的线程没有进行存款操作的机会。
 *TODO  这就是为什么我们需要条件对象的原因。
 *
 *TODO 5. 一个锁对象可以有一个或多个相关的条件对象。
 *TODO 6. 你可以用 newCondition 方法获得一个条件对象。
 *
 *TODO 7.  bankLock.newCondition().await() 当前线程现在被阻塞了，并放弃了锁。使得另外一个线程可以获得锁。
 * 在此设置
 * 一个条件对象来表达“ 余额充足” 条件。
 * class Bank
 * {
 *      private Condition sufficientFunds;
 *      public Bank()
 *      {
 *          sufficientFunds = bankLock.newCondition();
 *      }
 * }
 *
 * 如果 transfer 方法发现余额不足，它调用sufficientFunds.await();
 * 代表当前线程现在被阻塞了，并放弃了锁。
 * 我们希望这样可以使得另一个线程可以进行增加账户余额的操作。即让另外一个线程可以获得锁。
 *
 *TODO 5. 等待获得锁的线程和调用 await 方法的线程存在本质上的不同。
 *      TODO  5.1 一旦一个线程调用 await方法， 它进人该条件的等待集。
 *      TODO  5.2 当锁可用时，该线程不能马上解除阻塞。
 *      TODO  5.3 相反，它处于阻塞状态，直到另一个线程调用同一条件上的 signalAll() 方法时为止。
 *                当另一个线程转账时， 它应该调用
 *                 sufficientFunds.signalAll();
 *                 这一调用重新激活因为这一条件而等待的所有线程。
 *      TODO 5.4
 *                 当这些线程从等待集当中移出时，它们再次成为可运行的，调度器将再次激活它们。
 *                 同时， 它们将试图重新进人该对象。
 *                 一旦锁成为可用的，它们中的某个将从 await 调用返回， 获得该锁并从被阻塞的地方继续执行。
 *      TODO 5.5
 *              此时， 线程应该再次测试该条件。
 *              由于无法确保该条件被满足—signalAll() 方法仅仅是通知正在等待的线程：此时有可能已经满足条件， 值得再次去检测该条件。
 * TODO 6. 注释： 通常， 对 await 的调用应该在如下形式的循环体中
 *          while (!(ok to proceed) ){
 *               condition.await();
 *          }
 * TODO       至关重要的是最终需要某个其他线程调用 signalAll 方法。
 * TODO       当一个线程调用 await 时，它没有办法重新激活自身。它寄希望于其他线程。
 * TODO       如果没有其他线程来重新激活等待的线程，它就永远不再运行了。这将导致令人不快的死锁（deadlock) 现象。
 * TODO       如果所有其他线程被阻塞， 最后一个活动线程在解除其他线程的阻塞状态之前就调用 await 方法， 那么它也被阻塞。
 *             没有任何线程可以解除其他线程的阻塞，那么该程序就挂起了。
 *
 *TODO 7. 应该何时调用 signalAll 呢？经验上讲， 在对象的状态有利于等待线程的方向改变时调用signalAll。
 * 例如， 当一个账户余额发生改变时，等待的线程会应该有机会检查余额。在例子
 * 中， 当完成了转账时， 调用 signalAll 方法。
 *        public void transfer(int from, int to, int amount)
 *        {
 *              bankLock.lock();
 *              try
 *              {
 *                  while (accounts[from] < amount){
 *                      sufficientFunds.await()；
 *                  }
 *
 *                  // transfer funds
 *                  sufficientFunds.signalAll()；
 *              }
 *              finally
 *              {
 *                   bankLock.unlock();
 *              }
 *          }
 *
 * TODO  注意调用 signalAll 不会立即激活一个等待线程。它仅仅解除等待线程的阻塞，
 *        以便这些线程可以在当前线程退出同步方法之后， 通过竞争实现对对象的访问（即获取对象锁）。
 *TODO 8.  另一个方法 signal, 则是随机解除等待集中某个线程的阻塞状态。这比解除所有线程的阻塞更加有效，但也存在危险。
 *         如果随机选择的线程发现自己仍然不能运行，那么它再次被阻塞。如果没有其他线程再次调用 signal, 那么系统就死锁了。
 *
 * TODO 9. 警告： 当一个线程拥有某个条件的锁时， 它仅仅可以在该条件上调用 await、signalAll 或 signal 方法。
 *
 */
public class 条件对象_03 {

}
