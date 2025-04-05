package instrument;

import java.time.LocalDate;

public class Flute extends Instrument {

    public Flute(String fluteName, String model, int year) {
        super(fluteName, model, year);
    }

    public Flute(String name, String model, int year, boolean isRented, boolean isOverDue,
                 LocalDate rentedFrom, LocalDate rentedTo, int usage) {
        super(name, model, year, isRented, isOverDue, rentedFrom, rentedTo, usage);
    }

    @Override
    public String playInstrument() {
        return "Flute Sounds";
    }
}
