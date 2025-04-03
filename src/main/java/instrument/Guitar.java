package instrument;

import java.time.*;

public class Guitar extends Instrument {

    public Guitar(String guitarName, String model, int year) {
        super(guitarName, model, year);
    }

    public Guitar(String name, String model, int year, boolean isRented, boolean isOverDue,
                  LocalDateTime rentedFrom, LocalDateTime rentedTo) {
        super(name, model, year, isRented, isOverDue, rentedFrom, rentedTo);
    }

    @Override
    public String playInstrument() {
        return "Guitar Sounds";
    }
}
