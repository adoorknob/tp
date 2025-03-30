package instrument;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GuitarTest {

    @Test
    void rent_rentGuitar_expectTrue() {
        Guitar guitar = new Guitar("Guitar","Yamaha",2000);
        guitar.rent();
        assertTrue(guitar.isRented());
    }

    @Test
    void unrent_unrentGuitar_expectFalse() {
        Guitar guitar = new Guitar("Guitar","Yamaha",2000);
        guitar.rent();
        guitar.unrent();
        assertFalse(guitar.isRented());
    }

    @Test
    void toString_rentedGuitar_expectCorrectString() {
        Guitar guitar = new Guitar("Guitar","Yamaha",2000);
        guitar.rent();
        assertEquals("Guitar | Yamaha | 2000 | X", guitar.toString());
    }

    @Test
    void toString_unrentedGuitar_expectCorrectString() {
        Guitar guitar = new Guitar("Guitar","Yamaha",2000);
        guitar.rent();
        guitar.unrent();
        assertEquals("Guitar | Yamaha | 2000 | O", guitar.toString());
    }

    @Test
    void playInstrument_beingPlayed_expectGuitarSounds() {
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        assertEquals("Guitar Sounds", guitar.playInstrument());
    }
}
