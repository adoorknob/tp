package instrument;

public abstract class Instrument {

    public String name;

    public String model;

    public int year;

    private boolean isRented = false;

    private boolean isOverDue = false;

    private String rentedFrom;

    private String rentedTo;

    public Instrument(String name, String model, int year) {
        this.name = name;
        this.model = model;
        this.year = year;
        this.isRented = false;
        this.isOverDue = false;
        this.rentedFrom = "";
        this.rentedTo = "";
    }

    public Instrument(String name, String model, int year, boolean isRented, boolean isOverDue,
                      String rentedFrom, String rentedTo) {
        this.name = name;
        this.model = model;
        this.year = year;
        this.isRented = isRented;
        this.isOverDue = isOverDue;
        this.rentedFrom = rentedFrom;
        this.rentedTo = rentedTo;
    }

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
        return name + " | " + model + " | " + year + " | " + (isRented ? "X" : "O");
    }

    public String toFileEntry() {
        return name + " | " + model + " | " + year + " | " + (isRented) + " | " + (isOverDue) + " | " + rentedFrom
                + " | " + rentedTo;
    }
}
