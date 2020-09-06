package javaBase.数据类型;

import org.junit.Test;

public class StringTest {
    @Test
    public void join(){
        String all = String.join("|","a","b","c");
        System.out.println(all); // a|b|c
    }

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
     *          int cpCount = greeting.codePointCount(0, greeting.lengthQ);
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
        System.out.println("codePointCount = "+codePointCount);

        //TODO 3.获取指定位置的代码单元
        char c = greeting.charAt(1); //e
        System.out.println("c="+c);

        //TODO 4.获取指定位置的码点
        //获取第 i 个码点所在的位置
        int index = greeting.offsetByCodePoints(0,0);
        //获取对应字符的码点
        int cp = greeting.codePointAt(index) ;
        System.out.println("codePointAt ="+cp);


        // 使用 UTF-16 编码表示字符⑪(U+1D546) 需要两个代码单元。 测试发现是 一个代码单元
        String s = "⑪is the set of octonions";
        System.out.println(s);

        //测试发现  ⑪ 是 一个代码单元
        int i = s.codePointAt(1);
        System.out.println(Character.isSupplementaryCodePoint(i));

        i= 0 ;
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
