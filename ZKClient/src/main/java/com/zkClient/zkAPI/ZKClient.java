package com.zkClient.zkAPI;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ZKClient {
    //zookeeper的集群服务
    private static String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private static int sessionTimeout = 2000; //集群连接超时时间
    private ZooKeeper zkClient = null;  //zookeeper的客户端。zkAPI的入口

    //TODO 1. 创建zk客户端，指定默认的回调函数
    @Before
    public void init() throws Exception {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            //TODO 默认的回调函数。 这里使用的是设计模式中的观察者模式。
            //如果用户在使用api时候没指定回调函数（比如：zkClient.getChildren("/zkClient", true);），就使用这个回调函数。
            @Override
            public void process(WatchedEvent event) {
                // TODO 收到事件通知后的回调函数（用户的业务逻辑）  zk默认的回调函数：NodeChildrenChanged--/
                System.out.println("zk默认的回调函数："+event.getType() + "--" + event.getPath());

                // 再次启动监听,监听 /目录
                try {
                    zkClient.getChildren("/", true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //TODO 2.创建子节点
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        //节点创建完成后，默认的回调函数会被调用
        String result = zkClient.create("/zkClient3", "zk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("zk节点创建完毕:"+result);
    }

    //TODO 3.获取子节点并监听节点变化 -使用默认的回调函数
    @Test
    public void getChildren01() throws KeeperException, InterruptedException {
        //TODO  等价于 ls path [watch]    这里用户没有指定自定义的回到函数，就会调用默认的回调函数
        List<String> children = zkClient.getChildren("/zkClient", true);
        children.forEach(System.out::println);

        // 延时阻塞
        Thread.sleep(Long.MAX_VALUE);
    }
    //TODO 4.获取某节点的子节点目录 -使用自定义的回调函数，并且持续监听某一个节点的变化(节点目录的变化)。
    @Test
    public void getChildren02() throws KeeperException, InterruptedException {
        //TODO  等价于 ls path [watch]    这里用户自定义回调函数
        List<String> children = zkClient.getChildren("/", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("zk用户自定义的回调函数："+event.getType() + "--" + event.getPath());

                //TODO 持续监听节点的变化(节点目录的变化)
                try {
                    getChildren02();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        children.forEach(System.out::println);
    }

    @Test
    //TODO 5.判断Znode是否存在
    public void exist() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/zkClient", false);
        System.out.println(stat == null ? "not exist" : "exist");
    }

    @Test
    //TODO 获取某个节点的存储数据- 不进行监听
    public void getData() throws KeeperException, InterruptedException {
        byte[] data = zkClient.getData("/zkClient", false, null);
        String dataStr = new String(data,0,data.length);

        System.out.println("获取的节点数据："+dataStr);

    }

}
