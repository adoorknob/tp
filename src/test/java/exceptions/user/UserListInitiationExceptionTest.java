package exceptions.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserListInitiationExceptionTest {

    @Test
    void testExceptionMessage() {
        String inputMessage = "Error initializing user list!";
        UserListInitiationException exception = new UserListInitiationException(inputMessage);

        // Check if the exception message is exactly as expected
        String expectedMessage = "Error initializing user list!";
        assertEquals(expectedMessage, exception.getMessage(), "Exception message should be formatted correctly.");
    }

    @Test
    void testIsInstanceOfRuntimeException() {
        UserListInitiationException exception = new UserListInitiationException("Error");

        // Check if it is an instance of RuntimeException
        assertTrue(exception instanceof RuntimeException, "Exception should be an instance of RuntimeException.");
    }
}
