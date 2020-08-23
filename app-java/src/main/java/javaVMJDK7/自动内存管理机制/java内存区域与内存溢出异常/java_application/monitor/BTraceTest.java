package javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 @BTrace
 public class TracingScript {
@OnMethod(
        clazz ="javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application.monitor.BTraceTest",
        method = "add",
        location = @Location(Kind.RETURN)
)
public static void func(@Self javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application.monitor.BTraceTest instance , int a ,int b ,@Return int result){
        println("调用堆栈：");
        jstack();
        println(strcat("方法参数a:",str(a))) ;
        println(strcat("方法参数b:",str(b))) ;
        println(strcat("方法结果:",str(result))) ;
        }
        }

  * Starting BTrace task
  ** Compiling the BTrace script ...
  *** Compiled
  ** Instrumenting 1 classes ...
  *** Done
  ** BTrace up&running

  *** Done
  ** BTrace up&running

 调用堆栈：
 javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application.monitor.BTraceTest.add(BTraceTest.java:9)
 javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application.monitor.BTraceTest.main(BTraceTest.java:19)
 方法参数a:520
 方法参数b:463
 方法结果:983
 调用堆栈：
 javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application.monitor.BTraceTest.add(BTraceTest.java:9)
 javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application.monitor.BTraceTest.main(BTraceTest.java:19)
 方法参数a:993
 方法参数b:79
 方法结果:1072
 调用堆栈：
 */
public class BTraceTest {
    public int add(int a ,int b ){
        return a + b ;
    }

    public static void main(String[] args) throws IOException {
        BTraceTest bTraceTest = new BTraceTest();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        for(int i =0 ;i < 10 ; i++){
            int a = (int)Math.round(Math.random() * 1000);
            int b = (int)Math.round(Math.random() * 1000);
            System.out.println(bTraceTest.add(a,b));
        }

    }
}
