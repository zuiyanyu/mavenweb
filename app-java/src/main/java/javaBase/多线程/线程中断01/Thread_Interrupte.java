package javaBase.多线程.线程中断01;

import org.junit.Test;

/**
 * TODO 1.多任务与多线程的关系
 * 1. 操作系统中的多任务 （multitasking): 在同一刻运行多个程序的能力。
 * 2. 并发执行的进程数目并不是由 CPU 数目制约的。操作系统将 CPU 的时间片分配给每一个进程， 给人并行处理的感觉。
 *
 * 3. 多线程程序在较低的层次上扩展了多任务的概念：一个程序同时执行多个任务。通常，每一个任务称为一个线程（thread), 它是线程控制的简称。
 * 4. 可以同时运行一个以上线程的程序称为多线程程序（multithreaded)。
 *
 *TODO 2.多进程与多线程有哪些区别呢？
 *  5.1 本质的区别在于每个进程拥有自己的一整套变量， 而线程则共享数据。 这听起来似乎有些风险， 的确也是这样.
 *  5.2 然而，共享变量使线程之间的通信比进程之间的通信更有效、 更容易。 此外， 在有些操作系统中， 与进程相比较， 线程更“ 轻量级”，
 *      创建、 撤销一个线程比启动新进程的开销要小得多。 在实际应用中， 多线程非常有用。
 *
 * TODO 3.什么是线程
 *
 *
 * TODO 4. 线程方法
 * TODO java.Iang.Thread 1.0
 * 4.1  • static void sleep(long minis)
 *          休眠给定的毫秒数。参数：millis 休眠的毫秒数
 *          调用Thread.sleep()不会创建一个新线程，sleep 是 Thread 类的静态方法，用于暂停当前线程的活动。
 *           sleep 方法可以抛出一个 IntermptedException 异常。线程在中断时被终止。
 *使用线程给其他任务提供机会
 * 4.2 • Thread.start 方法。这个方法将创建一个执行 ran 方法的新线程
 *
 * TODO 中 断 线 程  （正常任务执行完毕 或 遇到未捕获的异常，线程将终止）
 * 当线程的 run 方法执行方法体中最后一条语句后， 并经由执行 return 语句返冋时，线程将终止。
 * 或出现了在方法中没有捕获的异常时，线程将终止。
 *
 * 4.3 stop方法：其他线程可以调用它终止线程。(但是， 这个方法现在已经被弃用了)
 * 4.4 interrupt 方法： 没有可以强制线程终止的方法。然而， interrupt 方法可以用来请求终止线程。
 *      4.4.1 当对一个线程调用 interrupt 方法时，线程的中断状态将被置位。这是每一个线程都具有的 boolean 标志。每个线程都应该不时地检査这个标志， 以判断线程是否被中断。
 *      4.4.2 要想弄清中断状态是否被置位，首先调用静态的 Thread.currentThread 方法获得当前线程， 然后调用 islnterrupted() 方法
 *          while (!Thread.currentThread().islnterrupted() && more work to do)
 *          {
 *             do more work
 *          }
 *      4.4.3 但是， 如果线程被阻塞， 就无法检测中断状态。这是产生 InterruptedExceptioii 异常的地方。
 *            TODO 当在一个被阻塞的线程（调用 sleep 或 wait ) 上调用 interrupt 方法时， 阻塞调用将会被Interrupted Exception 异常中断。
 *           (存在不能被中断的阻塞 I/O 调用， 应该考虑选择可中断的调用)
 *      4.4.4 TODO 没有任何语言方面的需求要求一个被中断的线程应该终止。 1) 中断一个线程不过是引起它的注意。2) 被中断的线程可以决定如何响应中断。
 *                 3) 某些线程是如此重要以至于应该处理完异常后， 继续执行，而不理会中断。4) 但是，更普遍的情况是，线程将简单地将中断作为一个终止的请求。
 *           这种线程的 run 方法具有如下形式：
 *           Runnable r = () -> {
 *                      try
 *                      {
 *                                while (!Thread.currentThread().islnterrupted() && more work to do)
 *                              {
 *                                  do more work
 *                               }
 *                       }catch(InterruptedException e){
 *                          // thread was interr叩ted during sleep or wait
 *                      }finally{
 *                           cleanup, if required
 *                      }
 *                      // exiting the run method terminates the thread
 *              }；
 *         4.4.5 TODO 如果在每次工作迭代之后都调用 sleep 方法（或者其他的可中断方法，) islnterrupted 检测既没有必要也没有用处。
 *         如果在中断状态被置位时调用 sleep 方法，它不会休眠。相反，它将清除这一状态（!）并拋出 IntemiptedException。
 *         因此， 如果你的循环调用 sleep， 不会检测中断状态。相反，要如下所示捕获 InterruptedException 异常：
 *         Runnable r = 0 -> {
 *                 try{
 *                     while { more work to do)
 *                     {
 *                         do more work
 *                         Thread,sleep(delay);
 *                     }
 *                 }catch(InterruptedException e){
 *                     // thread was interrupted during sleep
 *                 }finally{
 *                   cleanup,if required
 *                 }//exiting the run method terminates the thread
 *          }；
 *        4.4.6 interrupted 和 islnterrupted ：
 *               Interrupted   方法是一个静态方法， 它检测当前的线程是否被中断。而且， 调用 interrupted 方法会清除该线程的中断状态。
 *               islnterrupted 方法是一个实例方法， 可用来检验是否有线程被中断。调用这个方法不会改变中断状态。
 *        4.4.7   java.Iang.Thread 1.0
 *              • void interrupt()
 *                  向线程发送中断请求。线程的中断状态将被设置为 true。如果目前该线程被一个 sleep
 *                  调用阻塞，那么，InterruptedException 异常被抛出。
 *              • static boolean interrupted()
 *                  测试当前线程（即正在执行这一命令的线程）是否被中断。注意，这是一个静态方法。
 *                  这一调用会产生副作用—它将当前线程的中断状态重置为 false
 *              • boolean islnterrupted()
 *                  测试线程是否被终止。不像静态的中断方法，这一调用不改变线程的中断状态。
 *              • static Thread currentThread()
 *                  返回代表当前执行线程的 Thread 对象。
 * TODO 线程使用经验
 * TODO 1. 如果需要执行一个比较耗时的任务应当并发地运行任务。
 * TODO 2. 应该将要并行运行的任务与运行机制解耦合。如果有很多任务， 要为每个任务创建一个独立的线程所付出的代价太大了。 可以使用线程池来解决这个问题
 */
