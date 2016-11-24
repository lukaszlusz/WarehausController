package lukaszlusz.sql;


import lukaszlusz.config.DbInfo;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class TableCreator {
    public static void main(String[] args) {
        DBConnector dbConnector = new DBConnectorMySQL(new DbInfo("localhost", "warehousedb", "3306", "test2", ""));
        try {
            CREATE_TABLES(dbConnector.getConnection());
            System.out.print(ARE_TABLES_CREATED(dbConnector.getConnection(), "warehousedb"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String[] tablesNames = {"boxes", "categories", "items", "statuses"};

    public static  boolean ARE_TABLES_CREATED(Connection connection, String DbName)
    {
        Field TABLE_NAME = field("TABLE_NAME");
        Table information_schema = table("information_schema.tables");
        Field table_schema = field("table_schema");

        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
        Result<Record> result = create.select(TABLE_NAME).from(information_schema).where(table_schema.equal(DbName)).fetch();
        Integer counter = 0;
        for (Record r : result)
        {
            for (String s : tablesNames) {
                if (s.equals(r.getValue(TABLE_NAME))) ++counter;
            }
        }

        if (counter == tablesNames.length) return true;
        else return false;
    }

    public static void CREATE_TABLES(Connection connection) {
        try {
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
                    " Box int, PRIMARY KEY (ID));");

            statement.execute("CREATE TABLE Statuses(Status VARCHAR(30)," +
                    " PRIMARY KEY (Status));");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
