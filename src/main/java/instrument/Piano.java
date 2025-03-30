package instrument;

import java.time.LocalDate;

public class Piano extends Instrument {

    public Piano(String pianoName, String model, int year) {
        this.name = pianoName;
        this.model = model;
        this.year = year;
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
