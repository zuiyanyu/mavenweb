package javaVMJDK7.虚拟机执行子系统.类加载器;

import netscape.javascript.JSObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * 不同的类加载器对 instanceof关键字运算的结果的影响
 *
 * 类加载器 与 instanceof 关键字演示
 *
 */
public class MyClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println(Integer.class.getClassLoader());//null
        System.out.println( JSObject.class.getClassLoader()); // sun.misc.Launcher$ExtClassLoader@12a3a380
        System.out.println(MyClassLoader.class.getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println("======================");

        //TODO 自定义类加载器
        ClassLoader myLoader = new ClassLoader(){
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException
            {
                try {
//                    System.out.println(name.substring(name.lastIndexOf(".")+1));
                    String fileName = name.substring(name.lastIndexOf(".")+1) +".class";
                    InputStream is = getClass().getResourceAsStream(fileName) ;
                    if(is ==null){
                        return super.loadClass(name);
                    }

                    byte[] bytes = new byte[is.available()] ;
                    is.read(bytes);

                    return defineClass(name,bytes,0,bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }

            }
        };
        //-----------------------------------------
        Object obj = myLoader.loadClass("javaVMJDK7.虚拟机执行子系统.类加载器.MyClassLoader").newInstance();
        System.out.println(obj.getClass());

        boolean flag = obj instanceof javaVMJDK7.虚拟机执行子系统.类加载器.MyClassLoader ;
        System.out.println(flag);

        System.out.println(obj.getClass().getName());
        System.out.println(MyClassLoader.class.getName());

//        java.lang.Thread ;
//        Class.forName("");




    }
}
