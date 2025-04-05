package instrument;

import exceptions.instrument.NegativeUsageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PianoTest {

    @Test
    void rent_rentPiano_expectTrue() {
        Piano piano = new Piano("Piano", "Yamaha", 2000);
        piano.rent();
        assertTrue(piano.isRented());
    }

    @Test
    void unrent_unrentPiano_expectFalse() {
        Piano piano = new Piano("Piano", "Yamaha", 2000);
        piano.rent();
        piano.unrent();
        assertFalse(piano.isRented());
    }


    @Test
    void toString_rentedPiano_expectCorrectString() {
        Piano piano = new Piano("Piano", "Yamaha", 2000);
        piano.rent();
        assertEquals("Piano | Yamaha | 2000 | Rented", piano.toString());
    }


    @Test
    void toString_unrentedPiano_expectCorrectString() {
        Piano piano = new Piano("Piano", "Yamaha", 2000);
        piano.rent();
        piano.unrent();
        assertEquals("Piano | Yamaha | 2000 | ", piano.toString());
    }

    @Test
    void playInstrument_beingPlayed_expectPianoSounds() {
        Piano piano = new Piano("Piano", "Yamaha", 2000);
        assertEquals("Piano Sounds", piano.playInstrument());
    }

    @Test
    void getUsage_usedPiano_returnsCorrectValue() {
        Piano piano = new Piano("Piano", "Yamaha", 2000);
        piano.increaseUsage();
        assertEquals(1, piano.getUsage());
    }

    @Test
    void getUsage_unusedPiano_returnsCorrectValue() {
        Piano piano = new Piano("Piano", "Yamaha", 2000);
        assertEquals(0, piano.getUsage());
    }

    @Test
    void incrementUsage_incrementUsage_returnsCorrectValue() {
        Piano piano = new Piano("Piano", "Yamaha", 2000);
        piano.increaseUsage();
        assertEquals(1, piano.getUsage());
    }

    @Test
    void setUsage_positiveUsageSet_returnsCorrectValue() {
        Piano piano = new Piano("Piano", "Yamaha", 2000);
        piano.setUsage(10);
        assertEquals(10, piano.getUsage());
    }

    @Test
    void setUsage_zeroUsageSet_returnsCorrectValue() {
        Piano piano = new Piano("Piano", "Yamaha", 2000);
        piano.setUsage(0);
        assertEquals(0, piano.getUsage());
    }

    @Test
    void setUsage_negativeUsageSet_throwsNegativeUsageException() {
        Piano piano = new Piano("Piano", "Yamaha", 2000);
        assertThrows(NegativeUsageException.class, () -> piano.setUsage(-1));
    }

    @Test
    void toFileEntry_unrentedPiano_returnsCorrectValue() {
        Piano piano = new Piano("Piano", "Yamaha", 2000);
        assertEquals("Piano | Yamaha | 2000 | false | false | null | null | Invalid | 0", piano.toFileEntry());
    }

    @Test
    void toFileEntry_rentedPiano_returnsCorrectValue() {
        Piano piano = new Piano("Piano", "Yamaha", 2000);
        piano.rent();
        assertEquals("Piano | Yamaha | 2000 | true | false | null | null | Invalid | 0", piano.toFileEntry());
    }

}
