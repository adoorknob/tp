package exceptions;

import commands.AddInstrumentCommand;
import commands.Command;
import instrument.InstrumentList;
import org.junit.jupiter.api.Test;
import ui.Ui;
import user.UserList;
import user.UserUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class EmptyDescriptionExceptionTest {
    private final Ui ui = new Ui();
    private final UserList userList = new UserList(ui);
    private final UserUtils userUtils = new UserUtils(ui, userList);

    @Test
    void testEmptyDescriptionException() {
        Exception exception = assertThrows(EmptyDescriptionException.class, () -> {
            throw new EmptyDescriptionException("Input");
        });

        // Check if the message is correctly formatted
        assertEquals("Input doesn't look right: Input", exception.getMessage());
    }

    @Test
    void testAddInstrumentThrowsException() {
        InstrumentList instrumentList = new InstrumentList();
        String invalidInput = "Yamaha|2023"; // Missing instrument name, will fail parsing

        Command c = new AddInstrumentCommand(invalidInput);
        try {
            c.execute(instrumentList, new Ui(), userUtils);
            fail("Expected EmptyDescriptionException to be thrown");
        } catch (EmptyDescriptionException e) {
            // Adjust the message to match what your code actually throws
            assertEquals("Input doesn't look right: Input format is invalid:" +
                    " missing fields-> add [Instrument]|[Model]|[Year]", e.getMessage());
        }
    }

    @Test
    void testAddInstrumentValid() {
        InstrumentList instrumentList = new InstrumentList();
        String validInput = "Flute|Yamaha|2023";
        Command c = new AddInstrumentCommand(validInput);
        assertDoesNotThrow(() -> c.execute(instrumentList, new Ui(), userUtils));
    }
}
