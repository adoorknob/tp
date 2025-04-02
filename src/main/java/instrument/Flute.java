package instrument;

public class Flute extends Instrument {

    public Flute(String fluteName, String model, int year) {
        super(fluteName, model, year);
    }

    public Flute(String name, String model, int year, boolean isRented, boolean isOverDue,
                 String rentedFrom, String rentedTo) {
        super(name, model, year, isRented, isOverDue, rentedFrom, rentedTo);
    }

    @Override
    public String playInstrument() {
        return "Flute Sounds";
    }
}
