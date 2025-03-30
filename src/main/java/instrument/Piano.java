package instrument;

public class Piano extends Instrument {

    public Piano(String pianoName, String model, int year) {
        this.name = pianoName;
        this.model = model;
        this.year = year;
    }

    @Override
    public String playInstrument() {
        return "Piano Sounds";
    }
}
