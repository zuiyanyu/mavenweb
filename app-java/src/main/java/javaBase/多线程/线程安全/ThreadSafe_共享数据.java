package javaBase.多线程.线程安全;

/**
 * 什么是线程安全问题？
 * 所谓的线程安全问题，其实就是多线程在并发执行时候，对共享内存中的共享对象中的共享属性进行修所导致的数据冲突问题，
 * 称之为数据安全问题。
 */
public class ThreadSafe_共享数据 {
    public static void main(String[] args) {
        ShareData sd = new ShareData();

//        Thread t1 = new Thread(new Runnable(){
//            @Override
//            public void run() {
//                sd.userName = "张三" ;
//            }
//        });
        Thread t1 = new Thread(()->{
                sd.userName = "张三";
            try {
                //当前线程睡眠一秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1="+sd.userName);
        });
        Thread t2 = new Thread(()->{
            sd.userName = "李四";
            try {
                //当前线程睡眠一秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2="+sd.userName);

        });

        t1.start();
        t2.start();

        System.out.println("main 线程执行完毕！！！");

    }
}
class ShareData{
    public String userName ;
}
