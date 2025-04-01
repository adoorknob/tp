package commands;

import instrument.InstrumentList;
import parser.Parser;
import ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExitCommandTest {
    private ExitCommand exitCommand;
    private InstrumentList instrumentList;
    private Ui ui;
    private Parser parser;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        exitCommand = new ExitCommand();
        instrumentList = new InstrumentList();
        ui = new Ui();
        parser = new Parser();

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testExecutePrintsGoodbyeMessage() {
        // Execute the command
        exitCommand.execute(instrumentList, ui);

        // Capture output and check if the goodbye message is printed
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("bye bye"), "ExitCommand should print a goodbye message.");
    }

    @Test
    void testIsExitReturnsTrue() {
        assertTrue(exitCommand.isExit(), "ExitCommand should return true for isExit().");
    }
}
