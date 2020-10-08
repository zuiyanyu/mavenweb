package javaBase.IO流.实例应用;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class InputStreamReaderTest {
    public static void main(String[] args) throws FileNotFoundException {
        //应该总是在InputStreamReader的构造器中选择一个具体的编码方式。
        Reader reader = new InputStreamReader(new FileInputStream("file.txt"), StandardCharsets.UTF_8) ;

    }
}
