package javaBase.IO流.实例应用;

import java.io.*;
import java.util.stream.Stream;

public class BufferedReaderTest {
    /**
     * TODO 处理文本输入的唯一方式就是通过BufferedReader类。
     * readLine（）产生一行文本，或者在无法获得更多的输入时返回.
     *
     */
    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File(""));
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(inputStream))))
        {
            //读取的方式 1
            String line ;
            while((line = reader.readLine() )!= null){
                System.out.println(line);
            }
            //读取的方式 2
            Stream<String> lines = reader.lines();
            lines.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
