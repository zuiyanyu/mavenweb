package javaBase.java_socket.TCP_IP;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP编程实现C/S信息反馈
 * 实现功能：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给 客户端。并关闭相应的连接。
 */
public class TCPSocket_Demo03 {
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
            //TODO 关闭数据的输出 . 通知服务端数据已经完全发送完毕。有时候会发送一个特殊字符给服务端，表示客户端发送完毕。
            // TODO (因为服务端是阻塞式的等待读取客户端的信息，不知道客户端什么时候将信息发送完毕.)
            socket.shutdownOutput();

            // 接收服务端反馈的信息并输出到控制台
            InputStream is = socket.getInputStream();
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            byte[] buf = new byte[10];
            int l;
            while((l = is.read(buf)) != -1){
                byteArray.write(buf,0,l);
            }
            System.out.println(byteArray.toString());
            is.close();
            byteArray.close();
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
        String filePath = "C:\\Users\\Administrator\\Desktop\\dynamicTab\\account3.txt" ;

        ServerSocket socket = new ServerSocket(8089);
        System.out.println("正在等待客户端连接...");
        Socket clientSocket = socket.accept();

        System.out.println("客户端已连接IP地址为："+clientSocket.getInetAddress().getHostName());
        InputStream is = clientSocket.getInputStream();
        BufferedOutputStream reader = new BufferedOutputStream(new FileOutputStream(new File(filePath)));

        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1){
            reader.write(buffer,0,len);
        }
        System.out.println("接收成功!");

        // 服务端给客户端反馈信息
        OutputStream os = clientSocket.getOutputStream();
        os.write("你好客户端，照片已经收到".getBytes());

        //关闭资源
        socket.close();
        clientSocket.close();
        is.close();
        reader.close();
        os.close();
    }
}
