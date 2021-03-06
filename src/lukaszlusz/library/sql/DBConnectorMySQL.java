package lukaszlusz.library.sql;

import lukaszlusz.library.config.DbInfo;

public class DBConnectorMySQL extends DBConnector {

    public DBConnectorMySQL(DbInfo dbInfo) {
        super(dbInfo, "com.mysql.jdbc.Driver");
    }

    @Override
    protected String createURL() {
        String URL = "jdbc:mysql://";
        URL += dbInfo.address + ":" + dbInfo.port + "/" + dbInfo.dbName;
        return URL;
    }



}
