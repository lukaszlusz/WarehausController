package lukaszlusz.config;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigReaderTest {
    @Test
    public void correctXML() throws Exception {
        ConfigReader configReader = new ConfigReader();
        configReader.filepath = "./TestResourses/correct.xml";
        DbInfo dbInfo = new DbInfo();
        dbInfo.address = "127.0.0.1";
        dbInfo.dbName = "test";
        dbInfo.port = "3306";
        dbInfo.user = "username";
        dbInfo.password = "password";
        assertTrue(dbInfo.equals(configReader.getDbInfo()));
    }

}