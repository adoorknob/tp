package commands.instrument;

import exceptions.instrument.EmptyInstrumentListException;
import finance.FinanceManager;
import instrument.Guitar;
import instrument.Instrument;
import instrument.InstrumentList;
import org.junit.jupiter.api.Test;
import ui.Ui;
import user.UserList;
import user.UserUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ListCommandTest {
    Ui ui = new Ui();
    UserList userList = new UserList(ui);
    UserUtils userUtils = new UserUtils(ui, userList);
    FinanceManager financeManager = new FinanceManager(ui);

    boolean isSubcommandListPrinted = false;
    boolean isStockListPrinted = false;
    boolean isFilteredListPrinted = false;

    class StubUi extends Ui {
        @Override
        public void printListSubcommandList() {
            isSubcommandListPrinted = true;
        }

        @Override
        public void printStockList(ArrayList<Instrument> instrumentList) {
            isStockListPrinted = true;
        }

        @Override
        public void printFilteredList(ArrayList<Instrument> instrumentList, String filter, String searchTerm) {
            isFilteredListPrinted = true;
        }
    }

    @Test
    public void testListCommand_emptyList_throwsException() {
        ListCommand command = new ListCommand("stock");

        try {
            command.execute(new InstrumentList(), ui, userUtils, financeManager);
            fail("Expected EmptyInstrumentListException to be thrown");
        } catch (EmptyInstrumentListException e) {
            assertEquals("List is empty, let's add some instruments :)", e.getMessage());
        }
    }

    @Test
    void searchByFilter_help_assertListPrint() {
        StubUi stubUi = new StubUi();
        ListCommand command = new ListCommand("help");
        InstrumentList instrumentList = new InstrumentList();
        instrumentList.addInstrument(new Guitar("Guitar", "Yamaha", 2000));

        command.execute(instrumentList, stubUi, userUtils, financeManager);

        assertTrue(isSubcommandListPrinted, "List was not printed");
    }

    @Test
    void searchByFilter_stock_assertListPrint() {
        StubUi stubUi = new StubUi();
        ListCommand command = new ListCommand("stock");
        InstrumentList instrumentList = new InstrumentList();
        instrumentList.addInstrument(new Guitar("Guitar", "Yamaha", 2000));

        command.execute(instrumentList, stubUi, userUtils, financeManager);

        assertTrue(isStockListPrinted, "List was not printed");
    }

    @Test
    void searchByFilter_filter_assertListPrint() {
        StubUi stubUi = new StubUi();
        ListCommand command = new ListCommand("filter by: name guitar");
        InstrumentList instrumentList = new InstrumentList();
        instrumentList.addInstrument(new Guitar("Guitar", "Yamaha", 2000));

        command.execute(instrumentList, stubUi, userUtils, financeManager);

        assertTrue(isFilteredListPrinted, "List was not printed");
    }

    @Test
    void isExit_returnFalse() {
        ListCommand command = new ListCommand("exit");
        assertFalse(command.isExit(), "Command should not be exit.");
    }
}
