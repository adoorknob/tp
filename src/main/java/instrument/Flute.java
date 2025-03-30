package instrument;

public class Flute extends Instrument {

    public Flute(String fluteName, String model, int year) {
        this.name = fluteName;
        this.model = model;
        this.year = year;
    }

    public Flute(String fluteName, String model, int year, boolean isRented) {
        this.name = fluteName;
        this.model = model;
        this.year = year;
        this.isRented = isRented;
    }

    @Override
    public String playInstrument() {
        return "Flute Sounds";
    }
}
