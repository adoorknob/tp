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

class DefaultCommandTest {
    private DefaultCommand defaultCommand;
    private InstrumentList instrumentList;
    private Ui ui;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private UserUtils userUtils;
    private FinanceManager financeManager;

    @BeforeEach
    void setUp() {
        defaultCommand = new DefaultCommand();
        instrumentList = new InstrumentList();
        ui = new Ui();
        UserList userList = new UserList(ui);
        userUtils = new UserUtils(ui, userList);
        financeManager = new FinanceManager(ui);

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testExecutePrintsErrorMessage() {
        // Execute the command
        defaultCommand.execute(instrumentList, ui, userUtils, financeManager);

        // Capture output and check if the correct error message is printed
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("No matching command found"),
                "DefaultCommand should print an error message for unknown commands.");
    }

    @Test
    void testIsExitReturnsFalse() {
        assertFalse(defaultCommand.isExit(), "DefaultCommand should not trigger exit.");
    }
}
