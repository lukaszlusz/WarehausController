package lukaszlusz.library.Exceptions;

public class DatabaseOperationException extends Exception {
    public DatabaseOperationException() {}

    public DatabaseOperationException(String message) {
        super(message);
    }
}
