package javaBase.IO流.实例应用;

import org.junit.Test;

import java.io.*;

/**
 * TODO 使用DataInputStream,DataOutputStream写入文件且从文件中读取数据。
 */
public class DataInputStream_DataOutputStream {
    //TODO 世（4e 16）界(75 4c)
    /**
     * TODO Data Stream 读取
     * @throws IOException
     */
    @Test
    public void dataOutputStream() throws IOException {
        //
        //DataInputStream dis = new DataInputStream(new FileInputStream("datasteam.txt"));
        //TODO 我们希望使用DataInputStream功能，并希望他们能够使用待缓冲机制的read方法
        DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("datasteam.txt")));

        // 读取字节
//        byte[] b = new byte[2];
//        dis.read(b);
//        System.out.println(new String(b, 0, 2));

        // 读取字节
        byte[] b2 = new byte[6];
        dis.read(b2);
        System.out.println(new String(b2, 0, 6,"UTF-8"));

        // 读取字符
        char[] c = new char[2];
        for (int i = 0; i < 2; i++) {
            c[i] = dis.readChar();
        }
        System.out.println(new String(c, 0, 2));

        // 读取UTF
        String s = dis.readUTF();
        System.out.println(s);

        dis.close();
    }

    /**
     * TODO Data Stream写到输入流中
     * @throws IOException
     */
    @Test
    public void dataInputSteam() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("datasteam.txt"));

        //可以看出，第一个“世界”已经被截断，两个汉字都只被写入了低位，因此肯定乱码。
        //dos.writeBytes("世界"); //按2字节写入，都是写入的低位 todo（16 4c）  高位丢失，必乱码

        dos.write("世界".getBytes("UTF-8")); // 按UTF8编码(我的系统默认编码方式)写入 ,但是不会记录长度信息
        //dos.write("世界".getBytes("GBK"));  //指定其他编码方式

        dos.writeChars("世界"); // 按照Unicode写入 todo 4e 16(世)  75 4c(界)

        //按照UTF-8写入(UTF8变长，开头2字节是由writeUTF函数写入的长度信息，方便readUTF函数读取)
        dos.writeUTF("世界");//todo 00 06 (长度) e4 b8 96(世) e7 95 8c(界)
        dos.flush();
        dos.close();
    }
}
