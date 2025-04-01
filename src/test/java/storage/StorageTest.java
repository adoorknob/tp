package storage;

import exceptions.FileCannotBeMadeException;
import org.junit.jupiter.api.Test;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void testLoadOldFileValidFilepath() {
        Storage storage = new Storage(new Ui(), "src/test/resources/test_file.txt");

        assertDoesNotThrow(storage::loadOldFile);
    }

    @Test
    public void testLoadOldFileEmptyFilepath() {
        Storage storage = new Storage(new Ui(), "");
        try {
            storage.loadOldFile();
        } catch (FileCannotBeMadeException e) {
            assertEquals("File cannot be made to ", e.getMessage());
        }
    }

    @Test
    public void testLoadOldFileNoDirectory() {
        Storage storage = new Storage(new Ui(), "test_file.txt");
        assertDoesNotThrow(storage::loadOldFile);
    }
}
