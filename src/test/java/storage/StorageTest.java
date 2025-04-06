package storage;

import exceptions.storage.FileCannotBeMadeException;
import instrument.Guitar;
import instrument.InstrumentList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Ui;
import user.UserList;
import user.UserUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    Ui ui;
    UserList userList;
    UserUtils userUtils;

    static class StubUi extends Ui {
        boolean directoryCreated = false;
        boolean fileCreated = false;

        @Override
        public void printCreatingDirectory(String dir) {
            directoryCreated = true;
        }

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
        Storage storage = new Storage(ui, userUtils, "src/test/resources/test_file.txt");

        assertDoesNotThrow(storage::loadOldFile);
    }

    @Test
    public void testLoadOldFile_emptyFilepath_exception() {
        Storage storage = new Storage(ui, userUtils, "");
        try {
            storage.loadOldFile();
        } catch (FileCannotBeMadeException e) {
            assertEquals("File cannot be made to ", e.getMessage());
        }
    }

    @Test
    public void testLoadOldFile_noDirectory_success() {
        Storage storage = new Storage(ui, userUtils, "test_file.txt");
        assertDoesNotThrow(storage::loadOldFile);
    }

    @Test
    public void testSaveCurrentFile() throws IOException {
        StubUi stubUi = new StubUi();
        Storage storage = new Storage(stubUi, userUtils, "test/test_output.txt");
        storage.loadOldFile();

        Guitar guitar = new Guitar("Guitar", "ModelName", 2000);
        InstrumentList instrumentList = new InstrumentList();
        instrumentList.addInstrument(guitar);

        storage.saveCurrentFile(instrumentList);

        assertTrue(stubUi.directoryCreated, "Directory creation was not triggered");
        assertTrue(stubUi.fileCreated, "File creation was not triggered");

        assertEquals(guitar.toFileEntry() + "\n", storage.outputText,
                "Output was not written correctly");
    }
}
