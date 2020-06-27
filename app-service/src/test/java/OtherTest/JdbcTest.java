package OtherTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String driver="I98+I99+I100+I101";
        String[] split = driver.split("\\+");
        for (String s : split) {
            System.out.println(s);
        }

       driver="I98+I99+I100+I101+";

        driver = driver.substring(0,driver.lastIndexOf("+"));
        System.out.println(driver);



    }
    public static void jdbcTest() throws ClassNotFoundException, SQLException {
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/mavenweb";
        String username="root";
        String password="123456";
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url,username,password);
        System.out.println(connection);

    }
}
