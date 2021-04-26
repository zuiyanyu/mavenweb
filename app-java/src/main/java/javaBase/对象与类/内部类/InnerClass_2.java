package javaBase.对象与类.内部类;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 *TODO  6.4.2 节 内部类的特殊语法规则
 *
 * TODO 1.内部类访问外部类的机制:内部类有一个外围类的引用 outer . 其实 表达式 OuterClass.this 表示外围类引用
 * TODO 2. 反过来，可以采用下列语法格式更加明确地编写内部对象的构造器：outerObject.new InnerClass {construction parameters)
 * TODO    比如外部类中声明内部类实例： new TimePrinter(); 等价于 this.new TimePrinter();
 *
 * TODO 3.如果 TimePrinter 是一个公有内部类 ：
 * TODO 3.1 在外围类的作用域之外，可以这样引用内部类: OuterClass.InnerClass
 * TODO 3.2 在外围类的作用域之外, 可以这样创建内部类实例：new OuterClass().new InnerClass()  或者 outerClassObj.new InnerClass()
 *      实例：
 *          TalkingClock jabberer = new Ta1kingClock(1000, true);
 *          TalkingOock.TiiePrinter listener = jabberer.new TimePrinter()；
 *
 *TODO 4. 对于每个外部对象， 会分别有一个单独的内部类实例.(即内部类是和外部类实例是绑定的)
 *TODO 5. 内部类中声明的所有静态域都必须是 final。
 * 原因：
 * 我们希望一个静态域只有一个实例， 不过对于每个外部对象， 会分别有一个单独的内部类实例。如果这个域不是 final, 它可能就不是唯一的。
 * 每个 外部类的实例都会单独持有一个内部类Class，static 变量是绑定Class类上的， final static 存放到常量池中的 。
 * TODO 6. 内部类不能有 static 方法。
 */
public class InnerClass_2 {
    public static void main(String[] args) {
        //TODO 3. 外部类作用域外 使用内部类
        TalkingClock2.TimePrinter timePrinter = new TalkingClock2(2,true).new TimePrinter();
    }
}
class TalkingClock2 {
    //TODO 构造一个语音时钟时需要提供两个参数：发布通告的间隔和开关铃声的标志。
    private int interval ;//时间间隔
    private boolean beep ;//是否打开铃声提醒

    public void start() throws InterruptedException {
        // ActionListener timePrinterListener = new TimePrinter(); //编译器自动传递
        //TODO 1.
        ActionListener timePrinterListener = this.new TimePrinter(); // 显示传递

    }
    //TODO 2. TimePrinter 是一个公有内部类,在外围类的作用域之外，可以这样引用内部类: OuterClass.InnerClass
    public class TimePrinter implements ActionListener {
        //TODO 4.内部类中声明的所有静态域都必须是 final。
        final static String name ="";
        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("At the tone, the time is " + new Date()) ;

        }
        //TODO 5. 内部类不能有 static 方法。
        //public static getName(){}
    }

    public TalkingClock2(int interval,boolean beep){
        this.interval = interval ;
        this.beep = beep ;
    }
}
