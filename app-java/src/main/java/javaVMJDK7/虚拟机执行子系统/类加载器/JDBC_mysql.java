package javaVMJDK7.虚拟机执行子系统.类加载器;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBC_mysql {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 加载Class到AppClassLoader（系统类加载器），然后注册驱动类
//         Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://localhost:3306/test";
        // 通过java库获取数据库连接
        Connection conn = java.sql.DriverManager.getConnection(url, "root", "123456");
        System.out.println(conn);



    }
}
