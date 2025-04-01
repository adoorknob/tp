package instrument;

import exceptions.NegativeUsageException;

public abstract class Instrument {

    public String name;

    public String model;

    public int year;

    private boolean isRented = false;

    private boolean isOverDue = false;

    private String rentedFrom;

    private String rentedTo;

    private int usage = 0;

    public Instrument(String name, String model, int year) {
        this.name = name;
        this.model = model;
        this.year = year;
        this.isRented = false;
        this.isOverDue = false;
        this.rentedFrom = "";
        this.rentedTo = "";
    }

    public Instrument(String name, String model, int year, boolean isRented, boolean isOverDue,
                      String rentedFrom, String rentedTo) {
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
        rentedFrom = "";
        rentedTo = "";
    }

    public void rentFromTo(String from, String to) {
        rentedFrom = from;
        rentedTo = to;
    }

    public boolean isRented() {
        return isRented;
    }

    public String dueDate() {
        return rentedTo;
    }

    public void setOverdue(boolean overDue) {
        isOverDue = overDue;
    }

    public String toString() {
        return name + " | " + model + " | " + year + " | " + (isRented ? "Rented" : "") +
                (isOverDue ? "| Overdue " : "") + (!rentedFrom.isEmpty() ? " | Rented from: "
                + rentedFrom : "") + (!rentedTo.isEmpty() ? " | Rented to: " + rentedTo : "");
    }

    public String toFileEntry() {
        return name + " | " + model + " | " + year + " | " + (isRented) + " | " + (isOverDue) + " | " + rentedFrom
                + " | " + rentedTo + " | " + usage;
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

}
