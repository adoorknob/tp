package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
            String[] output = parser.separateNMY(validInput);
            assertArrayEquals(output, intendedOutput);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse input", e);
        }
    }

    @Test
    void parse_invalidInstrument_errorMessage() {
        String invalidInput = "Guitar|1999";

        try {
            parser.separateNMY(invalidInput);
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            assertEquals("Input instrument is invalid", e.getMessage());
        }
    }

    @Test
    void parse_invalidYear_errorMessage() {
        String invalidInput = "Guitar|PAC112VM|hehe";

        try {
            parser.separateNMY(invalidInput);
            fail("Expected IOException to be thrown");
        } catch (IOException e) {
            assertEquals("Input year is invalid", e.getMessage());
        }
    }
}
