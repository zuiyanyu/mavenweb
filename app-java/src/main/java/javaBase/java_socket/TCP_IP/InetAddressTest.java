package javaBase.java_socket.TCP_IP;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
1 java中使用InetAddress代表IP
2 IP的分类：IPV4和IPV6     万维网和局域网

3 域名：www.baidu.com www.cnblogs.com

4 本地回路地址：127.0.0.1 对应着：localhost

5 如何实例化InetAddress类的对象，两个静态方法：
    InetAddress getByName(String host)
    InetAddress getLocalHost()

6 两个常用方法
    getHostName()
    getHostAddress()

7 端口号：正在计算机上运行的进程
 */
public class InetAddressTest {
    public static void main(String[] args){
        try {
            // File file = new File("test.txt");
            InetAddress IP1 = InetAddress.getByName("192.168.3.2");
            System.out.println(IP1);

            InetAddress IP2 = InetAddress.getByName("www.baidu.com");
            System.out.println(IP2);

            // 获取本地IP
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);

            // getHostName()
            System.out.println(IP2.getHostName());
            // getHostAddress()
            System.out.println(IP2.getHostAddress());


        } catch(UnknownHostException e){
            e.printStackTrace();
        }
    }
}