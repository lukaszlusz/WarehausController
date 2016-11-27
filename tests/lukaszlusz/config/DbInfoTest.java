package lukaszlusz.config;

import org.junit.Test;

import static org.junit.Assert.*;

public class DbInfoTest {
    @Test
    public void equals() throws Exception {
        DbInfo dbInfo1 = new DbInfo();
        DbInfo dbInfo2 = new DbInfo();
        dbInfo1.dbName = "dbname";
        dbInfo1.address = "localhost";
        dbInfo2.dbName = "dbname";
        dbInfo2.address = "localhost";
        assertTrue(dbInfo1.equals(dbInfo2));

        dbInfo1.port = "5565";
        dbInfo2.port = "8888";
        assertFalse(dbInfo1.equals(dbInfo2));
    }

    @Test
    public void isNumeric() throws Exception {
        DbInfo dbInfo = new DbInfo();
        String string = "0125";
        assertTrue(dbInfo.isNumeric(string));
        string = "054s54";
        assertFalse(dbInfo.isNumeric(string));
        string = "05-04";
        assertFalse(dbInfo.isNumeric(string));
        string = "5565.64.";
        assertFalse(dbInfo.isNumeric(string));
    }
}