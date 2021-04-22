package javaBase.反射.ClassLoader_Info;

import javaBase.反射.domain.Person;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ClassLoader_loadResource {
    /**
     * 反射机制通过加载器获取流对象：getResourceAsStream方法
     */
    @Test
    public void testGetResourceAsStream() throws ClassNotFoundException, IOException {
        String resourcePath = "test.properties";
        //调用getResourceAsStream 获取类路径下的文件对应的输入流
        //到根目录下找资源
        ClassLoader classLoader =Person.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream(resourcePath);
        System.out.println("in: " +in);

        Properties properties = new Properties();
        properties.load(in);
        System.out.println("文件内容："+properties);

        //到 类所在目录下找资源
        in = Person.class.getResourceAsStream(resourcePath) ;
        System.out.println("in: " +in);

        properties = new Properties();
        properties.load(in);
        System.out.println("文件内容："+properties);


    }
}
