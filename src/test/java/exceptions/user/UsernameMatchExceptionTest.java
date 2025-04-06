package exceptions.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsernameMatchExceptionTest {

    @Test
    void testExceptionMessage() {
        String inputMessage = "Username already exists!";
        UsernameMatchException exception = new UsernameMatchException(inputMessage);

        // Check if the exception message is exactly as expected
        String expectedMessage = "Username already exists!";
        assertEquals(expectedMessage, exception.getMessage(), "Exception message should be formatted correctly.");
    }

    @Test
    void testIsInstanceOfRuntimeException() {
        UsernameMatchException exception = new UsernameMatchException("Error");

        // Check if it is an instance of RuntimeException
        assertTrue(exception instanceof RuntimeException, "Exception should be an instance of RuntimeException.");
    }
}
