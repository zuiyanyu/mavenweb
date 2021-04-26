package javaBase.数据类型;

import org.junit.Test;
import sun.util.locale.LocaleUtils;

public class StringTest {
    @Test
    public void internTest(){
        String language = "zh" ;
        language = LocaleUtils.toLowerString(language).intern();
        System.out.println(language);
    }
    @Test
    public void join(){
        String all = String.join("|","a","b","c");
        System.out.println(all); // a|b|c
    }

    @Test
    public void subString(){
        String greeting = "Hello";
        //在 substring 中从 0 开始计数，直到 3 为止， 但不包含 3
        /**
         * substring 的工作方式有一个优点： 容易计算子串的长度。字符串 s.substring(a, b) 的长度
         * 为 b-a。例如， 子串“ Hel ” 的长度为 3-0=3。
         */
        String s = greeting.substring(0, 3);

    }

    /**
     *TODO 码点与代码单元
     * TODO 1. Java 字符串由 char 值序列组成
     * TODO 2. char 数据类型是一个采用 UTF-16 编码表示 Unicode 码点的代码单元。
     * TODO 3. 大多数的常用 Unicode 字符使用一个代码单元就可以表示，而辅助字符需要一对代码单元表示。
     *
     * TODO 4. length 方法将返回采用 UTF-16 编码表示的给定字符串所需要的代码单元数量
     * TODO 5. 要想得到实际的长度，即码点数量，可以调用：
     *          int cpCount = "Hello".codePointCount(0, "Hello".length());
     *
     */
    @Test
    public void codePoint(){
        String greeting = "Hello";
        //TODO 1. 获取代码单元数量 ： 5
        int codeUnitCount = greeting.length(); // is 5
        System.out.println("codeUnitCount= "+codeUnitCount);

        //TODO 2. 获取码点数量 (即字符串实际长度)：
        int codePointCount = greeting.codePointCount(0,greeting.length()) ;
        System.out.println("codePointCount = "+codePointCount);// is 5

        //TODO 3.获取指定位置的代码单元
        char c = greeting.charAt(1); //e
        System.out.println("代码单元:"+c);

        //TODO 4.获取指定位置的码点
        System.out.println("============获取指定位置的码点");
        //获取第 i 个码点所在的位置
        //在index和codePointOffset计数为每个代码点。
        /**
         * 返回此 String 中从给定的 index 处偏移 codePointOffset 个代码点的索引。
         * 如果没有辅助字符的话，这个方法很没有意思
         * 如果有辅助字符的话，就有作用了
         *
         *TODO 这里的index就是你指定的任意第 i 个码点，假如你想知道距离第 i 个码点 x 个码点（x可正可负），
         *TODO 则可以用offsetByCodePoints( i , x )得到的你想要的值。
         *TODO 返回值就是： 相对于第0个代码点的 第 i+x 个代码点的索引值。
         *
         * offsetByCodePoints(1,0); 等价于 offsetByCodePoints(0,1); 指的都是同一个代码点
         * 从索引1开始，偏移0个代码点后，这个代码点的索引开始位置；
         * 从索引0开始，偏移1个代码点后，这个代码点的索引开始位置；
         */
        System.out.println("greeting = "+greeting);
        int index = greeting.offsetByCodePoints(2,-1);
        System.out.println("offsetByCodePoints = " + index); //offsetByCodePoints = 1
        //获取对应字符的码点
        int cp = greeting.codePointAt(index) ;
        System.out.println("码点cp:"+cp); //码点cp:101
        System.out.println("码点:"+(char)cp);//码点:e

        System.out.println("=====================");

        // 使用 UTF-16 编码表示字符⑪(U+1D546) 需要两个代码单元。 测试发现是 一个代码单元
        String s = "⑪is the set of octonions";
        System.out.println(s);

        System.out.println("length = "+s.length());
        System.out.println("length = "+s.codePointCount(0,s.length()));
        //测试发现  ⑪ 是 一个代码单元
        int i = s.codePointAt(0);
        System.out.println("i="+i);
        System.out.println("i="+(char)i);


        System.out.println(Character.isSupplementaryCodePoint(i));

        i= 0 ;
        System.out.println("charAt = "+s.charAt(0));
        if (Character.isSupplementaryCodePoint(cp))i += 2;
        else i++;

        char ch = s.charAt(1);
        System.out.println("ch="+ch); //

        /*如果想要遍历一个字符串，并且依次査看每一个码点
        显然， 这很麻烦。更容易的办法是使用 codePoints 方法， 它会生成一个 int 值的“ 流”，
        每个 int 值对应一个码点。（流将在卷 II 的第 2 章中讨论〉 可以将它转换为一个数组（见 3.10
        节，) 再完成遍历。
        */
        int[]  codePoints = s.codePoints().toArray();
//        反之， 要把一个码点数组转换为一个字符串， 可以使用构造函数（我们将在第 4 章详细
//        讨论构造函数和 new 操作符 )。
        String str = new String(codePoints, 0, codePoints.length) ;
        System.out.println("str="+str);

    }


}
