package exceptions.instrument;

public class InvalidDeleteException extends RuntimeException {
    public InvalidDeleteException(String message) {
        super("Invalid Delete: " + message);
    }
}
