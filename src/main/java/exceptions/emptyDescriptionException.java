package exceptions;

public class emptyDescriptionException extends RuntimeException {
    public emptyDescriptionException(String message) {
        super(message  + " doesn't look right");
    }
}
