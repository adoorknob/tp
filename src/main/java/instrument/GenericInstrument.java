package instrument;

import java.time.LocalDate;

public class GenericInstrument extends Instrument {
    public GenericInstrument(String name, String model, int year) {
        super(name, model, year);
    }

    public GenericInstrument(String name, String model, int year, boolean isRented, boolean isOverDue,
                 LocalDate rentedFrom, LocalDate rentedTo, int usage) {
        super(name, model, year, isRented, isOverDue, rentedFrom, rentedTo, usage);
    }

    @Override
    public String playInstrument() {
        return this.name + " Sounds";
    }
}
