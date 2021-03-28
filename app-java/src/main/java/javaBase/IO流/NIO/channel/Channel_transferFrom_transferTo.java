package javaBase.IO流.NIO.channel;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * TODO transferFrom()
 * channelA.transferFrom(channelB)
 * 将数据从源通道channelB传输到通道hannelA 中
 *
 * TODO transferTo()
 * channelA.transferTo(channelB)
 * 将数据从源通道channelA传输到通道hannelB 中
 */
public class Channel_transferFrom_transferTo {


    @Test
    public void transferFrom() throws IOException {
        //1. 获取 from channel
        RandomAccessFile fromFile = new RandomAccessFile("fromChannel.txt", "rw");
        FileChannel formChannel = fromFile.getChannel();

        //2. 获取 to channel
        RandomAccessFile toFile = new RandomAccessFile("toChannel.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        //3. 将数据从源通道formChannel 传输到另一个通道toChannel
        long position = 0 ;//从哪一个位置开始传输
        long count = formChannel.size() ; //要传输的字节数量
        long l = toChannel.transferFrom(formChannel, position, count);
    }
    @Test
    public void transferTo() throws IOException {
        //1. 获取 to channel
        RandomAccessFile toFile = new RandomAccessFile("toChannel.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        //2. 获取 from channel
        RandomAccessFile fromFile = new RandomAccessFile("fromChannel.txt", "rw");
        FileChannel formChannel = fromFile.getChannel();

        //3. 将数据从源通道toChannel 传输到其他通道formChannel 中
        long position = 0 ;//从哪一个位置开始传输
        long count = toChannel.size() ; //要传输的字节数量
        long writeCount = toChannel.transferTo(position, count, formChannel);
        System.out.println(" writeCount =" + writeCount);
    }
}
