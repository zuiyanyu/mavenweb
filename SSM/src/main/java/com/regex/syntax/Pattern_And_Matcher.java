package com.regex.syntax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pattern_And_Matcher{
    public static void main(String[] args) {
        //第1步：要匹配的字符串
        String content = "I am noob " +
                "from runoob.com.";

        //第2步：正则表达式
        String pattern = ".*runoob.*";

        // 第3步：Pattern形成正则表达式模板
        Pattern compile = Pattern.compile(pattern);
        //第4步：Matcher使用Pattern模板进行匹配  (Pattern中聚合了Matcher)
        Matcher matcher = compile.matcher(content);

        //第5步：Matcher 开始进行匹配，并返回匹配的结果
        boolean matches = matcher.matches();
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + matches);


    }
}
