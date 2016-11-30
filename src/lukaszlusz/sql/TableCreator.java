package lukaszlusz.sql;

import lukaszlusz.GUI.ErrorBox;
import lukaszlusz.config.DbInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TableCreator {
    private static String[] tablesNames = {"boxes", "categories", "items", "statuses"};

    public static void main(String[] args) {
        DBConnector dbConnector = new DBConnectorMySQL(new DbInfo("localhost", "warehousedb", "3306", "lukasz", "1234"));
        CREATE_TABLES(dbConnector.getConnection());

    }

    public static  boolean ARE_TABLES_CREATED(Connection connection, String dbName) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT TABLE_NAME FROM information_schema.tables WHERE table_schema='" + dbName +"';");
        Integer counter = 0;
        while (result.next())
        {
            for (String s : tablesNames) {
                if (s.equals(result.getString("TABLE_NAME"))) ++counter;
            }
        }
        statement.close();
        if (counter == tablesNames.length) return true;
        else return false;
    }

    public static void CREATE_TABLES(Connection connection) {
        try {
            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE Boxes(" +
                    " BoxID int NOT NULL AUTO_INCREMENT," +
                    " Localization VARCHAR(30)," +
                    " BoxDescription VARCHAR(255)," +
                    " PRIMARY KEY (BoxID));");

            statement.execute("CREATE TABLE Categories(" +
                    " Category VARCHAR(30) NOT NULL," +
                    " PRIMARY KEY (Category));");

            statement.execute("CREATE TABLE Items(" +
                    " ItemID int NOT NULL AUTO_INCREMENT," +
                    " Amount int NOT NULL," +
                    " ItemName VARCHAR (30) NOT NULL," +
                    " Category VARCHAR(30)," +
                    " Status VARCHAR(30)," +
                    " ItemDescription VARCHAR(255)," +
                    " BoxID int NOT NULL," +
                    " PRIMARY KEY (ItemID));");

            statement.execute("CREATE TABLE Statuses(" +
                    " Status VARCHAR(30) NOT NULL," +
                    " PRIMARY KEY (Status));");
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            new ErrorBox("Błąd podczas tworzenia tabel bazy danych.\n" +
                    "Baza nie może zawierać tabel o nazwach: Boxes, Categories, Items, Statuses,\n " +
                    "ponieważ takie tabele są używane przez ten program. Zaleca się utworzenie oddzielnej bazy na potrzeby tego programu.");
        }
    }
}
