package commands;

import finance.FinanceManager;
import instrument.InstrumentList;
import ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.UserList;
import user.UserUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HelpCommandTest {
    private HelpCommand helpCommand;
    private InstrumentList instrumentList;
    private Ui ui;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private UserUtils userUtils;
    private FinanceManager financeManager;

    @BeforeEach
    void setUp() {
        helpCommand = new HelpCommand();
        instrumentList = new InstrumentList();
        ui = new Ui();
        UserList userList = new UserList(ui);
        userUtils = new UserUtils(ui, userList);
        financeManager = new FinanceManager(ui);

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testExecutePrintsHelpMessage() {
        // Run the execute method
        helpCommand.execute(instrumentList, ui, userUtils, financeManager);

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
