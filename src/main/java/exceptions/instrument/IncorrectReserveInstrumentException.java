package exceptions.instrument;

public class IncorrectReserveInstrumentException extends EmptyDescriptionException {
    public IncorrectReserveInstrumentException(String message) {
        super(message + "-> add [Instrument Number]|[YYYY-MM-DD]");
    }
}
