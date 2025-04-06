package commands;


import commands.datetime.ExtendCommand;
import commands.instrument.AddInstrumentCommand;
import commands.instrument.ReserveCommand;
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

class ExtendCommandTest {
    private AddInstrumentCommand addInstrumentCommand;
    private ReserveCommand reserveCommand;
    private ExtendCommand extendCommand;
    private InstrumentList instrumentList;
    private Ui ui;
    private UserList userList;
    private UserUtils userUtils;
    private FinanceManager financeManager;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private static LocalDate TODAYDATE = LocalDate.now();
    private static LocalDate PASTDATE = LocalDate.now().minus(6, ChronoUnit.MONTHS);
    private static LocalDate FUTUREDATE = LocalDate.now().plus(6, ChronoUnit.MONTHS);
    private static LocalDate FURTHERDATE = LocalDate.now().plus(8, ChronoUnit.MONTHS);
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
    void testExtendInstrument() {
        extendCommand = new ExtendCommand(VALIDINSTNUM + " to: " + FURTHERDATE);
        extendCommand.execute(instrumentList, ui, userUtils, financeManager);
        assertTrue(instrumentList.getList().get(0).isRented(), "Instrument attribute `isRented` should remain true");
        assertEquals(TODAYDATE, instrumentList.getList().get(0).getRentedFrom(), "Instrument `rentedFrom` should remain");
        assertEquals(FURTHERDATE, instrumentList.getList().get(0).getRentedTo(), "Instrument `rentedTo` should be updated");
    }

    @Test
    void testOutOfBoundsInstrument() {
        extendCommand = new ExtendCommand(INVALIDINSTNUM + " to: " + FURTHERDATE);
        extendCommand.execute(instrumentList, ui, userUtils, financeManager);
        ArrayList<Instrument> instruments = instrumentList.getList();
        int furtherDateCount = 0;
        for (Instrument inst : instruments) {
            furtherDateCount += (inst.getRentedTo() == FURTHERDATE ? 1 : 0);
        }
        assertEquals(0, furtherDateCount);
    }

    @Test
    void testInvalidEndDate() {
        extendCommand = new ExtendCommand(VALIDINSTNUM + " to: " + PASTDATE);
        extendCommand.execute(instrumentList, ui, userUtils, financeManager);
        int pastDateCount = 0;
        ArrayList<Instrument> instruments = instrumentList.getList();
        for (Instrument inst : instruments) {
            pastDateCount += (inst.getRentedTo() == PASTDATE ? 1 : 0);
        }
        assertEquals(0, pastDateCount);
    }

    @Test
    void testIsExit() {
        extendCommand = new ExtendCommand(VALIDINSTNUM + " to: " + FURTHERDATE);
        assertFalse(extendCommand.isExit());
    }
}
