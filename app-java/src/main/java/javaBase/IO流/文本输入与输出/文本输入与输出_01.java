package javaBase.IO流.文本输入与输出;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * TODO 1. 在保存数据时，可以选择二进制格式或者文本格式。
 * TODO 2. 二进制格式的I/O 高速且高效，但是不宜人阅读。
 * TODO 3. 在存储文本字符串时候，需要考虑字符编码（character encoding）方式。
 * 在java 内部使用UTF-16.
 * TODO 4. OutputStreamWriter类将使用选定的字符编码方式，把Unicode码元的输出流转换为字节流。
 * 而InputStreamReader类将包含字节(使用某种字符编码方式表示的字符)的输入流转换为可以产生Unicode码元的读入器。
 *        //应该总是在InputStreamReader的构造器中选择一个具体的编码方式。
 *        Reader reader = new InputStreamReader(new FileInputStream("file.txt"), StandardCharsets.UTF_8) ;
 *TODO 5. print 方法不抛出异常，你可以调用checkError 方法来查看输出流是否出现了某些错误。
 */
public class 文本输入与输出_01 {
    public static void main(String[] args) throws FileNotFoundException {
        //应该总是在InputStreamReader的构造器中选择一个具体的编码方式。
        Reader reader = new InputStreamReader(new FileInputStream("file.txt"), StandardCharsets.UTF_8) ;

    }
}





















