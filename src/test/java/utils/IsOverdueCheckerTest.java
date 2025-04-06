package utils;

import instrument.Instrument;
import instrument.InstrumentList;
import instrument.Piano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IsOverdueCheckerTest {

    private InstrumentList instrumentList;

    @BeforeEach
    void setUp() {
        instrumentList = new InstrumentList();
    }

    @Test
    void testIsOverdue_withPastDate_returnsTrue() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        boolean output = IsOverdueChecker.isOverdue(pastDate);
        assertTrue(output, "The date should be overdue.");
    }

    @Test
    void testIsNotOverdue_withCurrentDate_returnsFalse() {
        LocalDate today = LocalDate.now();
        boolean output = IsOverdueChecker.isOverdue(today);
        assertFalse(output, "The date should not be overdue.");
    }

    @Test
    void testIsOverdue_withFutureDate_returnsFalse() {
        LocalDate futureDate = LocalDate.now().plusDays(2);
        boolean output = IsOverdueChecker.isOverdue(futureDate);
        assertFalse(output, "The date should not be overdue.");
    }

    @Test
    void testIsOverdue_withNullDate_returnsFalse() {
        boolean output = IsOverdueChecker.isOverdue(null);
        assertFalse(output, "Null date should not be considered overdue.");
    }

    @Test
    void testCheckAll_withOverdueInstrument_setsOverdueFlag() {
        // Creating instruments
        Instrument overdueInstrument = new Piano("Piano", "Yamaha", 2010, true, false,
                LocalDate.now().minusDays(5), LocalDate.now().minusDays(3), 100);
        Instrument notOverdueInstrument = new Piano("Guitar", "Fender", 2015, true, false,
                LocalDate.now().plusDays(2), LocalDate.now().plusDays(3), 50);

        instrumentList.addInstrument(overdueInstrument);
        instrumentList.addInstrument(notOverdueInstrument);

        // Checking overdue status
        IsOverdueChecker.checkAll(instrumentList);

        assertTrue(overdueInstrument.isOverDue(), "Instrument should be marked as overdue.");
        assertFalse(notOverdueInstrument.isOverDue(), "Instrument should not be marked as overdue.");
    }
}
