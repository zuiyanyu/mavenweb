package javaBase.多线程.阻塞队列05;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    private static final File DUMMY = new File("");
//    private static BlockingQueue<File> queue = new ArrayBlockingQueue(FILE_QUEUE_SIZE);
    private static ArrayBlockingQueue<File> queue = new ArrayBlockingQueue(FILE_QUEUE_SIZE);

    /**
     * 我们使用一个小技巧在工作结束后终止这个应
     * 用程序。为了发出完成信号， 枚举线程放置一个虚拟对象到队列中（这就像在行李输送带上
     * 放一个写着“ 最后一个包” 的虚拟包。) 当搜索线程取到这个虚拟对象时， 将其放回并终止。
     *
     * 注意，不需要显式的线程同步。在这个应用程序中， 我们使用队列数据结构作为一种同步机制。
     * @param args
     */
    public static void main(String[] args) {
        //TODO 1.文件扫描线程 和 文件搜索线程 同时进行。
        try {
            String directory = "C:\\Users\\Administrator\\Desktop\\a";
            String keyword ="mtf";

            Runnable enumerator = () -> {
                try{
                    enumerate(new File(directory));
                    queue.put(DUMMY) ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            //TODO 2. 启动文件扫描线程
            new Thread(enumerator).start();


            //我们同时启动了大量搜索线程o 每个搜索线程从队列中取出一个文件进行搜索
            for (int i = 1; i <= SEARCH_THREADS; i++) {
                Runnable searcher = () -> {
                    try{
                        boolean done = false ;
                        while(!done){
                            File file = queue.take(); // 队列空，线程阻塞；对列再次有值，退出阻塞；
                            if(file == DUMMY){
                                queue.put(DUMMY); // 防止其他阻塞的线程一直被阻塞着(queue.take()被阻塞的那些线程)；
                                done = true ;
                            }else{
                                search(file, keyword);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };
                //TODO 3. 启动搜索线程
                new Thread(searcher).start();
            }
        }finally {

        }

    }

    //生产者线程枚举在所有子目录下的所有文件并把它们放到一个阻塞队列中
    public static void enumerate(File directory) throws InterruptedException{
        File[] files = directory.listFiles();
        for (File file : files){
            if (file.isDirectory()){
                enumerate(file);
            }else {
                queue.put(file);
            }
        }
    }

    public static void search(File file, String keyword) throws IOException {
        try (Scanner in = new Scanner(file, "UTF-8")){
            int lineNuinber = 0;
            while (in.hasNextLine()){
                lineNuinber++;
                String line = in.nextLine();
                if (line.contains(keyword)){
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNuinber, line);
                }
            }
        }
    }
}
