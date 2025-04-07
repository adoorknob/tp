package commands.instrument;


import commands.datetime.ExtendCommand;
import exceptions.instrument.IncorrectReserveInstrumentException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

class ExtendCommandTest {
    private static LocalDate todayDate = LocalDate.now();
    private static LocalDate pastDate = LocalDate.now().minus(6, ChronoUnit.MONTHS);
    private static LocalDate futureDate = LocalDate.now().plus(6, ChronoUnit.MONTHS);
    private static LocalDate furtherDate = LocalDate.now().plus(8, ChronoUnit.MONTHS);
    private static LocalDate invalidDate = LocalDate.parse("1599-01-01");
    private static String validInstNum = "1";
    private static String invalidinstNum = "5";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private AddInstrumentCommand addInstrumentCommand;
    private ReserveCommand reserveCommand;
    private ExtendCommand extendCommand;
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

        reserveCommand = new ReserveCommand("1" + " from: " + todayDate + " to: " + futureDate);
        reserveCommand.execute(instrumentList, ui, userUtils, financeManager);
        reserveCommand = new ReserveCommand("2" + " from: " + todayDate + " to: " + futureDate);
        reserveCommand.execute(instrumentList, ui, userUtils, financeManager);
        reserveCommand = new ReserveCommand("3" + " from: " + todayDate + " to: " + futureDate);
        reserveCommand.execute(instrumentList, ui, userUtils, financeManager);
    }

    @Test
    void testExtendInstrument() {
        extendCommand = new ExtendCommand(validInstNum + " to: " + furtherDate);
        extendCommand.execute(instrumentList, ui, userUtils, financeManager);
        assertTrue(instrumentList.getList().get(0).isRented(), "Instrument attribute `isRented` should remain true");
        assertEquals(todayDate, instrumentList.getList().get(0).getRentedFrom(),
                "Instrument `rentedFrom` should remain");
        assertEquals(furtherDate, instrumentList.getList().get(0).getRentedTo(),
                "Instrument `rentedTo` should be updated");
    }

    @Test
    void testOutOfBoundsInstrument() {
        extendCommand = new ExtendCommand(invalidinstNum + " to: " + furtherDate);
        try {
            extendCommand.execute(instrumentList, ui, userUtils, financeManager);
            fail("Exception should have been thrown");
        } catch (IncorrectReserveInstrumentException e) {
            assertTrue(e.getMessage().contains("Instrument number out of bounds: " + invalidinstNum));
        }
    }

    @Test
    void testInvalidEndDate() {
        extendCommand = new ExtendCommand(validInstNum + " to: " + pastDate);
        try {
            extendCommand.execute(instrumentList, ui, userUtils, financeManager);
            fail("Exception should have been thrown");
        } catch (IncorrectReserveInstrumentException e) {
            assertTrue(e.getMessage().contains("New return date cannot be earlier" +
                    " than previous return date"));
        }
    }

    @Test
    void testIsExit() {
        extendCommand = new ExtendCommand(validInstNum + " to: " + furtherDate);
        assertFalse(extendCommand.isExit());
    }
}
