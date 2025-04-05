package exceptions;

import exceptions.instrument.IncorrectDescriptionException;
import exceptions.instrument.IncorrectInputForAddInstrumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncorrectInputForAddInstrumentExceptionTest {
    // Test to check that the exception message is correctly formatted
    @Test
    void testExceptionMessage() {
        String errorMessage = "Invalid input";
        IncorrectInputForAddInstrumentException exception = new IncorrectInputForAddInstrumentException(errorMessage);

        // Check if the exception message contains the provided message and the expected format
        String expectedMessage = "Input doesn't look right: " + errorMessage + "-> add [Instrument]|[Model]|[Year]";
        assertEquals(expectedMessage, exception.getMessage());
    }

    // Test to check if the exception inherits from EmptyDescriptionException
    @Test
    void testExceptionInheritance() {
        IncorrectInputForAddInstrumentException exception = new IncorrectInputForAddInstrumentException("Test");

        // Check if the exception is an instance of EmptyDescriptionException
        assertTrue(exception instanceof IncorrectDescriptionException);
    }

    // Test to ensure that the exception is correctly thrown
    @Test
    void testExceptionIsThrown() {
        String errorMessage = "Invalid input";

        // Check if the exception is thrown with the expected message
        Exception exception = assertThrows(IncorrectInputForAddInstrumentException.class, () -> {
            throw new IncorrectInputForAddInstrumentException(errorMessage);
        });

        String expectedMessage = "Input doesn't look right: " + errorMessage + "-> add [Instrument]|[Model]|[Year]";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
