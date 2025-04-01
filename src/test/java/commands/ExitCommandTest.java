package commands;

import instrument.InstrumentList;
import parser.Parser;
import ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.UserList;
import user.UserUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExitCommandTest {
    private ExitCommand exitCommand;
    private InstrumentList instrumentList;
    private Ui ui;
    private Parser parser;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private UserUtils userUtils;
    private UserList userList;

    @BeforeEach
    void setUp() {
        exitCommand = new ExitCommand();
        instrumentList = new InstrumentList();
        ui = new Ui();
        parser = new Parser();
        userList = new UserList(ui);
        userUtils = new UserUtils(ui, userList);

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testExecutePrintsGoodbyeMessage() {
        // Execute the command
        exitCommand.execute(instrumentList, ui, userUtils);

        // Capture output and check if the goodbye message is printed
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("bye bye"), "ExitCommand should print a goodbye message.");
    }

    @Test
    void testIsExitReturnsTrue() {
        assertTrue(exitCommand.isExit(), "ExitCommand should return true for isExit().");
    }
}
