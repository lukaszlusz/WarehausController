package lukaszlusz.config;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigReaderTest {
    @Test
    public void correctXML() throws Exception {
        ConfigReader configReader = new ConfigReader();
        configReader.filepath = "./TestResourses/correct.xml";
        DbInfo dbInfo = new DbInfo("127.0.0.1", "test", "3306", "username", "password");
        assertTrue(dbInfo.equals(configReader.getDbInfo()));
    }

}