package javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application;

/**
 * VM options: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails   -XX:SurvivorRatio=8
 * 代码限制大小为20M不可扩展：将堆的最小值:-Xms 参数与最大值: -Xmx 参数设置为一样即可避免堆自动扩展。
 */
public class YoungGenGC {
    public static void main(String[] args) {
        System.out.println("hellow");
    }
}
