package exceptions;

public class NegativeUsageException extends RuntimeException {
    public NegativeUsageException(String message) {
        super(message+"Please provide a positive number");
    }
    public NegativeUsageException() {
        this("");
    }
}
