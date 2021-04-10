package javaBase.IO流.properties加载;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


public class Load_properties {

    String resourceName = "javaBase/IO流/properties加载/applicationContext.properties";
    ClassLoader classLoader = this.getClass().getClassLoader() ;
    InputStream inputStream ;


    //TODO 5. 根据国际化资源的方式加载 底层还是 1,2,3,4/5 步骤组合实现的。
    @Test
    public void laod_by_resourceBundle(){
        String resoucePath = "javaBase/IO流/properties加载/";
        String baseName = "applicationContext" ;
        ResourceBundle bundle = ResourceBundle.getBundle(resoucePath + baseName);
        Set<String> keySet = bundle.keySet();
        for (String key : keySet) {
            //根据key获取value值
            String value = bundle.getString(key);
            System.out.println(key + "=" + value );

        }
    }
    //TODO 4.通过 classLoader直接加载 properties  用到了缓存，不能实时加载新数据
    @Test
    public void load_by_classLoader() throws IOException {
        inputStream = classLoader.getResourceAsStream(resourceName);

        //打印加载的资源文件内容
        Inputstream_to_Properties(inputStream);
    }

    //TODO 3. 通过 classLoader + URL加载 properties   可实时加载新数据
    @Test
    public void load_by_URL() throws IOException {
        URL url = classLoader.getResource(resourceName);

        URLConnection connection = url.openConnection();
        if (connection != null) {
            //禁用缓存以获取重新加载的新数据。
            // Disable caches to get fresh data for reloading.
            connection.setUseCaches(false);
            inputStream = connection.getInputStream();
        }

        //打印加载的资源文件内容
        Inputstream_to_Properties(inputStream);
    }

    //TODO 2. 将IO流中的数据读取到Properties对象中，以方便访问
    public Properties Inputstream_to_Properties ( InputStream inputStream ) throws IOException {
        System.out.println("===============Inputstream_to_Properties=================");

        Properties properties = new Properties();
        properties.load(inputStream);

        Enumeration<Object> keys = properties.keys();
        while (keys.hasMoreElements()){
            Object keyName = keys.nextElement();
            Object value = properties.get(keyName);
            System.out.println(keyName + "=" + value);
        }
        Properties_to_Map(properties);
        return properties ;
    }
    //TODO 1. 将Properties中的数据转换成Map存储
    public void Properties_to_Map(Properties properties ){
        System.out.println("===============Properties_to_Map=================");

        Map<String,Object> lookup = new HashMap(properties);
        for (Map.Entry<String, Object> entry : lookup.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key+"="+value);
        }
    }
}
