package exceptions.instrument;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncorrectReserveInstrumentExceptionTest {

    @Test
    void testExceptionMessageFormatting() {
        String inputMessage = "Missing instrument name";
        IncorrectReserveInstrumentException exception =
                new IncorrectReserveInstrumentException(inputMessage);

        String expectedMessage = "Missing instrument name\n" +
                "Reserve command -> `reserve [instrument]` \n" +
                "or\n" +
                "`reserve [instrument] from: [date] to: [date]`";

        // Check for exact message match
        assertEquals(expectedMessage, exception.getMessage(), "Message should match the " +
                "expected format exactly.");
    }

    @Test
    void testIsInstanceOfIncorrectDescriptionException() {
        IncorrectReserveInstrumentException exception =
                new IncorrectReserveInstrumentException("Error");

        assertTrue(exception instanceof IncorrectDescriptionException, "Exception should be an " +
                "instance of IncorrectDescriptionException.");
    }

    @Test
    void testIsInstanceOfRuntimeException() {
        IncorrectReserveInstrumentException exception =
                new IncorrectReserveInstrumentException("Error");

        assertTrue(exception instanceof RuntimeException, "Exception should be an " +
                "instance of RuntimeException.");
    }
}
