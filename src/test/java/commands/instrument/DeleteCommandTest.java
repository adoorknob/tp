package commands.instrument;


import exceptions.instrument.InvalidDeleteException;
import instrument.InstrumentList;
import user.UserUtils;
import user.UserList;
import finance.FinanceManager;
import ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DeleteCommandTest {
    private AddInstrumentCommand addInstrumentCommand;
    private DeleteCommand deleteCommand;
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
        financeManager = new FinanceManager(ui);
        System.setOut(new PrintStream(outputStreamCaptor));

        // Add 3 instruments
        addInstrumentCommand = new AddInstrumentCommand(
                "Guitar | yamaha | 2011 | false | false | null | null | Unassigned | 0",
                true);
        addInstrumentCommand.execute(instrumentList,ui, userUtils, financeManager);
        addInstrumentCommand = new AddInstrumentCommand(
                "Piano | yamaha | 2011 | false | false | null | null | Unassigned | 0",
                true);
        addInstrumentCommand.execute(instrumentList,ui, userUtils, financeManager);
        addInstrumentCommand = new AddInstrumentCommand(
                "Flute | yamaha | 2011 | false | false | null | null | Unassigned | 0",
                true);
        addInstrumentCommand.execute(instrumentList,ui, userUtils, financeManager);
    }

    @Test
    void testDeleteInstrument() {
        deleteCommand = new DeleteCommand("1");
        deleteCommand.execute(instrumentList,ui, userUtils, financeManager);
        assertEquals(2, instrumentList.getList().size(), "Instrument list size should increase by 1.");
    }

    @Test
    void testInvalidDeleteInstrument() {
        deleteCommand = new DeleteCommand("0");
        assertThrows(InvalidDeleteException.class, () -> {
            deleteCommand.execute(instrumentList, ui, userUtils, financeManager);
        });
    }

    @Test
    void testIsExit() {
        deleteCommand = new DeleteCommand("1");
        assertFalse(deleteCommand.isExit());
    }
}
