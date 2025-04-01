package exceptions;

public class incorrectReserveInstrumentException extends EmptyDescriptionException {
    public incorrectReserveInstrumentException(String message) {
        super(message + "-> add [Instrument Number]|[YYYY-MM-DD]");
    }
}
