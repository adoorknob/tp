package commands.datetime;

import finance.FinanceManager;
import instrument.Instrument;
import instrument.InstrumentList;
import org.junit.jupiter.api.Test;
import ui.Ui;
import user.UserList;
import user.UserUtils;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ExtendCommandTest {

    static class StubUi extends Ui {
        public boolean wasPrinted = false;

        @Override
        public void printInstrumentList(ArrayList<Instrument> list) {
            wasPrinted = true;
        }
    }

    static class StubInstrumentList extends InstrumentList {
        public int receivedIndex = -1;
        public LocalDate receivedDate = null;

        @Override
        public void extendInstrumentTo(int index, LocalDate newDate) {
            receivedIndex = index;
            receivedDate = newDate;
        }

        @Override
        public ArrayList<Instrument> getList() {
            return new ArrayList<>();
        }
    }

    @Test
    void execute_validInput_shouldCallExtendAndPrintList() {
        // Arrange
        String input = "0 to: 31/12/2025";
        ExtendCommand extendCommand = new ExtendCommand(input);
        StubInstrumentList stubList = new StubInstrumentList();
        StubUi stubUi = new StubUi();
        UserList dummyUserList = new UserList(stubUi);
        UserUtils dummyUserUtils = new UserUtils(stubUi, dummyUserList);
        FinanceManager dummyFinanceManager = new FinanceManager(stubUi);

        // Act
        extendCommand.execute(stubList, stubUi, dummyUserUtils, dummyFinanceManager);

        // Assert
        assertEquals(0, stubList.receivedIndex);
        assertEquals(LocalDate.of(2025, 12, 31), stubList.receivedDate);
        assertTrue(stubUi.wasPrinted, "Instrument list should be printed");
    }

    @Test
    void isExit_shouldReturnFalse() {
        ExtendCommand extendCommand = new ExtendCommand("0 to: 31/12/2025");
        assertFalse(extendCommand.isExit());
    }
}
