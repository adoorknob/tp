package exceptions.instrument;

public class IncorrectAddInstrumentException extends IncorrectDescriptionException {
    public IncorrectAddInstrumentException(String message) {
        super(message + " -> add [Instrument]|[Model]|[Year]");
    }
}
