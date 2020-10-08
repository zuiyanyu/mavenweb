package javaBase.IO流.输入输出流;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * TODO 1. 按照使用方法进行划分，流就形成了处理字节和字符的两个单独的层次结构。
 * TODO 2. InputStream 和 OutputStream类可以读写单个字节或者字节数组。是所有字节流的基础类。
 * TODO 3. DataInputStream 和 DataOutputStream可以以二进制格式读写所有的基本java类型。
 *          DataInputStream din =...
 *          double x = din.radDouble();
 *          DataInputStream 没有任何从文件中获取数据的方法
 * TODO 4. ZipInputStream 和 ZipOutputStream可以以常见的ZIP压缩格式读写文件。
 *
 * TODO 5. 抽象类Reader和Writer的子类可以读写 Unicode文本。
 *          abstract int read()
 *                 read方法将返回一个Unicode码元(0~65535之间的整数)，或者碰到文件结尾时候返回 -1.
 *          abstract void write(int c)
 *                  write方法在被调用时，需要传递一个Unicode码元。
 * TODO 6. 有四个附加的接口：Closeable,Flushable,Readable和Appendable
 * 前两方法拥有下面的方法：
 *      void close() throws IOException  和  void flush
 * InputStream ，OutputStream, Reader ，Writer都实现了Closeable接口。
 *
 * TODO 7. java.io.Closeable接口扩展了 java.lang.AutoCloseable接口，因此对任何Closeable进行操作时候，都可以使用try-with-resource语句。
 * todo  为什么有两个接口呢？因为Closeable接口的close方法只抛出IOException，而 AutoCloseable.close 方法可以抛出任何异常
 *
 * TODO ------------------------------
 * TODO java.io.Closeable 5.0
 * todo void close()   关闭这个Closeable，这个方法可能抛出IOException
 *
 * TODO java.io.Flushable 5.0
 * todo  void  flush()  冲刷这个Flushable
 *
 * TODO java.lang.Readable 5.0
 * todo int read(CharBuffer cb)
 * 尝试着向cb读入其可持有数量的char值，返回读入的char值的数量。
 * 或者当从这个Readable中无法再获得更多的值时返回 -1
 *
 * TODO java.lang.Appendable 5.0
 * Appendable append(char c)
 * Appendable append(CharSequence cs)
 * 向这个Appendable 中追加给定的码元 或者 给定的序列中的所有码元，返回this
 *
 * TODO java.lang.CharSequence 1.4
 * char charAt(int index)  返回给定索引出的码元
 * int length()  返回在这个序列中码元的数量
 * CharSequence subSequence(int startIndex, int endIndex) 返回 [startIndex,endIndex)范围的子序列
 * String toString() 返回这个序列中所有码元构成的字符串
 *
 *
 *
 *
 *
 *
 */

public class 完整的流家族_03 {
    public static void main(String[] args) throws IOException {
        DataInputStream din = new DataInputStream(System.in);
        double x = din.readDouble();
        System.out.println(x);
    }
}
