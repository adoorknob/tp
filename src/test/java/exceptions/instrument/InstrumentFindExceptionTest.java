package exceptions.instrument;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InstrumentFindExceptionTest {

    @Test
    void testExceptionMessage() {
        String inputMessage = "Instrument not found!";
        InstrumentFindException exception = new InstrumentFindException(inputMessage);

        // Check if the exception message is exactly as expected
        String expectedMessage = "Instrument not found!";
        assertEquals(expectedMessage, exception.getMessage(), "Exception message should be as expected.");
    }

    @Test
    void testIsInstanceOfRuntimeException() {
        InstrumentFindException exception = new InstrumentFindException("Error");

        // Check if it is an instance of RuntimeException
        assertTrue(exception instanceof RuntimeException, "Exception should be an instance of RuntimeException.");
    }
}
