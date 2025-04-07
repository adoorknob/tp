package exceptions.instrument;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InvalidDeleteExceptionTest {

    @Test
    void testExceptionMessage() {
        String inputMessage = "Instrument ID not found!";
        InvalidDeleteException exception = new InvalidDeleteException(inputMessage);

        // Check if the exception message is exactly as expected
        String expectedMessage = "Invalid Delete function: " + inputMessage + "\nUse: delete [integer]";
        assertEquals(expectedMessage, exception.getMessage(), "Exception message should be formatted correctly.");
    }

    @Test
    void testIsInstanceOfRuntimeException() {
        InvalidDeleteException exception = new InvalidDeleteException("Error");

        // Check if it is an instance of RuntimeException
        assertTrue(exception instanceof RuntimeException, "Exception should be an instance of RuntimeException.");
    }
}

