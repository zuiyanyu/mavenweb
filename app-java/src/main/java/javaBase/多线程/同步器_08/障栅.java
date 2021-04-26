package javaBase.多线程.同步器_08;

/**
 * CyclicBarrier 类实现了一个集结点（rendezvous) 称为障栅（barrier)。
 * 考虑大量线程运行在一次计算的不同部分的情形。当所有部分都准备好时，需要把结果组合在一起。
 * 当一个线程完成了它的那部分任务后， 我们让它运行到障栅处。一旦所有的线程都到达了这个障栅，障栅就撤销， 线程就可以继续运行。
 *
 * 下面是其细节:
 * 首先， 构造一个障栅， 并给出参与的线程数：
 * CyclicBarrier barrier = new CydicBarrier(nthreads);
 * 每一个线程做一些工作，完成后在障栅上调用 await :
 * public void run()
 * {
 * doWork() ;
 * bamer.await()；
 * ...
 * }
 * await 方法有一个可选的超时参数：
 * barrier.await(100, TineUnit.MILLISECONDS);
 * 如果任何一个在障栅上等待的线程离开了障栅， 那么障栅就被破坏了（线程离开可能是因为它调用 await 时设置了超时， 或者因为它被中断了）。
 * 在这种情况下， 所有其他线程的await 方法抛出 BrokenBarrierException 异常。那些已经在等待的线程立即终止 await 的调用。
 *
 *可以提供一个可选的障栅动作 （ barrier action), 当所有线程到达障栅的时候就会执行这一动作。 该动作可以收集那些单个线程的运行结果。
 * Runnable barrierAction = . .
 * CyclicBarrier barrier = new Cyc1icBarrier(nthreads, barrierAction);
 *
 * 障栅被称为是循环的（cyclic), 因为可以在所有等待线程被释放后被重用。在这一点上，有别于 CountDownLatch, CountDownLatch 只能被使用一次。
 *
 * Phaser 类增加了更大的灵活性，允许改变不同阶段中参与线程的个数。
 *
 */
public class 障栅 {
}
