package javaBase.IO流.读写二进制数据;

/**
 * TODO 1. RandomAccessFile类可以在文件中的任何位置查找和写入数据。
 * TODO 2. 磁盘文件都是随机访问的，但是与网络套接字通信的输入/输出流却不是。
 * TODO 3. 你可以打开一个随机访问文件，只用于读入或者同时用于读写,你可以通过使用字符串“r”（用于读入访问）
 * TODO    或者 “rw”（用于读入/写出访问）作为构造器的第二个参数来指定这个选项。
 * TODO 4.
 *         RandomAccessFile in = new RandomAccessFile("employee.dat","r")
 *         RandomAccessFile inOut = new RandomAccessFile("employee.dat","rw")
 * 当你将已有文件作为 RandomAccessFile打开时，这个文件并不会被删除。
 * TODO 5. 随机访问文件有一个表示下一个将被读入或者写出的字节所处的位置的文件指针，seek方法可以用来将这个文件指针设置
 * TODO    到文件中的任意字节位置，seek的参数是一个long类型的整数，它的值位于[0，文件按字节来度量的长度)之间。
 *
 * TODO 6. getFilePointer 方法将返回文件指针的当前位置。
 * TODO 7. RandomAccessFile 类同时实现了DataInput和DataOutput接口。
 *       为了读写随机访问文件，可以使用 readInt/writeInt  和 readChar/writeChar之类的方法。
 *
 */
public class 随机访问文件_2 {
    /**TODO java.io.RandomAccessFile 1.0
     * RandomAccessFile(String file,String mode)
     * RandomAccessFile(File file,String mode)
     * 参数：  file: 要打开的文件
     *         mode: "r"表示只读模式；  “rw”表示读、写模式； "rws"表示每次更新时候，都对数据和元数据的写磁盘进行同步的读/写模式；
     *               “rwd”表示每次更新时候，只对数据的写磁盘操作进行同步的读/写模式。
     * long getFilePointer()
     * 返回文件指针的当前位置
     * void seek(long pos)
     * 将文件指针设置到距离文件开头pos个字节处 。
     * long length()
     * 返回文件按照字节来度量的长度。
     */
}


















