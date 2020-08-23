package jdbc;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CachedRowSet 类是 CachedRowSet 包中最重要的类。 它继承自 BaseRowSet 类，
 * CachedRowSet 对象为规范的数据提供了一个无连接的(disconnected) 、 可串行化的
 * (serializable)、 可以滚动的(scrollable， 指数据指针可以前后移动)的容器。 CachedRowSet 对象
 * 可以简单地看作是一个与数据库断开连接的结果记录集， 它被缓存在数据源之外， 因为
 * 所有的数据都被缓存在内存之中， 所以 CachedRowSet 对象不适合于处理含有海量数据的
 * 记录集。
 * CachedRowSet 对象的重要作用是： 它可以在作为数据容器， 在不同应用程序的不同的
 * 组件之间传送数据。 例如， 一个运行于 Application Server 上的 Enterprise JavaBeans 组件可以
 * 使用 JDBC API 访问数据库， 然后可以使用 CachedRowSet 对象将数据库返回的数据通过网络
 * 发送到运行于客户端浏览器上的 Java Applet 程序或者 JavaBeans 组件。
 * 如果客户端由于资源的限制或者出于安全上的考虑， 没有办法使用 JDBC 数据库驱动
 * 程序， 例如 PersonalJava Clients Personal Digital Assistant (PDA) Network Computer(NC)
 * 等客户端， 这时使用 CachedRowSet 类就可以提供一个 Java Client 用以处理数据库的规
 * 范数据。
 * CachedRowSet 类的第三个作用是： 它可以通过使用数据源以外的空间缓存记录集的数
 * 据， 从而不需要 JDBC 数据库驱动程序帮助， 而实现了结果记录集的数据库游标的前后移
 * 动， 还可以执行行更新的操作 CachedRowSet 对象在读入数据库的数据时， 采用 get rows in
 * 的机制， 当它更新记录集的数据时采用 get changed rows out 的机制。 CachedRowSet 对象
 * 获取数据以后， 就断开了和数据源的连接只有执行行更新操作时， 才再度与数据库建立
 * 连接。 某些 JDBC 驱动程序目前仍然不支持结果记录集的数据库游标的前后移动， 这时使
 * 用 CachedRowSet 类就可以实现你的愿望。
 * 注意： 如果你使用的 JDBC 数据库驱动程序是 JDBC-ODBC 桥驱动程序， 用 ResultSet
 * 接口、 Statement 接口以通常的方法访问数据库， 似乎无法实现记录集的数据库
 * 游标的前后移动， 特别是不能向后移动亦不能定位到任意行去， 如果读者碰
 * 到了类似的问题， 除了更换 JDBC 驱动程序以外不妨使用 CachedRowSet 类。
 */
public class CachedRowSetTest {
    public static void main(String[] args) throws SQLException {
        // 1） 创建 CachedRowSet 类的实例对象

        // 2） 使用记录集数据填充 CachedRowSet 对象
        CachedRowSetTest cachedRowSetTest = new CachedRowSetTest();
        cachedRowSetTest.useCachedRowSet(2,3);
        cachedRowSetTest.useCachedRowSet(1,4);


    }

    /**
     * 1） 创建 CachedRowSet 类的实例对象
     * 如果想使用 CachedRowSet 类的强大功能， 那么你必须首先创建 CachedRowSet 类的实
     * 例对象， 如何创建呢?可以使用 CachedRowSet 类的构造函数， CachedRowSet 类的构造函数是
     * CachedRowSet() ， 该函数没有任何参数 CachedRowSet()函数初始化了下面的属性值：
     * onInsertRow = false
     * insertRow = null
     * cursorPos = 0
     * numRows = 0
     * showDeleted = false
     * queryTimeout = 0
     * maxRows = 0
     * maxFieldSize = 0
     * RowSetType = ResultSet.TYPE_SCROLL_INSENSITIVE
     * concurrency = ResultSet.CONCUR_READ_ONLY
     * readOnly = false
     * isolation = Connection .TRANSACTION_READ_COMMITTED
     * escapeProcessing = true
     * absolutePos = 0
     * 新创建的 CachedRowSet 对象缺省可以容纳 100 个记录所包含的数据。
     * 指定 CachedRowSet 对象和数据库建立连接的连接属性创建了 CachedRowSet 对象， 就可以使用
     * setPassword() 、 setUsername()、 setURL()等方法
     * 指定 CachedRowSet 对象和数据库建立连接的连接参数(当然了， 需要首先载入 JDBC 驱动
     * 程序)。 setPassword()、 setUsername() 等方法都是在 BaseRowSet 类中被声明， 然后在
     * CachedRowSet 类中实现了方法的功能。
     */
    public CachedRowSet getCacheedRowSet() throws SQLException {
        CachedRowSet cachedRowSet = new CachedRowSetImpl();
        cachedRowSet.setUrl(JDBCUtil.url);
        cachedRowSet.setUsername(JDBCUtil.userName);
        cachedRowSet.setPassword(JDBCUtil.password);

        return cachedRowSet ;
    }

