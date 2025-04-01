package instrument;

import exceptions.NegativeUsageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FluteTest {

    @Test
    void rent_rentFlute_expectTrue() {
        Flute flute = new Flute("Flute","Yamaha",2000);
        flute.rent();
        assertTrue(flute.isRented());
    }

    @Test
    void unrent_unrentFlute_expectFalse() {
        Flute flute = new Flute("Flute","Yamaha",2000);
        flute.rent();
        flute.unrent();
        assertFalse(flute.isRented());
    }


    @Test
    void toString_rentedFlute_expectCorrectString() {
        Flute flute = new Flute("Flute","Yamaha",2000);
        flute.rent();
        assertEquals("Flute | Yamaha | 2000 | Rented",flute.toString());
    }



    @Test
    void toString_unrentedFlute_expectCorrectString() {
        Flute flute = new Flute("Flute","Yamaha",2000);
        flute.rent();
        flute.unrent();
        assertEquals("Flute | Yamaha | 2000 | ",flute.toString());
    }

    @Test
    void playInstrument_beingPlayed_expectFluteSounds(){
        Flute flute = new Flute("Flute","Yamaha",2000);
        assertEquals("Flute Sounds", flute.playInstrument());
    }

    @Test
    void getUsage_usedFlute_returnsCorrectValue(){
        Flute flute = new Flute("Flute","Yamaha",2000);
        flute.increaseUsage();
        assertEquals(1, flute.getUsage());
    }

    @Test
    void getUsage_unusedFlute_returnsCorrectValue(){
        Flute flute = new Flute("Flute","Yamaha",2000);
        assertEquals(0, flute.getUsage());
    }

    @Test
    void incrementUsage_incrementUsage_returnsCorrectValue(){
        Flute flute = new Flute("Flute","Yamaha",2000);
        flute.increaseUsage();
        assertEquals(1, flute.getUsage());
    }

    @Test
    void setUsage_positiveUsageSet_returnsCorrectValue(){
        Flute flute = new Flute("Flute","Yamaha",2000);
        flute.setUsage(10);
        assertEquals(10, flute.getUsage());
    }

    @Test
    void setUsage_zeroUsageSet_returnsCorrectValue(){
        Flute flute = new Flute("Flute","Yamaha",2000);
        flute.setUsage(0);
        assertEquals(0, flute.getUsage());
    }

    @Test
    void setUsage_negativeUsageSet_throwsNegativeUsageException(){
        Flute flute = new Flute("Flute","Yamaha",2000);
        assertThrows(NegativeUsageException.class, () -> flute.setUsage(-1));
    }

    @Test
    void toFileEntry_unrentedFlute_returnsCorrectValue(){
        Flute flute = new Flute("Flute","Yamaha",2000);
        assertEquals("Flute | Yamaha | 2000 | false | false |  |  | 0",flute.toFileEntry());
    }

    @Test
    void toFileEntry_rentedFlute_returnsCorrectValue(){
        Flute flute = new Flute("Flute","Yamaha",2000);
        flute.rent();
        assertEquals("Flute | Yamaha | 2000 | true | false |  |  | 0",flute.toFileEntry());
    }



}
