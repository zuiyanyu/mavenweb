package javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 线程监控： jstack命令
 * 线程长时间停顿的主要原因主要有：等待外部资源(数据库连接，网络资源，设备资源等)、死循环、锁等待（活锁和死锁）
 */
public class ThreadTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        System.out.println(line);

        createBusyThread();

        String line1 = br.readLine();
        System.out.println(line1);

        Object obj = new Object();
        createLockThread(obj);
    }
    /**
     * 线程锁等待演示
     *
     */
    public static void createLockThread(final Object lock){
        Thread thread = new Thread(()->{
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"testLockThread");
        thread.start();
    }
    /**
     * 线程死循环演示
     */
    public static void createBusyThread(){
        Thread thread = new Thread(()->{
            while(true);
        },"testBusyThread");
        thread.start();
    }
    /**
     * 无法再被激活的死锁等待
     */

}
