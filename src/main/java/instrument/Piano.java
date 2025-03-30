package instrument;

public class Piano extends Instrument {

    public Piano(String pianoName, String model, int year) {
        super(pianoName, model, year);
    }

    public Piano(String name, String model, int year, boolean isRented, boolean isOverDue,
                 String rentedFrom, String rentedTo) {
        super(name, model, year, isRented, isOverDue, rentedFrom, rentedTo);
    }

    @Override
    public String playInstrument() {
        return "Piano Sounds";
    }
}
