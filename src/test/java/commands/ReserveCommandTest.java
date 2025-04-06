package commands;


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

class ReserveCommandTest {
    private AddInstrumentCommand addInstrumentCommand;
    private ReserveCommand reserveCommand;
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

    private static LocalDate TODAYDATE = LocalDate.now();
    private static LocalDate PASTDATE = LocalDate.now().minus(6, ChronoUnit.MONTHS);
    private static LocalDate FUTUREDATE = LocalDate.now().plus(6, ChronoUnit.MONTHS);
    private static LocalDate INVALIDDATE = LocalDate.parse("1599-01-01");
    private static String VALIDINSTNUM = "1";
    private static String INVALIDINSTNUM = "5";

    @Test
    void testReserveInstrument() {
        reserveCommand = new ReserveCommand(VALIDINSTNUM + " from: " + TODAYDATE + " to: " + FUTUREDATE);
        reserveCommand.execute(instrumentList, ui, userUtils, financeManager);
        assertTrue(instrumentList.getList().get(0).isRented(), "Instrument attribute `isRented` should be true");
        assertEquals(LocalDate.now(), instrumentList.getList().get(0).getRentedFrom(), "Instrument `rentedFrom` should be updated");
        assertEquals(LocalDate.now(), instrumentList.getList().get(0).getRentedFrom(), "Instrument `rentedFrom` should be updated");
    }

    @Test
    void testOutOfBoundsInstrument() {
        reserveCommand = new ReserveCommand(INVALIDINSTNUM + " from: " + TODAYDATE + " to: " + FUTUREDATE);
        reserveCommand.execute(instrumentList, ui, userUtils, financeManager);
        ArrayList<Instrument> instruments = instrumentList.getList();
        int rentedCount = 0;
        for (Instrument inst : instruments) {
            rentedCount += (inst.isRented() ? 1 : 0);
        }
        assertEquals(0, rentedCount);
    }

    @Test
    void testInvalidStartDate() {
        reserveCommand = new ReserveCommand(VALIDINSTNUM + " from: " + INVALIDDATE + " to: " + FUTUREDATE);
        reserveCommand.execute(instrumentList, ui, userUtils, financeManager);
        int rentedCount = 0;
        ArrayList<Instrument> instruments = instrumentList.getList();
        for (Instrument inst : instruments) {
            rentedCount += (inst.isRented() ? 1 : 0);
        }
        assertEquals(0, rentedCount);
    }

    @Test
    void testInvalidEndDate() {
        reserveCommand = new ReserveCommand(VALIDINSTNUM + " from: " + TODAYDATE + " to: " + PASTDATE);
        reserveCommand.execute(instrumentList, ui, userUtils, financeManager);
        int rentedCount = 0;
        ArrayList<Instrument> instruments = instrumentList.getList();
        for (Instrument inst : instruments) {
            rentedCount += (inst.isRented() ? 1 : 0);
        }
        assertEquals(0, rentedCount);
    }

}
