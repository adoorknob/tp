package instrument;

import java.time.LocalDate;

public class Guitar extends Instrument {

    public Guitar(String guitarName, String model, int year) {
        super(guitarName, model, year);
    }

    public Guitar(String name, String model, int year, boolean isRented, boolean isOverDue,
                  LocalDate rentedFrom, LocalDate rentedTo, int usage) {
        super(name, model, year, isRented, isOverDue, rentedFrom, rentedTo, usage);
    }

    @Override
    public String playInstrument() {
        return "Guitar Sounds";
    }
}
