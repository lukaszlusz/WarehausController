package lukaszlusz.sql;

import org.junit.Test;

import static org.junit.Assert.*;

public class DBConnectorMySQLTest {
    @Test
    public void createURL() throws Exception {
        DBConnectorMySQL dbConnectorMySQL = new DBConnectorMySQL("localhost", "test", "3306", "user", "password");
        assertEquals("jdbc:mysql://localhost:3306/test", dbConnectorMySQL.createURL());
    }

}