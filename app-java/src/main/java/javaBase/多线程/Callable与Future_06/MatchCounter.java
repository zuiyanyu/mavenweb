package javaBase.多线程.Callable与Future_06;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class MatchCounter implements Callable<Integer> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String directory = "C:\\Users\\Administrator\\Desktop\\a";
        String keyword ="mtf";
        MatchCounter matchCounter = new MatchCounter(new File(directory), keyword);

        //利用 MatchCounter 创建一个 FutureTask 对象， 并用来启动一个线程。
        FutureTask<Integer> futureTask = new FutureTask(matchCounter);
        new Thread(futureTask).start();

        //对 get 的调用会发生阻塞， 直到有可获得的结果为止。
        Integer counts = futureTask.get();
        System.out.println(counts);


    }
    private File directory;
    private String keyword;
    public MatchCounter(File directory, String keyword){
        this.directory = directory ;
        this.keyword = keyword ;
    }
    @Override
    public Integer call() throws Exception {
        File[] files = directory.listFiles();

        //每个目录下 各个文件查找到符合要求的文件量
        int count = 0;

        //每一个目录下的子目录形成的任务队列 (计算每个子目录下符合的文件数)
        List<Future<Integer>> tasks = new ArrayList();

        //总符合文件数= 当前目录下符合的文件数 + 每个子目录下符合的文件数

        //一个目录下的文件数量
        for (File file : files){
            if (file.isDirectory()){
                //在 call 方法内部， 使用相同的递归机制。 对于每一个子目录， 我们产生一个新的MatchCounter 并为它启动一个线程。
                //创建任务 一个文件一个线程，没有限制
                MatchCounter counter = new MatchCounter(file, keyword);
                FutureTask<Integer> task = new FutureTask(counter);
                //需要保存引用，用于获取线程的执行结果
                tasks.add(task);

                //启动任务
                Thread t = new Thread(task);
                t.start();
            }else{
                if (search(file)) count++;
            }
        }
        //把 FutureTask 对象隐藏在 ArrayList<Future<Intege>>中。最后， 把所有结果加起来
        for (Future<Integer> result : tasks){
            //TODO get 方法的调用被阻塞， 直到计算完成。
            //每一次对 get 的调用都会发生阻塞直到结果可获得为止。 当然，线程是并行运行的， 因此， 很可能在大致相同的时刻所有的结果都可获得。
            count += result.get();
        }
        return count;
    }
    public  boolean search(File file) throws IOException {
        try (Scanner in = new Scanner(file, "UTF-8")){
            int lineNuinber = 0;
            while (in.hasNextLine()){
                lineNuinber++;
                String line = in.nextLine();
                if (line.contains(this.keyword)){
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNuinber, line);
                    return true;
                }
            }
            return false ;
        }
    }
}
