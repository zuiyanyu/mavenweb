package javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application;

/**
 * 此代码演示两点：
 * 1. 对象可以在GC时自我拯救。
 * 2. 这种自救的机会只会有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次。
 * 注意：
 * 3. 尽量避免使用finalize()方法，因为他的执行代价高昂，不确定性大，无法保证各个对象的调用顺序。
 */
public class FinalizeEscapeGC {
    public  static FinalizeEscapeGC SAVE_HOOK = null ;
    public void isAlive(){
        System.out.println("yes, i am is still alive !");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finallize method executed !");
        FinalizeEscapeGC.SAVE_HOOK = this ;
    }
    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK  = new FinalizeEscapeGC() ;
        // 对象第一次拯救自己
        SAVE_HOOK = null ;
        System.gc();

        //因为finallize方法优先级很低，所以暂停0.5秒以等待他
        Thread.sleep(500);

        if(SAVE_HOOK != null ){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no,i am dead !");
        }

        //下面代码与上次的完全相同，但是这次自救失败了。因为finallize 方法只执行一次。
        SAVE_HOOK = null ;
        System.gc();

        //因为finallize方法优先级很低，所以暂停0.5秒以等待他
        Thread.sleep(500);

        if(SAVE_HOOK != null ){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no,i am dead !");
        }

    }


}
