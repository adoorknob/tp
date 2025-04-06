package instrument;

import java.util.ArrayList;
import java.time.LocalDate;

import exceptions.instrument.OutOfRangeException;
import utils.TimeChecker;

import exceptions.instrument.IncorrectDescriptionException;
import exceptions.instrument.InvalidExtendDateException;

public class InstrumentList {
    private static final Integer currYEAR = TimeChecker.getCurrentYear();
    private static final Integer minYEAR = 1600;

    private ArrayList<Instrument> instruments;
    private int numberOfInstruments;


    public InstrumentList() {
        this.instruments = new ArrayList<>();
        this.numberOfInstruments = 0;
    }

    public Instrument addInstrument(Instrument instrument) {
        try {
            assert instrument != null;
            assert instrument.year >= minYEAR && instrument.year <= currYEAR
                    : "Invalid year (" + minYEAR + " to " + currYEAR + ") ->"  + instrument.year;
            if (instrument.name.isBlank() || instrument.model.isBlank()) {
                throw new IncorrectDescriptionException("No name or model found");
            }
            this.instruments.add(instrument);
            this.numberOfInstruments++;
            return instrument;
        } catch (Exception | AssertionError e) {
            throw new IncorrectDescriptionException(e.getMessage());
        }
    }

    public void deleteInstrument(int number) {
        try {
            assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
            assert !instruments.isEmpty() : "No instruments to delete";
            System.out.println("Deleting instrument: " + instruments.get(number - 1));
            instruments.remove(number - 1);
            numberOfInstruments--;
            System.out.println("Now you have " + numberOfInstruments + " instruments");
        } catch (Exception | AssertionError e) {
            System.err.println("Error in deleting instrument: " + (number));
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

    public void reserveInstrumentFromTo(int number, LocalDate from, LocalDate to) {
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

    public void extendInstrumentTo(int number, LocalDate to) {
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

        LocalDate prevTo = instToRent.getRentedTo();

        if (to.isBefore(prevTo)) {
            throw new InvalidExtendDateException("Invalid date: ");
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

        if (!instToUnrent.isRented()) {
            System.out.println("Instrument is cannot be returned as it is not reserved");
            return;
        }

        System.out.println("Returning instrument: " + instToUnrent);
        instToUnrent.unrent();
    }

    public Instrument getInstrument(int number) {
        try {
            if (this.instruments.isEmpty()) {
                throw new OutOfRangeException("Instrument List is empty");
            }
            assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
            return instruments.get(number - 1);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (AssertionError e) {
            throw new OutOfRangeException(e.getMessage());
        }
    }

    public ArrayList<Instrument> getList() {
        return this.instruments;
    }
}
