package lukaszlusz.sql;

import lukaszlusz.config.DbInfo;

import java.sql.*;

public abstract class DBConnector {
    protected final String jdbcDriver;
    protected DbInfo dbInfo;
    protected Connection connection;

    public DBConnector(DbInfo dbInfo, String jdbcDriver) {
        this.dbInfo = dbInfo;
        this.jdbcDriver = jdbcDriver;
    }

    protected abstract String createURL();

    protected void connect() throws SQLException{
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(createURL(),dbInfo.user,dbInfo.password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() throws SQLException {
        if (connection == null) connect();
        return connection;
    }
    
    public void closeConnection() throws SQLException {
        if (connection != null) connection.close();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (connection != null) connection.close();
    }
}
