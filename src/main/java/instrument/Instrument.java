package instrument;

import exceptions.instrument.NegativeUsageException;
import user.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Instrument {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");
    public String name;
    public String model;
    public int year;

    private User user;

    private boolean isRented = false;
    private boolean isOverDue = false;

    private LocalDate rentedTo;
    private LocalDate rentedFrom;

    // Rental per day
    private int rental = 20;

    // Overdue rental
    private int overdueRate = 50;

    private int usage = 0;

    public Instrument(String name, String model, int year) {
        this.name = name;
        this.model = model;
        this.year = year;
        this.isRented = false;
        this.isOverDue = false;
        this.rentedFrom = null;
        this.rentedTo = null;
    }

    public Instrument(String name, String model, int year, boolean isRented, boolean isOverDue,
                      LocalDate rentedFrom, LocalDate rentedTo) {
        this.name = name;
        this.model = model;
        this.year = year;
        this.isRented = isRented;
        this.isOverDue = isOverDue;
        this.rentedFrom = rentedFrom;
        this.rentedTo = rentedTo;
    }

    public abstract String playInstrument();

    public void rent() {
        isRented = true;
    }

    public void unrent() {
        isRented = false;
        rentedFrom = null;
        rentedTo = null;
    }

    public void rentFromTo(LocalDate from, LocalDate to) {
        rentedFrom = from;
        rentedTo = to;
    }

    public void rentTo(LocalDate to) {
        rentedTo = to;
    }

    public boolean isRented() {
        return isRented;
    }

    public LocalDate getdueDate() {
        return rentedTo;
    }

    public LocalDate getRentedFrom() {
        return rentedFrom;
    }

    public void setOverdue(boolean overDue) {
        isOverDue = overDue;
    }

    public String toString() {
        return name + " | " + model + " | " + year + " | " + (isRented ? "Rented" : "") +
                (isOverDue ? " | Overdue" : "") +
                (rentedFrom != null ? " | Rented from: " + rentedFrom.format(DATE_TIME_FORMATTER) : "") +
                (rentedTo != null ? " | Rented to: " + rentedTo.format(DATE_TIME_FORMATTER) : "");
    }

    public String toFileEntry() {
        return name + " | " + model + " | " + year + " | " + isRented + " | " + isOverDue + " | " +
                (rentedFrom != null ? rentedFrom.format(DATE_TIME_FORMATTER) : "null") + " | " +
                (rentedTo != null ? rentedTo.format(DATE_TIME_FORMATTER) : "null") + " | " + usage;
    }

    public void setUsage(int usage) throws NegativeUsageException {
        if (usage < 0) {
            throw new NegativeUsageException("Usage set is " + usage + ". ");
        }

        this.usage = usage;
    }

    public int getUsage() {
        return usage;
    }

    public void increaseUsage() {
        this.usage++;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRental() {
        return rental;
    }

    public int getOverdueRental() {
        return overdueRate;
    }

    public boolean isOverDue() {
        return isOverDue;
    }

}
