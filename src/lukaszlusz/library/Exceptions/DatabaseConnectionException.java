package lukaszlusz.library.Exceptions;

public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException() {}

    public DatabaseConnectionException(String message) {
        super(message);
    }
}
