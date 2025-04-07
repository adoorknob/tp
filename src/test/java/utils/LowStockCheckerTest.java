package utils;

import commands.instrument.AddInstrumentCommand;
import commands.instrument.ReserveCommand;
import commands.instrument.ReturnCommand;
import finance.FinanceManager;
import instrument.InstrumentList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Ui;
import user.UserList;
import user.UserUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ui.Ui.RED;
import static ui.Ui.RESET;

public class LowStockCheckerTest {

    private InstrumentList instrumentList;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private AddInstrumentCommand addInstrumentCommand;
    private ReserveCommand reserveCommand;
    private ReturnCommand returnCommand;
    private Ui ui;
    private UserList userList;
    private UserUtils userUtils;
    private FinanceManager financeManager;

    @BeforeEach
    void setUp() {

        instrumentList = new InstrumentList();
        ui = new Ui();
        userList = new UserList(ui);
        userUtils = new UserUtils(ui, userList);
        financeManager = new FinanceManager(ui);
        System.setOut(new PrintStream(outputStreamCaptor));

        // Add 3 instruments
        addInstrumentCommand = new AddInstrumentCommand(
                "Guitar | yamaha | 2011 | false | false | null | null | Unassigned | 0",
                true);
        addInstrumentCommand.execute(instrumentList, ui, userUtils, financeManager);
        addInstrumentCommand = new AddInstrumentCommand(
                "Piano | yamaha | 2011 | false | false | null | null | Unassigned | 0",
                true);
        addInstrumentCommand.execute(instrumentList, ui, userUtils, financeManager);
        addInstrumentCommand = new AddInstrumentCommand(
                "Flute | yamaha | 2011 | false | false | null | null | Unassigned | 0",
                true);
        addInstrumentCommand.execute(instrumentList, ui, userUtils, financeManager);
    }

    @Test
    void testLowStockCheck() {
        LowStockChecker.checkAll(instrumentList.getList());
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Guitar" + ": " + RED + "1" + RESET + " available"));
        assertTrue(output.contains("Piano" + ": " + RED + "1" + RESET + " available"));
        assertTrue(output.contains("Flute" + ": " + RED + "1" + RESET + " available"));
    }

    @Test
    void testSufficientStockCheck() {
        int guitarCount = 5;
        // add 5 more guitars to the list
        for (int i = 0; i < guitarCount; i++) {
            addInstrumentCommand = new AddInstrumentCommand(
                    "Guitar | yamaha | 2011 | false | false | null | null | Unassigned | 0",
                    true);
            addInstrumentCommand.execute(instrumentList, ui, userUtils, financeManager);
        }
        LowStockChecker.checkAll(instrumentList.getList());
        String output = outputStreamCaptor.toString();
        assertFalse(output.contains("Guitar" + ": " + RED + "6" + RESET + " available"));
        assertTrue(output.contains("Piano" + ": " + RED + "1" + RESET + " available"));
        assertTrue(output.contains("Flute" + ": " + RED + "1" + RESET + " available"));
    }

    @Test
    void testZeroStockCheck() {
        reserveCommand = new ReserveCommand("1");
        reserveCommand.execute(instrumentList, ui, userUtils, financeManager);
        LowStockChecker.checkAll(instrumentList.getList());
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Guitar" + ": " + RED + "0" + RESET + " available"));
        assertTrue(output.contains("Piano" + ": " + RED + "1" + RESET + " available"));
        assertTrue(output.contains("Flute" + ": " + RED + "1" + RESET + " available"));
    }

}
