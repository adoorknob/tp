package exceptions.instrument;

public class IncorrectRecommendInstrumentException extends IncorrectDescriptionException {
    public IncorrectRecommendInstrumentException(String message) {
        super(message + "-> recommend [Instrument]");
    }
}
