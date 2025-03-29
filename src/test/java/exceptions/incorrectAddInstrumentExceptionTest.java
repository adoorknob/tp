package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class incorrectAddInstrumentExceptionTest {
    // Test to check that the exception message is correctly formatted
    @Test
    void testExceptionMessage() {
        String errorMessage = "Invalid input";
        incorrectAddInstrumentException exception = new incorrectAddInstrumentException(errorMessage);

        // Check if the exception message contains the provided message and the expected format
        String expectedMessage = "Input doesn't look right: " + errorMessage + "-> add [Instrument]|[Model]|[Year]";
        assertEquals(expectedMessage, exception.getMessage());
    }

    // Test to check if the exception inherits from EmptyDescriptionException
    @Test
    void testExceptionInheritance() {
        incorrectAddInstrumentException exception = new incorrectAddInstrumentException("Test");

        // Check if the exception is an instance of EmptyDescriptionException
        assertTrue(exception instanceof EmptyDescriptionException);
    }

    // Test to ensure that the exception is correctly thrown
    @Test
    void testExceptionIsThrown() {
        String errorMessage = "Invalid input";

        // Check if the exception is thrown with the expected message
        Exception exception = assertThrows(incorrectAddInstrumentException.class, () -> {
            throw new incorrectAddInstrumentException(errorMessage);
        });

        String expectedMessage = "Input doesn't look right: " + errorMessage + "-> add [Instrument]|[Model]|[Year]";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
