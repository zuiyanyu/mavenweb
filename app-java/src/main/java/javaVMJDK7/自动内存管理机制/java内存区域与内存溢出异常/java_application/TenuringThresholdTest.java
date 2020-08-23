package javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application;

/**
 * -XX:+PrintGCDetails  -Xms20M -Xmx20M -Xmn10M    -XX:MaxTenuringThreshold=1
 */
public class TenuringThresholdTest {
    private static final int _1MB = 1024 * 1024 ;

    private static void testTenuringThresholdTest(){
        byte[] allocation1 = new byte[_1MB / 4];  // 256k

        //什么时候进入老年代 取决-XX:MaxTenuringThreshold 设置的值
        byte[] allocation2 = new byte[_1MB * 4];
        byte[] allocation3 = new byte[_1MB * 4];
        allocation3 = null ;
        allocation3 =  new byte[_1MB * 4];

    }
    public static void main(String[] args) throws InterruptedException {
        testTenuringThresholdTest();
        Thread.sleep(10000);
    }
}
