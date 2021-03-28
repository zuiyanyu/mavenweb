package javaBase.IO流.NIO.selector;

import org.junit.Before;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Selector_Opts {
    Selector selector ;
    @Before
    //TODO 1.  //创建选择器
    public void createSelector() throws IOException {
        //创建选择器
        selector = Selector.open();
        selector.select();
    }

    public void registerChannelToSelector() throws IOException {
        //1. 创建一个 socket套接字
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),2020);

        //2. 获取socketChannel
        SocketChannel socketChannel = socket.getChannel();

        //3. 将socketChannel切换到非阻塞式状态
        socketChannel.configureBlocking(false) ;

        //4.向Selector注册 Channel
        SelectionKey register = socketChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);

    }
}
