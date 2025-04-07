package commands.instrument;


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
    private static LocalDate todayDate = LocalDate.now();
    private static LocalDate pastDate = LocalDate.now().minus(6, ChronoUnit.MONTHS);
    private static LocalDate futureDate = LocalDate.now().plus(6, ChronoUnit.MONTHS);
    private static LocalDate invalidDate = LocalDate.parse("1599-01-01");
    private static String validInstNum = "1";
    private static String invalidInstNum = "5";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private AddInstrumentCommand addInstrumentCommand;
    private ReserveCommand reserveCommand;
    private InstrumentList instrumentList;
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
    void testReserveInstrument() {
        reserveCommand = new ReserveCommand(validInstNum + " from: " + todayDate + " to: " + futureDate);
        reserveCommand.execute(instrumentList, ui, userUtils, financeManager);
        assertTrue(instrumentList.getList().get(0).isRented(), "Instrument attribute `isRented` should be true");
        assertEquals(todayDate, instrumentList.getList().get(0).getRentedFrom(),
                "Instrument `rentedFrom` should be updated");
        assertEquals(futureDate, instrumentList.getList().get(0).getRentedTo(),
                "Instrument `rentedFrom` should be updated");
    }

    @Test
    void testOutOfBoundsInstrument() {
        reserveCommand = new ReserveCommand(invalidInstNum + " from: " + todayDate + " to: " + futureDate);
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
        reserveCommand = new ReserveCommand(validInstNum + " from: " + invalidDate + " to: " + futureDate);
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
        reserveCommand = new ReserveCommand(validInstNum + " from: " + todayDate + " to: " + pastDate);
        reserveCommand.execute(instrumentList, ui, userUtils, financeManager);
        int rentedCount = 0;
        ArrayList<Instrument> instruments = instrumentList.getList();
        for (Instrument inst : instruments) {
            rentedCount += (inst.isRented() ? 1 : 0);
        }
        assertEquals(0, rentedCount);
    }

    @Test
    void testIsExit() {
        reserveCommand = new ReserveCommand(validInstNum + " from: " + todayDate + " to: " + futureDate);
        assertFalse(reserveCommand.isExit());
    }
}
