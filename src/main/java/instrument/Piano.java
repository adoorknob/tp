package instrument;

public class Piano extends Instrument {

    public Piano(String pianoName, String model, int year) {
        this.name = pianoName;
        this.model = model;
        this.year = year;
    }

    public Piano(String pianoName, String model, int year, boolean isRented) {
        this.name = pianoName;
        this.model = model;
        this.year = year;
        this.isRented = isRented;
    }

    @Override
    public String playInstrument() {
        return "Piano Sounds";
    }
}
