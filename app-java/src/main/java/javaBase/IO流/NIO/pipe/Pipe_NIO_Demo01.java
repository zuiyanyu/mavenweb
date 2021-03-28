package javaBase.IO流.NIO.pipe;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class Pipe_NIO_Demo01 {
    @Test
    public void test1_02() throws IOException{
        //1. 获取管道
        Pipe pipe = Pipe.open();

        //2. 将缓冲区中的数据写入管道
        ByteBuffer buf = ByteBuffer.allocate(1024);

        Pipe.SinkChannel sinkChannel = pipe.sink();
        buf.put("通过单向管道发送数据".getBytes());
        buf.flip();
        sinkChannel.write(buf);

        //3. 读取缓冲区中的数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        buf.clear();
        int len = sourceChannel.read(buf);
        System.out.println(new String(buf.array(), 0, len));

        sourceChannel.close();
        sinkChannel.close();
    }


    @Test
   public void pipe_01() throws IOException {
       //1.创建管道
       Pipe pipe = Pipe.open();

       //2.向管道写入数据
       Pipe.SinkChannel sinkChannel = pipe.sink();

       //3. 通过SinkChannel的writer方法写数据
       ByteBuffer sinkBuffer = ByteBuffer.allocate(1024);
       sinkBuffer.put("通过单向管道发送数据".getBytes());

       //4.将缓冲切换到读模式
       sinkBuffer.flip() ;

       //5.将缓冲中的数据写入到管道的通道中。
       while(sinkBuffer.hasRemaining()){
            System.out.println("5. 将缓冲中的数据写入到管道的通道中");
            sinkChannel.write(sinkBuffer);
       }

       //6. 从管道中读取数据. 需要访问source通道
       Pipe.SourceChannel sourceChannel = pipe.source();

       //7. 调用Source通道的read()方法来读取数据。
       ByteBuffer sourceBuf = ByteBuffer.allocate(1024);
       sourceChannel.read(sourceBuf);

       //8.将sourceBuf切换为读模式
       sourceBuf.flip();
       System.out.println("数据："+new String(sourceBuf.array(), 0, sourceBuf.limit()));

       //9.将sourceBuf的数据读取出来，进行输出
       byte[] dataBytes = new byte[3] ;

       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int length  ;
       while (sourceBuf.hasRemaining()){
           // 这次读取几个字节
           length = Math.min(sourceBuf.remaining(),dataBytes.length) ;

           //将sourceBuf中的数据读取到dataBytes中。
           sourceBuf.get(dataBytes,0,Math.min(sourceBuf.remaining(),dataBytes.length));

           //将数据写入到字节缓冲流
           outputStream.write(dataBytes,0,length);
       }
       //10. 输出数据
       System.out.println("输出数据："+outputStream.toString());


   }
}
