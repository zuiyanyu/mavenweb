package javaBase.java_socket.TCP_IP;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * TCP编程简单C/S通信示例
 *
 * 需求：客户端向服务端发送信息，服务端进行接收并打印输出。
 * TCP编程，模拟基于C/S架构客户端与服务端间的通信
 */
public class TCPSocket_Demo01 {

    /* 客户端 */
    @Test
    public void client(){
        OutputStream output = null;
        Socket socket = null;
        try {
            //1. 创建客户端套接字，指定要连接的服务器地址和端口号
            InetAddress localHost = InetAddress.getByName("127.0.0.1");
            socket = new Socket(localHost,9080);
            //2.获取输出流，向服务器发送消息
            output = socket.getOutputStream();
            //3.开始输出数据
            output.write("hello I'm the client".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4. 关闭资源
            if(output != null){
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* 服务端 */
    @Test
    public void server() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        InputStream input = null;
        ByteArrayOutputStream out = null;
        try {
            //1、创建服务端的ServerSocket，指明本服务绑定的端口号
            serverSocket = new ServerSocket(9080);

            //2. server 对端口9080进行监听，看是否有客户端连接。
            System.out.println("等待客户端的连接...");
            //TODO   服务器要在这里进行阻塞等待 NIO可以解决这个问题。
            clientSocket = serverSocket.accept();
            System.out.println("有客户端连接到本服务器："+ clientSocket);

            // 3、获取输入流，读取客户端输入给本服务的数据
            input = clientSocket.getInputStream();

            //4. 开始读取客户端发送给服务器的数据
            /*
            // 一般不建议这样书写，数据传输时可能会出现乱码！！
            byte[] buffer = new byte[1024];
            int len;
            while((len = input.read(buffer)) != -1){
                String data = new String(buffer,0,len);
                System.out.println(data);
            }
            */

            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[10];
            int len;
            while((len = input.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            System.out.println(out.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5. 关闭资源.
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(input != null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 先中断和客户端的连接，再关闭服务端。
            if(clientSocket != null){
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
