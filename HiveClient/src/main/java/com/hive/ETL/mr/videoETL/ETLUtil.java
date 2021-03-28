package com.hive.ETL.mr.videoETL;

/**
 * 表结构：
 * 字段 备注 详细描述
 * video id 视频唯一id 11位字符串
 * uploader 视频上传者 上传视频的用户名String
 * age 视频年龄 视频在平台上的整数天
 * category 视频类别 上传视频指定的视频分类
 * length 视频长度 整形数字标识的视频长度
 * views 观看次数 视频被浏览的次数
 * rate 视频评分 满分5分
 * Ratings 流量 视频的流量，整型数字
 * conments 评论数 一个视频的整数评论数
 * related ids 相关视频id 相关视频的id，最多20个，可为空
 *
 * ：
 * TODO 原始数据特点分析：
 *   1. 视频可以有多个所属分类，每个所属分类用&符号分割，且分割的两边有空格字符，
 *   2. 各个字段值是以 "\t"进行分割的。
 *   3. 最后一个字段,相关视频也是可以有多个元素，多个相关视频又用“\t”进行分割。
 *
 * TODO 我们首先进行数据重组清洗操作：
 *   将所有的类别用“&”分割，同时去掉两边空格，多个相关视频id也使用“&”进行分割。
 */
public class  ETLUtil {
    public static String videoTEL(String ori){
        //System.out.println("ori = "+ori);
        StringBuilder etlString = new StringBuilder();
        String[] splits = ori.split("\t");
        //System.out.println("splits.length = " + splits.length);

        if(splits.length < 9) return null;

        splits[3] = splits[3].replace(" ", "");
        for(int i = 0; i < splits.length; i++){

            if(i < 9){
                etlString.append(splits[i] + "\t");
            }else if(i == splits.length - 1){
                etlString.append(splits[i]); //最后一个不用加"\t"或者“&” 分割符
            }else{
                //9,10,11 ... 都是相关视频
                etlString.append(splits[i] + "&");
            }
        }

        return etlString.toString();
    }
}
