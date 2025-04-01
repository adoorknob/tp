package exceptions;

import commands.AddInstrumentCommand;
import instrument.InstrumentList;
import org.junit.jupiter.api.Test;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class EmptyDescriptionExceptionTest {
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

        AddInstrumentCommand c = new AddInstrumentCommand(invalidInput);
        try {
            c.addInstrument(instrumentList, new Ui());
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
        AddInstrumentCommand c = new AddInstrumentCommand(validInput);
        assertDoesNotThrow(() -> c.addInstrument(instrumentList, new Ui()));
    }
}
