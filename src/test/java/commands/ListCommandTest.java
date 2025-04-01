package commands;

import exceptions.EmptyInstrumentListException;
import instrument.InstrumentList;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void setUp() {

    }
    @Test
    public void testListCommandEmptyList() {
        ListCommand command = new ListCommand();

        try {
            command.execute(new InstrumentList(), ui, userUtils);
            fail("Expected EmptyInstrumentListException to be thrown");
        } catch (EmptyInstrumentListException e) {
            assertEquals("List is empty, let's add some instruments :)", e.getMessage());
        }
    }
}
