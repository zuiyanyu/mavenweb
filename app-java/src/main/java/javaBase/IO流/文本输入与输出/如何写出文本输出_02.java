package javaBase.IO流.文本输入与输出;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * TODO 1. 对于文本输出，可以使用PrintWriter。
 * 这个类拥有以文本格式打印字符串和数字的方式，它还有一个将PrintWriter 链接到FileWriter的便捷方式：
 *  PrintWriter printWriter = new PrintWriter("a.txt", "UTF-8");
 *  //等价于
 *  //new PrintWriter( new FileOutputStream("a.txt"),"UTF-8");
 *  因为底层是这样包装的：new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("a.txt")), charset)
 */
public class 如何写出文本输出_02 {
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
























