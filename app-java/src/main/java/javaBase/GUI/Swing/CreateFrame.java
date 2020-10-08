package javaBase.GUI.Swing;

import javax.swing.*;

/**
 * 10.2 创 建 框 架
 * 1.在 Java 中， 顶层窗口（就是没有包含在其他窗口中的窗口）被称为框架（frame)。
 * 2.在AWT 库中有一个称为 Frame 的类， 用于描述顶层窗口。这个类的 Swing 版本名为 JFrame,它扩展于 Frame 类。
 * JFrame 是极少数几个不绘制在画布上的 Swing 组件之一。因此，它的修饰部件（按钮、标题栏、 图标等）由用户的窗口系统绘制，而不是由 Swing 绘制。
 *
 * 绝大多数 Swing 组件类都以“ J” 开头， 例如，JButton、JFrame 等。在 Java 中有Button 和 Frame 这样的类， 但它们属于 AWT 组件。
 * 如果偶然地忘记书写“ J”， 程序仍然可以进行编译和运行，但是将 Swing 和 AWT 组件混合在一起使用将会导致视觉和行为的不一致。
 *
 */
public class CreateFrame {
    public static void main(String[] args) {
        // Swing 的 JFrame 的常用方法

        SimpleFrame simpleFrame = new SimpleFrame();
        //关闭窗口按钮
        simpleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //窗口视图可见
        simpleFrame.setVisible(true);
    }
}
class SimpleFrame extends JFrame{
    public static final int DEFAULT_WIDTH = 200 ;
    public static final int DEFAULT_HEIGTH = 200 ;

    public SimpleFrame (){
        super.setSize(DEFAULT_WIDTH,DEFAULT_HEIGTH);
    }
}
