package exceptions;

import commands.instrument.AddInstrumentCommand;
import exceptions.instrument.IncorrectDescriptionException;
import instrument.InstrumentList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Ui;
import user.UserList;
import user.UserUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class EmptyDescriptionExceptionTest {
    Ui ui;
    UserList userList;
    UserUtils userUtils;

    @BeforeEach
    void setUp() {
        ui = new Ui();
        userList = new UserList(ui);
        userUtils = new UserUtils(ui, userList);
    }

    @Test
    void testEmptyDescriptionException() {
        Exception exception = assertThrows(IncorrectDescriptionException.class, () -> {
            throw new IncorrectDescriptionException("Input");
        });

        // Check if the message is correctly formatted
        assertEquals("Input", exception.getMessage());
    }

    @Test
    void testAddInstrumentThrowsException() {
        InstrumentList instrumentList = new InstrumentList();
        String invalidInput = "Yamaha|2023"; // Missing instrument name, will fail parsing

        AddInstrumentCommand c = new AddInstrumentCommand(invalidInput, false);
        try {
            c.addInstrumentToSession(instrumentList, ui, userUtils);
            fail("Expected RuntimeException to be thrown");
        } catch (IncorrectDescriptionException e) {
            // Adjust the message to match what your code actually throws
            assertEquals("Input format is invalid:" +
                    " missing fields -> add [Instrument]|[Model]|[Year]", e.getMessage());
        }
    }

    @Test
    void testAddInstrumentValid() {
        InstrumentList instrumentList = new InstrumentList();
        String validInput = "Flute|Yamaha|2023";
        AddInstrumentCommand c = new AddInstrumentCommand(validInput, false);
        assertDoesNotThrow(() -> c.createInstrument(instrumentList, ui));
    }


}
