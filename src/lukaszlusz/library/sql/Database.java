package lukaszlusz.library.sql;

import lukaszlusz.library.Exceptions.DatabaseConnectionException;
import lukaszlusz.library.config.DbInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    DBConnector dbConnector;

    public boolean ItemName = true;
    public boolean Amount = true;
    public boolean Category = true;
    public boolean Status = true;
    public boolean ItemDescription= true;
    public boolean BoxID = true;


    public Database(DbInfo dbInfo) {
        dbConnector = new DBConnectorMySQL(dbInfo);
    }

    public ResultSet getMainTableData() throws SQLException, DatabaseConnectionException {
        return executeQuery(getMainTableQuery());
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
        Statement statement = getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        resultSet = statement.executeQuery(sqlQuery);
        return resultSet;
    }

    protected String getMainTableQuery(){
        StringBuilder query = new StringBuilder("SELECT items.ItemID, ");
        if (ItemName) query.append("items.ItemName AS 'Nazwa', ");
        if (Amount) query.append("items.Amount AS 'Ilość', ");
        if (Category) query.append("items.Category AS 'Kategoria', ");
        if (Status) query.append("items.Status, ");
        if (ItemDescription) query.append("items.ItemDescription AS 'Opis przedmiotu', ");
        if (BoxID) query.append("items.BoxID AS 'Kod', ");
        query.delete(query.length() - 2 , query.length() );
        query.append(" FROM items;");
        return query.toString();
    }
}