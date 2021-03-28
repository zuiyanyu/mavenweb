package javaBase.java_socket.socket_NIO.TCP_IP.非阻塞式;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

//TODO selector监听2一个Channel，处理2个端口服务
public class NoneBlock_Socket_demo02 {
    //客户端
    @Test
    public void client() throws IOException, InterruptedException {
        int port = 9090 ;
        //1. 获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", port));

        //2. 切换非阻塞模式
        sChannel.configureBlocking(false);

        //3. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        Date date = new Date();
        //4. 发送数据给服务端
        String str = port+":hello_"+ date.toString();
        buf.put((new Date().toString() + "\n" + str).getBytes());
        buf.flip();
        sChannel.write(buf);
        buf.clear();

        //5. 关闭通道
        sChannel.close();
    }
    //服务端
    @Test
    public void server() throws IOException{
        //TODO 1.启动两个后端服务-两个端口
        ServerSocketChannel ssChannel01 = getSelectableChannel(9098);
        ServerSocketChannel ssChannel02 = getSelectableChannel(9090);

        //4. 获取选择器
        Selector selector = Selector.open();

        //TODO 2.向选择器中注册两个后端服务，选择器会监听这两个Channel的IO状态
        //5. 将通道注册到选择器上, 并且指定“监听接收事件”
        ssChannel01.register(selector, SelectionKey.OP_ACCEPT);
        ssChannel02.register(selector, SelectionKey.OP_ACCEPT);

        //6. 轮询式的获取选择器上已经“准备就绪”的事件--监听
        /** selector.select() 会进行阻塞：
         * 监控所有注册的Channel，当它们中间有需要处理的 IO 操作时，该方法返回，
         * 并将对应得的 SelectionKey 加入被选择的SelectionKey 集合(selectedKeys)中，该方法返回这些 Channel 的数量。
         */
        int i =0 ;
        while((i=selector.select()) > 0){
//            System.out.println("selector.select() = " + i);
            //7. 获取当前选择器中所有注册的“选择键(已就绪的监听事件)”
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while(it.hasNext()){
                //8. 获取准备“就绪”的是事件
                SelectionKey sk = it.next();

                //9. 判断具体是什么事件准备就绪
                if(sk.isAcceptable()){
                    //TODO 3. SelectionKey中存放了对应的后端服务channel，可以进行取出来。
                    ServerSocketChannel channel = (ServerSocketChannel)sk.channel();
                    //TODO 可以对特殊的channel服务单独做一些事情
                    if(channel ==ssChannel01 ){
                        System.out.println("channel == ssChannel01");
                    }
                    if(channel ==ssChannel02 ){
                        System.out.println("channel == ssChannel02");
                    }
                    System.out.println();
                    //10. 若“接收就绪”，获取客户端连接
                    SocketChannel sChannel = channel.accept();

                    //11. 切换非阻塞模式
                    sChannel.configureBlocking(false);

                    //12. 将该通道注册到选择器上
                    sChannel.register(selector, SelectionKey.OP_READ);
                }else if(sk.isReadable()){
                    //13. 获取当前选择器上“读就绪”状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();

                    //14. 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len = 0;
                    while((len = sChannel.read(buf)) > 0 ){
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }

                //15. 取消选择键 SelectionKey
                it.remove();
            }
        }
    }

    public ServerSocketChannel getSelectableChannel(int port) throws IOException {
        //1. 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //2. 切换非阻塞模式
        ssChannel.configureBlocking(false);

        //3. 绑定连接
        ssChannel.bind(new InetSocketAddress(port));

        return ssChannel ;
    }
}
