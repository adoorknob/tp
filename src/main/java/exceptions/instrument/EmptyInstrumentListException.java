package exceptions.instrument;

public class EmptyInstrumentListException extends RuntimeException {
    public EmptyInstrumentListException(String message) {
        super(message);
    }
}
