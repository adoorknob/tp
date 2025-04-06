package exceptions.storage;

public class CorruptStorageException extends RuntimeException {
    public CorruptStorageException(String message) {
        super("Storage is corrupted: " + message);
    }
}
