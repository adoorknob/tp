package instrument;

public class Flute extends Instrument {

    public Flute(String fluteName, String model, int year) {
        this.name = fluteName;
        this.model = model;
        this.year = year;
    }

    @Override
    public String playInstrument() {
        return "Flute Sounds";
    }
}
