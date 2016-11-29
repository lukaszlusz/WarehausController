package lukaszlusz.sql;

import lukaszlusz.GUI.ErrorBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TableCreator {
    private static String[] tablesNames = {"boxes", "categories", "items", "statuses"};

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

        if (counter == tablesNames.length) return true;
        else return false;
    }

    public static void CREATE_TABLES(Connection connection) {
        try {
            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE Boxes(" +
                    " ID int NOT NULL AUTO_INCREMENT," +
                    " Localization VARCHAR(30)," +
                    " Description VARCHAR(255)," +
                    " PRIMARY KEY (ID));");

            statement.execute("CREATE TABLE Categories(" +
                    " ID int NOT NULL AUTO_INCREMENT," +
                    " Category VARCHAR(30)," +
                    " PRIMARY KEY (ID));");

            statement.execute("CREATE TABLE Items(" +
                    " ID int NOT NULL AUTO_INCREMENT," +
                    " Amount int NOT NULL," +
                    " ItemName VARCHAR (30) NOT NULL," +
                    " CategoryID int," +
                    " StatusID int," +
                    " Description VARCHAR(255)," +
                    " BoxID int NOT NULL," +
                    " PRIMARY KEY (ID));");

            statement.execute("CREATE TABLE Statuses(" +
                    " ID int NOT NULL AUTO_INCREMENT," +
                    " Status VARCHAR(30)," +
                    " PRIMARY KEY (ID));");

        } catch (SQLException e) {
            e.printStackTrace();
            new ErrorBox("Błąd podczas tworzenia tabel bazy danych.\n" +
                    "Baza nie może zawierać tabel o nazwach: Boxes, Categories, Items, Statuses,\n " +
                    "ponieważ takie tabele są używane przez ten program. Zaleca się utworzenie oddzielnej bazy na potrzeby tego programu.");
        }
    }
}
