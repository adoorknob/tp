package ui;

import exceptions.incorrectAddInstrumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    void parse_validInput_parsedCorrectly() {
        String validInput = "Guitar|PAC112VM|2000";
        String[] intendedOutput = {"Guitar", "PAC112VM", "2000"};
        try {
            String[] output = commandParser.separateNMY(validInput);
            assertArrayEquals(output, intendedOutput);
        } catch (incorrectAddInstrumentException e) {
            throw new RuntimeException("Failed to parse input", e);
        }
    }

    @Test
    void parse_invalidInstrument_errorMessage() {
        String invalidInput = "Guitar|1999";

        try {
            commandParser.separateNMY(invalidInput);
            fail("Expected incorrectAddInstrumentException to be thrown");
        } catch (incorrectAddInstrumentException e) {
            assertEquals("Input doesn't look right: Input instrument is invalid-> add [Instrument]|[Model]|[Year]", e.getMessage());
        }
    }

    @Test
    void parse_invalidYear_errorMessage() {
        String invalidInput = "Guitar|PAC112VM|hehe";

        try {
            commandParser.separateNMY(invalidInput);
            fail("Expected IOException to be thrown");
        } catch (incorrectAddInstrumentException e) {
            assertEquals("Input doesn't look right: Input year is invalid-> add [Instrument]|[Model]|[Year]", e.getMessage());
        }
    }
}
