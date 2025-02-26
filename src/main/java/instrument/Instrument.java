package instrument;

public abstract class Instrument {

    private boolean isRented = false;

    public String name;

    public abstract String playInstrument();


    public void rent() {
        isRented = true;
    }
    public void unrent() {
        isRented = false;
    }
    public boolean isRented() {
        return isRented;
    }
}
