package com.mavenweb;

public class OnlyTest {
    public static void main(String[] args) {
//        String path =  "ContextLoader.properties" ;
//
//        String s = StringUtils.cleanPath(path);
//        System.out.println(s);

        System.out.println(OnlyTest.class.getResource(""));//获取class类所在的文件目录
        System.out.println(Float.class.getResource(""));
        System.out.println(OnlyTest.class.getResource("/"));
        System.out.println(OnlyTest.class.getResource("OnlyTest.class"));
        System.out.println(OnlyTest.class.getResource("/OnlyTest2.class"));
    }
}
