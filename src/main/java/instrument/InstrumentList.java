package instrument;

import java.util.ArrayList;
import java.time.LocalDate;

import exceptions.instrument.EmptyInstrumentListException;
import exceptions.instrument.IncorrectReserveInstrumentException;
import exceptions.instrument.IncorrectDescriptionException;
import exceptions.instrument.InvalidExtendDateException;
import exceptions.instrument.OutOfRangeException;

import utils.TimeChecker;

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
            System.out.println(e.getMessage());
        }
    }

    public void deleteInstrumentSilent(int number) {
        try {
            assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
            assert !instruments.isEmpty() : "No instruments to delete";
            instruments.remove(number - 1);
            numberOfInstruments--;
        } catch (Exception | AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    public void reserveInstrument(int number) {
        try {
            assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;

            if (this.instruments.isEmpty()) {
                throw new EmptyInstrumentListException("No instruments available for reservation");
            }

            Instrument instToRent = instruments.get(number - 1);

            if (instToRent.isRented()) {
                throw new IncorrectReserveInstrumentException("Instrument is already reserved");
            }

            System.out.println("Reserving instrument: " + instToRent);
            instToRent.rent();

            //Increase Usage
            instToRent.increaseUsage();
        } catch (Exception | AssertionError e) {
            throw new IncorrectReserveInstrumentException(e.getMessage());
        }
    }

    public void reserveInstrumentFromTo(int number, LocalDate from, LocalDate to) {
        try {
            assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
            if (this.instruments.isEmpty()) {
                throw new EmptyInstrumentListException("List is empty, no instruments available for reservation");
            }
            Instrument instToRent = instruments.get(number - 1);

            if (instToRent.isRented()) {
                throw new RuntimeException("Instrument is already reserved");
            }
            instToRent.rent();
            instToRent.rentFromTo(from, to);

            //Increase usage
            instToRent.increaseUsage();
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectReserveInstrumentException("Instrument " + number + " does not exist, " +
                    "please choose an instrument in the list.");
        } catch (Exception | AssertionError e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void extendInstrumentTo(int number, LocalDate to) {
        try {
            assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
            if (this.instruments.isEmpty()) {
                throw new EmptyInstrumentListException("No instruments available for extension");
            }
            Instrument instToRent = instruments.get(number - 1);

            if (!instToRent.isRented()) {
                throw new IncorrectReserveInstrumentException("Please make a reservation for your instrument" +
                        " before extending the loan period");
            }

            LocalDate prevTo = instToRent.getRentedTo();

            if (prevTo != null && to.isBefore(prevTo)) {
                throw new InvalidExtendDateException("Invalid date: ");
            }

            if (to.isBefore(LocalDate.now().minusDays(1))) {
                throw new InvalidExtendDateException("Please set a date after the current date.\n");
            }

            System.out.println("Extending reservation of instrument: " + instToRent.name
                    + " from " + instToRent.getRentedFrom() + " to " + to);

            instToRent.rentTo(to);
        } catch (Exception | AssertionError e) {
            throw new IncorrectReserveInstrumentException(e.getMessage());
        }
    }

    public void returnInstrument(int number) {
        try {
            assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
            if (this.instruments.isEmpty()) {
                throw new EmptyInstrumentListException("No instruments available for extension");
            }

            Instrument instToUnrent = instruments.get(number - 1);

            if (!instToUnrent.isRented()) {
                throw new IncorrectReserveInstrumentException("Instrument is cannot be returned as it is not reserved");
            }

            System.out.println("Returning instrument: " + instToUnrent);
            instToUnrent.unrent();
        } catch (Exception | AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    public Instrument getInstrument(int number) {
        try {
            if (this.instruments.isEmpty()) {
                throw new OutOfRangeException("Instrument List is empty");
            }
            assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
            return instruments.get(number - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Instrument " + number + " does not exist," +
                    " please choose an instrument in the list.");
        } catch (AssertionError e) {
            throw new OutOfRangeException(e.getMessage());
        }
    }

    public ArrayList<Instrument> getList() {
        return this.instruments;
    }
}
