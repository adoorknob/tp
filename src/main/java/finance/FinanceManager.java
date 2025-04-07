package finance;

import instrument.Instrument;
import ui.Ui;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class FinanceManager {
    private long totalCash;
    private Ui ui;

    public FinanceManager(Ui ui) {
        totalCash = 0;
        this.ui = ui;
    };

    public void setTotalCash(int totalCash) {
        this.totalCash = totalCash;
    }

    public long getTotalCash() {
        return totalCash;
    }

    public void rentalPayment(Instrument instrument, LocalDate to) {
        LocalDate from = instrument.getRentedFrom();
        long days = ChronoUnit.DAYS.between(from, to); // Calculate rental duration in days
        if (days < 0) {
            throw new IllegalArgumentException("Rental period must be at least 1 day.");
        } else if (days == 0) {
            days = 1;
        }
        long cash = (long) instrument.getRental() * days;
        System.out.println("Received Rental Amount: " + cash);
        totalCash += cash;
    }

    public void overduePayment(Instrument instrument, LocalDate now) {
        long days = ChronoUnit.DAYS.between(instrument.getDueDate(), now); // Calculate rental duration in days
        if (days <= 0) {
            throw new IllegalArgumentException("Rental period must be at least 1 day.");
        }
        long cash = (long) instrument.getOverdueRental() * days;
        System.out.println("Received Overdue Amount: " + cash);
        totalCash += cash;
    }

    public void inflowPayment(int amount) {
        if (amount > 0) {
            totalCash += amount;
        } else {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }

    public void outflowPayment(int amount) {
        if (amount >= 0) {
            totalCash -= amount;
        } else {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }

    public String toFileEntry() {
        return "Cash: " + totalCash + "\n";
    }
}
