package exceptions.finance;

import exceptions.instrument.IncorrectDescriptionException;

public class IncorrectFinanceInstructionException extends IncorrectDescriptionException {
    public IncorrectFinanceInstructionException(String message) {
        super(message + "\nRefer to `finance help` for list of finance instructions");
    }
}
