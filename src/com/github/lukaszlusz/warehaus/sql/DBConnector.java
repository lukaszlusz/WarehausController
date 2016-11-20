package com.github.lukaszlusz.warehaus.sql;

import java.sql.*;

public interface DBConnector {
    Connection getConnection() throws SQLException;
    void closeConnection() throws SQLException;
}
