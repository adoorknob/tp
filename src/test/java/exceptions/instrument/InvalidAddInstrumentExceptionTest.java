package exceptions.instrument;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InvalidAddInstrumentExceptionTest {

    @Test
    void testExceptionMessage() {
        String inputMessage = "Invalid instrument addition attempt!";
        InvalidAddInstrumentException exception = new InvalidAddInstrumentException(inputMessage);

        // Check if the exception message is exactly as expected
        String expectedMessage = "Invalid instrument addition attempt!";
        assertEquals(expectedMessage, exception.getMessage(), "Exception message should be as expected.");
    }

    @Test
    void testIsInstanceOfRuntimeException() {
        InvalidAddInstrumentException exception = new InvalidAddInstrumentException("Error");

        // Check if it is an instance of RuntimeException
        assertTrue(exception instanceof RuntimeException, "Exception should be an instance of RuntimeException.");
    }
}
