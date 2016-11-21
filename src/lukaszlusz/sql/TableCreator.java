package lukaszlusz.sql;

import lukaszlusz.config.ConfigReader;
import lukaszlusz.config.DbInfo;

import java.sql.Connection;
import java.sql.Statement;

public class TableCreator {
    public static void CREATE_TABLES() {
        ConfigReader configReader = new ConfigReader();
        DbInfo dbInfo = configReader.getDbInfo();
        DBConnector dbConnector = new DBConnectorMySQL(dbInfo);
        try {
            Connection connection = dbConnector.getConnection();

            Statement statement = connection.createStatement();

            statement.execute("CREATE TABLE Boxes(" +
                    "ID int NOT NULL AUTO_INCREMENT," +
                    "BarCode VARCHAR(30)," +
                    "Description VARCHAR(255)," +
                    " PRIMARY KEY (ID));");

            statement.execute("CREATE TABLE Categories(" +
                    "Category VARCHAR(30)," +
                    " PRIMARY KEY (Category));");

            statement.execute("CREATE TABLE Items(" +
                    "ID int NOT NULL AUTO_INCREMENT," +
                    " Amount int NOT NULL," +
                    " ItemName VARCHAR (30) NOT NULL," +
                    " Category VARCHAR(30)," +
                    " Status VARCHAR(30), " +
                    "Description VARCHAR(255)," +
                    " Box int, PRIMARY KEY (ID))");

            statement.execute("CREATE TABLE Statuses(Status VARCHAR(30)," +
                    " PRIMARY KEY (Status))");

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            dbConnector.closeConnection();
        }
    }
}
