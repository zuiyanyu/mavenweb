package javaBase.IO流;

public class IOClassMethod {
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
     *
     * TODO 6. java.io.PrintWriter 1.1
     * PrintWriter(Writer out,boolean autoFlush)
     * PrintWriter(Writer out)
     * 创建一个向给定的写出器写出的新的PrintWriter
     * PrintWriter(String filename,String encoding)
     * printWriter(File file ,String encoding)
     * 创建一个使用给定的编码方式向给定的文件写出的新的PrintWriter.
     * void print(Object obj)
     * void print(String s)
     * 打印一个包含Unicode码元的字符串。
     * void println(String s)
     * 打印一个字符串，后面紧跟一个行终止符。如果这个流处于自动冲刷模式，那么就会冲刷这个流。
     * void print(char[] s)
     * void print(char c)
     * void print(int i)
     * void print(long l)
     * void print(float f)
     * void print(double d)
     * void print(boolean b)
     * 以文本格式打印给定的值
     * void printf(String format,Object ... args)
     * 按照格式字符串指定的方式打印给定的值。
     * todo boolean checkError()
     * 如果产生格式化或输出错误，则返回true. 一旦这个碰到了错误，就会受到了污染，并且所有对checkError的
     * 调用都将返回true .
     */
    public static void main(String[] args) {

    }
}




































