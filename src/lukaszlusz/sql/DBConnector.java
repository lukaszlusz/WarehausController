package lukaszlusz.sql;

import lukaszlusz.GUI.ErrorBox;
import lukaszlusz.config.DbInfo;

import java.sql.*;

public abstract class DBConnector {
    private final String jdbcDriver;
    DbInfo dbInfo;
    private Connection connection;

    public DBConnector(DbInfo dbInfo, String jdbcDriver) {
        this.dbInfo = dbInfo;
        this.jdbcDriver = jdbcDriver;
    }

    protected abstract String createURL();

    public Connection getConnection() throws SQLException {
        if (connection == null) tryToConnect();
        return connection;
    }
    
    public void closeConnection() {
        if (connection != null) tryToCloseConnection();
    }

    private void tryToConnect() {
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(createURL(),dbInfo.user,dbInfo.password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            new ErrorBox("Nie można połączyć się z bazą danych");
        }

    }

    private void tryToCloseConnection() {
        try {
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
            new ErrorBox("Nie udało się poprawnie zakończyć połaczenia");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (connection != null) connection.close();
    }
}
