package seedu.duke;

import exceptions.instrument.IncorrectReserveInstrumentException;
import instrument.Guitar;
import instrument.Instrument;
import instrument.InstrumentList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void extend_validExtendInstrument_expectTrue() {
        InstrumentList instrumentList = new InstrumentList();
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        Guitar newGuitar = new Guitar("Guitar", "Yamaha", 2004,
                true, false, today, tomorrow, 1);
        instrumentList.addInstrument(newGuitar);
        LocalDate date = LocalDate.of(2025, 12, 1);
        instrumentList.extendInstrumentTo(1, date);
        assertEquals(newGuitar.getRentedTo(), date, "Rent date not correctly extended");
    }

    @Test
    void reserveFromTo_validReserveInstrument_expectTrue() {
        InstrumentList instrumentList = new InstrumentList();
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        Guitar newGuitar = new Guitar("Guitar", "Yamaha", 2004);
        instrumentList.addInstrument(newGuitar);
        instrumentList.reserveInstrumentFromTo(1, today, tomorrow);
        assertTrue(instrumentList.getList().get(0).isRented());
        assertEquals(newGuitar.getRentedTo(), tomorrow,
                "Rent date not correctly extended");
        assertEquals(newGuitar.getRentedFrom(), today,
                "Rent date not correctly extended");
    }

    public void sampleTest() {
        assertTrue(true);
    }
}
