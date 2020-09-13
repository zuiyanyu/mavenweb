package javaBase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

    public static void main(String[] args) {

//        String str = "REP0001  + 001  + REP0002 + RULE0003";    //一句标准字幕，带时间轴、带参数、带中英双语
////        String pattern = "[^a-zA-Z]";    //本案例使用的正则表达式
////        String replacestr = "";    //替换字符，将无用部分替换为""
////        //String pattern1 = str.replaceAll("\\s+","").replaceAll("([^a-zA-Z][^\\d+])", ":");
////        String pattern1 = str.replaceAll("\\s+","")
////                .replaceAll("([a-zA-Z]+\\d+)", ":");
////
////        System.out.println(pattern1);

//        String s = "邀请您加入随心购，自动搜索淘宝天猫优惠券！先领券，再购物，更划算！\r\n" +
//                "-------------\r\n" +
//                "下载链接：https://dwz.cn/YMZudYNQ\r\n" +
//                "-------------\r\n" +
//                "复制这条∞信息∞4573586∞\r\n" +
//                "打开随心购，注册领取优惠券";
//        Pattern pa = Pattern.compile("(?<=∞)([A-Za-z0-9]{7})+(?=∞)");
//
//        String str = "REP0001  + 001  + REP0002 + RULE0003";
//        Pattern pa1 = Pattern.compile("([A-Za-z]+\\d+)");
//        Matcher ma = pa1.matcher(str);
//        while (ma.find()) {
//            System.out.println(ma.group(0));
//        }

        System.out.println("=================");
        String st = "K00<a" ;
        Pattern compile = Pattern.compile("[a-zA-Z]+\\d+|(<=\\d)[a-zA-Z]+"); // ([a-zA-Z]+\d+)|((<=\d)[a-zA-Z]+)
        Matcher matcher = compile.matcher(st);
//               boolean b = matcher.find();
//        System.out.println(b);

        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }

        System.out.println(st.matches("[a-zA-Z]+\\d+|(?<=\\d)[a-zA-Z]+"));

    }
}

