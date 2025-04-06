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

class ExitCommandTest {
    private ExitCommand exitCommand;
    private InstrumentList instrumentList;
    private Ui ui;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private UserUtils userUtils;
    private FinanceManager financeManager;

    @BeforeEach
    void setUp() {
        exitCommand = new ExitCommand();
        instrumentList = new InstrumentList();
        ui = new Ui();
        UserList userList = new UserList(ui);
        userUtils = new UserUtils(ui, userList);
        financeManager = new FinanceManager(ui);

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testExecutePrintsGoodbyeMessage() {
        // Execute the command
        exitCommand.execute(instrumentList, ui, userUtils, financeManager);

        // Capture output and check if the goodbye message is printed
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("bye bye"), "ExitCommand should print a goodbye message.");
    }

    @Test
    void testIsExitReturnsTrue() {
        assertTrue(exitCommand.isExit(), "ExitCommand should return true for isExit().");
    }
}
