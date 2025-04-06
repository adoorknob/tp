package commands.instrument;

import exceptions.instrument.IncorrectReturnInstructionException;
import finance.FinanceManager;
import instrument.Instrument;
import instrument.InstrumentList;
import org.junit.jupiter.api.Test;
import ui.Ui;
import user.UserUtils;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReturnCommandTest {

    static class StubInstrument extends Instrument {
        private final boolean overdue;

        StubInstrument(boolean overdue) {
            super("SampleName", "SampleModel", 2000);
            this.overdue = overdue;
        }

        @Override
        public String playInstrument() {
            return "";
        }

        @Override
        public boolean isOverDue() {
            return overdue;
        }
    }

    static class StubInstrumentList extends InstrumentList {
        Instrument instrument;
        boolean returned = false;

        public StubInstrumentList(Instrument instrument) {
            this.instrument = instrument;
        }

        @Override
        public Instrument getInstrument(int index) {
            return instrument;
        }

        @Override
        public void returnInstrument(int index) {
            returned = true;
        }

        @Override
        public ArrayList<Instrument> getList() {
            return new ArrayList<>();
        }
    }

    static class StubFinanceManager extends FinanceManager {
        boolean rentalPaid = false;
        boolean overduePaid = false;

        public StubFinanceManager(Ui ui) {
            super(ui);
        }

        @Override
        public void rentalPayment(Instrument instrument, LocalDate date) {
            rentalPaid = true;
        }

        @Override
        public void overduePayment(Instrument instrument, LocalDate date) {
            overduePaid = true;
        }
    }

    static class StubUi extends Ui {
        boolean printed = false;

        @Override
        public void printInstrumentList(ArrayList<Instrument> list) {
            printed = true;
        }
    }

    @Test
    void execute_validReturn_shouldTriggerAllActions() {
        StubInstrument instrument = new StubInstrument(false);
        StubInstrumentList stubList = new StubInstrumentList(instrument);
        StubUi stubUi = new StubUi();
        StubFinanceManager stubFinance = new StubFinanceManager(stubUi);
        UserUtils dummyUserUtils = new UserUtils(stubUi, null);

        ReturnCommand cmd = new ReturnCommand("0");

        cmd.execute(stubList, stubUi, dummyUserUtils, stubFinance);

        assertTrue(stubFinance.rentalPaid, "Rental payment should be made");
        assertFalse(stubFinance.overduePaid, "Overdue payment should not be made");
        assertTrue(stubList.returned, "Instrument should be returned");
        assertTrue(stubUi.printed, "Instrument list should be printed");
    }

    @Test
    void execute_overdueInstrument_shouldTriggerOverduePayment() {
        StubInstrument instrument = new StubInstrument(true);
        StubInstrumentList stubList = new StubInstrumentList(instrument);
        StubUi stubUi = new StubUi();
        StubFinanceManager stubFinance = new StubFinanceManager(stubUi);
        UserUtils dummyUserUtils = new UserUtils(stubUi, null);

        ReturnCommand cmd = new ReturnCommand("0");

        cmd.execute(stubList, stubUi, dummyUserUtils, stubFinance);

        assertTrue(stubFinance.overduePaid, "Overdue payment should be triggered");
    }

    @Test
    void execute_invalidInput_shouldThrowException() {
        ReturnCommand cmd = new ReturnCommand("invalid"); // not a number

        StubInstrumentList dummyList = new StubInstrumentList(null);
        StubUi dummyUi = new StubUi();
        StubFinanceManager dummyFinance = new StubFinanceManager(dummyUi);
        UserUtils dummyUserUtils = new UserUtils(dummyUi, null);

        assertThrows(IncorrectReturnInstructionException.class, () ->
                cmd.execute(dummyList, dummyUi, dummyUserUtils, dummyFinance)
        );
    }

    @Test
    void isExit_shouldReturnFalse() {
        ReturnCommand cmd = new ReturnCommand("1");
        assertFalse(cmd.isExit());
    }
}
