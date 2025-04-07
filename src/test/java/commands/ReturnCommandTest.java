package commands;


import commands.instrument.AddInstrumentCommand;
import commands.instrument.ReserveCommand;
import commands.instrument.ReturnCommand;
import exceptions.instrument.IncorrectReturnInstructionException;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ReturnCommandTest {
    private static LocalDate todayDate = LocalDate.now();
    private static LocalDate pastDate = LocalDate.now().minus(6, ChronoUnit.MONTHS);
    private static LocalDate futureDate = LocalDate.now().plus(6, ChronoUnit.MONTHS);
    private static LocalDate furtherDate = LocalDate.now().plus(8, ChronoUnit.MONTHS);
    private static LocalDate invalidDate = LocalDate.parse("1599-01-01");
    private static String validInstNum = "1";
    private static String invalidInstNum = "5";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private AddInstrumentCommand addInstrumentCommand;
    private ReserveCommand reserveCommand;
    private ReturnCommand returnCommand;
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
        reserveCommand = new ReserveCommand("3" + " from: " + futureDate + " to: " + furtherDate);
        reserveCommand.execute(instrumentList, ui, userUtils, financeManager);
    }

    @Test
    void testReturnInstrument() {
        assertTrue(instrumentList.getList().get(0).isRented(), "Instrument attribute `isRented` should be true");
        returnCommand = new ReturnCommand(validInstNum);
        returnCommand.execute(instrumentList, ui, userUtils, financeManager);
        assertFalse(instrumentList.getList().get(0).isRented(), "Instrument attribute `isRented` should be false");
        assertEquals(1, instrumentList.getList().get(0).getUsage());
    }

    @Test
    void testOutOfBoundsInstrument() {
        returnCommand = new ReturnCommand(invalidInstNum);
        assertThrows(IncorrectReturnInstructionException.class, () ->
                returnCommand.execute(instrumentList, ui, userUtils, financeManager)
        );
    }

    @Test
    void testInsufficientRentalPeriod() {
        returnCommand = new ReturnCommand("3");
        assertThrows(IncorrectReturnInstructionException.class, () ->
                returnCommand.execute(instrumentList, ui, userUtils, financeManager)
        );
    }

    @Test
    void testIsExit() {
        returnCommand = new ReturnCommand(validInstNum);
        assertFalse(returnCommand.isExit());
    }
}
