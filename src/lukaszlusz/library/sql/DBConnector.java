package lukaszlusz.library.sql;

import lukaszlusz.library.Exceptions.DatabaseConnectionException;
import lukaszlusz.library.config.DbInfo;

import java.sql.*;

abstract class DBConnector {
    private final String jdbcDriver;
    DbInfo dbInfo;
    private Connection connection;

    DBConnector(DbInfo dbInfo, String jdbcDriver) {
        this.dbInfo = dbInfo;
        this.jdbcDriver = jdbcDriver;
    }

    Connection getConnection() throws DatabaseConnectionException {
        if (connection == null) connect();
        return connection;
    }
    
    void closeConnection() throws DatabaseConnectionException {
        if (connection != null) tryToCloseConnection();
    }

    protected abstract String createURL();

    private void connect() throws DatabaseConnectionException {
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(createURL(),dbInfo.user,dbInfo.password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseConnectionException("Nie można połączyć z bazą danych");
        }

    }

    private void tryToCloseConnection() throws DatabaseConnectionException {
        try {
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseConnectionException("Nie udało się poprawnie zakończyć połaczenia");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (connection != null) connection.close();
    }
}
