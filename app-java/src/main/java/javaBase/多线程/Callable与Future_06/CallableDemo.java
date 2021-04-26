package javaBase.多线程.Callable与Future_06;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
 * 创建线程的方式有四种：继承Thread,实现Runnable，实现Callable,使用线程池
 * 一、 创建执行线程的方式三：实现Callable接口。相较于Runnable接口的方式，方法可以有返回值，并可以抛出异常
 * 二、执行Callable方式，需要FutureTask实现类的支持，用于接收执行结果。FutureTask是Future接口的实现类。
 * 三、利用FutureTask 也能实现闭锁的功能。因为：
      // FutureTask的 get() 方法会对主线程产生阻塞，直到拿到线程的执行结果 ，主线程才会继续往下执行。
 * 实例：计算
 */
public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadDemo td1 = new ThreadDemo(200);
        ThreadDemo td2 = new ThreadDemo(100);

        //1、执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果
        FutureTask<Integer> f1 = new FutureTask<>(td1);
        FutureTask<Integer> f2 = new FutureTask<>(td2);

        long start = System.currentTimeMillis();
        //2 启动线程  这两个线程和主线程是同时并行运行的。
        new Thread(f1).start();
        new Thread(f2).start();

        int sum3 = 0 ;
        for(int i=0;i<200;i++){
            sum3 += i ;
        }
        System.out.println("sum3 = "+sum3);

        //接收线程运算后的返回结果
        //get()会对主线程产生阻塞，直到拿到线程的执行结果 ，主线程才会继续往下执行。
        //由此可见，FutureTask可用于闭锁：等到所有的其他线程都执行完了，才进行最终的运算。
        int sum1 = f1.get();
        int sum2 = f2.get();

        long end = System.currentTimeMillis();
        System.out.println("用时："+(end - start)); //利用FutureTask 也能实现闭锁的功能。
        System.out.println("sum3+sum1+sum2 = "+(sum3+sum1+sum2));
        //============================================================
    }
}
class ThreadDemo implements Callable<Integer> {
    int num ;
    public ThreadDemo(int num){
        this.num = num ;
    }
    @Override
    public Integer call() throws Exception {

        int sum = 0 ;
        for(int i=0;i<num;i++){
            sum += i ;
            //Thread.sleep(20);
        }
        System.out.println(Thread.currentThread().getName() +" ; sum = "+sum);
        return sum;
    }
}
