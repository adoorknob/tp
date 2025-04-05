package storage;

import exceptions.storage.FileCannotBeMadeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Ui;
import user.User;
import user.UserList;
import user.UserUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    Ui ui;
    UserList userList;
    UserUtils userUtils;

    @BeforeEach
    void setUp() {
        ui = new Ui();
        userList = new UserList(ui);
        userUtils = new UserUtils(ui, userList);
    }

    @Test
    public void testLoadOldFileValidFilepath() {
        Storage storage = new Storage(ui, userUtils, "src/test/resources/test_file.txt");

        assertDoesNotThrow(storage::loadOldFile);
    }

    @Test
    public void testLoadOldFileEmptyFilepath() {
        Storage storage = new Storage(ui, userUtils, "");
        try {
            storage.loadOldFile();
        } catch (FileCannotBeMadeException e) {
            assertEquals("File cannot be made to ", e.getMessage());
        }
    }

    @Test
    public void testLoadOldFileNoDirectory() {
        Storage storage = new Storage(ui, userUtils, "test_file.txt");
        assertDoesNotThrow(storage::loadOldFile);
    }
}
