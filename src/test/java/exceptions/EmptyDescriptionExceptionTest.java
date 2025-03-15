package exceptions;

import instrument.InstrumentList;
import org.junit.jupiter.api.Test;

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
        assertEquals("Input doesn't look right", exception.getMessage());
    }

    @Test
    void testAddInstrumentThrowsException() {
        InstrumentList instrumentList = new InstrumentList();
        String[] invalidInput = {"", "Yamaha", "2023"}; // Empty instrument name

        try {
            instrumentList.addInstrument(invalidInput);
            fail("Expected EmptyDescriptionException to be thrown");
        } catch (EmptyDescriptionException e) {
            assertEquals("event doesn't look right", e.getMessage());
        }
    }

    @Test
    void testAddInstrumentValid() {
        InstrumentList instrumentList = new InstrumentList();
        String[] validInput = {"Flute", "Yamaha", "2023"};

        assertDoesNotThrow(() -> instrumentList.addInstrument(validInput));
    }
}
