package commands;


import commands.instrument.AddInstrumentCommand;
import commands.instrument.ReserveCommand;
import commands.instrument.ReturnCommand;
import instrument.Instrument;
import instrument.InstrumentList;
import user.UserUtils;
import user.UserList;
import finance.FinanceManager;
import ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ReturnCommandTest {
    private AddInstrumentCommand addInstrumentCommand;
    private ReserveCommand reserveCommand;
    private ReturnCommand returnCommand;
    private InstrumentList instrumentList;
    private Ui ui;
    private UserList userList;
    private UserUtils userUtils;
    private FinanceManager financeManager;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private static LocalDate TODAYDATE = LocalDate.now();
    private static LocalDate PASTDATE = LocalDate.now().minus(6, ChronoUnit.MONTHS);
    private static LocalDate FUTUREDATE = LocalDate.now().plus(6, ChronoUnit.MONTHS);
    private static LocalDate INVALIDDATE = LocalDate.parse("1599-01-01");
    private static String VALIDINSTNUM = "1";
    private static String INVALIDINSTNUM = "5";

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

        reserveCommand = new ReserveCommand("1" + " from: " + TODAYDATE + " to: " + FUTUREDATE);
        reserveCommand.execute(instrumentList, ui, userUtils, financeManager);
        reserveCommand = new ReserveCommand("2" + " from: " + TODAYDATE + " to: " + FUTUREDATE);
        reserveCommand.execute(instrumentList, ui, userUtils, financeManager);
        reserveCommand = new ReserveCommand("3" + " from: " + TODAYDATE + " to: " + FUTUREDATE);
        reserveCommand.execute(instrumentList, ui, userUtils, financeManager);
    }

    @Test
    void testReturnInstrument() {
        assertTrue(instrumentList.getList().get(0).isRented(), "Instrument attribute `isRented` should be true");
        returnCommand = new ReturnCommand(VALIDINSTNUM);
        returnCommand.execute(instrumentList, ui, userUtils, financeManager);
        assertFalse(instrumentList.getList().get(0).isRented(), "Instrument attribute `isRented` should be false");
        assertEquals(1, instrumentList.getList().get(0).getUsage());
    }

    @Test
    void testOutOfBoundsInstrument() {
        returnCommand = new ReturnCommand(INVALIDINSTNUM);
        returnCommand.execute(instrumentList, ui, userUtils, financeManager);
        ArrayList<Instrument> instruments = instrumentList.getList();
        int rentedCount = 0;
        for (Instrument inst : instruments) {
            rentedCount += (inst.isRented() ? 1 : 0);
        }
        assertEquals(3, rentedCount);
    }

}
