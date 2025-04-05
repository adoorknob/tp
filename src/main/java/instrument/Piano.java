package instrument;

import java.time.LocalDate;

public class Piano extends Instrument {

    public Piano(String pianoName, String model, int year) {
        super(pianoName, model, year);
    }

    public Piano(String name, String model, int year, boolean isRented, boolean isOverDue,
                 LocalDate rentedFrom, LocalDate rentedTo, int usage) {
        super(name, model, year, isRented, isOverDue, rentedFrom, rentedTo, usage);
    }

    @Override
    public String playInstrument() {
        return "Piano Sounds";
    }
}
