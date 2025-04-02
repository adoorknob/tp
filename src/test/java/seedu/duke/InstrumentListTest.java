package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import instrument.Guitar;
import instrument.Instrument;
import instrument.InstrumentList;
import org.junit.jupiter.api.Test;

public class InstrumentListTest {

    @Test
    void add_addInstrument_expectEquals() {
        InstrumentList instrumentList = new InstrumentList();
        Instrument newInstrument = new Guitar("Guitar", "Yamaha", 2004);
        instrumentList.addInstrument(newInstrument);
        assertEquals(1, instrumentList.getList().size());
    }

    @Test
    void delete_deleteInstrument_expectEquals() {
        InstrumentList instrumentList = new InstrumentList();
        Instrument newInstrument = new Guitar("Guitar", "Yamaha", 2004);
        instrumentList.addInstrument(newInstrument);
        instrumentList.deleteInstrument(1);
        assertEquals(0, instrumentList.getList().size());
    }

    @Test
    void reserve_reserveInstrument_expectTrue() {
        InstrumentList instrumentList = new InstrumentList();
        Instrument newInstrument = new Guitar("Guitar", "Yamaha", 2004);
        instrumentList.addInstrument(newInstrument);
        instrumentList.reserveInstrument(1);
        assertTrue(instrumentList.getList().get(0).isRented());
    }

    @Test
    void return_returnInstrument_expectFalse() {
        InstrumentList instrumentList = new InstrumentList();
        Instrument newInstrument = new Guitar("Guitar", "Yamaha", 2004);
        instrumentList.addInstrument(newInstrument);
        instrumentList.reserveInstrument(1);
        instrumentList.returnInstrument(1);
        assertFalse(instrumentList.getList().get(0).isRented());
    }


    public void sampleTest() {
        assertTrue(true);
    }
}
