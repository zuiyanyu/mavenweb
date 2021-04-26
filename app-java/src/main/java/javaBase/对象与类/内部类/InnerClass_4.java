package javaBase.对象与类.内部类;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * TODO 匿名内部类
 * 通常的语法格式为：
 * new SuperType(construction parameters)
 * {
 * inner class methods and data
 * }
 * TODO 其中， a. SuperType 可以是 ActionListener 这样的接口， 于是内部类就要实现这个接口。
 * TODO       b. SuperType 也可以是一个类，于是内部类就要扩展它。
 *
 * TODO 由于构造器的名字必须与类名相同， 而匿名类没有类名， 所以， 匿名类不能有构造器。
 * TODO 取而代之的是，将构造器参数传递给超类 （superclass) 构造器。
 * 尤其是在内部类实现接口的时候， 不能有任何构造参数。不仅如此，还要像下面这样提供一组括号：
 *      new InterfaceType(){
 *              methods and data
 *      }
 *
 * 请仔细研究一下，看看构造一个类的新对象与构造一个扩展了那个类的匿名内部类的对象之间有什么差别。
 * Person queen = new Person("Mary"); // a Person object
 * Person count = new Person("Dracula") { . . .} // an object of an inner class extending Person
 * 如果构造参数的闭小括号后面跟一个开大括号， 正在定义的就是匿名内部类。
 *
 * TODO 提示：生成曰志或调试消息时， 通常希望包含当前类的类名， 如：
 * Systen.err.println("Something awful happened in " + getClass())；
 * 不过， 这对于静态方法不奏效。毕竟， 调用 getClass 时调用的是 this.getClass(), 而静态方法没有 this 。
 * 所以应该使用以下表达式：
 *      new Object(){}.getCIass().getEndosingClass() // gets class of static method
 * 在这里，newObject(){} 会建立 Object 的一个匿名子类的一个匿名对象，getEnclosingClass则得到其外围类， 也就是包含这个静态方法的类。
 */
public class InnerClass_4 {
    public static void main(String[] args) {
//        TalkingClock4 talkingClock41 = new TalkingClock4(2, true) {};
//        System.out.println(""+talkingClock41.getClass().getName());//javaBase.对象与类.内部类.InnerClass_4$1
//        System.out.println(talkingClock41.getClass().getEnclosingClass().getName());//javaBase.对象与类.内部类.InnerClass_4
        TalkingClock4 talkingClock4 = new TalkingClock4(2,true);
        talkingClock4.start();
        System.out.println("语音时钟已经开始了！");
        System.out.println("=============================================");
        /**
         * TODO 双括号初始化技巧：这里利用了内部类语法
         * TODO 注意这里的双括号。 外层括号建立了 ArrayList 的一个匿名子类。 内层括号则是一个对象构造块
         *
         *
         */
        ArrayList<String> names = new ArrayList<String>() {{
            add("Harry");
            add("Tony");
            this.add("Tom");
        }};
        System.out.println("names = "+ names);
        System.out.println("=============================================");
    }
}

/**
 * TODO 语音时钟
 */
class TalkingClock4 {
    //TODO 构造一个语音时钟时需要提供两个参数：发布通告的间隔和开关铃声的标志。
    private int interval ;//时间间隔
    private boolean beep ;//是否打开铃声提醒

    public void start(){
        /**
         * TODO 匿名内部类
         */
        ActionListener timePrinterListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("工作中，我喜欢工作，今天日期是： " + new Date()) ;
                if (beep){
                    //表达式 OuterClass.this 表示外围类引用
                    //TODO 访问包含它们的外部类
                    System.out.println("发布通知... beep="+beep);
                    System.out.println("发布通知... TalkingClock4.this.beep="+TalkingClock4.this.beep);
                }
                System.out.println("=============================");
            }
        };
        /**
         * 匿名内部类可以再次使用 lambda表达式进行简化
         */
        ActionListener timePrinterListener2 = event->
        {
                System.out.println("工作中，我喜欢工作，今天日期是： " + new Date()) ;
                if (beep){
                    //表达式 OuterClass.this 表示外围类引用
                    //TODO 访问包含它们的外部类
                    System.out.println("发布通知... beep="+beep);
                    System.out.println("发布通知... TalkingClock4.this.beep="+TalkingClock4.this.beep);
                }
                System.out.println("=============================");
        };
        //发布通知
        new Thread(() ->{
            int i = 1;
            while (true && i-- > 0) {
                System.out.println("开始工作了... ");
                timePrinterListener2.actionPerformed(null);
                //定时
                try {
                    System.out.printf("工作类了，休息 %d 分钟",interval);
                    Thread.sleep(interval * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } ).start();

    }




    public TalkingClock4(int interval,boolean beep){
        this.interval = interval ;
        this.beep = beep ;
    }

}
