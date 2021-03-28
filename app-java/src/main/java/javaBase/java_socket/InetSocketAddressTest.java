package javaBase.java_socket;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * 在Java中InetAddress和InetSocketAddress看起来很相似，用来描述IP地址和主机名称。
 * 它们都支持使用常规方法来检查地址：回环地址、本地地址、组播地址；基本的返回方法：获得IP，获得主机名称等。
 * 重要的是InetSocketAddress包含InetAddress。这意味着，如果我们想对InetSocketAddress中的InetAddress做任何操作，只需要通过getInetAddress()方法获得即可。
 *
 *属性 InetAddress
 * 描述对象 IP地址
 * 描述 IP和主机对象名称
 * 解决问题 IP到主机名称，主机名称到IP
 * 获取对象 InetAddress.getLocalhost();InetAddress.getByName(String);InetAddress.getByAddress(String);
 *
 * 属性 InetSocketAddress
 * 描述对象 Socket地址（IP地址+端口）
 * 描述 IP和主机的对象名称，并包括端口号
 * 解决问题 IP到主机名称，主机名称到IP，可以包含端口
 * 获取对象 InetSocketAddress.createUnresolved(String, port);
 *
 */
public class InetSocketAddressTest {
    public static void main(String args[]){

        byte[] b = new byte[] {(byte)192,(byte)168,(byte)1,(byte)1};

        try {

            InetAddress add = InetAddress.getByAddress(b);

            System.out.println(add.toString());

            add = InetAddress.getByName("localhost");

            System.out.println(add.toString());

            InetSocketAddress addsock = InetSocketAddress.createUnresolved(

                    "localhost", 90);

            System.out.println(addsock.toString());

        }catch(Exception e) {

        }

    }
}
