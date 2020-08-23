package javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application;

import java.util.ArrayList;
import java.util.List;

/**
 * jdk 7的运行时常量池是方法区的一部分。
 * String.intern() 是一个Native方法，它的作用是：
 * 1.如果字符串常量池中已经包含一个等于此String对象的字符串，则返回代表池中这个字符串的String对象
 * 2.否则，将此String对象包含的字符串添加到常量池中，并返回此String对象的引用。
 *
 * JDK 1.6 版本中，由于常量池分配在永久代中，我们可以通过 -XX : PermSize 和 -XX:MaxPermSize限制方法区大小，从而
 * 间接限制其中常量池的容量。
 * -Xms20M -Xmx20M -Xmn10M -Xss108k  -XX:PermSize=10M  -XX:MaxPermSize=10MB
 *
 * JDK1.8中去除了  -XX:PermSize  -XX:MaxPermSize  这两个参数。
 * (JVM的PermGen空间被移除：取代它的是Metaspace（JEP 122）元空间)
 *      Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 *      ...
 *      Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=10MB; support was removed in 8.0
 *      Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=10M; support was removed in 8.0
 *      Java HotSpot(TM) 64-Bit Server VM warning: MaxNewSize (10240k) is equal to or greater than the entire heap (10240k).  A new max generation size of 9728k will be used.
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        internTest2();
    }
    public static void runtimeConstantPoolOOM(){
        //使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<>();
        int i = 0 ;
        while(true){
            String s = String.valueOf(i++);
            String intern = s.intern();
            list.add(intern);
        }
    }
    public  static void internTest2(){
        //字面常量创建的字符串是在常量池中。
        String ss = "helloworld";

        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();

        //StringBuilder.toString() 创建的字符串实例在java堆上。
        String s1 = stringBuilder1.append("你好").append("世界").toString();
        String s2 = stringBuilder2.append("hello").append("world").toString();

        //这个返回的是true . 因为intern()实现不会复制实现到常量池中，而是在常量池中记录首次出现的实例引用。
        //因此intern()返回的引用和由StringBuilder.toString()创建的字符串实例是同一个。
        System.out.println(s1 == s1.intern());

        //返回false .因为helloworld这个字符串在执行StringBuilder.toString()之前已经出现过，字符串常量池中已经有它的应用，
        //不符合首次出现的冤死。 而 "你好世界" 字符串则是首次出现的。
        System.out.println(s2 == s2.intern());
    }

}
