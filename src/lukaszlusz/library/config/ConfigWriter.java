package lukaszlusz.library.config;

import lukaszlusz.library.Exceptions.FileWriteException;

import java.io.IOException;
import java.io.PrintWriter;

public class ConfigWriter {
    static String FILEPATH = "dbconfig.xml";

    public static void WRITE_DB_INFO(DbInfo dbInfo) throws FileWriteException {
        String content = "<?xml version=\"1.0\"?>\n" +
                "<database>\n" +
                "\t<dbinfo id=\"1\">\n" +
                "\t\t<address>"+ dbInfo.address +"</address>\n" +
                "\t\t<dbname>"+ dbInfo.dbName +"</dbname>\n" +
                "\t\t<port>"+ dbInfo.port +"</port>\n" +
                "\t\t<user>"+ dbInfo.user +"</user>\n" +
                "\t\t<password>"+ dbInfo.password +"</password>\n" +
                "\t</dbinfo>\n" +
                "</database>";

        PrintWriter writer = null;
        try{
            writer = new PrintWriter(FILEPATH, "UTF-8");
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileWriteException("Błąd podczas zapisywania pliku z konfiguracją");
        } finally {
            writer.close();
        }
    }
}
