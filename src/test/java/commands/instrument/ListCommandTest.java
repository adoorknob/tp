package commands.instrument;

import exceptions.instrument.EmptyInstrumentListException;
import finance.FinanceManager;
import instrument.InstrumentList;
import org.junit.jupiter.api.Test;
import ui.Ui;
import user.UserList;
import user.UserUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ListCommandTest {
    Ui ui = new Ui();
    UserList userList = new UserList(ui);
    UserUtils userUtils = new UserUtils(ui, userList);
    FinanceManager financeManager = new FinanceManager(ui);


    @Test
    public void testListCommandEmptyList() {
        ListCommand command = new ListCommand("stock");

        try {
            command.execute(new InstrumentList(), ui, userUtils, financeManager);
            fail("Expected EmptyInstrumentListException to be thrown");
        } catch (EmptyInstrumentListException e) {
            assertEquals("List is empty, let's add some instruments :)", e.getMessage());
        }
    }
}
