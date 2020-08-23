package javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存占位符对象，一个OOMObjec大约占64KB
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class OOMObject {
    static class OOMObjectInner{
        public byte[] placeholder = new byte[64*1024] ;
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObjectInner> list = new ArrayList<>();
        for(int i = 0 ; i < num ; i++){
            //稍作演示，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObjectInner());
            System.gc();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
