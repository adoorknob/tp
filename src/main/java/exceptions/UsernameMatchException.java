package exceptions;

public class UsernameMatchException extends RuntimeException {
    public UsernameMatchException(String message) {
        super(message);
    }
}
