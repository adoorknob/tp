package exceptions.instrument;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InvalidExtendDateExceptionTest {

    @Test
    void testExceptionMessage() {
        String inputMessage = "Invalid date extension!";
        InvalidExtendDateException exception = new InvalidExtendDateException(inputMessage);

        // Check if the exception message is formatted correctly
        String expectedMessage = "Invalid date extension!New return date cannot be earlier than previous return date";
        assertEquals(expectedMessage, exception.getMessage(), "Exception message " +
                "should be formatted correctly.");
    }

    @Test
    void testIsInstanceOfIncorrectDescriptionException() {
        InvalidExtendDateException exception = new InvalidExtendDateException("Error");

        // Check if it is an instance of IncorrectDescriptionException
        assertTrue(exception instanceof IncorrectDescriptionException, "Exception should be an " +
                "instance of IncorrectDescriptionException.");
    }

    @Test
    void testIsInstanceOfRuntimeException() {
        InvalidExtendDateException exception = new InvalidExtendDateException("Error");

        // Check if it is an instance of RuntimeException
        assertTrue(exception instanceof RuntimeException, "Exception " +
                "should be an instance of RuntimeException.");
    }
}
