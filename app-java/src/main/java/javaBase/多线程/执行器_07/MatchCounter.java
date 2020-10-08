package javaBase.多线程.执行器_07;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class MatchCounter implements Callable<Integer> {

    private File directory;
    private String keyword;
    private ExecutorService pool ;
    private int count;

    public int getCount(){
        return count ;
    }

    public MatchCounter(File directory, String keyword, ExecutorService pool){
        this.directory = directory ;
        this.keyword = keyword ;
        this.pool = pool ;
    }
    @Override
    public Integer call() throws Exception {
        File[] files = directory.listFiles();

        //每个目录下 各个文件查找到符合要求的文件量
        count = 0;

        //每一个目录下的子目录形成的任务队列 (计算每个子目录下符合的文件数)
        List<Future<Integer>> results = new ArrayList();

        //总符合文件数= 当前目录下符合的文件数 + 每个子目录下符合的文件数

        for (File file : files){
            if (file.isDirectory()){
                //在 call 方法内部， 使用相同的递归机制。 对于每一个子目录， 我们产生一个新的MatchCounter 并为它启动一个线程。
                //创建任务
                MatchCounter counter = new MatchCounter(file, keyword,pool);
                Future<Integer> submit = pool.submit(counter);
                results.add(submit);

                //启动任务
                //pool.submit(counter); 进行提交后，任务就自动start执行了
            }else{
                if (search(file)) count++;
            }
        }
        //把 FutureTask 对象隐藏在 ArrayList<Future<Intege>>中。最后， 把所有结果加起来
        for (Future<Integer> result : results){
            //TODO get 方法的调用被阻塞， 直到计算完成。
            //每一次对 get 的调用都会发生阻塞直到结果可获得为止。 当然，线程是并行运行的， 因此， 很可能在大致相同的时刻所有的结果都可获得。
            count += result.get();
        }
        return count;
    }
    public  boolean search(File file) throws IOException {
        try (Scanner in = new Scanner(file, "UTF-8")){
            int lineNuinber = 0;
            boolean found = false;
            while (!found && in.hasNextLine()){
                lineNuinber++;
                String line = in.nextLine();
                if (line.contains(this.keyword)){
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNuinber, line);
                    found = true;
                }
            }
            return found ;
        }
    }
}
