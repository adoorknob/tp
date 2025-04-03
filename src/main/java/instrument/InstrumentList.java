package instrument;

import java.time.LocalDateTime;
import java.util.ArrayList;

import exceptions.EmptyDescriptionException;

public class InstrumentList {
    private static final Integer currYEAR = 2025; // Current

    private ArrayList<Instrument> instruments;
    private int numberOfInstruments;


    public InstrumentList() {
        this.instruments = new ArrayList<>();
        this.numberOfInstruments = 0;
    }

    public Instrument addInstrument(Instrument instrument) {
        assert instrument != null;
        assert instrument.year >= 1600 && instrument.year <= currYEAR : "Invalid year: " + instrument.year;
        if (instrument.name.isBlank() || instrument.model.isBlank()) {
            throw new EmptyDescriptionException("event");
        }
        this.instruments.add(instrument);
        this.numberOfInstruments++;
        return instrument;
    }

    public void deleteInstrument(int number) {
        assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
        if (this.instruments.isEmpty()) {
            System.out.println("No instruments to delete");
            return;
        }

        try {
            System.out.println("Deleting instrument: " + instruments.get(number - 1));
            instruments.remove(number - 1);
            numberOfInstruments--;
            System.out.println("Now you have " + numberOfInstruments + " instruments");
        } catch (Exception e) {
            System.out.println("Error in deleting instrument: " + (number));
            System.out.println(e.getMessage());
        }
    }

    public void reserveInstrument(int number) {
        assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
        if (this.instruments.isEmpty()) {
            System.out.println("No instruments available for reservation");
            return;
        }
        Instrument instToRent = instruments.get(number - 1);

        if (instToRent.isRented()) {
            System.out.println("Instrument is already reserved");
            return;
        }
        System.out.println("Reserving instrument: " + instToRent);
        instToRent.rent();

        //Increase Usage
        instToRent.increaseUsage();
    }

    public void reserveInstrumentFromTo(int number, LocalDateTime from, LocalDateTime to) {
        assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
        if (this.instruments.isEmpty()) {
            System.out.println("No instruments available for reservation");
            return;
        }
        Instrument instToRent = instruments.get(number - 1);

        if (instToRent.isRented()) {
            System.out.println("Instrument is already rented");
            return;
        }

        instToRent.rent();
        instToRent.rentFromTo(from, to);

        //Increase usage
        instToRent.increaseUsage();
    }

    public void extendInstrumentTo(int number, LocalDateTime to) {
        assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
        if (this.instruments.isEmpty()) {
            System.out.println("No instruments available for extension");
            return;
        }
        Instrument instToRent = instruments.get(number - 1);

        if (!instToRent.isRented()) {
            System.out.println("Instrument number " + number + " is not yet reserved");
            System.out.println("Please make a reservation for your instrument before extending the loan period");
            return;
        }

        System.out.println("Extending reservation of instrument: " + instToRent.name
                + " from " + instToRent.getRentedFrom() + " to " + to);

        instToRent.rentTo(to);

    }

    public void returnInstrument(int number) {
        assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
        if (this.instruments.isEmpty()) {
            System.out.println("No instruments to return");
            return;
        }
        Instrument instToUnrent = instruments.get(number - 1);
        System.out.println("Returning instrument: " + instToUnrent);
        instToUnrent.unrent();
    }

    public Instrument getInstrument(int number) {
        assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
        if (this.instruments.isEmpty()) {
            System.out.println("No instruments available for reservation");
            throw new EmptyDescriptionException("event"); // TODO change this exception
        }
        return instruments.get(number - 1);
    }

    public ArrayList<Instrument> getList() {
        return this.instruments;
    }
}
