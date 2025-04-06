package commands;

import commands.instrument.AddInstrumentCommand;
import exceptions.instrument.IncorrectAddInstrumentException;
import instrument.InstrumentList;
import instrument.Instrument;
import user.UserUtils;
import user.UserList;
import finance.FinanceManager;
import ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AddInstrumentCommandTest {
    private AddInstrumentCommand addInstrumentCommand;
    private InstrumentList instrumentList;
    private Ui ui;
    private UserList userList;
    private UserUtils userUtils;
    private FinanceManager financeManager;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        instrumentList = new InstrumentList();
        ui = new Ui();
        userList = new UserList(ui);
        userUtils = new UserUtils(ui, userList);
        financeManager = new FinanceManager();

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testExecuteAddsInstrumentToList() {
        // Given an AddInstrumentCommand with a valid instrument input
        addInstrumentCommand = new AddInstrumentCommand("Guitar|Fender|2023", false);

        // Execute the command
        Instrument output = addInstrumentCommand.createInstrument(instrumentList, ui);
        instrumentList.addInstrument(output);

        // Verify that the instrument was added
        assertEquals(1, instrumentList.getList().size(), "Instrument list size should increase by 1.");
    }

    @Test
    void testExecutePrintsInstrumentList() {
        // Given an AddInstrumentCommand
        addInstrumentCommand = new AddInstrumentCommand("Piano|Yamaha|2022", false);

        // Execute the command
        Instrument instrument = addInstrumentCommand.createInstrument(instrumentList, ui);
        instrumentList.addInstrument(instrument);
        ui.printInstrumentList(instrumentList.getList());

        String output = outputStreamCaptor.toString().trim();

        assertTrue(output.contains("Piano"), "Printed list should contain the newly added instrument.");
    }

    @Test
    void testIsExitReturnsFalse() {
        addInstrumentCommand = new AddInstrumentCommand("Violin|Stradivarius|1700", false);
        assertFalse(addInstrumentCommand.isExit(), "AddInstrumentCommand should not trigger exit.");
    }

    @Test
    void testInvalidLateModelDate() {
        addInstrumentCommand = new AddInstrumentCommand("Piano|Stradivarius|3200", false);
        assertThrows(IncorrectAddInstrumentException.class, () ->
                addInstrumentCommand.createInstrument(instrumentList, ui));
    }

    @Test
    void testNegativeModelDate() {
        addInstrumentCommand = new AddInstrumentCommand("Piano|Stradivarius|-320", false);
        assertThrows(IncorrectAddInstrumentException.class, () ->
                addInstrumentCommand.createInstrument(instrumentList, ui));
    }

    @Test
    void testInvalidEarlyModelDate() {
        addInstrumentCommand = new AddInstrumentCommand("Piano|Stradivarius|1", false);
        assertThrows(IncorrectAddInstrumentException.class, () ->
                addInstrumentCommand.createInstrument(instrumentList, ui));
    }

    @Test
    void testExecute() {
        addInstrumentCommand = new AddInstrumentCommand(
                "Guitar | yamaha | 2011 | false | false | null | null | Unassigned | 0",
                true);
        addInstrumentCommand.execute(instrumentList,ui, userUtils, financeManager);
        assertEquals(1, instrumentList.getList().size(), "Instrument list size should increase by 1.");
    }

    @Test
    void testCorruptedStorageExecute() {
        addInstrumentCommand = new AddInstrumentCommand(
                "Guitar | yamaha | -500 | false | false | null | null | Unassigned | 0",
                true);
        addInstrumentCommand.execute(instrumentList,ui, userUtils, financeManager);
        assertEquals(1, instrumentList.getList().size(), "Instrument list size should increase by 1.");
    }


}
