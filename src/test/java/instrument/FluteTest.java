package instrument;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("Flute | X",flute.toString());
    }

    @Test
    void toString_unrentedFlute_expectCorrectString() {
        Flute flute = new Flute("Flute","Yamaha",2000);
        flute.rent();
        flute.unrent();
        assertEquals("Flute | O",flute.toString());
    }

    @Test
    void playInstrument_beingPlayed_expectFluteSounds(){
        Flute flute = new Flute("Flute","Yamaha",2000);
        assertEquals("Flute Sounds", flute.playInstrument());
    }

}