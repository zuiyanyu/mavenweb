package javaBase.多线程.执行器_07;

import java.io.File;
import java.util.ArrayDeque;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 前面的程序例子产生了大量的生命期很短的线程， 每个目录产生一个线程。程序
 * 清单 14-11 中的程序使用了一个线程池来运行任务。
 * 出于信息方面的考虑， 这个程序打印出执行中池中最大的线程数。 但是不能通过
 * ExecutorService 这个接口得到这一信息。 因此， 必须将该pool 对象强制转换为 ThreadPoolExecutor 类对象。
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String directory = "C:\\Users\\Administrator\\Desktop\\a";
        String keyword ="mtf";

        //创建线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        //创建任务
        MatchCounter matchCounter = new MatchCounter(new File(directory), keyword,pool);
        //向线程池中提交任务
        Future<Integer> result = pool.submit(matchCounter);

        List<Future<Integer>> futures = pool.invokeAll(new ArrayDeque<MatchCounter>());

        System.out.println("before result.get():" + matchCounter.getCount());
        //打印线程执行的结果   会阻塞
        System.out.println(result.get() + " matching files.");

        System.out.println("after result.get():" + matchCounter.getCount());
    }
}
