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

}