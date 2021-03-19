package hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HDFSClient {
    @Test
    public void testMkdirs() throws IOException, InterruptedException, URISyntaxException {
        System.setProperty("HADOOP_USER_NAME","hadoop");
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        // 配置在集群上运行
        // configuration.set("fs.defaultFS", "hdfs://hadoop102:9000");
        // FileSystem fs = FileSystem.get(configuration);

        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), configuration,null);

        // 2 创建目录
        fs.mkdirs(new Path("/user/hadoop/HdfsClient/input"));

        // 3 关闭资源
        fs.close();

        System.out.println("运行成功");
    }

    //查看文件名称、权限、长度、块信息
    @Test
    public void testListFiles() throws IOException, InterruptedException, URISyntaxException{
        // 1获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), configuration, "hadoop");

        // 2 获取文件详情  就是 ls 命令， 会列出所有的文件夹目录和文件目录 ，并且可以设置为递归罗列目录
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);


        while(listFiles.hasNext()){
            LocatedFileStatus status = listFiles.next();

            // 输出详情
            // 文件名称
            System.out.println(status.getPath().getName());
            // 长度 /1024 = KB  比如168431 是168KB大小
            System.out.println(status.getLen());
            // 权限
            System.out.println(status.getPermission());
            // 分组
            System.out.println(status.getGroup());

            // 获取存储的块信息
            BlockLocation[] blockLocations = status.getBlockLocations();

            for (BlockLocation blockLocation : blockLocations) {

                // 获取块存储的主机节点
                String[] hosts = blockLocation.getHosts();

                for (String host : hosts) {
                    System.out.println(host);
                }
            }

            System.out.println("----------分割线----------");
        }

// 3 关闭资源
        fs.close();
    }
}
