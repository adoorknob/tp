package exceptions;

public class incorrectAddInstrumentException extends EmptyDescriptionException {
    public incorrectAddInstrumentException(String message) {
        super(message + "-> add [Instrument]|[Model]|[Year]");
    }
}
