package javaBase.IO流.实例应用;

import java.io.*;

/**
 * TODO 有时候当多个输入流连接在一起时候，你需要跟踪各个中介输入流。
 * 例如：当读入输入时候，你经常需要预览下字节，以了解它是否是你想要的值。
 * java 提供了用于此目的的PushbackInputStream
 */
public class PushbackInputStreamTest {
    /**
     * TODO 当读入输入时候，你经常需要预览下字节，以了解它是否是你想要的值。
     */
    public void pushbackInputStream() throws IOException {
       PushbackInputStream pbin = new PushbackInputStream(
                new BufferedInputStream(
                        new FileInputStream("")));

        //预读下一个字节
        int b = pbin.read();
        //如果值并非期望的值时，将其推回流中
        if(b !='<'){
            pbin.unread(b);
        }
    }

    /**
     *  //TODO 读入和推回是可应用于 可推回（pushback）输入流 的仅有的方法。
     *  //TODO 如果你希望能够预先浏览并且可以读入数字，那么你就需要一个既是可回推流，又是一个输入输入流的引用
     *
     */
    public void pushbackInputStream_dataInputStream() throws IOException {
        PushbackInputStream pbin;
        DataInputStream dis = new DataInputStream(
                pbin=  new PushbackInputStream(
                            new BufferedInputStream(
                                new FileInputStream(""))));
        //读取数据
        String s = dis.readUTF();
        //推回数据
        pbin.unread(s.getBytes());

    }
}
