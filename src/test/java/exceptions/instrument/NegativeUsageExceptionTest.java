package exceptions.instrument;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NegativeUsageExceptionTest {

    @Test
    void negativeUsageException_thrown_expectCorrectErrorMessage() {
        String errorMessage = "Usage is negative. ";
        NegativeUsageException exception = new NegativeUsageException(errorMessage);
        assertEquals(errorMessage + "Please provide a positive number", exception.getMessage());
    }

    @Test
    void negativeUsageException_emptyArgument_shouldThrowException() {
        NegativeUsageException exception = new NegativeUsageException();
        assertEquals("Please provide a positive number", exception.getMessage());
    }
}
