package com.regex.syntax;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 捕获组
 * 捕获组是把多个字符当一个单独单元进行处理的方法，它通过对括号内的字符分组来创建。
 *
 * 例如，正则表达式 (dog) 创建了单一分组，组里包含"d"，"o"，和"g"。
 *
 * 捕获组是通过从左至右计算其开括号来编号。例如，在表达式（（A）（B（C））），有四个这样的组：
 *
 * ((A)(B(C)))
 * (A)
 * (B(C))
 * (C)
 * 可以通过调用 matcher 对象的 groupCount 方法来查看表达式有多少个分组。groupCount 方法返回一个 int 值，表示matcher对象当前有多个捕获组。
 *
 * 还有一个特殊的组（group(0)），它总是代表整个表达式。该组不包括在 groupCount 的返回值中。
 */
public class Regexs {


    /**1.
     * (?:pattern)
     * 匹配 pattern 但不捕获该匹配的子表达式，即它是一个非捕获匹配，不存储供以后使用的匹配。这对于用"or"字符 (|) 组合模式部件的情况很有用。
     * 例如，'industr(?:y|ies) 是比 'industry|industries' 更经济的表达式
     */
    @Test
    public void a(){
        String line = "industry or industries";
        String pattern ="industr(?:y|ies)" ;

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);

        while(m.find()){
            for (int i = 0; i <= m.groupCount(); i++) {
                System.out.println("Found value("+i+"): " + m.group(i) );
            }
        }
    }

    /**
     *(?=pattern)
     * 执行正向预测先行搜索的子表达式，该表达式匹配处于匹配 pattern 的字符串的起始点的字符串。
     * 它是一个非捕获匹配，即不能捕获供以后使用的匹配。
     * 例如，'Windows (?=95|98|NT|2000)' 匹配"Windows 2000"中的"Windows"，但不匹配"Windows 3.1"中的"Windows"。
     * 预测先行不占用字符，即发生匹配后，下一匹配的搜索紧随上一匹配之后，而不是在组成预测先行的字符后。
     */
    @Test
    public void b(){
        String line = "Windows 2000 and  Windows 31 and Windows 95";
        String pattern ="Windows\\s*(?=2000|95)" ;

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);

        while(m.find()){
            for (int i = 0; i <= m.groupCount(); i++) {
                System.out.println("Found value("+i+"): " + m.group(i) + " | start index:" + m.start() );
            }
        }
    }

    /**
     *  (?!pattern)
     *  执行反向预测先行搜索的子表达式，该表达式匹配不处于匹配 pattern 的字符串的起始点的搜索字符串。
     *  它是一个非捕获匹配，即不能捕获供以后使用的匹配。
     *  例如，'Windows (?!95|98|NT|2000)' 匹配"Windows 3.1"中的 "Windows"，但不匹配"Windows 2000"中的"Windows"。
     *  预测先行不占用字符，即发生匹配后，下一匹配的搜索紧随上一匹配之后，而不是在组成预测先行的字符后。
     */
    @Test
    public void c(){
        String line = "Windows 2000 and  Windows 3.1 and Windows 95";
        String pattern ="Windows\\s+(?!2000|95)" ;

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);

        while(m.find()){
            for (int i = 0; i <= m.groupCount(); i++) {
                System.out.println("Found value("+i+"): " + m.group(i) + " | start index:" + m.start() );
            }
        }
    }

    /**
     * [xyz]
     * 字符集。匹配包含的任一字符。例如，"[abc]"匹配"plain"中的"a"。
     */
    @Test
    public void d(){
        String line = "world";
        String pattern ="[lo]" ;

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);

        while(m.find()){
            for (int i = 0; i <= m.groupCount(); i++) {
                System.out.println("Found value("+i+"): " + m.group(i) + " | start index:" + m.start() );
            }
        }
    }
    /**
     * \b
     * 匹配一个字边界，即字与空格间的位置。例如，"er\b"匹配"never"中的"er"，但不匹配"verb"中的"er"。
     */
    @Test
    public void e(){
        String line = "hello hellow ";
        String pattern ="lo\\B" ;

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);

        while(m.find()){
            for (int i = 0; i <= m.groupCount(); i++) {
                System.out.println("Found value("+i+"): " + m.group(i) + " | start index:" + m.start() );
            }
        }
    }

    /**
     * appendReplacement 和 appendTail 方法
     * Matcher 类也提供了appendReplacement 和 appendTail 方法用于文本替换：
     */
    @Test
    public void f(){
         String REGEX = "a*b";
         String INPUT = "aabfooaabfooabfoobkkk";
         String REPLACE = "-";

        Pattern r = Pattern.compile(REGEX);
        Matcher m = r.matcher(INPUT);

        StringBuffer sb = new StringBuffer();
        while(m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                //第一步：取出 m.group(i) ，进行一些值判断或者操作
                System.out.println("Found value(" + i + "): " + m.group(i) + " | start index:" + m.start());

                //第二步：逐个进行替换工作
                m.appendReplacement(sb, REPLACE);
                System.out.println(sb.toString());
            }
            System.out.println("--------------");
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        // 按指定模式在字符串查找
        //字符串中的 \\\\表示 两个普通反斜杠
        String line = "//";
        System.out.println(line);
        //正则表达式中的 \\\\表示 一个普通反斜杠

       //String pattern = "((\\D{2})(\\d+)(.*))";
       String pattern = "\\\\\\\\";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);

        System.out.println("m.matches()="+m.matches());

//        if (m.find( )) {
//            System.out.println("Found value: " + m.group(0) ); //Found value: QT3000! OK?
//
//            System.out.println("Found value: " + m.group(1) ); //Found value: QT3000! OK?
//            System.out.println("Found value: " + m.group(2) ); //Found value: QT
//            System.out.println("Found value: " + m.group(3) ); //Found value: 3000
//            System.out.println("Found value: " + m.group(4) ); //Found value: ! OK?
//        } else {
//            System.out.println("NO MATCH");
//        }
        System.out.println("==========================");

        if(m.find()){
            for (int i = 0; i <= m.groupCount(); i++) {
                System.out.println("Found value("+i+"): " + m.group(i) );
            }
        }
    }
}
