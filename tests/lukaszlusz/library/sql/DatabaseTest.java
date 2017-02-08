package lukaszlusz.library.sql;

import lukaszlusz.library.config.DbInfo;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {
    @Test
    public void getMainTableQuery() throws Exception {
        Database database = new Database(new DbInfo("localhost", "test", "3306", "user", "password"));

        assertEquals("SELECT items.ItemID, items.ItemName AS 'Nazwa', items.Amount AS 'Ilość', items.Category AS 'Kategoria'," +
                " items.Status, items.ItemDescription AS 'Opis przedmiotu', items.BoxID AS 'Kod'" +
                " FROM items;", database.getMainTableQuery());

        database.ItemDescription = false;
        database.Status = false;

        assertEquals("SELECT items.ItemID, items.ItemName AS 'Nazwa', items.Amount AS 'Ilość', items.Category AS 'Kategoria'," +
                " items.BoxID AS 'Kod' FROM items;", database.getMainTableQuery());
    }

}