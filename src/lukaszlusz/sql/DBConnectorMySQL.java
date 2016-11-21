package lukaszlusz.sql;


public class DBConnectorMySQL extends DBConnector {

    public DBConnectorMySQL(String address, String dbName, String port, String user, String password) {
        super(address, dbName, port, user, password, "com.mysql.jdbc.Driver");
    }

    @Override
    protected String createURL() {
        String URL = "jdbc:mysql://";
        URL += address + ":" + port + "/" + dbName;
        return URL;
    }



}
