package java基础.对象与类.接口;

import java.awt.event.ActionEvent;

public class TimePrinterTest {
    static ActionListener actionListener  ;
    static{
        actionListener = new TinePrinter() ;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            //每10 秒中触发一次事件
            for (int i = 0; i < 10; i++) {
                actionListener.actionPerfonned(new ActionEvent("",1,""));
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        Thread.sleep(20*1000);
     }
}
