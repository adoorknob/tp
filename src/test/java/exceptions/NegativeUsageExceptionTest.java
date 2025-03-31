package exceptions;

import exceptions.NegativeUsageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NegativeUsageExceptionTest {

    @Test
    void NegativeUsageException_thrown_expectCorrectErrorMessage() {
        String errorMessage = "Usage is negative. ";
        NegativeUsageException exception = new NegativeUsageException(errorMessage);
        assertEquals(errorMessage+"Please provide a positive number", exception.getMessage());
    }

    @Test
    void NegativeUsageException_emptyArgument_shouldThrowException() {
        NegativeUsageException exception = new NegativeUsageException();
        assertEquals("Please provide a positive number", exception.getMessage());
    }
}
