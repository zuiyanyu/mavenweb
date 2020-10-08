package jdbc;

import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.RowSet;
import java.sql.SQLException;
/**
 * 在 JDBC API 2.0 中， ResultSet 接口有了很大的变化， 增加了很多行操作、 行定位的新方
 * 法， 功能也强大许多。 最主要的变化有以下几方面：
 * 1、 新定义了若干个常数
 * 这些常数用于指定 ResultSet 的类型， 游标移动的方向等性质如下所示：
 * public static final int FETCH_FORWARD;
 * public static final int FETCH_REVERSE;
 * public static final int FETCH_UNKNOWN;
 * public static final int TYPE_FORWARD_ONLY;
 * public static final int TYPE_SCROLL_INSENSITIVE;
 * public static final int TYPE_SCROLL_SENSITIVE;
 * public static final int CONCUR_READ_ONLY;
 * public static final int CONCUR_UPDATABLE;
 * FETCH_FORWORD： 该常数的作用是指定处理记录集中行的顺序是由前到后， 即从
 * 第一行开始处理， 一直到最后一行。
 * FETCH_REVERSE： 该常数的作用是指定处理记录集中行的顺序是由后到前， 即从最
 * 后一行开始处理一直到第一行。
 * FETCH_UNKNOWN ： 该常数的作用是不指定处理记录集中行的顺序， 由 JDBC 驱动
 * 程序和数据库系统决定。
 * TYPE_FORWARD_ONLY： 该常数的作用是指定数据库游标的移动方向是向前， 不允
 * 许向后移动， 即只能使用 ResultSet 接口的 next()方法， 而不能使用 previous()方法， 否则会产生
 * 错误。
 * TYPE_SCROLL_INSENSITIVE： 该常数的作用是指定数据库游标可以在记录集中前后移
 * 动， 并且当前数据库用户获取的记录集对其他用户的操作不敏感， 就是说当前用户正
 * 在浏览记录集中的数据， 与此同时， 其他用户更新了数据库中的数据， 但是当前用户所获
 * 取的记录集中的数据不会受到任何影响。
 * TYPE_SCROLL_SENSITIVE： 该常数的作用是指定数据库游标可以在记录集中前后移动，
 * 并且当前数据库用户获取的记录集对其他用户的操作敏感， 就是说， 当前用户正在浏
 * 览记录集， 但是其它用户的操作使数据库中的数据发生了变化， 当前用户所获取的记录集
 * 中的数据也会同步发生变化， 这样有可能会导致非常严重的错误产生， 建议慎重使用该常
 * 数。
 * CONCUR_READ_ONLY： 该常数的作用是指定当前记录集的协作方式(concurrency
 * mode)为只读， 一旦使用了这个常数， 那么用户就不可以更新记录集中的数据。
 * CONCUR_UPDATABLE 该常数的作用是指定当前记录集的协作方式(concurrency
 * mode)为可以更新， 一旦使用了这个常数， 那么用户就可以使用 updateXXX()等方法更新记
 * 录集中的数据。
 *实例：
 * stmt =con.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE，
 * ResultSet.CONCUR_READ_ONLY);
 * 该行代码十分重要它创建了一个 SQL 语句接口(Statement 接口)的实例对象。 并且指
 * 定凡是由该对象执行 SQL 语句所返回的记录集(ResultSet)， 都可以前后移动数据库游标，
 * 而且记录集中的数据不可以修改， 如果调用无参数的 createStatement ()方法创建 SQL 语句，
 * 接口的实例对象， 那么 stmt 对象执行 SQL 语句所返回的记录集仅可以向前移动数据库游标，
 * 如果这时调用 ResultSet 接口的 previous() 、 absolute()、 relative()等方法就会出错
 *
 * 2 、 ResultSet 接口提供了一整套的定位方法
 * 这些可以在记录集中定位到任意一行， 具体有：
 * TODO 1. public boolean absolute(int row); 该方法的作用是将记。 录集中的某一行设定为当前
 * 行， 亦即将数据库游标移动到指定的行参数 row 指定了目标行的行号， 这是绝对的行号，
 * 由记录集的第一行开始计算， 不是相对的行号。
 * TODO 2. public boolean relative(int rows); 该方法的作用也是将记录集中的某一行设定为当
 * 前行， 但是它的参数 rows 表示目标行相对于当前行的行号， 例如当前行是第 3 行， 现在需
 * 要移动到第 5 行去， 既可以使用 absolute()方法， 也可以使用 relative()方法， 代码如下：
 * 例：
 * rs.absolute(5);
 * 或者
 * rs.relative(2);
 * 其中 rs 代表 ResultSet 接口的实例对象
 * 又如当前行是第 5 行需要移动到第 3 行去代码如下
 * 例 rs.
 * absolute(3);
 * 或者
 * rs.relative(-2);
 * 其中 rs 代表 ResultSet 接口的实例对象。
 * 读者需要注意的问题是， 传递给 relative()方法的参数， 如果是正数， 那么数据库游标
 * 向前移动， 如果是负数， 那么数据库游标向后移动。
 * public boolean first(); 该方法的作用是将当前行定位到数据库记录集的第一行。
 * public boolean last(); 该方法的作用刚好和 first()方法相反， 是将当前行定位到数据
 * 库记录集的最后一行。
 * public boolean isFirst(); 该方法的作用是检查当前行是否记录集的第一行， 如果是，
 * 返回 true ， 否则返回 false。
 * public boolean isLast(); 该方法的作用是检查当前行是否记录集的最后一行， 如果
 * 是， 返回 true， 否则返回 false。
 * public void afterLast(); 该方法的作用是将数据库游标移到记录集的最后， 位于记录
 * 集最后一行的后面， 如果该记录集不包含任何的行， 该方法不产生作用。
 * public void beforeFirst(); 该方法的作用是将数据库游标移到记录集的最前面， 位于
 * 记录集第一行的前面， 如果记录集不包含任何的行， 该方法不产生作用。
 * public boolean isAfterLast(); 该方法检查数据库游标是否处于记录集的最后面， 如果
 * 是返回 true 否则返回 false。
 * public boolean isBeforeFirst(); 该方法检查数据库游标是否处于记录集的最前面， 如
 * 果是返回 true ， 否则返回 false。
 * public boolean next(); 该方法的作用是将数据库游标向前移动一位， 使得下一行成为
 * 当前行， 当刚刚打开记录集对象时数据库游标的位置在记录集的最前面第一次使用 next()
 * 方法， 将会使数据库游标定位到记录集的第一行， 第二次使用 next()方法， 将会使数据库游
 * 标定位到记录集的第二， 行以此类推。
 * public boolean previous(); 该方法的作用是将数据库游标向后移动一位， 使得上一行
 * 成为当前行。
 * 下面以程序清单 5.1(dbScroll.jsp)为例演示如何使用 ResultSet 接口的方法在记录集中定位到特定的
 * 行
 *
 */
