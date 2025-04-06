package exceptions.instrument;

public class InvalidExtendDateException extends IncorrectDescriptionException {
    public InvalidExtendDateException(String message) {
        super(message + "New return date cannot be earlier than previous return date");
    }
}
