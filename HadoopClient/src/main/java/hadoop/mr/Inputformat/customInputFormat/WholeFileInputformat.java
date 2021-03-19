package hadoop.mr.Inputformat.customInputFormat;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 *
 * 自定义InputFromat
 *
 * 需求
 * 将多个小文件合并成一个SequenceFile文件（SequenceFile文件是Hadoop用来存储二进制形式的key-value对的文件格式），
 * SequenceFile里面存储着多个文件，存储的形式为文件路径+名称为key，文件内容为value。
 *
 *
 */
public class WholeFileInputformat extends FileInputFormat<Text, BytesWritable> {

    /**
     * 是否允许文件进行拆分
     * 因为这里将整个文件作为一个value，所以不进行拆分
     * @param  context
     * @param filename
     * @return
     */
    @Override
    protected boolean isSplitable(JobContext context, Path filename) {
        return super.isSplitable(context, filename);
    }

    @Override
    public RecordReader<Text, BytesWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        WholeRecordReader recordReader = new WholeRecordReader();
        recordReader.initialize(split, context);
        return recordReader;
    }


}
