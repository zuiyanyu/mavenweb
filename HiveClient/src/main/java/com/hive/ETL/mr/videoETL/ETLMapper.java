package com.hive.ETL.mr.videoETL;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 在map端进行清洗就行，reduce端不必进行操作，避免shuffer过程
 */
public class ETLMapper  extends Mapper<LongWritable,Text,Text,NullWritable> {
    private Text k = new Text();
    String etlString = null ;
    String original = null ;
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        original = value.toString();
        etlString = ETLUtil.videoTEL(original); //如果数据是脏数据，进行丢弃。 生产要进行其他地方的保存和处理

        //进行数据清洗
        if (StringUtils.isNotEmpty(etlString)) {
            k.set(etlString);
            context.write(k, NullWritable.get());
            context.getCounter("ETL", "True").increment(1);
        } else {
            //使用hadoop的累加器，统计脏数据的数量
            context.getCounter("ETL", "False").increment(1);
        }
    }
}
