package exceptions.finance;

import exceptions.instrument.EmptyDescriptionException;

public class IncorrectFinanceInstructionException extends EmptyDescriptionException {
    public IncorrectFinanceInstructionException(String message) {
        super(message + "\nRefer to `finance help` for list of finance instructions");
    }
}
