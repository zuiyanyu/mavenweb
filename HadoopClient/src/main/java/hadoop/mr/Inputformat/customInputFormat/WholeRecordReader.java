package hadoop.mr.Inputformat.customInputFormat;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * 自定义RecordReader类  一个mapTask只有一个Recoder，处理一个切片
 * (文件小的话，一个文件一个切片(如果允许进行切片的话))
 */
public class WholeRecordReader extends RecordReader<Text, BytesWritable> {
    FileSystem fileSystem ; //需要使用完毕后关闭流
    private  FSDataInputStream fsDataInputStream ; //读取文件
    FileSplit fileSplit ;  //获取文件信息

    //因为这个切片只生成一个key-value值，所以就false或true来判断此切片的内容是否已经被读完了。读完了，就说明key-value值已经生成好了。
    private boolean isRead = false; // 文件是否已经读取过了

    //文件路径+名称为key，文件内容为value。文件内容可能是字节文件，所以使用BytesWritable
    private Text key = new Text();
    private BytesWritable value = new BytesWritable();




    //    初始化发方法
    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        //我们要对文件切面进行读取，那么我们就要做两件事：1. 获取文件的信息，比如文件名，存储路径 。2.开流 读取文件内容
        //1.文件切片  获取文件路径，文件名
        fileSplit = (FileSplit) split;
        Path fileInputPath = fileSplit.getPath();

        //2. 开流读取文件内容
        fileSystem = FileSystem.get(context.getConfiguration());
        fsDataInputStream = fileSystem.open(fileInputPath);


    }

    // 读取下一组的key-value
    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if(isRead){
            return false ;
        }
        //1. 获取key
        key.clear();
        //获取全路径，并封装进key
        String filePathWithName = fileSplit.getPath().toString();
        key.set(filePathWithName);
        System.out.println("==========path = "+filePathWithName);


        // 2.获取value
        value.setSize(0);

        //2.1 获取文件的长度 ，以确定缓冲区的大小
        long fileLength = fileSplit.getLength();
        byte[] contents = new byte[(int) fileLength];

        //2.2  读取文件内容
        //获取文件的全部内容，并封装进value
        //fsDataInputStream.read(contents) ;
        IOUtils.readFully(fsDataInputStream, contents, 0, contents.length);
        value.set(contents, 0, contents.length);

        //3. 标记我们的文件读过了
        isRead = true;
        System.out.println("文件内容读取完成！");
        return true;
    }

    //获取当前行的key
    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    //获取当前的value
    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    //切片读取进度
    @Override
    public float getProgress() throws IOException, InterruptedException {
        return isRead ? 1 : 0;
    }

    //关闭资源
    @Override
    public void close() throws IOException {
        IOUtils.closeStream(fileSystem);
        IOUtils.closeStream(fsDataInputStream);
    }
}
