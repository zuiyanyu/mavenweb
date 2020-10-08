package javaBase.IO流.输入输出流;

import java.io.IOException;

/**
 * TODO 1. InputStream类有一个抽象方法： abstract int read()
 * 这个方法读入一个字节，并返回读入的字节， 或者在遇到输入源结尾时返回 -1
 * TODO 2. OutputStream类有一个抽象方法：abstract void write(int b)
 * 它可以向某个输出位置写出一个字节
 * TODO 3. read 和 write方法在执行时都将阻塞，直至字节确实被读取或写出。
 * 这就意味着：如果流不能被立即访问(通常是因为网络连接忙)，那么当前的线程将被阻塞。
 *              这使得在这两个方法等待指定的流变为可用的这段时间里，其他的线程就有机会去执行有用的工作。
 *
 * TODO 4. avaliable 方法是我们可以检查当前可以读入的字节数量；
 * 这就意味着：像下面的代码片段就不可能被阻塞：
 *         InputStream inputStream = new FileInputStream(new File("文件路径"));
 *         int bytesAvailable = inputStream.available();
 *         if(bytesAvailable > 0){
 *             byte[] bytes = new byte[bytesAvailable];
 *             inputStream.read(bytes);
 *         }
 * TODO 5. close 方法，会释放掉十分有限的操作系统资源。
 * 如果一个应用程序打开了过多的输入、输出流而没关闭，那么系统资源将被耗尽。
 * 关闭一个输出流的同时，还会冲刷用于该输出流的缓冲区：所有被临时置于缓冲区中，以便用更大的包的形式传递的字节在关闭输出流时都将被送出。
 * TODO 特别是，如果不关闭文件，那么写出字节的最后一个包可能将永远也得不到传递。(可以使用flush方法来手动冲刷)
 *
 * TODO 6. 即使某个输入输出流类提供了原生的read 和 write功能的某些具体方法，但应用系统程序员很少使用他们，因为大家感兴趣的
 * 数据可能包含数字、字符串、对象，而不是原生字节 byte.
 *
 *TODO 7.--------------java.io.InputStream 1.0---------------------------------
 *
 * TODO abstract int read()
 * 从数据中读入一个字节，并返回该字节。碰到输入流的结尾时返回 -1
 *
 * TODO int read(byte[] b)
 * 读入一个字节数组，并返回实际读入的字节数，或者碰到输入流的结果时返回 -1 。
 *
 * TODO int read(byte[] b ,in off, int len)
 * 读入一个字节数组，并返回实际读入的字节数，或者碰到输入流的结果时返回 -1 .
 * 参数：   b:  数据读入的数组      off: 第一个读入字节应该被放置到b中的位置   len :读入字节的最大数量。
 *
 * TODO long skip(long n)
 * 在输入流中跳过n个字节，返回实际跳过的字节数(如果碰到输入流的结尾，则可能小于n)
 *
 * TODO void mark(int readlimit)
 * 在输入流的当前位置打一个标记（并非所有的流都支持这个特性）。如果从输入流中已经读入的字节多于readlimit个，则这个流允许忽略这个标记
 *
 * TODO void reset()
 * 返回到最后一个标记，随后对read的调用将重写读入这些字节。如果当前没有任何标记，则这个流不被重置。
 *
 * TODO boolean markSupported()
 * 如果这个流支持打标记，则返回true
 *
 *
 * TODO 8.--------------java.io.OutputStream-------------------
 * todo abstract void write(int b)
 * 写出一个字节的数据
 *
 * todo void write(byte[] b)
 * todo void write(byte[] b , int off, int len)
 * 从数组b中写出所有字节或者某个范围的字节到目的地。。
 * 参数： b: 数据写出的数组    off: 第一个字节在b中的偏移量。   len: 写出字节的最大数量。
 *
 * todo void close()
 * 冲刷并关闭输出流
 *
 * todo void flush()
 * 冲刷输出流，也就是将所有缓冲的数据发送到目的地。
 *
 */
public class 读写字节_02 {
    public static void main(String[] args) throws IOException {
/**
 * TODO 1. java.io.FileInputStream 1.0
 * FileInputStream(String name)
 * FileInputStream(File file)
 * 使用name字符串 或者 file对象指定路径名的文件 来创建一个新的输入流。
 * （非绝对路径名将按照相对于VM启动时所设置的工作目录来解析System.getProperty("user.dir") 可以获取到）
 *
 * TODO 2. java.io.FileOutputStream 1.0
 * FileOutputStream(String name)
 * FileOutputStream(String name,boolean append)
 * FileOutputStream(File file)
 * FileOutputStream(File file, boolean append)
 *  如果append参数为true,那么数据将被添加到文件尾，而具有相同名字的已有文件不会被删除；
 *  否则，这个方法会删除所有相同名字的已有文件。
 * TODO 3.java.io.BufferedInputStream 1.0
 * BufferedInputStream(InputStream in)
 * 创建一个带缓冲区的输入流。
 * 带缓冲区的输入流在流中读取字符时，不会每次都对设备访问，当缓冲区为空时，会向缓冲区读入一个新的数据块。
 *
 * TODO 4. java.io.BufferedOutputStream 1.0
 * BufferedOutputStream(OutputStream out)
 * 创建一个带缓冲区的输出流。
 * 带缓冲区的输出流在收集要写出的字符时，不会每次都对设备访问，当缓冲区填满或当流被冲刷时，数据会被写出。
 *
 * TODO 5. java.io.PushbackInputStream 1.0
 * PushbackInputStream(InputStream in)
 * PushbackInputStream(InputStream in, int size)
 * 构建一个可以预览一个字节或者具有指定尺寸的 回推缓冲区的输入流。
 * void unread(int b)
 * 回推一个字节，它可以在下次调用read时候再次获取
 * 参数：b  要再次读入的字节
 */
    }
}
