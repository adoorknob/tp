package instrument;

import java.util.ArrayList;
import exceptions.EmptyDescriptionException;

public class InstrumentList {
    private static final Integer currYEAR =  2025; // Current

    private ArrayList<Instrument> instruments;
    private int numberOfInstruments;


    public InstrumentList() {
        this.instruments = new ArrayList<>();
        this.numberOfInstruments = 0;
    }

    public void addInstrument(Instrument instrument) {
        assert instrument != null;
        assert instrument.year >= 1600 && instrument.year <= currYEAR : "Invalid year: " + instrument.year;
        if (instrument.name.isBlank() || instrument.model.isBlank()) {
            throw new EmptyDescriptionException("event");
        }
        this.instruments.add(instrument);
        this.numberOfInstruments++;
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
        }
    }

    public void reserveInstrument(int number) {
        assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
        if (this.instruments.isEmpty()) {
            System.out.println("No instruments available for reservation");
            return;
        }
        Instrument instToRent = instruments.get(number - 1);
        //        System.out.println("Would you like to reserve " + instToRent + "? [Y/N]");
        //        String userInput = ui.readUserInput();
        //
        //        if (userInput.equals("Y")) {
        System.out.println("Reserving instrument: " + instToRent);
        instToRent.rent();
        //        } else {
        //            System.out.println("Reserve cancelled");
        //        }
    }

    public void reserveInstrumentFromTo(int number, String from, String to) {
        assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
        if (this.instruments.isEmpty()) {
            System.out.println("No instruments available for reservation");
            return;
        }
        Instrument instToRent = instruments.get(number - 1);
        instToRent.rent();
        instToRent.rentFromTo(from, to);
    }

    public void returnInstrument(int number) {
        assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
        if (this.instruments.isEmpty()) {
            System.out.println("No instruments to return");
            return;
        }
        Instrument instToUnrent = instruments.get(number - 1);
        //        System.out.println("Would you like to return " + instToUnrent + "? [Y/N]");
        //        String userInput = ui.readUserInput();
        //
        //        if (userInput.equals("Y")) {
        System.out.println("Returning instrument: " + instToUnrent);
        instToUnrent.unrent();
        //        } else {
        //            System.out.println("Return cancelled");
        //        }
    }

    public ArrayList<Instrument> getList() {return this.instruments;}

}
