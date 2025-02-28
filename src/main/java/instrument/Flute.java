package instrument;

public class Flute extends Instrument {

    public Flute(String fluteName) {
        this.name = fluteName;
    }

    @Override
    public String playInstrument() {
        return "Flute Sounds";
    }
}
