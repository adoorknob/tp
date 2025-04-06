package finance;

import instrument.Instrument;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class FinanceManager {
    private long totalCash;

    public FinanceManager() {
        totalCash = 0;
    }

    ;

    public void setTotalCash(int totalCash) {
        this.totalCash = totalCash;
    }

    public long getTotalCash() {
        return totalCash;
    }

    public void rentalPayment(Instrument instrument, LocalDate from, LocalDate to) {
        long days = ChronoUnit.DAYS.between(from, to); // Calculate rental duration in days
        if (days <= 0) {
            throw new IllegalArgumentException("Rental period must be at least 1 day.");
        }
        totalCash += (long) instrument.getRental() * days;
    }

    public void overduePayment(Instrument instrument, LocalDate now) {
        long days = ChronoUnit.DAYS.between(instrument.getDueDate(), now); // Calculate rental duration in days
        if (days <= 0) {
            throw new IllegalArgumentException("Rental period must be at least 1 day.");
        }
        totalCash += (long) instrument.getOverdueRental() * days;
    }

    public void inflowPayment(int amount) {
        totalCash += amount;
    }

    public void outflowPayment(int amount) {
        totalCash -= amount;
    }

    public String toFileEntry() {
        return "Cash: " + totalCash + "\n";
    }
}
