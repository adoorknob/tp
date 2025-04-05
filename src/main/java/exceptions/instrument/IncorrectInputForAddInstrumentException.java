package exceptions.instrument;

public class IncorrectInputForAddInstrumentException extends IncorrectDescriptionException {
    public IncorrectInputForAddInstrumentException(String message) {
        super(message + "-> add [Instrument]|[Model]|[Year]");
    }
}
