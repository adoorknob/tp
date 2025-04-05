package exceptions;

import commands.instrument.AddInstrumentCommand;
import exceptions.instrument.IncorrectDescriptionException;
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
            c.addInstrument(instrumentList, new Ui());
            fail("Expected EmptyDescriptionException to be thrown");
        } catch (IncorrectDescriptionException e) {
            // Adjust the message to match what your code actually throws
            assertEquals("Input format is invalid:" +
                    " missing fields-> add [Instrument]|[Model]|[Year]", e.getMessage());
        }
    }

    @Test
    void testAddInstrumentValid() {
        InstrumentList instrumentList = new InstrumentList();
        String validInput = "Flute|Yamaha|2023";
        AddInstrumentCommand c = new AddInstrumentCommand(validInput, false);
        assertDoesNotThrow(() -> c.addInstrument(instrumentList, new Ui()));
    }
}
