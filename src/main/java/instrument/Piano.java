package instrument;

import java.time.LocalDateTime;

public class Piano extends Instrument {

    public Piano(String pianoName, String model, int year) {
        super(pianoName, model, year);
    }

    public Piano(String name, String model, int year, boolean isRented, boolean isOverDue,
                 LocalDateTime rentedFrom, LocalDateTime rentedTo) {
        super(name, model, year, isRented, isOverDue, rentedFrom, rentedTo);
    }

    @Override
    public String playInstrument() {
        return "Piano Sounds";
    }
}
