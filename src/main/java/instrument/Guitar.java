package instrument;

import java.time.LocalDate;

public class Guitar extends Instrument {

    public Guitar(String guitarName, String model, int year) {
        this.name = guitarName;
        this.model = model;
        this.year = year;
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
