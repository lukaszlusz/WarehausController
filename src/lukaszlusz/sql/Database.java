package lukaszlusz.sql;

import lukaszlusz.config.ConfigReader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Database ourInstance = new Database();

    DBConnector dbConnector = new DBConnectorMySQL(ConfigReader.getInstance().getDbInfo());

    public static Database getInstance() {
        return ourInstance;
    }

    private Database() {
    }

    private ResultSet executeQuery(String sqlQuery) throws SQLException {
        ResultSet resultSet;
        Statement statement = getConnection().createStatement();
        resultSet = statement.executeQuery(sqlQuery);
        return resultSet;
    }

    public ResultSet getStatuses() throws SQLException{
        return executeQuery("SELECT Status FROM Statuses");
    }

    public ResultSet getCategories() throws SQLException {
        return executeQuery("SELECT Category FROM Categories");
    }

    public Connection getConnection() {
        return dbConnector.getConnection();
    }

    public void closeConnection() {
        dbConnector.closeConnection();
    }
}
