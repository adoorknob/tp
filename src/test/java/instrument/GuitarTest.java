package instrument;

import exceptions.instrument.NegativeUsageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GuitarTest {

    @Test
    void rent_rentGuitar_expectTrue() {
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        guitar.rent();
        assertTrue(guitar.isRented());
    }

    @Test
    void unrent_unrentGuitar_expectFalse() {
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        guitar.rent();
        guitar.unrent();
        assertFalse(guitar.isRented());
    }


    @Test
    void toString_rentedGuitar_expectCorrectString() {
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        guitar.rent();
        assertEquals("Guitar | Yamaha | 2000 | Rented", guitar.toString());
    }


    @Test
    void toString_unrentedGuitar_expectCorrectString() {
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        guitar.rent();
        guitar.unrent();
        assertEquals("Guitar | Yamaha | 2000", guitar.toString());
    }

    @Test
    void playInstrument_beingPlayed_expectGuitarSounds() {
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        assertEquals("Guitar Sounds", guitar.playInstrument());
    }

    @Test
    void getUsage_usedGuitar_returnsCorrectValue() {
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        guitar.increaseUsage();
        assertEquals(1, guitar.getUsage());
    }

    @Test
    void getUsage_unusedGuitar_returnsCorrectValue() {
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        assertEquals(0, guitar.getUsage());
    }

    @Test
    void incrementUsage_incrementUsage_returnsCorrectValue() {
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        guitar.increaseUsage();
        assertEquals(1, guitar.getUsage());
    }

    @Test
    void setUsage_positiveUsageSet_returnsCorrectValue() {
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        guitar.setUsage(10);
        assertEquals(10, guitar.getUsage());
    }

    @Test
    void setUsage_zeroUsageSet_returnsCorrectValue() {
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        guitar.setUsage(0);
        assertEquals(0, guitar.getUsage());
    }

    @Test
    void setUsage_negativeUsageSet_throwsNegativeUsageException() {
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        assertThrows(NegativeUsageException.class, () -> guitar.setUsage(-1));
    }

    @Test
    void toFileEntry_unrentedGuitar_returnsCorrectValue() {
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        assertEquals("Guitar | Yamaha | 2000 | false | false | null | null | Invalid | 0", guitar.toFileEntry());
    }

    @Test
    void toFileEntry_rentedGuitar_returnsCorrectValue() {
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        guitar.rent();
        assertEquals("Guitar | Yamaha | 2000 | true | false | null | null | Invalid | 0", guitar.toFileEntry());
    }


}
