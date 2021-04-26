package javaBase.多线程.同步04;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    //TODO 对象实例锁
    private Lock bankLock = new ReentrantLock();

    //TODO 条件对象  在此设置一个条件对象来表达“ 余额充足” 条件。
    private Condition sufficientFunds;

    //银行账户
    double[] accounts   ;

    public Bank(int naccounts, double initialBalance) {
        accounts = new double[naccounts];
        Arrays.fill(accounts,initialBalance);

        //TODO 用 newCondition 方法获得一个条件对象。
        sufficientFunds = bankLock.newCondition();
    }


    /**
     * 一个账户转移一定数目的钱款到另一个账户（还没有考虑负的账户余额)
     * @param from
     * @param to
     * @param amount
     */
    //public synchronized void transfer(int from ,int to ,double amount)
    public   void transfer(int from ,int to ,double amount)  {
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        //getTotalBalance() 获取银行总金额，总金额不应该改变
        System.out.println("currentThread is "+ name +" and getTotalBalance = "+ getTotalBalance());

        bankLock.lock();
        try{
            while (accounts[from] < amount){
                //如果账户的钱少于当前要转的金额，就进行阻塞等待。
                sufficientFunds.await();
            }

            System.out.printf("%s:accounts[from] =accounts[%d] = %10.2f ，amount = %10.2f%n ",name,from,accounts[from],amount);
            accounts[from] -= amount ;
            System.out.printf("%s: accounts[from]=accounts[%d] = %10.2f + %10.2f = %10.2f %n ",name,from,accounts[from], amount, accounts[from] + amount);

            accounts[to] += amount;
            System.out.printf(name + ":Total Balance: %10.2f%n%n", getTotalBalance());

            //唤醒所有正在等待的线程
            sufficientFunds .signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bankLock.unlock();
        }
    }

    //获取银行总金额，总金额不应该改变
    private Object getTotalBalance() {

        bankLock.lock() ;
        try{
            double sum = 0;
            for (double account : accounts) {
                sum += account;
            }
            return sum;
        }finally {
            bankLock.unlock();
        }
    }
    public int size(){
        return accounts.length;
    }
}