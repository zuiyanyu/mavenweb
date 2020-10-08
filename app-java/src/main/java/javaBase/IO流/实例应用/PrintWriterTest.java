package javaBase.IO流.实例应用;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class PrintWriterTest {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //TODO 读取文件
        PrintWriter printWriter = new PrintWriter("alice.txt", "UTF-8");
        //等价于
        //new PrintWriter( new FileOutputStream(""),"UTF-8");

        /**
         * TODO 打印数据到控制台
         * TODO 为了输出到打印写出器，需要使用与使用System.out时相同的print,println和printf方法。
         */
        PrintWriter out = new PrintWriter(System.out);
        out.println("hello");
        out.println(23);
        out.println(23.33);
        out.flush();
        out.close();
    }
}
