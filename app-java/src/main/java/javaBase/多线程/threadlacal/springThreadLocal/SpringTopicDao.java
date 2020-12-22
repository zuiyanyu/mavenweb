package javaBase.多线程.threadlacal.springThreadLocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//使用ThreadLocal对conn这个非线程安全的"状态"进行改造。
public class SpringTopicDao {
    //1）使用ThreadLocal保存Connection变量
    private static ThreadLocal<Connection> connThreadLocal = new ThreadLocal<Connection>();
    public static Connection getConnection() throws SQLException {

        //2）如果connThreadLocal没有本线程对应的Connection，创建一个新的Connection。
        //并将其保存到线程本地变量中
        if(connThreadLocal.get()==null){
            //Connection connection = ConnectionManager.getConnection(); //从连接池中获取
            Connection connection = DriverManager.getConnection("");//直接新建一个
            connThreadLocal.set(connection);
            return connection ;
        }
        else{
            //3)直接返回线程本地变量
            return connThreadLocal.get() ;
        }
    }

    public void addTopic() throws SQLException {
        //4）从ThreadLocal中获取线程对应Connection  线程安全的
        Statement stat = getConnection().createStatement();
        //4）使用stat进行操作 ...
    }
}
