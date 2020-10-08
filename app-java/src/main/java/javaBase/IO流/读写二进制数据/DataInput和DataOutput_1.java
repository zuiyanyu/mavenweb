package javaBase.IO流.读写二进制数据;

/**
 * TODO DataOutput 接口定义了下面用于二进制格式写数组，字符，boolean值和字符串的方法：
 * void writeChar(int c)       写出一个给定的字符
 * void writeChars(String s)   写出字符串的所有字符
 * void writeByte(int b)
 * void writeInt(int i)
 * void writeShort(int s)
 * void writeLong(long l)
 * void writeFloat(float f)
 * void writeDouble(double d)
 * void writeBoolean(boolean b)
 * void writeUTF(String s) 写出由"修订过的UTF8"格式的字符构成的字符串。
 * 例如：
 * writeInt总是将一个整数写出为4字节的二进制数据量，而不管它有多少为；
 * writeDouble总是将一个double写出为8字节的二进制数据量。
 * 这样产生的结果并非人可阅读的，但是对于给定类型的每个值，所需的空间都是相同的，而且将其读回也比解析文本要快。
 * writeUTF方式使用修订版的8位Unicode转换格式写出字符串（这种方式只能用于Java虚拟机的字符串）。
 * writeChars 写字符
 *
 * TODO DataInput接口定了下列方法进行读回数据：
 * readInt()
 * readShort()
 * readLong()
 * readFloat()
 * readDouble()
 * readChar()
 * readBoolean()
 * String readUTF()   读入由 “修订过的UTF-8”格式的字符构成的字符串
 * void readFully(byte[] b)
 * 将字节读入到数组b中，其间阻塞直至所有字节都读入。 参数： b   数据读入的缓冲区
 * void readFully(byte[] b ,int off, int len)
 * 将字节读入到数组b中，其间阻塞直至所有字节都读入。
 * 参数： b:数据读入的缓冲区  off:数据起始位置的偏移量  len: 读入字节的最大数量
 * int skipBytes(int n)
 * 跳过n个字节，期间阻塞直至所有字节都被跳过。
 * 参数： n  被跳过的字节数
 *
 * TODO DataInputStream类实现了DataInput接口，为了从文件中读入二进制数据，可以将DataInputStream 和 某个字节源进行组合。
 * 例如：FileInputStream
 * DataInputStream in = new DataInputStream(new FileInputStream("employee.dat")) ;
 *
 * TODO 要想写出二进制数据，可以使用实现了DataOutput接口的DataOutputStream类：
 * DataOutputStream out = new DataOutputStream(new FileOutputSteam("employee.dat")) ;
 */
public class DataInput和DataOutput_1 {

}
