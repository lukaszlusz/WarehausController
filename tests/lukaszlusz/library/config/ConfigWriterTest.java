package lukaszlusz.library.config;

import lukaszlusz.library.config.DbInfo;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ConfigWriterTest {
    @Test
    public void WRITE_DB_INFO() throws Exception {
        DbInfo dbInfo = new DbInfo("127.0.0.1", "test", "3306", "username", "password");
        String filepath = "./TestResources/writerTest.xml";
        ConfigWriter.FILEPATH = filepath;
        ConfigWriter.WRITE_DB_INFO(dbInfo);
        ConfigReader configReader = ConfigReader.getInstance();
        configReader.filepath = filepath;
        DbInfo dbInfo2 = configReader.getDbInfo();
        assertTrue(dbInfo.equals(dbInfo2));

        File file = new File(filepath);
        assertTrue(file.delete());
    }

}