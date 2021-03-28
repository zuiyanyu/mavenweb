package javaBase.java_socket.socket_NIO.TCP_IP.非阻塞式;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;

/*
 * 一、使用 NIO 完成网络通信的三个核心：
 *
 * 1. 通道（Channel）：负责连接
 *
 * 	   java.nio.channels.Channel 接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 *
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 *
 * 2. 缓冲区（Buffer）：负责数据的存取
 *
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 *
 */

//TODO selector监听一个Channel，处理一个端口服务， 但是要求服务端返回给客户端接收成功的信息！
public class NoneBlock_Socket_demo01 {

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

        //TODO 通知服务端已经发送完毕
        sChannel.shutdownOutput();

        //接收服务端的反馈
        int len = 0;
        while((len = sChannel.read(buf)) != -1){
            buf.flip();
            System.out.println(new String(buf.array(), 0, len).trim());
            buf.clear();
        }

        //5. 关闭通道
        sChannel.close();
    }
    //服务端
    @Test
    public void server() throws IOException{
        int port = 9090 ;
        //1. 获取通道
        ServerSocketChannel ssChannel01 = ServerSocketChannel.open();

        //2. 切换非阻塞模式
        ssChannel01.configureBlocking(false);

        //3. 绑定连接
        ssChannel01.bind(new InetSocketAddress(port));

        //4. 获取选择器
        Selector selector = Selector.open();

        //5. 将通道注册到选择器上, 并且指定“监听接收事件”
        ssChannel01.register(selector, SelectionKey.OP_ACCEPT);

        //6. 轮询式的获取选择器上已经“准备就绪”的事件
        /** selector.select() 会进行阻塞：
         * 监控所有注册的Channel，当它们中间有需要处理的 IO 操作时，该方法返回，
         * 并将对应得的 SelectionKey 加入被选择的SelectionKey 集合中，该方法返回这些 Channel 的数量。
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

                    System.out.println();
                    //10. 若“接收就绪”，获取客户端连接
                    SocketChannel sChannel = ssChannel01.accept();

                    //11. 切换非阻塞模式
                    sChannel.configureBlocking(false);

                    //12. 将该通道注册到选择器上
                    sChannel.register(selector, SelectionKey.OP_READ );
                }else if(sk.isReadable()){

                    //13. 获取当前选择器上“读就绪”状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();

                    //14. 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len = 0;
                    boolean isRead = false;
                    while((len = sChannel.read(buf)) > 0 ){
                        isRead = true ;
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                    if(isRead){
                        System.out.println("从读模式切换为写模式");
                        sChannel.register(selector, SelectionKey.OP_WRITE);
                    }

                }else if(sk.isWritable()){
                    System.out.println("进入写模式...");
                    //10. 若“接收就绪”，获取客户端连接
                    SocketChannel sChannel = (SocketChannel)sk.channel();
                    //14. 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    //发送反馈给客户端
                    buf.put("服务端接收数据成功!".getBytes());
                    buf.clear();
                    try{
                        sChannel.write(buf);
                        sChannel.shutdownOutput() ;

                        System.out.println("切换为读模式");
                        sChannel.register(selector, SelectionKey.OP_READ);
                    }catch (ClosedChannelException e){
                        System.out.println("客户端关闭了连接:java.nio.channels.ClosedChannelException");
                        //15. 取消选择键 SelectionKey
                        System.out.println("切换为读模式");
                        sChannel.register(selector, SelectionKey.OP_READ);
                    }


                }
                //15. 取消选择键 SelectionKey
                it.remove();
//                Set<SelectionKey> selectionKeys2 = selector.selectedKeys();
//                System.out.println("移除后：selectionKeys2.size() =" +selectionKeys2.size());
            }
//            Set<SelectionKey> selectionKeys3 = selector.selectedKeys();
//            System.out.println("移除后：selectionKeys3.size() =" +selectionKeys3.size());




        }
    }


}
