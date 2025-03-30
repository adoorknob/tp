package instrument;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PianoTest {

    @Test
    void rent_rentPiano_expectTrue() {
        Piano piano = new Piano("Piano","Yamaha",2000);
        piano.rent();
        assertTrue(piano.isRented());
    }

    @Test
    void unrent_unrentPiano_expectFalse() {
        Piano piano = new Piano("Piano","Yamaha",2000);
        piano.rent();
        piano.unrent();
        assertFalse(piano.isRented());
    }

    @Test
    void toString_rentedPiano_expectCorrectString() {
        Piano piano = new Piano("Piano","Yamaha",2000);
        piano.rent();
        assertEquals("Piano | Yamaha | 2000 | X", piano.toString());
    }

    @Test
    void toString_unrentedPiano_expectCorrectString() {
        Piano piano = new Piano("Piano","Yamaha",2000);
        piano.rent();
        piano.unrent();
        assertEquals("Piano | Yamaha | 2000 | O", piano.toString());
    }


    @Test
    void playInstrument_beingPlayed_expectPianoSounds(){
        Piano piano = new Piano("Piano","Yamaha",2000);
        assertEquals("Piano Sounds", piano.playInstrument());
    }
}
