package exceptions.storage;

public class FileCannotBeFoundException extends RuntimeException {
    public FileCannotBeFoundException(String message) {
        super("File could not be found: " + message);
    }
}
