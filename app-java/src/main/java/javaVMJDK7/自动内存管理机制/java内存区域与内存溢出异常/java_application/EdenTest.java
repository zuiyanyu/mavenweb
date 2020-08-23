package javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application;

/**
 * -XX:+PrintGCDetails  -Xms20M -Xmx20M -Xmn10M  -XX:SurvivorRator = 8
 * 1. -Xms20M -Xmx20M 限制堆大小为20M.  2.并且-Xmn10M 分配给新生代10M的空间。3.剩下的10M分配给老年代。
 * 4.-XX:SurvivorRator = 8 决定了新生代中 Eden:Survivor = 8 ：1 ，  Survivor有两个。
 * 5.Full GC 一般比 Minor GC慢 10 倍以上。 所以要尽量避免老年代的GC .
 * 6. 大对象会直接进入老年代。 (新生代采用复制算法收集内存)
 * (写程序的时候尽量避免  朝生夕灭的“短命大对象”。 大对象：比如很长的字符串 以及数组等 )
 * 经常出现大对象容易导致还有不少空间时候，就提前触发垃圾收集GC，来获取足够的连续内存空间 来安置要创建的大对象。
 *
 *
 */
public class EdenTest {
    private static final int _1MB = 1024 * 1024 ;
    public static void testAllocation(){
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB] ;
        allocation2 = new byte[4 * _1MB] ;
        allocation3 = new byte[4 * _1MB] ;
        allocation4 = new byte[5 * _1MB] ;
    }
    public static void main(String[] args) {
        testAllocation();
    }
}
