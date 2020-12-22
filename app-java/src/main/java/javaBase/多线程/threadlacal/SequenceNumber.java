package javaBase.多线程.threadlacal;

//序列号生成器
public class SequenceNumber {
    //通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0 ;
        }
    };

    //获取下一个序列值
    public int getNextNum(){
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }

    /**
     * 每个线程所产生的序列号虽然都共享同一个SequenceNumber实例，但是他们并没有发生相互干扰的情况，
     * 而是各自产生独立的序列号，这是因为我们通过ThreadLocal为每一个线程提供了各自独立的副本。
     * @param args
     */
    public static void main(String[] args) {
        SequenceNumber sn = new SequenceNumber();

        //三个线程共享sn，但是各自产生序列号，互不影响
        TestClient testClient01 = new TestClient(sn);
        TestClient testClient02 = new TestClient(sn);
        TestClient testClient03 = new TestClient(sn);

        testClient01.start();
        testClient02.start();
        testClient03.start();
    }
    private static class TestClient extends Thread{
        private SequenceNumber sn ;
        public TestClient(SequenceNumber sn){
            this.sn = sn ;
        }

        @Override
        public void run() {
            //每个线程打印出3个序列值
            for (int i = 0; i < 3; i++) {
                System.out.println("Thread["+Thread.currentThread().getName()+"] sn["+sn.getNextNum()+"]");
            }
        }
    }
}





























