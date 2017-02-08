package lukaszlusz.library.sql;

import lukaszlusz.library.Exceptions.DatabaseConnectionException;
import lukaszlusz.library.config.DbInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    DBConnector dbConnector;

    public Database(DbInfo dbInfo) {
        dbConnector = new DBConnectorMySQL(dbInfo);
    }

    public ResultSet getStatuses() throws SQLException, DatabaseConnectionException {
        return executeQuery("SELECT Status FROM Statuses");
    }

    public ResultSet getCategories() throws SQLException, DatabaseConnectionException {
        return executeQuery("SELECT Category FROM Categories");
    }

    public Connection getConnection() throws DatabaseConnectionException {
        return dbConnector.getConnection();
    }

    public void closeConnection() throws DatabaseConnectionException {
        dbConnector.closeConnection();
    }

    private ResultSet executeQuery(String sqlQuery) throws SQLException, DatabaseConnectionException {
        ResultSet resultSet;
        Statement statement = getConnection().createStatement();
        resultSet = statement.executeQuery(sqlQuery);
        return resultSet;
    }
}