package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    /**
     * 获取驱动连接
     * @param url
     * @param userName
     * @param password
     * @return
     * @throws SQLException
     */
    public  static final String url  = "jdbc:mysql://localhost:3306/mavenweb" ;;
    public static final String userName = "root" ;
    public static final String password ="123456";
    public static final String dirver ="com.mysql.jdbc.Driver" ;

    static {
        try {
//            url = "jdbc:mysql://localhost:3306/mavenweb" ;
//            userName = "root" ;
//            password ="123456";
            //这里即使不加在，DriverManager类初始化的时候也会自动扫描自动加载
            Class.forName(dirver) ;
            System.out.println("mysql驱动加载成功！");

        } catch (ClassNotFoundException e) {
            System.out.println("mysql驱动加载失败！");
            e.printStackTrace();
        }
    }
    public static Connection getConnection(final String url, final String userName, final String password) throws SQLException {
        //调用者负责关闭 Connection 数据库连接
        return DriverManager.getConnection(url, userName, password);
    }
    public static Connection getConnection() throws SQLException {
        return getConnection(url,userName,password) ;
    }
    public void close(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
