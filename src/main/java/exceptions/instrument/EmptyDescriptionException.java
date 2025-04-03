package exceptions.instrument;

public class EmptyDescriptionException extends RuntimeException {
    public EmptyDescriptionException(String message) {
        super("Input doesn't look right: " + message);
    }
}
