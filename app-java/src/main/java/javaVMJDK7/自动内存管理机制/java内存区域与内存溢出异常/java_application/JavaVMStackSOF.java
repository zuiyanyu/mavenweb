package javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application;

/**
 * -Xss108k 设置栈内存可用大小
 *
 * 使用-Xss 参数减小栈内存容量。结果：跑出 StackOverflowError异常，异常出现时输出的栈深度相应减小。
 * -Xms20M -Xmx20M -Xmn10M -Xss108k
 */
public class JavaVMStackSOF {
    private int stackLength =1 ;
    //递归 再现栈内存溢出
    public void stackLeak(){
        stackLength ++ ;
        stackLeak();
    }
    //
    private  void dontStop(){
        while(true){
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void stackLeakByThread(){
        while(true){
            Thread thread = new Thread(()->dontStop());
            thread.start();
        }
    }
    public static void main(String[] args) {
//       // 栈内存异常   JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:13)
        JavaVMStackSOF sof = new JavaVMStackSOF();
////        try{
////            sof.stackLeak();
////        }catch (Throwable e ){
////            e.printStackTrace();
////            System.out.println("stack length:"+sof.stackLength);
////        }

        //java.lang.OutOfMemoryError: Java heap space
        sof.stackLeakByThread();
    }
}
