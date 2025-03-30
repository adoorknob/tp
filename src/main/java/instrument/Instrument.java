package instrument;

import java.time.LocalDate;

public abstract class Instrument {

    public String name;

    public String model;

    public int year;

    public LocalDate dueBy;

    public boolean isRented = false;

    public abstract String playInstrument();


    public void rent(LocalDate date) {
        isRented = true;
        dueBy = date;
    }

    public void unrent() {
        isRented = false;
    }

    public boolean isRented() {
        return isRented;
    }

    public String toString() {
        return name + " | " + (isRented ? "X" : "O");
    }

    public String toFileEntry() {
        return name + " | " + model + " | " + year + " | " + (isRented ? "X" : "O")
                + (dueBy == null ? "" : " | " + dueBy);
    }
}
