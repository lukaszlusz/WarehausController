package lukaszlusz.library.config;

import lukaszlusz.library.config.DbInfo;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigReaderTest {
    @Test
    public void correctXML() throws Exception {
        ConfigReader configReader = ConfigReader.getInstance();
        configReader.filepath = "./TestResources/correct.xml";
        DbInfo dbInfo = new DbInfo("127.0.0.1", "test", "3306", "username", "password");
        assertTrue(dbInfo.equals(configReader.getDbInfo()));
    }

}