package exceptions.instrument;

public class OutOfRangeException extends RuntimeException {
    public OutOfRangeException(String message) {
        super("Input out of Bounds: " + message);
    }
}
