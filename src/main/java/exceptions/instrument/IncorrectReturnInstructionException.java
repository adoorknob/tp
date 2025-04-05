package exceptions.instrument;

public class IncorrectReturnInstructionException extends RuntimeException {
    public IncorrectReturnInstructionException(String message) {
        super(message + "\nReturn command -> `return [instrument]` ");
    }
}
