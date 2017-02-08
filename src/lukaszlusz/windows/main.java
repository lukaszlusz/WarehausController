package lukaszlusz.windows;

import lukaszlusz.library.Exceptions.DatabaseConnectionException;
import lukaszlusz.library.Exceptions.DatabaseOperationException;
import lukaszlusz.library.Exceptions.FileReadException;
import lukaszlusz.library.Exceptions.FileWriteException;
import lukaszlusz.library.config.ConfigReader;
import lukaszlusz.library.config.ConfigWriter;
import lukaszlusz.library.sql.Database;
import lukaszlusz.library.sql.TableCreator;
import lukaszlusz.library.config.DbInfo;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {
        DbInfo dbInfo = loadDbInfo();
        Database database = new Database(dbInfo);
        checkTables(database, dbInfo.dbName);
        MainTable mainTable = createMainTable(database);

        JFrame frame = new JFrame();
        JScrollPane jScrollPane = new JScrollPane(mainTable);
        frame.add(jScrollPane);
        frame.setPreferredSize(new Dimension(1920, 1080));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static DbInfo loadDbInfo() {
        DbInfo dbInfo;
        try {
            dbInfo = ConfigReader.getInstance().getDbInfo();
        } catch (FileReadException | FileNotFoundException e) {
            e.printStackTrace();
            new ErrorBox(e.getMessage());
            DbInfoInput dbInfoInput = new DbInfoInput();
            dbInfoInput.waitUntilDataAvailable();
            dbInfo = dbInfoInput.getDbInfo();
            try {
                ConfigWriter.WRITE_DB_INFO(dbInfo);
            } catch (FileWriteException e1) {
                e1.printStackTrace();
                new ErrorBox(e1.getMessage());
            }
        }
        return dbInfo;
    }

    private static void checkTables(Database database, String dbName) {
        try {
            if (!TableCreator.ARE_TABLES_CREATED(database.getConnection(), dbName)) {
                DialogResult dialogResult = Dialog.YES_NO("Brak potrzebnych tabel w bazie danych, czy chcesz jes utworzyć?");
                switch (dialogResult) {
                    case YES: TableCreator.CREATE_TABLES(database.getConnection()); break;
                    case NO: System.exit(-1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DatabaseConnectionException e) {
            e.printStackTrace();
            DialogResult dialogResult = Dialog.RETRY_CLOSE(e.getMessage());
            switch (dialogResult) {
                case RETRY: checkTables(database, dbName); break;
                case CLOSE: System.exit(-1);
            }
        } catch (DatabaseOperationException e) {
            e.printStackTrace();
            new ErrorBox(e.getMessage());
        }
    }

    private static MainTable createMainTable(Database database) {
        MainTable mainTable = null;
        try {
            mainTable = new MainTable(database.getMainTableData(), database.getStatuses(), database.getCategories());
        } catch (DatabaseConnectionException | SQLException e) {
            e.printStackTrace();
            DialogResult dialogResult = Dialog.RETRY_CLOSE(e.getMessage());
            switch (dialogResult) {
                case RETRY: mainTable = createMainTable(database); break;
                case CLOSE: System.exit(-1);
            }
        }
        return mainTable;
    }
}
