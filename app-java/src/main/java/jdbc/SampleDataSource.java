package jdbc;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class SampleDataSource implements javax.sql.DataSource,javax.naming.Referenceable, java.io.Serializable {

    private String serverName = null;
    private String databaseName = null;

    /* Constructors */
    public SampleDataSource() {
    // This constructor is needed by the object factory
    }
    /** Properties */
    public String getServerName() {
        return serverName;
    }public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    public String getDatabaseName() {
        return databaseName;
    }
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    /**
    Methods inherited from DataSource
    */
    public Connection getConnection() throws SQLException {
        // vendorspecific code to create a JDBCConnection goes here
        try {
            Class.forName(JDBCUtil.dirver);
            Connection conn = DriverManager.getConnection(JDBCUtil.url, JDBCUtil.userName, JDBCUtil.password);
            return conn;
        } catch (Exception fe) {
        // to do nothing
        }
        return null;
   }
    public Connection getConnection(String username, String password)
            throws SQLException {
        // vendorspecific code to create a JDBCConnection goes here
        String user = username;
        String pass = password;
        try {
            Class.forName(JDBCUtil.dirver);
            Connection conn = DriverManager.getConnection(JDBCUtil.url, user, pass);
            return conn;
        } catch (Exception fe) {
        // to do nothing
        }
        return null;
   }
    public java.io.PrintWriter getLogWriter() throws SQLException {
    // vendorspecific code goes here
        return null;
   }
    public void setLogWriter(java.io.PrintWriter out) throws SQLException {
    // vendorspecific code goes here
   }
    public void setLoginTimeout(int seconds) throws SQLException {
    // vendorspecific code goes here
    }
    public int getLoginTimeout() throws SQLException {
        return 1; 
    // vendorspecific code goes here
    }



    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }


    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public Reference getReference() throws NamingException {
        return null;
    }
}