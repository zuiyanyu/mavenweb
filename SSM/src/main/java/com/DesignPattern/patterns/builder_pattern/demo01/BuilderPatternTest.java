package com.DesignPattern.patterns.builder_pattern.demo01;

import com.DesignPattern.patterns.builder_pattern.demo01.builder.concreteBuilder.ManBuilder;
import com.DesignPattern.patterns.builder_pattern.demo01.builder.concreteBuilder.WomenBuilder;
import com.DesignPattern.patterns.builder_pattern.demo01.product.Person;

import java.io.*;

/**
 * TODO 建立一个建造者模式的小栗子。
 *
 * 我们建造一个属性为Person的产品，同时创建Man 和Women.
 *
 * 1.创建接口PersonBuilder，其中包含需要创建的 head body foot   即  Builder
 *
 * 2.创建实现类WomenBuilder和ManBuilder来实现PersonBuilder接口中的方法，即 ConcreteBuilder
 *
 * 3.创建PersonDirector 来指定你要创建的是Women还是Man,即Director
 *
 * 4.由于最后生成的产品是Person，所以我们要创建实体类Person,
 *
 * 5.同时我们可以创建普通类Man和Women,来继承Person, 增强拓展性，方便PersonDirector进行管理。
 */
public class BuilderPatternTest {
    public static void main(String[] args) throws IOException {
        PersonDirector pd=new PersonDirector();
        Person manPerson=pd.constructPerson(new ManBuilder());
        Person womenPerson=pd.constructPerson(new WomenBuilder());

        System.out.println(manPerson);
        System.out.println(womenPerson);

        //TODO 装饰者设计模式
        FileInputStream fileInputStream = new FileInputStream("");
        // 缓存字节流    public BufferedInputStream(InputStream in, int size)  size是缓存的字节大小
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream,1024);

        //先进行缓存的填充，然后从缓存中读取一个字节进行返回
        int read = bufferedInputStream.read();
    }
}
