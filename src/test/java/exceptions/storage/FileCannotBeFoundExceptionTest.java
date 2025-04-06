package exceptions.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileCannotBeFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        String inputMessage = "config.txt";
        FileCannotBeFoundException exception = new FileCannotBeFoundException(inputMessage);

        // Check if the exception message is formatted correctly
        String expectedMessage = "File could not be found: config.txt";
        assertEquals(expectedMessage, exception.getMessage(), "Exception message should be formatted correctly.");
    }

    @Test
    void testIsInstanceOfRuntimeException() {
        FileCannotBeFoundException exception = new FileCannotBeFoundException("Error");

        // Check if it is an instance of RuntimeException
        assertTrue(exception instanceof RuntimeException, "Exception should be an instance of RuntimeException.");
    }
}
