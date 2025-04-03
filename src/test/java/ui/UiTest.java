package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UiTest {
    private Ui ui;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        ui = new Ui();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testPrintStartMessage() {
        ui.printStartMessage();
        String output = outputStream.toString();
        assertTrue(output.contains("Welcome to"));
        assertTrue(output.contains(Ui.DUKEBOX));
    }

    @Test
    void testPrintCommandList() {
        ui.printCommandList();
        String output = outputStream.toString();
        assertTrue(output.contains("""
            Available Commands:
            help: list all commands
            list: list all instruments
            add: adds a new instrument
            delete: deletes an existing instrument
            reserve: reserves an available instrument
            extend: changes the return date of a reserved instrument
            return: returns a reserved instrument
            user: choose user commands
            recommend: recommends a recommended instrument
            finance: Manage finances: (use -h flag to see commands)
            exit: quit SirDukeBox"""));

    }

    @Test
    void testPrintGoodbye() {
        ui.printGoodbye();
        String output = outputStream.toString();
        assertTrue(output.contains("bye bye"));
    }

    @Test
    void testReadUserInput() {
        String input = "list\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui testUi = new Ui();
        assertEquals("list", testUi.readUserInput());
    }


}
