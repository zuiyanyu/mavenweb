package javaBase.IO流.NIO.channel;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Demo01   {
    /*
          使用通道 完成文件的复制
          使用的是非直接缓冲区
         */
    @Test
    public void copyByChannel_02(){
        try (
                //TODO 建立的通道有可读、可写的模式
                //TODO 建立的直接缓冲区也有可读、可写的模式
                //获取通道
                //只读通道
                FileChannel inputChannel = FileChannel.open(Paths.get("channel.txt"), StandardOpenOption.READ);
                //可取可写通道
                FileChannel outputChannel = FileChannel.open(Paths.get("channe6.txt"),
                        StandardOpenOption.READ, StandardOpenOption.WRITE,StandardOpenOption.CREATE );
        )
        {
            //创建 直接缓冲区 ：磁盘映射   这种方式获取直接缓冲区的时候，通道必须是可读的。
            MappedByteBuffer inputDirectBuffer = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputChannel.size());
            MappedByteBuffer outputDirectBuffer = outputChannel.map(FileChannel.MapMode.READ_WRITE, 0, inputChannel.size());
            inputDirectBuffer.limit(2);

            // 分配指定大小的缓冲区
            byte[] bytes = new byte[inputDirectBuffer.limit()];
            inputDirectBuffer.get(bytes);
            outputDirectBuffer.put(bytes);

            System.out.println("复制完成！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
      使用通道 完成文件的复制
      使用的是非直接缓冲区
     */
    @Test
    public void copyByChannel_01(){
        try (
                FileInputStream fileInputStream = new FileInputStream("channel.txt");
                FileOutputStream fileOutputStream = new FileOutputStream("aaa.txt");

                //获取通道
                FileChannel inputChannel = fileInputStream.getChannel();
                FileChannel outputChannel = fileOutputStream.getChannel();
                )
        {
            // 分配指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while(inputChannel.read(buffer) != -1){
                buffer.flip();
                outputChannel.write(buffer);
                buffer.clear();
            }
            System.out.println("复制完成！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 使用FileInputStream获取只读通道
     */
    @Test
    public void getChannel_type02(){
        try(
                // append 默认为false ,即不能追加，只能覆盖
                FileOutputStream fileOutputStream = new FileOutputStream("channe2.txt",true);
                //1. 获取通道
                /*
                 *TODO fileOutputStream.getChannel();打开的通道只能写，不能读取，写的时候能否可对文件进行追加看个人设置
                 *TODO 底层调用：FileChannelImpl.open(FileDescriptor fd, String path, boolean readable, boolean writable, Object parent)
                 *  path
                 *  readable = false ;
                 *  writable = true ;
                 *  append = true ;
                 */
                FileChannel fileChannel = fileOutputStream.getChannel();

        ) {

            //2. 获取非直接缓冲区
            //默认是写模式
            ByteBuffer buffer = ByteBuffer.allocate(1024);


            //3.向缓冲区中放入数据。
           String content = "thankyou" ;
           buffer.put(content.getBytes());

           //5.通道中写入数据，通道会将缓冲区中的数据写到文件中
            buffer.flip();//将buffer切换到读模式
            fileChannel.write(buffer);

            System.out.println("====数据写入成功：");
            readByteBuffer(buffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用FileInputStream获取只读通道
     */
    @Test
    public void getChannel_type01(){
        try(
                FileInputStream fileInputStream = new FileInputStream("channel.txt");
                //1. 获取通道
                /*
                 *TODO fileInputStream.getChannel();打开的通道只能读取，不能写入，并且不能对文件进行追加
                 *TODO 底层调用：FileChannelImpl.open(FileDescriptor fd, String path, boolean readable, boolean writable, Object parent)
                 *  path
                 *  readable = true ;
                 *  writable = false ;
                 *  append = false ;
                 */
                //
                FileChannel inputStreamChannel = fileInputStream.getChannel();
           ) {
            //2. 获取非直接缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //3.从通道中读取数据到缓冲区
            inputStreamChannel.read(buffer);

            //4.打印读取的数据
            readByteBuffer(buffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readByteBuffer(ByteBuffer buffer){
        buffer.flip();

        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        System.out.println(new String(bytes));
        buffer.clear();
    }
}
