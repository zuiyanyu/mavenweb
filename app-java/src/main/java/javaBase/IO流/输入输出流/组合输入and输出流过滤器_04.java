package javaBase.IO流.输入输出流;

import org.junit.Test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * TODO 1. FIleInputStream 和 FileOutputStream可以提供附着在一个磁盘文件上的输入流流和输出流，而
 * 我们只需要向其构造器提供文件名或者文件的完整路径名。
 *         FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\a\\a.txt");
 *
 *TODO 2. System.getProperty("user.dir")  获取用户工作目录
 * 所有在java.io中的类都将相对路径名解释为 以用户工作目录开始，你可以通过调用System.getProperty("user.dir")来获得这个信息。
 * FileInputStream fileInputStream = new FileInputStream("alice.txt");
 *
 * TODO 3. URL resource = Object.class.getResource("/alice.txt"); 获取模块目录下的文件( / 必须带上)
 *
 * TODO 4. java.io.File.separator  获取程序所运行平台的文件分隔符。
 *
 *
 */
public class 组合输入and输出流过滤器_04 {
    /**TODO 5. 流的组合
     * 正如FileInputStream 没有任何读入数值类型的方法一样，DataInputStream没有任何从文件中获取数据的方法。
     * 当时可以通过组合两种流来实现从文件中读取数值：
     * 某些输入流（例如FileInputStream 和 由URL类的openStream方法返回的输入流）可以从文件和其他更外部的位置上
     * 获取字节，而其他的输入流（例如DataInputStream）可以将字节组装到更有用的数据类型中。
     * java程序员必须对二者进行组合。
     * 例如：为了从文件中读入数值，首先创建一个FileInputStream，然后将其传递给DataInputStream的构造器。
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        URL resource = Object.class.getResource("/dataInputStream.dat");
        InputStream inputStream = resource.openStream();

//        byte[] bytes = new byte[1024] ;
//        while ((inputStream.read(bytes))!=-1){
//            System.out.println(new String(bytes,0,bytes.length));
//        }


        DataInputStream dataInputStream = new DataInputStream(inputStream);
        double v = dataInputStream.readDouble();
        System.out.println(v);
        dataInputStream.close();
    }

    @Test
     public void test1() throws IOException {
         //获取 mavenweb/目录下的 alice.txt
         FileInputStream fileInputStream = new FileInputStream("alice.txt");
         byte[] bytes = new byte[1024] ;
         while ((fileInputStream.read(bytes))!=-1){
             System.out.println(new String(bytes,0,bytes.length));
         }
         System.out.println();

         //获取 mavenweb/app-java/目录下的 alice.txt
         URL resource = Object.class.getResource("/alice.txt");
         fileInputStream = new FileInputStream(resource.getPath());
         while ((fileInputStream.read(bytes))!=-1){
             System.out.println(new String(bytes,0,bytes.length));
         }
    }
}
