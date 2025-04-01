package commands;

import instrument.InstrumentList;
import parser.Parser;
import ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HelpCommandTest {
    private HelpCommand helpCommand;
    private InstrumentList instrumentList;
    private Ui ui;
    private Parser parser;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        helpCommand = new HelpCommand();
        instrumentList = new InstrumentList();
        ui = new Ui();
        parser = new Parser();

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testExecutePrintsHelpMessage() {
        // Run the execute method
        helpCommand.execute(instrumentList, ui);

        // Capture output and check if it contains expected text
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Available Commands:"), "Help message should include 'Available commands'");
    }

    @Test
    void testIsExitReturnsFalse() {
        // Ensure that HelpCommand does not exit the program
        assertFalse(helpCommand.isExit(), "HelpCommand should not trigger exit.");
    }
}
