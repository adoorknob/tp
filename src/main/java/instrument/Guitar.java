package instrument;

import java.time.LocalDate;

public class Guitar extends Instrument {

    public Guitar(String guitarName, String model, int year) {
        super(guitarName, model, year);
    }

    public Guitar(String name, String model, int year, boolean isRented, boolean isOverDue,
                 String rentedFrom, String rentedTo) {
        super(name, model, year, isRented, isOverDue, rentedFrom, rentedTo);
    }

    public Guitar(String guitarName, String model, int year, LocalDate dueBy) {
        this.name = guitarName;
        this.model = model;
        this.year = year;
        this.isRented = true;
        this.dueBy = dueBy;
    }

    @Override
    public String playInstrument() {
        return "Guitar Sounds";
    }
}
