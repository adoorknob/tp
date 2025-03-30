package instrument;

import java.time.LocalDate;

public class Flute extends Instrument {

    public Flute(String fluteName, String model, int year) {
        this.name = fluteName;
        this.model = model;
        this.year = year;
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
