package exceptions.instrument;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncorrectReturnInstructionExceptionTest {

    @Test
    void testExceptionMessageFormatting() {
        String inputMessage = "Missing instrument to return";
        IncorrectReturnInstructionException exception =
                new IncorrectReturnInstructionException(inputMessage);

        String expectedMessage = "Missing instrument to return\nReturn command -> `return [instrument]` ";
        assertEquals(expectedMessage, exception.getMessage(), "Exception message should be formatted correctly.");
    }

    @Test
    void testIsInstanceOfRuntimeException() {
        IncorrectReturnInstructionException exception =
                new IncorrectReturnInstructionException("Error");

        // Check if it is an instance of RuntimeException
        assertTrue(exception instanceof RuntimeException, "Exception should be an instance of RuntimeException.");
    }
}
