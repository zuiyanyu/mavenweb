package javaBase.对象与类.接口;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;

/**
 * TODO 接口与回调
 * TODO 1. 回调（callback) 是一种常见的程序设计模式。
 * TODO 2. 在这种模式中， 可以指出某个特定事件发生时应该采取的动作。
 *          例如，可以指出在按下鼠标或选择某个菜单项时应该采取什么行动。
 *
 * TODO 定时器 需求
 * 在 java.swing 包中有一个 Timer 类，可以使用它在到达给定的时间间隔时发出通告。 例
 * 如，假如程序中有一个时钟， 就可以请求每秒钟获得一个通告， 以便更新时钟的表盘。
 *
 * 在构造定时器时，需要设置一个时间间隔， 并告之定时器，当到达时间间隔时需要做些什么操作。
 *
 *TODO 如何告之定时器做什么呢？
 * type1: 在很多程序设计语言中，可以提供一个函数名， 定时器周期性地调用它。
 * type2: 在 Java 标准类库中的类采用的是面向对象方法。它将某个类的对象传递给定时器，然后，定时器调用这个对象的方法。
 * （由于对象可以携带一些附加的信息，所以传递一个对象比传递一个函数要灵活得多。）
 *
 *TODO 对象要求
 * 当然， 定时器需要知道调用哪一个方法，并要求传递的对象所属的类实现了java.awt.event 包的 ActionListener 接口。
 *
 *
 */
public class Interface_callback {
}

//监听器
interface ActionListener
{
    //入参必须是 事件
    void actionPerfonned(ActionEvent event);
}
/**
 当到达指定的时间间隔时，定时器就调用 actionPerformed 方法。
 假设希望每隔 10 秒钟打印一条信息“ At the tone, the time is . . .”， 然后响一声， 就应该
 定义一个实现 ActionListener 接口的类， 然后将需要执行的语句放在 actionPerformed 方法中。
 */
class TinePrinter implements ActionListener
{
    @Override
    public void actionPerfonned(ActionEvent event)
    {
        System.out.println("At the tone, the time is " + new Date());
        Toolkit.getDefaultToolkit().beep();
    }
}

