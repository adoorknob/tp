package instrument;

import java.util.ArrayList;
import exceptions.emptyDescriptionException;

public class InstrumentList {
    private ArrayList<Instrument> instruments;
    private int numberOfInstruments;

    public InstrumentList() {
        this.instruments = new ArrayList<>();
        this.numberOfInstruments = 0;
    }

    public void addInstrument(String instrument) {
        if (instrument.isBlank()) { // Check if the description is empty
            throw new emptyDescriptionException("event");
        }

        try {
            switch (instrument) {
            case "Flute":
                this.instruments.add(new Flute(instrument));
                this.numberOfInstruments++;
                break;
            case "Piano":
                this.instruments.add(new Piano(instrument));
                this.numberOfInstruments++;
                break;
            case "Guitar":
                this.instruments.add(new Guitar(instrument));
                this.numberOfInstruments++;
                break;
            default:
                System.out.println("invalid instrument");
            }
        } catch (emptyDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteInstrument(int number) {

        if (this.instruments.isEmpty()) {
            System.out.println("No instruments to delete");
            return;
        } else if (number > numberOfInstruments) {
            System.out.println("Instrument number out of bounds");
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

    public ArrayList<Instrument> getList() {
        return this.instruments;
    }

}
