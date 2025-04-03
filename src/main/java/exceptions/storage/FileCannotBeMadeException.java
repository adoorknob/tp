package exceptions.storage;

public class FileCannotBeMadeException extends RuntimeException {
    public FileCannotBeMadeException(String message) {
        super("File cannot be made to " + message);
    }
}