public class Thread_Interrupte {
    public static void main(String[] args)  {
        Thread thread = Thread.currentThread();

        thread.interrupt();
        boolean interrupted = thread.isInterrupted();
        System.out.println(interrupted);//true

        boolean interrupted2 = thread.isInterrupted();
        System.out.println(interrupted2);//true


        try {
            //TODO  如果在中断状态被置位时调用 sleep 方法，它不会休眠。相反，它将清除这一状态（!）并拋出 IntemiptedException。
            Thread.sleep(100);  //中断状态由 true 清除为 false
        } catch (InterruptedException e) {
            // //TODO 中断状态由 true 清除为 false
            System.out.println(thread.isInterrupted()); //false

            //通常做法，再次将中断 状态设置为true
            Thread.currentThread().interrupt();
        }
        System.out.println(thread.isInterrupted());//true

    }
    @Test
    public void interuptedTest2(){
        Thread thread = Thread.currentThread();
        thread.interrupt();

        //TODO islnterrupted 方法是一个实例方法， 可用来检验是否有线程被中断。调用这个方法不会改变中断状态。
        boolean interrupted2 = thread.isInterrupted();
        System.out.println("before interrupted : Thread.isInterrupted() = "+interrupted2); //true

        //TODO Interrupted   方法是一个静态方法， 它检测当前的线程是否被中断。而且， 调用 interrupted 方法会清除该线程的中断状态。
        boolean interrupted = Thread.interrupted();
        System.out.println("Thread.interrupted() ："+interrupted);  //true

        //TODO islnterrupted 方法是一个实例方法， 可用来检验是否有线程被中断。调用这个方法不会改变中断状态。
        boolean interrupted3 = thread.isInterrupted();
        System.out.println("after interrupted : Thread.isInterrupted() ="+interrupted3); //false
    }
    @Test
    public  void interuptedTest() throws InterruptedException {
        // TODO 当在一个被阻塞的线程（调用 sleep 或 wait ) 上调用 interrupt 方法时， 阻塞调用将会被Interrupted Exception 异常中断。
        Thread thread = new Thread(()->{
            boolean interrupted = Thread.currentThread().isInterrupted();

            Thread.currentThread().interrupt();
            try {
                System.out.println("111111111111111111111");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("中断异常");
                e.printStackTrace();
            }
        }) ;
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
