package commands;

import commands.finance.FinanceCommand;
import finance.FinanceManager;
import instrument.InstrumentList;
import ui.Ui;
import user.UserList;
import user.UserUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FinanceCommandTest {
    private FinanceCommand financeCommand;
    private FinanceManager financeManager;
    private InstrumentList instrumentList;
    private Ui ui;
    private UserList userList;
    private UserUtils userUtils;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        instrumentList = new InstrumentList();
        ui = new Ui();
        userList = new UserList(ui);
        userUtils = new UserUtils(ui, userList);
        financeManager = new FinanceManager(ui);
        System.setOut(new PrintStream(outputStreamCaptor));

    }

    @Test
    void testAddAmount() {
        financeCommand = new FinanceCommand("add: 500");
        financeCommand.execute(instrumentList, ui, userUtils, financeManager);
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Received payment"), "Output should contain the received amount.");
        assertTrue(output.contains("500"), "Output should include the correct amount added.");
    }

    @Test
    void testSubtractAmount() {
        financeCommand = new FinanceCommand("subtract: 200");
        financeCommand.execute(instrumentList, ui, userUtils, financeManager);
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Transferred payment of:"), "Output should contain the payment amount.");
        assertTrue(output.contains("200"), "Output should include the correct amount subtracted.");
    }

    @Test
    void testGetTotalCash() {
        financeManager.inflowPayment(1000);
        financeCommand = new FinanceCommand("get");
        financeCommand.execute(instrumentList, ui, userUtils, financeManager);
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Total Amount is "), "Output should contain the total cash amount.");
        assertTrue(output.contains("1000"), "Output should include the correct total cash.");
    }

    @Test
    void testHelpCommand() {
        financeCommand = new FinanceCommand("help");
        financeCommand.execute(instrumentList, ui, userUtils, financeManager);
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("add:"), "Output should list the add command.");
        assertTrue(output.contains("subtract:"), "Output should list the subtract command.");
        assertTrue(output.contains("get"), "Output should list the get command.");
    }

    @Test
    void testInvalidCommand() {
        financeCommand = new FinanceCommand("invalidCommand");
        financeCommand.execute(instrumentList, ui, userUtils, financeManager);
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("No matching command"), "Output should indicate no matching command.");
    }


    @Test
    void testIsExit() {
        financeCommand = new FinanceCommand("get");
        assertFalse(financeCommand.isExit(), "isExit should return false for FinanceCommand.");
    }
}
