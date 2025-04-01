package commands;

import exceptions.EmptyInstrumentListException;
import instrument.InstrumentList;
import org.junit.jupiter.api.Test;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    public void testListCommandEmptyList() {
        ListCommand command = new ListCommand();

        try {
            command.execute(new InstrumentList(), new Ui());
        } catch (EmptyInstrumentListException e) {
            assertEquals("List is empty, let's add some instruments :)", e.getMessage());
        }
    }
}
