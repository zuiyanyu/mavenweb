package javaBase.IO流.实例应用;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.zip.ZipInputStream;

/**
 *
 */
public class ZipInputStreamTest {
    //todo 从一个ZIP压缩文件中读取数字
    public void zipInputStream() throws FileNotFoundException {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(""));
        DataInputStream dataInputStream = new DataInputStream(zipInputStream);

    }

}
