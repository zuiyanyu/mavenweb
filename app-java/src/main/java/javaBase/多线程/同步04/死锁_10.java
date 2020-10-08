package javaBase.多线程.同步04;

/**
 *TODO 死锁（deadlock )
 *TODO 1. 锁和条件不能解决多线程中的所有问题。
 * 考虑下面的情况：
 * 账户 1: $200
 * 账户 2: $300
 * 线程 1: 从账户 1 转移 $300 到账户 2
 * 线程 2: 从账户 2 转移 $400 到账户 1
 *TODO 线程 1 和线程 2 都被阻塞了。因为账户 1 以及账户 2 中的余额都不足以进行转账，两个线程都无法执行下去。
 *TODO 有可能会因为每一个线程要等待更多的钱款存人而导致所有线程都被阻塞。这样的状态称为死锁（deadlock )
 *
 * TODO 2. 我们的bank程序的死锁现象：
 * 在这个程序里，死锁不会发生， 原因很简单。 每一次转账至多 $1 000。因为有 100 个账
 * 户， 而且所有账户的总金额是 $100 000, 在任意时刻， 至少有一个账户的余额髙于 $1 000，从该账户取钱的线程可以继续运行。
 * 但是， 如果修改 run方法，把每次转账至多 $1 000 的限制去掉，死锁很快就会发生。
 * 试试看。将 NACCOUNTS 设为 10。每次交易的金额上限设置为 2 * INITIAL_BALANCE, 然后运行该程序。程序将运行一段时间后就会挂起。
 *
 *TODO 3. 必须仔细设计程序， 以确保不会出现死锁。 别无他法。
 */
public class 死锁_10 {
    public static final int NACCOUNTS = 10;
    public static final double INITIAL_BALANCE = 1000 ;
    public static final double MAX_AMOUNT = 10000;
    public static  int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, 2*INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {

                while (true) {
                    int toAccount = (int) (bank.size() * Math.random());
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(fromAccount, toAccount, amount);
                    try {
//                            Thread.sleep((int) (DELAY * Math.random())) ;

                        // ++DELAY ;
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                    }
                }

            };
            new Thread(r, "thread_" + i).start();
        }
    }
}
