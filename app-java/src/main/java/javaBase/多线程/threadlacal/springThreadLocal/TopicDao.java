package javaBase.多线程.threadlacal.springThreadLocal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//由于 “1)"出的 conn是成员变量，因为addTopic()方法是非线程安全的，必须在使用创建一个新TopicDao实例(非singleton)。
public class TopicDao {
    //1）一个非线程安全的变量
    private Connection conn ;

    public void addTopic() throws SQLException {
        //2）引用非线程安全变量
        Statement stat = conn.createStatement();
        //3）使用非线程安全变量进行操作 ...
    }
}