    /**
     * TODO 2） 如何用数据库的数据填充CachedRowSet 对象内部记录集结构:方法1
     * 如何使用记录集的数据填充 CachedRowSet 对象呢?一般说来， 有三种较为常用的方法。
     * 第一种方法和 javax.sql.RowSet 接口所使用的方法如出一辙， 设定了 CachedRowSet 对象的数据
     * 库连接参数以后调用 setCommand()方法指定 SQL 命令， 再使用 setXXX()方法设定 SQL 命令的
     * 输入参数(如果有输入参数的话)， 接着就可以使用 execute()方法首先利用设
     * 定好的连接参数和数据库建立连接， 发送 SQL 命令， 执行 SQL 命令， 获取数据库返回的
     * 数据， 并用这些数据， 填充 CachedRowSet 对象的内部记录集结构注意这种方法所使用
     * 的 execute()方法不带任何参数
     * execute()方法的定义如下：
     *  public void execute();
     */
    public void getCachedRowSet_Type1() throws SQLException {
        //TODO 1.获取CachedRowSet 实例
        CachedRowSet cachedRowSet = new CachedRowSetImpl();
        cachedRowSet.setUrl(JDBCUtil.url);
        cachedRowSet.setUsername(JDBCUtil.userName);
        cachedRowSet.setPassword(JDBCUtil.password);


        //TODO 2. 为CachedRowSet填充数据
        String sql = "select * from user where user_no = ?" ;
        cachedRowSet.setCommand(sql);
        cachedRowSet.setObject(1,"10005");
        cachedRowSet.execute();


    }

    /**
     * TODO 2）  如何用数据库的数据填充CachedRowSet 对象内部记录集结构 ：方法2
     *  第二种方法是首先载入 JDBC 数据库驱动程序然后与
     * 数据库建立连接创建 Connection 接口的实例对象接着用 setcommand()方法指定 SQL 命
     * 令， 如果存在 SQL 输入参数则可以使用 setXXX()方法指定 IN 参数， 一切就绪后， 就可
     * 以调用 execute()方法，  注意此 execute()方法需要参数参数， 就是 Connection 接口的实例对象，
     * execute() 方法可以利用这个对象往数据库发送 SQL 命令， 并用数据库服务器返回的数据填充 CachedRowSet 对象。
     * execute()方法的定义如下：
     * public void execute(java.sql.Connection connection);
     *
     */
    public void getCachedRowSet_Type2() throws SQLException {

        //TODO 1.获取CachedRowSet 实例
        CachedRowSet cachedRowSet = new CachedRowSetImpl();
        Connection connection = JDBCUtil.getConnection();


         //TODO 2. 为CachedRowSet填充数据
         String sql = "select * from user where user_no = ?" ;
         cachedRowSet.setCommand(sql);
         cachedRowSet.setObject(1,"10005");
         cachedRowSet.execute(connection);

    }

