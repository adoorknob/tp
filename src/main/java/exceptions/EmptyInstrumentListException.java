package exceptions;

public class EmptyInstrumentListException extends RuntimeException {
    public EmptyInstrumentListException(String message) {
        super(message);
    }
}
