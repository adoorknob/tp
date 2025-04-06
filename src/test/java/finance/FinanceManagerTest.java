package finance;

import instrument.Instrument;
import instrument.InstrumentList;
import instrument.Piano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Ui;
import user.UserList;
import user.UserUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FinanceManagerTest {
    private FinanceManager financeManager;
    private InstrumentList instrumentList;
    private Ui ui;
    private UserList userList;
    private UserUtils userUtils;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private Instrument instrument;

    @BeforeEach
    void setUp() {
        instrumentList = new InstrumentList();
        ui = new Ui();
        userList = new UserList(ui);
        userUtils = new UserUtils(ui, userList);
        financeManager = new FinanceManager(ui);
        System.setOut(new PrintStream(outputStreamCaptor));

        instrument = new Piano("Piano", "Yamaha", 2010, true, false,
                LocalDate.now(), LocalDate.now().plus(1, ChronoUnit.DAYS), 1);
    }

    @Test
    void testRentalPaymentSingleDay() {
        financeManager.rentalPayment(instrument, LocalDate.now().plus(1, ChronoUnit.DAYS));
        String output = outputStreamCaptor.toString().trim();
        assertTrue(output.contains("Received Rental Amount: " + instrument.getRental()));
        assertEquals(20, financeManager.getTotalCash());
    }

    @Test
    void testRentalPaymentMultipleDays() {
        financeManager.rentalPayment(instrument, LocalDate.now().plus(3, ChronoUnit.DAYS));
        String output = outputStreamCaptor.toString().trim();
        assertEquals(instrument.getRental() * 3L, financeManager.getTotalCash());
    }

    @Test
    void testRentalPaymentInvalidPeriod() {
        assertThrows(IllegalArgumentException.class, () ->
                financeManager.rentalPayment(instrument, LocalDate.of(2025, 4, 1)));
    }

    @Test
    void testOverduePayment() {
        financeManager.overduePayment(instrument, LocalDate.now().plus(4, ChronoUnit.DAYS));
        String output = outputStreamCaptor.toString().trim();
        assertEquals(instrument.getOverdueRental() * 3L, financeManager.getTotalCash());
    }

    @Test
    void testOverduePaymentInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                financeManager.overduePayment(instrument, LocalDate.of(2025, 4, 2)));
    }

    @Test
    void testInflowPaymentValid() {
        financeManager.inflowPayment(100);
        assertEquals(100, financeManager.getTotalCash());
    }

    @Test
    void testInflowPaymentNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                financeManager.inflowPayment(-50));
    }

    @Test
    void testOutflowPaymentValid() {
        financeManager.inflowPayment(500);
        financeManager.outflowPayment(300);
        assertEquals(200, financeManager.getTotalCash());
    }

    @Test
    void testOutflowPaymentNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                financeManager.outflowPayment(-100));
    }

    @Test
    void testToFileEntry() {
        financeManager.inflowPayment(1000);
        assertEquals("Cash: 1000\n", financeManager.toFileEntry());
    }
}