    /**
     *  TODO 2） 如何用数据库的数据填充CachedRowSet 对象内部记录集结构 ：方法3 最常用
     *  首先载入 JDBC 数据库驱
     * 动程序， 然后分别创建 Connection 接口的实例对象， Statement 接口的实例对象接着调用
     * Statement 对象的 execute()方法执行数据库操作， 返回一个 ResultSet 接口的实例对象， 然
     * 后就可以使用 CachedRowSet 类的 populate()方法将 ResultSet 对象的数据填充 CachedRowSet
     * 对象内部的记录集结构。 populate()方法接受 ResultSet 接口的实例对象为方法参数。 populate()
     * 方法的定义如下：
     * public void populate(java.sql.ResultSet data)
     */
    public CachedRowSet getCachedRowSet_Type3() throws SQLException {
        //TODO 1.获取CachedRowSet 实例
        CachedRowSet cacheedRowSet = new CachedRowSetImpl();
        Connection connection = JDBCUtil.getConnection();

        //TODO 2.使用JDBC原生方式获取数据集
        String sql = "select * from user" ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setObject(1,"10005");
        ResultSet resultSet = preparedStatement.executeQuery();

        //TODO 3. 填充数据
        cacheedRowSet.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        //将数据复制到cacheedRowSet 中
        cacheedRowSet.populate(resultSet);

        //关闭数据库连接
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return cacheedRowSet ;
    }

    /**
     * TODO 3)  那么如何访问这些存储于 CachedRowSet 对象内部的数据呢?
     *  CachedRowSet 类定义了一套 getXXX()方法可以获取 CachedRowSet 对象内部记录
     * 集结构的数据， 如 getString()
     * getBlob()、 getClob()等方法， 这些方法的用法和 java.sql.ResultSet 接口 javax.sql.RowSet 接口
     * 的同名方法十分相似
     * CachedRowSet 类可以将内部记录集结构的数据拷贝到 RowSet 对象中去有两个方法
     * 可以实现这个功能分别如下所示
     * public javax.sql.RowSet createCopy();
     * public javax.sql.RowSet createShared();
     * 这两个方法都返回 RowSet 接口的实例对象， 看起来， 这两种方法似乎没有什么
     * 差别， 不就是将 CachedRowSet 对象的内部数据备份到 RowSet 对象中去吗?其实这两个方
     * 法有很大的差别第一个方法仅仅是将 CachedRowSet 对象的内部数据备份到 RowSet 对象
     * 中， 如果后来 CachedRowSet 对象所包含的数据发生了变化， 例如某个记录的数据被更新
     * 或者被删除了， 但是 RowSet 对象不会受到任何的影响即 RowSet 对象内部记录集的数据
     * 保持原样， 没有发生变化。 createShare()方法同样将 CachedRowSet 对象内部记录集的数据
     * 复制到 RowSet 对象中去， 这两个对象的数据是共享的如果 CachedRowSet 对象修改了内容
     *
     * CachedRowSet 对象除了可以将内部记录集的数据复制到 RowSet 对象上， 还可以将数
     * 据复制到 Collection 对象上， 这需要使用 toCollection()方法该方法的定义如下：
     * public java.util.Collection toCollection();
     * public java.util.Collection toCollection(int column);
     * 上面的两个方法同名， 都返回一个 java.util.Collection 对象， 第一个方法返回的
     * Collection 对象含有 CachedRowSet 对象内部记录集的全部数据， 但是第二个方法需要参数
     * column(整型)， 这个方法仅仅将 CachedRowSet 对象内部记录集的第 cloumn 列的数据返回， 并
     * 赋给 Collection 对象， 而不是返回全部的数据。
     */
    CachedRowSet cachedRowSet_type3 ;
     {
         try {
             cachedRowSet_type3 = this.getCachedRowSet_Type3();
             System.out.println("====================");
             while(cachedRowSet_type3.next()){
                 Object object = cachedRowSet_type3.getObject(1);
                 System.out.println("user_no:"+object);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
    public void useCachedRowSet(int pageNum,int pageSize) throws SQLException {
        if(pageNum < 1 )   pageNum = 1 ;
        if(pageSize < 1 )  pageSize = 0 ;
        int pageStart = (pageNum -1) * pageSize + 1 ;

        //进行分页查询
        //
        System.out.println("-----------------------"  );
        cachedRowSet_type3.absolute(pageStart);
        cachedRowSet_type3.relative(-1);

        while(cachedRowSet_type3.next() && pageSize>0){
            Object object = cachedRowSet_type3.getObject(1);
            System.out.println("user_no:"+object);
            pageSize -- ;
        }
    }


}
