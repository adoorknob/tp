package instrument;

public class Guitar extends Instrument {

    public Guitar(String guitarName) {
        this.name = guitarName;
    }

    @Override
    public String playInstrument() {
        return "Guitar Sounds";
    }
}
