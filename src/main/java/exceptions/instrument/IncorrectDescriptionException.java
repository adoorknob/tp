package exceptions.instrument;

public class IncorrectDescriptionException extends RuntimeException {
    public IncorrectDescriptionException(String message) {
        super("Input doesn't look right: " + message);
    }
}
