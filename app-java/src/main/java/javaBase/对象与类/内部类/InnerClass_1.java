package javaBase.对象与类.内部类;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * TODO 6.4.1 使用内部类访问对象状态
 * TODO 1. 内部类访问外部类的机制:内部类有一个外围类的引用 outer . 其实 表达式 OuterClass.this 表示外围类引用
 * TODO 2. TimePrinter 类声明为私有的。这样一来， 只有 TalkingClock 的方法才能够构造TimePrinter 对象。
 * TODO 3. 只有内部类可以是私有类，而常规类只可以具有包可见性，或公有可见性。
 *
 */
public class InnerClass_1 {
    public static void main(String[] args) throws InterruptedException {
        TalkingClock talkingClock = new TalkingClock(5,true);
        talkingClock.start();
        System.out.println("语音时钟已经开始了！");
    }
}
/**TODO 语音时钟
 *
 */
class TalkingClock {
    //TODO 构造一个语音时钟时需要提供两个参数：发布通告的间隔和开关铃声的标志。
    private int interval ;//时间间隔
    private boolean beep ;//是否打开铃声提醒

    /**
     * TODO 内部类使用了 beep.if(beep) ,编译后的看到的结果是： if (TalkingClock.access$000(this.this$0))
     * TODO 编译器自动为 外部类提供了一个静态方法：
     * static boolean access$000(TalkingClock this$0){
     *     return this$0.beep ;
     * }
     * TODO 编译器在外围类添加静态方法 access$000。它将返回作为参数传递给它的对象域beep。
     * 所以：内部类方法将调用 if(beep) 就会编译成if (TalkingClock.access$000(this.this$0)) 。
     *
     *
     */

    public void start() throws InterruptedException {

        /*TODO 内部类中的机制：第1步：传递外部类引用给内部类
          TODO 当在 start 方法中创建了 TimePrinter 对象后， 编译器就会将 this 引用传递给当前的语音时钟的构造器：
         */
        // ActionListener timePrinterListener = new TimePrinter(); //编译器自动传递
        ActionListener timePrinterListener = new TimePrinter(this); // 显示传递
        //ActionListener timePrinterListener2 = this.new TimePrinter(this); // 显示传递
        //发布通知
        new Thread(() ->{
            while (true) {
                Thread thread = Thread.currentThread();
                System.out.println("threadName=" + thread.getName());
                System.out.println("className=" + getClass().getSimpleName());
                //定时
                try {
                    Thread.sleep(interval * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timePrinterListener.actionPerformed(null);
            }
        } ).start();

    }
    /* An inner class : 监听器，打印时间*
       TODO TimePrinter 类声明为私有的。这样一来， 只有 TalkingClock 的方法才能够构造TimePrinter 对象。
     */
    private class TimePrinter implements ActionListener {

        // TODO 内部类中的机制：第2步：接收外部类引用
        /* TODO 1.外围类的引用在构造器中设置。编译器修改了所有的内部类的构造器， 添加一个外围类引用的参数。
         * TODO 2.因为 TimePrinter 类没有定义构造器，所以编译器为这个类生成了一个默认的构造器，其代码如下所示
         * */
        TalkingClock outer ;

        public TimePrinter(TalkingClock clock) // automatically generated code
        {
            outer = clock;

        }

        /**
         * TODO 编译后的结果如下： (this$0 便是 编译器修改了所有的内部类的构造器，添加一个外围类引用的参数)
         *  TalkingClock outer;
         *  final TalkingClock this$0;  //TODO 这就是编译器自动生成的域
         *   public TalkingClock$TimePrinter(TalkingClock this$0, TalkingClock clock)
         *   {
         *      outer = clock;
         *      this.this$0 =this$0;
         *   }
         */

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("At the tone, the time is " + new Date()) ;
            //TODO 内部类中的机制：第3步：使用外部类引用来访问外部类的 域和方法。
            /* TODO 1.TimePrinter 类没有实例域或者名为 beep 的变量，取而代之的是beep 引用了创建 TimePrinter 的 TalkingClock 对象的域。
             * TODO 2.一个方法可以引用调用这个方法的对象数据域。内部类既可以访问自身的数据域，也可以访问创建它的外围类对象的数据域
             * TODO 3.为了能够运行这个程序， 内部类的对象总有一个隐式引用， 它指向了创建它的外部类对象。
             * TODO 4.这个引用在内部类的定义中是不可见的。然而， 为了说明这个概念， 我们将外围类对象的引用称为 outer。
             */
            if (beep){
                //Toolkit.getDefaultToolkit().beep();
                System.out.println("发布通知... outer.beep="+outer.beep);

                //TODO 表达式 OuterClass.this 表示外围类引用
                System.out.println("发布通知... beep="+beep); //
                System.out.println("发布通知... TalkingClock.this.beep="+TalkingClock.this.beep);
            }
            System.out.println("=============================");

        }
    }


    public TalkingClock(int interval,boolean beep){
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