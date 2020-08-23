package java基础.对象与类.内部类;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * TODO 局部内部类 :定义在方法中的类
 * TODO 局部类不能用 public 或 private 访问说明符进行声明。它的作用域被限定在声明这个局部类的块中
 * TODO 局部类有一个优势， 即对外部世界可以完全地隐藏起来。 即使 TalkingClock 类中的其他代码也不能访问它。
 * 如下示例：除 start 方法之外， 没有任何方法知道 TimePrinter 类的存在。
 *
 * TODO 与其他内部类相比较， 局部类还有一个优点。它们不仅能够访问包含它们的外部类， 还可以访问局部变量。
 * TODO 不过， 那些局部变量必须事实上为 final。这说明， 它们一旦赋值就绝不会改变。
 * 毕竟在 start 方法内部， 为什么不能访问 beep 变量的值呢？
 * 为了能够清楚地看到内部的问题， 让我们仔细地考査一下控制流程。
 * 1 ) 调用 start 方法。
 * 2 ) 调用内部类 TimePrinter 的构造器， 以便初始化对象变量 listener。
 * TODO  3 ) 将 listener 引用传递给 一个线程使用，并启动线程，定时器开始计时，start 方法结束。此时，start
 * TODO  方法的 beep 参数变量不复存在。但是外部类中的变量一直存在。(这就是根本原因所在了)
 * 4 ) 然后，actionPerformed方法执行 if (beep)..。.
 * 为了能够让 actionPerformed方法工作，TimePrinter 类在 beep 域释放之前将 beep 域用
 * start 方法的局部变量进行备份。实际上也是这样做的。在我们列举的例子中， 编译器为局
 * 部内部类构造了名字 TalkingClock$TimePrinter。 如果再次运行 ReflectionTest 程序， 查看
 * TalkingClock$Time- Printer 类，就会看到下列结果：
 *TODO     classFullName=java基础.对象与类.内部类.TalkingClock3$1TimePrinter
 *TODO     {
 *TODO         java基础.对象与类.内部类.TalkingClock3$1TimePrinter(TalkingClock3, boolean, double, String);
 *TODO
 *TODO         public void actionPerformed(java.awt.event.ActionEvent) ;
 *TODO
 *TODO         final boolean val$beep2;
 *TODO         final double val$PI;
 *TODO         final java.lang.String val$threadName;
 *TODO         final java基础.对象与类.内部类.TalkingClock3 this$0;
 *TODO     }
 * 请注意构造器的 boolean 参数和 val$beep2 实例变量。当创建一个对象的时候， beep2 就会
 * 被传递给构造器，并存储在 val$beep2 域中。编译器必须检测对局部变量的访问， 为每一个变
 * 量建立相应的数据域， 并将局部变量拷贝到构造器中， 以便将这些数据域初始化为局部变量的副本。
 * TODO 部类的方法只可以引用定义为 final 的局部变量,对它进行初始化后不能够再进行修改,因此，就使得局部变量与在局部类内建立的拷贝保持一致。
 */
public class InnerClass_3 {
    public static void main(String[] args) throws InterruptedException {
        TalkingClock3 talkingClock = new TalkingClock3(2,true);
        talkingClock.start("TalkingClock3",false);
        System.out.println("语音时钟已经开始了！");
    }
}

/**TODO 语音时钟
 *
 */
class TalkingClock3 {
    //TODO 构造一个语音时钟时需要提供两个参数：发布通告的间隔和开关铃声的标志。
    private int interval ;//时间间隔
    private boolean beep ;//是否打开铃声提醒

    /**
     * TODO 内部类使用了 beep  . if(beep) ,编译后的看到的结果是： if (TalkingClock3.access$000(this.this$0))
     * TODO 编译器自动为 外部类提供了一个静态方法：
     * static boolean access$000(TalkingClock3 this$0){
     *     return this$0.beep ;
     * }
     * TODO 编译器在外围类添加静态方法 access$000。它将返回作为参数传递给它的对象域beep。
     * 所以：内部类方法将调用 if(beep) 就会编译成if (TalkingClock3.access$000(this.this$0)) 。
     *
     *
     */
    //
    public void start(String threadName,boolean beep2) throws InterruptedException {
        //TODO 内部类访问的局部变量必须为final,不过不是，编译器会自动加上final修饰符。
        double PI = 3.14 ; //编译后的形式  final double PI = 3.14D;

        /**
         * TODO 定义在方法中的类：局部内部类
         * TODO TimePrinter 这个类名字只在start 方法中创建这个类型的对象时使用了一次。当遇到这类情况时， 可以在一个方法中定义局部类。
         * TODO 除 start 方法之外， 没有任何方法知道 TimePrinter 类的存在。即局部类对外部世界可以完全地隐藏起来。
         */
        class TimePrinter implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("At the tone, the time is " + new Date()) ;
                if(!beep2){
                    System.out.println("beep is "+beep2);
                    //TODO 还可以访问局部变量。那些局部变量必须事实上为 final。
                    System.out.println("PI ="+PI);
                    System.out.println("threadName ="+threadName);
                    System.out.println("----------");

                    //TODO 内部类访问的局部变量必须为final,不可更改
                    /**
                     * 编译后的start方法：
                     *     public void start(final String threadName, final boolean beep2) throws InterruptedException {
                     *         final double PI = 3.14D;
                     * TODO 可见编译器自动 为内部类使用的局部变量 加上了final修饰符。
                     */
                    //beep2 = false ;//error
                    //PI = 22.2 ;//error
                }

                if (beep){
                    //Toolkit.getDefaultToolkit().beep();
                    //表达式 OuterClass.this 表示外围类引用
                    //TODO 访问包含它们的外部类
                    System.out.println("发布通知... beep="+beep); //
                    System.out.println("发布通知... TalkingClock3.this.beep="+TalkingClock3.this.beep);
                    //beep = false ; //ok 外部类的变量可以改变
                }
                System.out.println("=============================");
            }
        }

        ActionListener timePrinterListener = new TimePrinter(); // 显示传递
        //ActionListener timePrinterListener2 = this.new TimePrinter(this); // 显示传递
        //发布通知
            new Thread(() ->{
            int i = 1;
            while (true && i-- > 0) {
                Thread thread = Thread.currentThread();
                System.out.println("threadName=" + thread.getName());
                System.out.println("className=" + getClass().getSimpleName());

                timePrinterListener.actionPerformed(null);
                //定时
                try {
                    Thread.sleep(interval * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } ).start();

    }




    public TalkingClock3(int interval,boolean beep){
        this.interval = interval ;
        this.beep = beep ;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }


    public boolean getBeep() {
        return beep;
    }

    public void setBeep(boolean beep) {
        this.beep = beep;
    }
}