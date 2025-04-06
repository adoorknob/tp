package exceptions.finance;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class IncorrectFinanceInstructionExceptionTest {
    @Test
    void testExceptionMessageFormatting() {
        String inputMessage = "Invalid finance command format";
        IncorrectFinanceInstructionException exception =
                new IncorrectFinanceInstructionException(inputMessage);

        String expectedMessage = "Invalid finance command format\nRefer to `finance help` " +
                "for list of finance instructions";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testExceptionIsInstanceOfParent() {
        IncorrectFinanceInstructionException exception =
                new IncorrectFinanceInstructionException("Error");

        assertTrue(exception instanceof RuntimeException);
        assertTrue(exception instanceof IncorrectFinanceInstructionException);
    }
}
