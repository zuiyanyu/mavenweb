package javaBase.java_socket.TCP_IP;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP编程实现C/S文件传输
 *
 * 实现功能：客户端发送文件给服务端，服务端将文件保存在本地。
 */
public class TCPSocket_Demo02 {
    /* 客户端 */
    @Test
    public void client() {
        Socket socket = null;
        OutputStream writer = null;
        BufferedInputStream bis = null;
        String filePath = "C:\\Users\\Administrator\\Desktop\\dynamicTab\\account.txt" ;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"),8089);
            writer = socket.getOutputStream();

            bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            byte[] buffer = new byte[1024];
            int len;
            while((len = bis.read(buffer)) != -1){
                writer.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bis != null){
                try {
                    bis.close();
                    System.out.println("发送成功！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /* 服务端 */
    @Test
    public void server() throws IOException { // 这里异常应该使用try-catch-finally
        String fileToSavePath ="C:\\Users\\Administrator\\Desktop\\dynamicTab\\account2.txt" ;


        ServerSocket socket = new ServerSocket(8089);
        System.out.println("正在等待客户端连接...");
        Socket clientSocket = socket.accept();

        System.out.println("客户端已连接IP地址为："+clientSocket.getInetAddress().getHostName());
        InputStream reader = clientSocket.getInputStream();
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(new File(fileToSavePath)));

        byte[] buffer = new byte[1024];
        int len;
        while((len = reader.read(buffer)) != -1){
            writer.write(buffer,0,len);
        }
        System.out.println("接收成功");

        //关闭资源
        socket.close();
        clientSocket.close();
        reader.close();
        writer.close();
    }
}
