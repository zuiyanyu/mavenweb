package jdbc;

import java.sql.*;
import java.util.ArrayList;

/**
 1、 Statement 接口、 CallableStatement 接口、 PreparesStatement 接口的创建JSP
 * 这 三 个 接 口 分 别 由 Connection 接 口 的 createStatement ()、 prepareStatement () 、
 * prepareCall()、 等方法创建， 这几个方法的定义如下：
 * public Statement createStatement ();
 * public Statement createStatement (int ResultSetType int ResultSetConcurrency);
 * public CallableStatement prepareCall(String sql);
 * public CallableStatement prepareCall(String sql int ResultSetType
 * int ResultSetConcurrency);
 * public PreparedStatement prepareStatement (String sql);
 * public PreparedStatement prepareStatement (String sql int ResultSetType
 * int ResultSetConcurrency);
 * 上面列出的方法中， 参数 sql 代表需要执行的 SQL 语句， 这些 SQL 语句不是完整的 SQL
 * 语句， 一般带有 IN/OUT/INOUT 参数， 参数 ResultSetType 代表该方法创建的 SQL 语句接口执
 * 行 SQL 语句所返回的 ResultSet 的类型， 例如是否允许数据库游标前后移动，
 * 是否对其他用户的数据库更新操作敏感等， 它们都是一些整型的常数， 在 ResultSet 接口中
 * 定义了， 读者可以参考 5.2.1 小节的相关内容， 参数 ResultSetConcurrency 代表该方法创建
 * 的 SQL 语句接口执行 SQL 语句所返回的 ResultSet 的协同模式， 如允许更新记录集的数据或
 * 者仅仅只读， 不能更新等， 它们也是一些整型的常数， 在 ResultSet 接口中定义了， 读者可以参考
 * 5.2.1 小节的相关内容。 下面的代码段是创建 Statement 接口对象的示例(数据库连接代码已经省
 * 略了 con 是 Connection 接口的实例对象)。
 * 例：
 * stmt=con.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE
 * ResultSet.CONCUR_UPDATABLE);
 * 上面的代码创建了一个 SQL 语句接口(Statement )的实例对象， 该实例对象允许它执行 SQL
 * 语句所返回的记录集中的数据库游标前后移动， 允许更新记录集中的数据
 */
