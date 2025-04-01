package instrument;

import java.time.LocalDate;

public class Flute extends Instrument {

    public Flute(String fluteName, String model, int year) {
        super(fluteName,model,year);
    }

    public Flute(String name, String model, int year, boolean isRented, boolean isOverDue,
                 String rentedFrom, String rentedTo) {
        super(name, model, year, isRented, isOverDue, rentedFrom, rentedTo);
    }

    public Flute(String fluteName, String model, int year, LocalDate dueBy) {
        this.name = fluteName;
        this.model = model;
        this.year = year;
        this.isRented = true;
        this.dueBy = dueBy;
    }

    @Override
    public String playInstrument() {
        return "Flute Sounds";
    }
}
