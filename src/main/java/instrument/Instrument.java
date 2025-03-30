package instrument;

public abstract class Instrument {

    public String name;

    public String model;

    public int year;

    private boolean isRented = false;

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

    public String toString() {
        return name + " | " + (isRented ? "X" : "O");
    }

    public String toFileEntry() {
        return name + " | " + model + " | " + year + " | " + (isRented ? "X" : "O");
    }
}
