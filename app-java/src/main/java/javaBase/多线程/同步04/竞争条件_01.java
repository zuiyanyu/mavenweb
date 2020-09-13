package javaBase.多线程.同步04;

/**
 * TODO 同步
 * 0. TODO 在大多数实际的多线程应用中， 两个或两个以上的线程需要共享对同一数据的存取。
 *
 * TODO 竞争
 * TODO 1. 如果两个线程存取相同的对象， 并且每一个线程都调用了一个修改该对象状态的方法，将会发生什么呢？
 *         可以想象， 线程彼此踩了对方的脚。 根据各线程访问数据的次序， 可能会产生讹误的对象。这样一个情况通常称为竞争条件（race condition。)
 * TODO 2. 为了避免多线程引起的对共享数据的说误，必须学习如何同步存取。
 * TODO 3. 竞争条件的一个例子:
 *      模拟一个有若干账户的银行。随机地生成在这些账户之间转移钱款的交易。
 *      每一个账户有一个线程。每一笔交易中， 会从线程所服务的账户中随机转移一定数目的钱款到另一个随机账户。
 *      银行中的总金额不应该改变。
 *
 */
public class 竞争条件_01 {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000 ;
    public static final double MAX_AMOUNT = 1000;
    public static  int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS ; i++) {
            int fromAccount =i;
            Runnable r = () -> {

                    while (true)
                    {
                        int toAccount = (int) (bank.size() * Math.random()) ;
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount , toAccount, amount);
                        try
                        {
//                            Thread.sleep((int) (DELAY * Math.random())) ;

                            // ++DELAY ;
                            Thread.sleep(3 ) ;
                        }catch (InterruptedException e){}
                    }

            };
            new Thread(r,"thread_"+i).start();
        }

        /**TODO 一个运行结果。
         * currentThread is thread_9
         *     216.88 from 9 to 25Total Balance:  100000.00
         * currentThread is thread_69
         * currentThread is thread_55
         *     890.18 from 55 to 25Total Balance:   94940.39
         *
         * TODO 运行结果出现了错误。
         *在最初的交易中， 银行的余额保持在 $100 000, 这是正确的， 因为共 100 个账户， 每个账户 $1 000。
         * 但是， 过一段时间， 余额总量有轻微的变化。
         * 当运行这个程序的时候， 会发现有时很快就出错了， 有时很长的时间后余额发生混乱。这样的
         * 状态不会带来信任感， 人们很可能不愿意将辛苦挣来的钱存到这个银行。
         *
         *  synchronized (Bank.class){
         *      //在此加了静态锁，但是还是会出现金额变少的并发情况，所以，不全是书上说的，只是因为 -= 操作是非原子性的操作。
         *      //其实主要原因 是 -=  和 += 不是 原子操作的原因造成的并发异常。
         *      accounts[from] -= amount ;
         *  }
         */
    }
}

