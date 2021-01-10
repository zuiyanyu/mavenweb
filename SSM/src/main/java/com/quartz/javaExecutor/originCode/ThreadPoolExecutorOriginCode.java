package com.quartz.javaExecutor.originCode;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorOriginCode {

    /**
     * TODO java.util.concurrent.ThreadPoolExecutor 的源码分析：
     *
     * RUNNING
     * 1110 0000 0000 0000 0000 0000 0000 0000   -1 << 29
     *
     * SHUTDOWN=
     * 0000 0000 0000 0000 0000 0000 0000 0000   0<< 29
     *
     * STOP=
     * 0010 0000 0000 0000 0000 0000 0000 0000   1 << 29
     *
     * TIDYING=
     * 0100 0000 0000 0000 0000 0000 0000 0000  2 << 29
     *
     * TERMINATED=
     * 0110 0000 0000 0000 0000 0000 0000 0000  3 << 29
     *
     * CAPACITY
     * 0001 1111 1111 1111 1111 1111 1111 1111  (1 << 29) - 1
     *
     * ~CAPACITY
     * 1110 0000 0000 0000 0000 0000 0000 0000
     *
     * TODO workerCountOf()方法解析：
     * // a | b & c = a & c | b
     *int j = (( -2 << 29 ) | 1 ) & ((1 << 29) - 1)
     * = (RUNNING | workCount)  & CAPACITY
     * = (RUNNING & CAPACITY)| workCount;
     * = 00000 0000 0000 0000 0000 0000 0000 0000 | workCount
     * = workCount
     *用法：
     *   int wc = workerCountOf(c);
        if (wc >= CAPACITY || wc >= (core ? corePoolSize : maximumPoolSize))
            return false;
     *
     *
     * TODO runStateOf()方法解析：
     *   RUNNING  &  ~CAPACITY
     * = RUNNING
     *用法：
     * if(runStateOf(c) == SHUTDOWN ){
     *
     * }
     *
     * =====================================
     * 11111111111111111111111111111111               -1
     TODO 1110 0000 0000 0000 0000 0000 0000 0000    -1<< 29    RUNNING
     * 1110 0000 0000 0000 0000 0000 0000 0001        (-1<< 29) | 1
     *
     *
     *
     * 0010 0000 0000 0000 0000 0000 0000 0000    1 << 29
     TODO 0001 1111 1111 1111 1111 1111 1111 1111    (1 << 29) -1
     *
     *
     *
     * @param args
     */
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 1));
    private static final int COUNT_BITS = Integer.SIZE - 3; //29
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;//536870911

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS; //-536870912
    private static final int SHUTDOWN   =  0 << COUNT_BITS;  //0
    private static final int STOP       =  1 << COUNT_BITS;   //536870912
    private static final int TIDYING    =  2 << COUNT_BITS;   //1073741824
    private static final int TERMINATED =  3 << COUNT_BITS;   //1610612736

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }


    public static void main(String[] args) {
        System.out.println(" aa  =" + ((-1 << 29 )  ));
        System.out.println("-1 binary ="+ Integer.toBinaryString(-1));
        System.out.println("-1 << 29 binary ="+ Integer.toBinaryString(-1 << 29));
        System.out.println("-1 << 29  | 1 binary ="+ Integer.toBinaryString((-1 << 29 )|1));

        System.out.println("1 << 29 binary ="+ Integer.toBinaryString(1 << 29));
        System.out.println("1 << 29  - 1 binary ="+ Integer.toBinaryString((1 << 29)-1));

        int j = (( -2 << 29 ) | 1 ) & ((1 << 29) - 1) ;// a | b & c = a &c | b
        System.out.println("j =" +j);

        System.out.println("RUNNING & (~CAPACITY)"+ Integer.toBinaryString(RUNNING & (~CAPACITY)));

        System.out.println("COUNT_BITS ="+COUNT_BITS);
        System.out.println("CAPACITY ="+CAPACITY);

        System.out.println("RUNNING ="+Integer.toBinaryString(RUNNING));
        System.out.println("SHUTDOWN="+Integer.toBinaryString(SHUTDOWN));
        System.out.println("STOP="+Integer.toBinaryString(STOP));
        System.out.println("TIDYING="+Integer.toBinaryString(TIDYING));
        System.out.println("TERMINATED="+Integer.toBinaryString(TERMINATED));

//        System.out.println();
//
//        OnlyTest onlyTest = new OnlyTest();
//        int i = onlyTest.ctl.get();
//        System.out.println("onlyTest.ctl.get()="+ i);
//
//        System.out.println(" runStateOf = "+ runStateOf(i));
//        System.out.println(" workerCountOf = "+ workerCountOf(i));
    }

}
