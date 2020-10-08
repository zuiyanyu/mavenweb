package javaBase.IO流.操作文件.Path类;

import java.io.IOException;

/**
 * TODO 1. Files类可以使得普通文件操作变的快捷。
 *
 * --------------------java.lang.utils.Files--------------
 * TODO 2. byte[] readAllBytes(Path path) 读取文件的所有内容
 *          byte[] bytes = readAllBytes(path) 读取文件的所有内容
 *          String content = new String(bytes,charset); 将文件当做字符串读入
 * TODO 3. List<String> readAllLines(path,charset) 将文件当做行序列读入
 *          List<String> lines = Files.readAllLines(path,charset);
 * TODO 4. Files.write(path,content.getBytes(charset)); 写出一个字符串到文件中
 * TODO 5. Files.write(path,content.getBytes(charset),StandardOpenOption.APPEND) 向指定文件追加内容。
 * TODO 6. Files.write(path,lines)  将一个行的集合写出到文件中
 *
 * 上面这些简便的方法适用于处理中等长度的文本文件，如果需要处理的文件长度比较大，或者二进制文件，那么还需要
 * 使用所熟知的输入/输出流 或者 读入器/写出器
 *         InputStream inputStream = Files.newInputStream(path);
 *         OutputStream outputStream = Files.newOutputStream(path);
 *         BufferedReader bufferedReader = Files.newBufferedReader(path,charset);
 *         BufferedWriter bufferedWriter = Files.newBufferedWriter(path,charset);
 *
 */
public class 读写文件_2 {
    public static void main(String[] args) throws IOException {

    }
}




































