package lukaszlusz.config;

public class DbInfo {
    public String address;
    public String dbName;
    public String port;
    public String user;
    public String password;


    public DbInfo() {}

    public DbInfo(String address, String dbName, String port, String user, String password) {
        this.address = address;
        this.dbName = dbName;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public boolean isCorrect() {
        return isNumeric(port);
    }

    boolean isNumeric(String string)
    {
        return string.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DbInfo dbInfo = (DbInfo) o;

        if (address != null ? !address.equals(dbInfo.address) : dbInfo.address != null) return false;
        if (dbName != null ? !dbName.equals(dbInfo.dbName) : dbInfo.dbName != null) return false;
        if (port != null ? !port.equals(dbInfo.port) : dbInfo.port != null) return false;
        if (user != null ? !user.equals(dbInfo.user) : dbInfo.user != null) return false;
        return password != null ? password.equals(dbInfo.password) : dbInfo.password == null;

    }

}