public class RowSetTest {
    private static String url ;
    private static String userName ;
    private static String password ;
    static {
        try {
            url = "jdbc:mysql://localhost:3306/mavenweb" ;
            userName = "root" ;
            password ="123456";

            Class.forName("com.mysql.jdbc.Driver") ;
            System.out.println("mysql驱动加载成功！");

        } catch (ClassNotFoundException e) {
            System.out.println("mysql驱动加载失败！");
            e.printStackTrace();
        }
    }


    RowSet rs  ;

    public void go() throws SQLException {
        rs = new JdbcRowSetImpl();
        RowSetListenerTest rowSetListener = new RowSetListenerTest() ;
        rs.addRowSetListener(rowSetListener);

        rs.setUrl(url);
        rs.setUsername(userName);
        rs.setPassword(password);

        String sql = "select * from user where user_no = ?" ;
        rs.setCommand(sql);
        rs.setObject(1,"10005");
        rs.execute();

        while(rs.next()){
            Object userNo = rs.getObject(1);
            Object userName = rs.getObject(1);
//            Object userAge = rs.getObject(1);
//            Object birthday = rs.getObject(1);
            if(rs.wasNull()==false){
                System.out.println("userNo="+userNo+"\t userName="+userName);
            }else{
                System.out.println("value is null");
            }
        }
        if(null !=rs){
            rs.close();
        }
    }
    public static void main(String[] args) {
        try {
            new RowSetTest().go();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
