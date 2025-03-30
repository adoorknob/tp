package instrument;

public class Guitar extends Instrument {

    public Guitar(String guitarName, String model, int year) {
        this.name = guitarName;
        this.model = model;
        this.year = year;
    }

    public Guitar(String guitarName, String model, int year, boolean isRented) {
        this.name = guitarName;
        this.model = model;
        this.year = year;
        this.isRented = isRented;
    }

    @Override
    public String playInstrument() {
        return "Guitar Sounds";
    }
}
