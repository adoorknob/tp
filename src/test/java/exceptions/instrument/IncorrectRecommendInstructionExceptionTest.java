package exceptions.instrument;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncorrectRecommendInstrumentExceptionTest {

    @Test
    void testExceptionMessageAppendedProperly() {
        String inputMessage = "Missing instrument type ";
        IncorrectRecommendInstrumentException exception =
                new IncorrectRecommendInstrumentException(inputMessage);

        String expected = "Missing instrument type -> recommend [Instrument]";
        assertEquals(expected, exception.getMessage(), "Exception message should match expected format.");
    }

    @Test
    void testIsInstanceOfIncorrectDescriptionException() {
        IncorrectRecommendInstrumentException exception =
                new IncorrectRecommendInstrumentException("Error ");

        assertTrue(exception instanceof IncorrectDescriptionException, "Should be instance of " +
                "IncorrectDescriptionException");
    }

    @Test
    void testIsInstanceOfRuntimeException() {
        IncorrectRecommendInstrumentException exception =
                new IncorrectRecommendInstrumentException("Error ");

        assertTrue(exception instanceof RuntimeException, "Should be instance of RuntimeException");
    }
}
