package exceptions.instrument;


public class IncorrectReserveInstrumentException extends EmptyDescriptionException {
    public IncorrectReserveInstrumentException(String message) {
        super(message + "\nReserve command -> `reserve [instrument]` " +
                "\nor\n`reserve [instrument] from: [date] to: [date]`");
    }
}


