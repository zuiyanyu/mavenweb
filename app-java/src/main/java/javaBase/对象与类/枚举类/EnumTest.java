package javaBase.对象与类.枚举类;

import org.junit.Test;

import java.util.Arrays;

/**
 * TODO 1. 在比较两个枚举类型的值时， 永远不需要调用 equals, 而直接使用“ = =” 就可以了。
 * TODO 2. 每个枚举类型都有一个静态的 values 方法， 它将返回一个包含全部枚举值的数组
 * TODO 3. 所有的枚举类型都是 Enum 类的子类。它们继承了这个类的许多方法。
 *          其中最有用的一个是 toString， 这个方法能够返回枚举常量名。
 *
 * TODO 4. ordinal 方 法 返 冋 enum 声 明 中 枚 举 常 量 的 位 置， 位 置 从 0 开始计数。
 *
 * •static Enum valueOf ( Cl ass enumClass , String name )
 * 返回指定名字、给定类的枚举常量。
 * •String toString( )
 * 返回枚举常量名。
 * •int ordinal ( )
 * 返回枚举常量在 enum 声明中的位置，位置从 0 开始计数。
 * •int compareTo( E other )
 * 如果枚举常量出现在 Other 之前， 则返回一个负值；如果 this=other ，则返回 0; 否则，
 * 返回正值。枚举常量的出现次序在 enum 声明中给出。
 */
public class EnumTest{

    @Test
    public void SizeTest(){
        Size small = Size.SMALL;

        //Size.SMALL.toString( ) 将返回字符串“ SMALL”。
        System.out.println("Size.SMALL.toString( )= "+small);

        String name = small.name();
        System.out.println("name="+name);//name=SMALL

        //TODO 每个枚举类型都有一个静态的 values 方法， 它将返回一个包含全部枚举值的数组
        Size[] values = Size.values();
        System.out.println("values="+ Arrays.toString(values));//values=[SMALL, MEDIUM, LARGE, EXTRA_LARGE]

        Size valueName = values[1];
        System.out.println("valueName = "+valueName);//valueName = MEDIUM

        Size size = Size.valueOf("SMALL");
        System.out.println("size = "+size);//size = SMALL

        //toString 的逆方法是静态方法 valueOf。
        Size small1 = Enum.valueOf(Size.class, "SMALL");
        System.out.println(small);//SMALL

        // ordinal 方 法 返 冋 enum 声 明 中 枚 举 常 量 的 位 置， 位 置 从 0 开始计数。
        int ordinal = small1.ordinal();
        System.out.println("ordinal="+ordinal);
    }

    @Test
    public void Size2Test(){

        Size2 small = Size2.SMALL;
        String name = small.name();
        System.out.println("name="+name);

        String size = small.getSize();
        System.out.println("size="+size);

    }
}
enum Size {
    //TODO  这个声明定义的类型是一个类， 它刚好有 4 个实例.
    //TODO  这些都是使用 无参构造函数 构建的内部对象
    SMALL,MEDIUM,LARGE,EXTRA_LARGE ;
}



enum Size2{
    //TODO 可以在枚举类型中添加一些构造器、 方法和域。
    SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRA_LARGE ;

    //TODO 当然， 构造器只是在构造枚举常量的时候被调用
    private String size ="S";
    //TODO 使用构造器的时候要先自定义一个有参构造。(最好同时提供无参构造)
    private Size2(){}
    private Size2(String size){
        this.size = size ;
    }
    public String getSize(){
        return this.size;
    }
}
