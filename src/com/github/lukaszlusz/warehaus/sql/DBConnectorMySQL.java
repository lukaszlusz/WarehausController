package com.github.lukaszlusz.warehaus.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectorMySQL implements DBConnector {
    private final String jdbcDriver = "com.mysql.jdbc.Driver";
    private String address;
    private String dbName;
    private String port;
    private String user;
    private String password;
    protected Connection connection;

    public DBConnectorMySQL(String address, String dbName, String port, String user, String password) {
        this.address = address;
        this.dbName = dbName;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    protected String createURL() {
        String URL = "jdbc:mysql://";
        URL += address + ":" + port + "/" + dbName;
        return URL;
    }

    protected void connect() throws SQLException{
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(createURL(),user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null) connect();
        return connection;
    }

    @Override
    public void closeConnection() throws SQLException {
        if (connection != null) connection.close();
    }
}
