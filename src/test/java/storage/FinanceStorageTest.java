package storage;

import exceptions.storage.FileCannotBeMadeException;
import finance.FinanceManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Ui;
import user.UserList;
import user.UserUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FinanceStorageTest {
    Ui ui;
    UserList userList;
    UserUtils userUtils;

    static class StubUi extends Ui {
        boolean fileCreated = false;

        @Override
        public void printCreatingFile(String file) {
            fileCreated = true;
        }
    }

    @BeforeEach
    void setUp() {
        ui = new Ui();
        userList = new UserList(ui);
        userUtils = new UserUtils(ui, userList);
    }

    @Test
    public void testLoadOldFile_validFilepath_success() {
        FinanceStorage storage = new FinanceStorage(ui, "src/test/resources/test_file.txt");

        assertDoesNotThrow(storage::loadOldFile);
    }

    @Test
    public void testLoadOldFile_emptyFilepath_exception() {
        FinanceStorage storage = new FinanceStorage(ui, "");
        try {
            storage.loadOldFile();
        } catch (FileCannotBeMadeException e) {
            assertEquals("File cannot be made to ", e.getMessage());
        }
    }

    @Test
    public void testLoadOldFile_noDirectory_success() {
        FinanceStorage storage = new FinanceStorage(ui, "test_file.txt");
        assertDoesNotThrow(storage::loadOldFile);
    }

    @Test
    public void testSaveCurrentFile() throws IOException {
        StubUi stubUi = new StubUi();
        FinanceStorage storage = new FinanceStorage(stubUi, "test/test_output.txt");
        storage.loadOldFile();

        FinanceManager financeManager = new FinanceManager(ui);
        financeManager.setTotalCash(1000);
        storage.saveCurrentFile(financeManager);

        assertTrue(stubUi.fileCreated, "File creation was not triggered");
        assertEquals("Cash: 1000\n\n", storage.outputText, "Output was not correctly saved.");
    }
}
