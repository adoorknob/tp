package instrument;

import exceptions.instrument.NegativeUsageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GenericInstrumentTest {

    @Test
    void rent_rentGeneric_expectTrue() {
        GenericInstrument generic = new GenericInstrument("Generic", "Yamaha", 2000);
        generic.rent();
        assertTrue(generic.isRented());
    }

    @Test
    void unrent_unrentGeneric_expectFalse() {
        GenericInstrument generic = new GenericInstrument("Generic", "Yamaha", 2000);
        generic.rent();
        generic.unrent();
        assertFalse(generic.isRented());
    }


    @Test
    void toString_rentedGeneric_expectCorrectString() {
        GenericInstrument generic = new GenericInstrument("Generic", "Yamaha", 2000);
        generic.rent();
        assertEquals("Generic | Yamaha | 2000 | Rented", generic.toString());
    }


    @Test
    void toString_unrentedGeneric_expectCorrectString() {
        GenericInstrument generic = new GenericInstrument("Generic", "Yamaha", 2000);
        generic.rent();
        generic.unrent();
        assertEquals("Generic | Yamaha | 2000 | ", generic.toString());
    }

    @Test
    void playInstrument_beingPlayed_expectGenericSounds() {
        GenericInstrument generic = new GenericInstrument("Generic", "Yamaha", 2000);
        assertEquals("Generic Sounds", generic.playInstrument());
    }

    @Test
    void getUsage_usedGeneric_returnsCorrectValue() {
        GenericInstrument generic = new GenericInstrument("Generic", "Yamaha", 2000);
        generic.increaseUsage();
        assertEquals(1, generic.getUsage());
    }

    @Test
    void getUsage_unusedGeneric_returnsCorrectValue() {
        GenericInstrument generic = new GenericInstrument("Generic", "Yamaha", 2000);
        assertEquals(0, generic.getUsage());
    }

    @Test
    void incrementUsage_incrementUsage_returnsCorrectValue() {
        GenericInstrument generic = new GenericInstrument("Generic", "Yamaha", 2000);
        generic.increaseUsage();
        assertEquals(1, generic.getUsage());
    }

    @Test
    void setUsage_positiveUsageSet_returnsCorrectValue() {
        GenericInstrument generic = new GenericInstrument("Generic", "Yamaha", 2000);
        generic.setUsage(10);
        assertEquals(10, generic.getUsage());
    }

    @Test
    void setUsage_zeroUsageSet_returnsCorrectValue() {
        GenericInstrument generic = new GenericInstrument("Generic", "Yamaha", 2000);
        generic.setUsage(0);
        assertEquals(0, generic.getUsage());
    }

    @Test
    void setUsage_negativeUsageSet_throwsNegativeUsageException() {
        GenericInstrument generic = new GenericInstrument("Generic", "Yamaha", 2000);
        assertThrows(NegativeUsageException.class, () -> generic.setUsage(-1));
    }

    @Test
    void toFileEntry_unrentedGeneric_returnsCorrectValue() {
        GenericInstrument generic = new GenericInstrument("Generic", "Yamaha", 2000);
        assertEquals("Generic | Yamaha | 2000 | false | false | null | null | Invalid | 0", generic.toFileEntry());
    }

    @Test
    void toFileEntry_rentedGeneric_returnsCorrectValue() {
        GenericInstrument generic = new GenericInstrument("Generic", "Yamaha", 2000);
        generic.rent();
        assertEquals("Generic | Yamaha | 2000 | true | false | null | null | Invalid | 0", generic.toFileEntry());
    }
}
