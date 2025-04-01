package instrument;

import java.time.LocalDate;

public class Piano extends Instrument {

    public Piano(String pianoName, String model, int year) {
        super(pianoName, model, year);
    }

    public Piano(String name, String model, int year, boolean isRented, boolean isOverDue,
                 String rentedFrom, String rentedTo) {
        super(name, model, year, isRented, isOverDue, rentedFrom, rentedTo);
    }

    public Piano(String pianoName, String model, int year, LocalDate dueBy) {
        this.name = pianoName;
        this.model = model;
        this.year = year;
        this.isRented = true;
        this.dueBy = dueBy;
    }

    @Override
    public String playInstrument() {
        return "Piano Sounds";
    }
}