public class JDBCTest {
    public static void main(String[] args) throws SQLException {
//        int[] ints = statement_ExecuteBatchTest();
        int[] ints1 = preparedStatement_ExecuteBatchTest();
    }
    private static void test() throws SQLException{
        System.out.println(JDBCUtil.getConnection());
        String sql = "select user_no as userNo , user_name as userName ,user_age as userAge ,birthday  from user ;" ;
        ResultSet resultSet = execute(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();



        // 判断 result的下一行数据指针是否指向 null ,如果true，代表指向null,说明ResultSet中没数据了。
        boolean wasNull = resultSet.wasNull();
        System.out.print("wasNull="+wasNull);

        //如果列名已知， 但不知其索引， 则可用方法 findColumn 得到其列号。
        int column = resultSet.findColumn("userName");
        System.out.println("colum = "+column);

        // ResultSet.getMetaData 得 到 的ResultSetMetaData 对象将给出其 ResultSet 对象各列的编号、 类型和属性
        int columnCount = metaData.getColumnCount();
        String[] columnLabelArray = new String[columnCount] ;
        for (int index = 1; index <= columnCount; index++) {
            String columnName = metaData.getColumnName(index);
            String columnLabel = metaData.getColumnLabel(index);
            columnLabelArray[index-1] = columnLabel ;
            //columnName=user_name		columnLabel=userName
            System.out.println("columnName="+columnName +"\t\t"+"columnLabel="+columnLabel);
        }
        for (String colLabelName : columnLabelArray) {
            System.out.print(colLabelName+"\t");
        }
        System.out.println();



        Boolean flag = false ;
        while(resultSet.next()){
            //某一行的某一列
            Array array = resultSet.getArray(1);
            ArrayList array1 = (ArrayList)array.getArray();
            ResultSet resultSet1 = array.getResultSet();


            //TODO 使第一行的数据和最后一行的数据进行互位置换输出
            //如果是第一行，就跳转到最后一行
            if(resultSet.isFirst() && !flag){
                resultSet.last();
                for (String colLabelName : columnLabelArray) {
                    Object calValue = resultSet.getObject(colLabelName);
                    System.out.print("\t"+calValue+"\t");
                }
                System.out.println();
                resultSet.first();
            }
            if(!resultSet.isFirst() && !resultSet.isLast() ){
                for (String colLabelName : columnLabelArray) {
                    Object calValue = resultSet.getObject(colLabelName);
                    System.out.print("\t"+calValue+"\t");
                }
                System.out.println();
            }
            //如果当前访问行是最后一行，就跳转到第一行
            if(resultSet.isLast() && !flag){
                resultSet.first();
                flag = true ;
                for (String colLabelName : columnLabelArray) {
                    Object calValue = resultSet.getObject(colLabelName);
                    System.out.print("\t"+calValue+"\t");
                }
                resultSet.last();
                System.out.println();
            }
        }
        Ref ref = resultSet.getRef(1);
    }

   
    public static Integer executeUpdate(Connection connection,String sql,Object ...parms) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if(null != parms) {
            for (int index = 0; index < parms.length; index++) {
                preparedStatement.setObject(index, parms[index]);
            }
        }
        return preparedStatement.executeUpdate(sql) ;
    }
    public static ResultSet executeQuery(Connection connection, String sql, Object ...parms) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if(null != parms) {
            for (int index = 0; index < parms.length; index++) {
                preparedStatement.setObject(index, parms[index]);
            }
        }
        return preparedStatement.executeQuery(sql) ;
    }
    public static ResultSet execute(Connection connection, String sql, Object ...parms) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        if(null != parms) {
            for (int index = 0; index < parms.length; index++) {
                preparedStatement.setObject(index, parms[index]);
            }
        }
        preparedStatement.execute(sql);
        ResultSet resultSet = preparedStatement.getResultSet();
        System.out.println("UpdateCount = "+preparedStatement.getUpdateCount());
        return resultSet ;
    }
   
    public static Integer executeUpdate(String sql,Object ...parms) throws SQLException {
        Connection connection = JDBCUtil.getConnection() ;
        return executeUpdate(connection,sql,parms) ;
    }
    public static ResultSet executeQuery(String sql,Object ...parms) throws SQLException {
        Connection connection = JDBCUtil.getConnection() ;
        return executeQuery(connection,sql,parms) ;
    }
    public static ResultSet execute (String sql,Object ...parms) throws SQLException {
        Connection connection = JDBCUtil.getConnection() ;
        return execute(connection,sql,parms) ;
    }

    /**
     * 2、 支持批操作
     * Statement 接口、 PreparedStatement 接口、 CallableStatement 接口都支持数据库批操作，
     * 就是将若干个 SQL 语句添加到一个 SQL 语句块(Batch)中， 一并发送到数据库服务器去， 数据库
     * 引擎执行完 SQL 语句块中的语句后会将所有的结果一并返回这种功能特别适用于大批量的数据
     * 库 INSERT 操作。 为了实现这样的功能， 必须用到的 Statement 接口的方法如下所示：
     * public void addBatch(String sql); 该方法用于将 SQL 语句添加到 SQL 语句块中。
     * public void clearBatch(); 该方法用于将 SQL 语句块中的所有 SQL 语句全部删除。
     * public int[] executeBatch(); 该方法用于将 SQL 语句块发送到数据库服务器去， 并执
     * 行它， 返回的结果是一个整型数组， 数组中的元素是数据库服务器执行 SQL 语句块中 SQL
     * 语句所返回的更新计数， SQL 语句块中含有多少个 SQL 语句， 返回的整行数组中就含有多少个
     * 元素。
     * 使用 JDBC API 执行数据库批操作的方法是
     * （ 1） 创建 Statement 接口的实例对象。
     * （ 2 ） 调用 addBatch()方法， 往 SQL 语句块中添加若干个 SQL 语句。
     * （ 3 ） 使用 executeBatch()方法， 完成数据库批操作。
     *
     *
     * @return
     */


    public static int[] statement_ExecuteBatchTest() throws SQLException {
        int[] ints = null;
        Connection connection = JDBCUtil.getConnection();
        String sql1="insert into user(user_no,user_name,user_age,birthday ) values(\"10009\",\"张三\",20,\"20200813\") ;" ;
        String sql2="insert into user(user_no,user_name,user_age,birthday ) values(\"10001\",\"张三\",21,\"20200813\") ;" ;
        String sql3="update user set user_Name = \"李四\" where user_No = \"10008\" ;" ;
        String sql4="delete from user where   user_No = \"10001\" ;" ;

        try{
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            //Statement 可以操作 增 删 改 的sql执行 ，但是sql条件不能进行预处理，执行写在sql语句中。
            statement.addBatch(sql1);
            statement.addBatch(sql2);
            statement.addBatch(sql3);
            statement.addBatch(sql4);
            ints = statement.executeBatch();
            for (int anInt : ints) {
                System.out.println("anInt="+anInt);
            }
            connection.commit();
        }catch (Exception e){
            connection.rollback();
            System.out.println(e.getMessage());
        }

        return ints ;
    }

    /**
     * Connection ctn= DriverManager .JDBCUtil.getConnection (url prop);
     * //Creates a PreparedStatement object with single parameter
     * PreparedStatement prepStmt = ctn.prepareStatement ("insert into book(book_name,book_type,book_desc)values(?,?,?)")
     *
     * prepStmt.setString(1 “结构化学基础” );
     * prepStmt.setString(2 “书籍” );
     * prepStmt.setString(3 “北京大学化学系本科教材免费赠送” );
     * prepStmt.addBatch();
     *
     * prepStmt.setString(1 “万水青山踏遍” );
     * prepStmt.setString(2 “兵器” );
     * prepStmt.setString(3 “曾是白云城主的配剑唯有此剑方能使出天外飞仙的剑招” );
     * prepStmt.addBatch();
     *
     * prepStmt.addBatch();
     * prepStmt.executeBatch();
     * prepStmt.close();
     * ctn.close();
     * @return
     * @throws SQLException
     */
    public static int[] preparedStatement_ExecuteBatchTest() throws SQLException {
        Connection connection = JDBCUtil.getConnection();

        //使用一条 插入sql插入多条数据记录的使用可以使用这种方式 .
        PreparedStatement preparedStatement = connection.prepareStatement("insert into user(user_no,user_name,user_age,birthday ) values(?,?,?,?)");
        for (int i = 10010; i < 10010+5; i++) {
            preparedStatement.setString(1,""+i);
            preparedStatement.setString(2,"王五");
            preparedStatement.setInt(3,22);
            preparedStatement.setDate(4,new Date(System.currentTimeMillis()));

            // 这种批量操作的方式必须有参数，不然异常。
            preparedStatement.addBatch();
        }
        int[] ints = preparedStatement.executeBatch();

        return ints ;
    }
}
