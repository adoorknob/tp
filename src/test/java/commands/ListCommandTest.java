package commands;

import exceptions.EmptyInstrumentListException;
import instrument.InstrumentList;
import org.junit.jupiter.api.Test;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ListCommandTest {
    @Test
    public void testListCommandEmptyList() {
        ListCommand command = new ListCommand();

        try {
            command.execute(new InstrumentList(), new Ui());
            fail("Expected EmptyInstrumentListException to be thrown");
        } catch (EmptyInstrumentListException e) {
            assertEquals("List is empty, let's add some instruments :)", e.getMessage());
        }
    }
}
