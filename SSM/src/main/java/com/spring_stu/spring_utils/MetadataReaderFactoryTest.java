package com.spring_stu.spring_utils;

import com.sun.deploy.util.StringUtils;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MetadataReaderFactoryTest {
    PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
    //MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(patternResolver) ;
    MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(patternResolver) ;

    @Test
    //根据类全限定名来解析类的元数据信息 比如类的接口，类的父类，类的包名等
    public void getMetadataReader() throws IOException {
        String classFullName ="com.spring_stu.dao.daoImpl.AccountDaoImpl";
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(classFullName);

        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        String className = classMetadata.getClassName();
        //className =com.spring_stu.dao.daoImpl.AccountDaoImpl,className =com.spring_stu.dao.UserDao等类全限定名
        System.out.println("className ="+className);

        Set packageSet = new HashSet();
        //对获取的资源进行加载，从而进行验证，再获取可加载的资源的包名
        Class<?> aClass = null;
        try {
            aClass = Class.forName(className);
            Package aPackage = aClass.getPackage();
            String packageName = aPackage.getName();
            // packageName =com.spring_stu.dao.daoImpl ,packageName =com.spring_stu.dao 包名
            System.out.println("packageName ="+packageName);

            packageSet.add(packageName);
        } catch (ClassNotFoundException e) {
            System.out.println("当前的资源["+className+"]不能被加载!");
            e.printStackTrace();
        }

    }

    /**
     * 应用：解析springEL表达式，获取其中的类所在的所有包名。
     * @throws IOException
     */
    @Test
    public void getResources() throws IOException {
        //"**" 匹配多层级的目录
        String springEL ="com.spring_stu.dao.**" ;
        //file [D:\software\java\ideaU\workspace\mavenweb\SSM\target\classes\com\spring_stu\dao\daoImpl\test\Hellow.class]
        String classPattern04 ="classpath*:" + ClassUtils.convertClassNameToResourcePath(springEL) + "/*.class";

        //打印资源的资源路径(绝对路径)
        Resource[] resources = patternResolver.getResources(classPattern04);
        Set packageSet = new HashSet();
        for (Resource resource : resources) {
            if(resource.isReadable()){
                //getMetadataReader() 根据resource 或者 类全限定名来解析类的基本信息
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                ClassMetadata classMetadata = metadataReader.getClassMetadata();
                String className = classMetadata.getClassName();
                //className =com.spring_stu.dao.daoImpl.AccountDaoImpl,className =com.spring_stu.dao.UserDao等类全限定名
                System.out.println("className ="+className);

                //对获取的资源进行加载，从而进行验证，再获取可加载的资源的包名
                Class<?> aClass = null;
                try {
                    aClass = Class.forName(className);
                    Package aPackage = aClass.getPackage();
                    String packageName = aPackage.getName();
                    // packageName =com.spring_stu.dao.daoImpl ,packageName =com.spring_stu.dao 包名
                    System.out.println("packageName ="+packageName);

                    packageSet.add(packageName);
                } catch (ClassNotFoundException e) {
                    System.out.println("当前的资源["+resource+"]不能被加载!");
                    e.printStackTrace();
                }
            }
        }
        //可加载的资源路径为：[com.spring_stu.dao, com.spring_stu.dao.daoImpl]
        System.out.println("可加载的资源路径为："+packageSet);

        //com.spring_stu.dao;com.spring_stu.dao.daoImpl
        String join = StringUtils.join(packageSet, ";");
        System.out.println(join);
    }
}
