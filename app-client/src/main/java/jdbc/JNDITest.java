package jdbc;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库连接缓冲池
 * 一个数据库缓冲池指的是缓存于内存空间中的数据库物理连接， 这些数据库连接可以
 * 被重复使用。 数据库缓冲池对于提高 Java 数据库应用程序的性能十分重要， 尤其是当这个
 * Java 数据库应用程序运行于中间层服务器环境时。
 * 数据缓冲池存在于中间层服务器环境当中， 可以被不同的 Java 应用程序所调用
 * javax.sql.RowSet 包添加了对缓冲数据源的支持， 即可以将缓冲池缓冲的数据库连接看作是
 * 一个是实实在在的数据源服务来使用。
 *
 *TODO RowSet 包提供了好几个接口用于处理数据库缓冲池， 主要的接口有：
 *TODO  DataSource 接口:
 * DataSource 接口的实例对象代表了存在于中间层服务器中的缓冲数
 * 据源服务。 使用它可以返还数据库缓冲池中现存的数据库连接， DataSource 接口的实例对
 * 象实际上是某个 JNDI 服务的提供者， 在使用它之前， 该 JNDI 服务对象必须先在中间层服务器环
 * 境中注册， 并且和某个服务名绑定在一起， 然后它才能被别的 Java 应用程序调用。
 * TODO ConnectionPoolDataSource 接口:
 * 该接口可以用于创建一个被缓冲于缓冲池的数据库物理连接， 它有可能会被 DataSource 接口的实例对象调用
 * TODO PooledConnection 接口:
 * 该接口代表被缓冲的数据库连接。 它定义了一个 getConnection ()方法， 使用这个方法可以返回 java.sql.Connection
 * 接口的实例对象
 */
public class JNDITest {
    public static void main(String[] args) throws NamingException, SQLException {
        //==================获取数据源，并绑定到池中
        SampleDataSource sds = new SampleDataSource();
        sds.setServerName("rainbow");
        sds.setDatabaseName("fancy");

        Context ctx = new InitialContext();
        ctx.bind("dns:mavenweb", sds);


        //=============从池中获取已经注册的数据源=============================
 //        Context ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("dns:mavenweb");

        // First get a Connection . Connection pooling is done
        // internally by the DataSource object.
        Connection con = ds.getConnection("root", "123456");
        // Do all the work as a single transaction (optional).
        con.setAutoCommit(false);

        String sql = "select user_no as userNo , user_name as userName ,user_age as userAge ,birthday  from user ;" ;
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.execute(sql);
        ResultSet resultSet = preparedStatement.getResultSet();
        while(resultSet.next()){
            String userName = resultSet.getString("userName");
            System.out.println("userName="+userName);
        }

        // The actual work (queries and updates) would go here.
        // Work is done using standard JDBCcode as defined in the
        // rest of the JDBC API .
        // Commit the transaction.
        con.commit();
        // Close the connection. This returns the underlying physical
        // database connection to the pool.
        con.close();
    }
}
