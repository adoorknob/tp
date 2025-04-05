package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasingStandardiserTest {

    @Test
    void casingStandardiser_inputLengthOf1_expectCapitilised() {
        String testString = "t";
        assertEquals("T", CasingStandardiser.casingStandardise(testString));
    }

    @Test
    void casingStandardiser_inputLengthOf5_expectFirstCharacterOnlyCapitilised() {
        String testString = "aBcdE";
        assertEquals("Abcde", CasingStandardiser.casingStandardise(testString));
    }

    @Test
    void casingStandardiser_inputLengthOf0_expectNothing() {
        String testString = "";
        assertEquals("", CasingStandardiser.casingStandardise(testString));
    }

    @Test
    void casingStandardiser_inputIsinteger_expectNoChange() {
        String testString = "2";
        assertEquals("2", CasingStandardiser.casingStandardise(testString));
    }


}
