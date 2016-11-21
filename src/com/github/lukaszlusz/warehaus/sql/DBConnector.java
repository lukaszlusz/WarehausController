package com.github.lukaszlusz.warehaus.sql;

import java.sql.*;

public abstract class DBConnector {
    protected final String jdbcDriver;
    protected String address;
    protected String dbName;
    protected String port;
    protected String user;
    protected String password;
    protected Connection connection;

    public DBConnector(String address, String dbName, String port, String user, String password, String jdbcDriver) {
        this.address = address;
        this.dbName = dbName;
        this.port = port;
        this.user = user;
        this.password = password;
        this.jdbcDriver = jdbcDriver;
    }

    protected abstract String createURL();

    protected void connect() throws SQLException{
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(createURL(),user,password);
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
