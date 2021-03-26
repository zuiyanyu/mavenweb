package javaBase.java_socket.UDP;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSocket_Demo01 {
    @Test // 发送端
    public void send() throws IOException {
        //1. 创建DatagramSocket
        DatagramSocket socket = new DatagramSocket();

        //2. 创建DatagramPacket
        //TODO UDP协议中每个数据报都给出了完整的地址信息，因此无须建立发送方和接收方的连接
        byte[] data = "hello world".getBytes();
        DatagramPacket packet = new DatagramPacket(data,0,data.length, InetAddress.getLocalHost(),8080);

        //3. 发送数据报
        socket.send(packet);

        //4. 关闭资源
        socket.close();
    }

    @Test // 接收端
    public void receiver() throws IOException {
        //1. 创建DatagramSocket
        DatagramSocket socket = new DatagramSocket(8080);

        //2. 创建DatagramPacket
        byte[] buffer = new byte[100];
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);

        System.out.println("等待接收数据报...");
        //3. 接收数据报. TODO 服务端进行阻塞等待接收数据报
        socket.receive(packet);
        System.out.println(new String(packet.getData(), 0, packet.getLength()));

        //4. 关闭资源
        socket.close();
    }
}
