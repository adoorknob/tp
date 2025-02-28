package instrument;

import java.util.ArrayList;

public class InstrumentList {
    private ArrayList<Instrument> instruments;
    private int numberOfInstruments;

    public InstrumentList() {
        this.instruments = new ArrayList<>();
        this.numberOfInstruments = 0;
    }

    public ArrayList<Instrument> getList() {
        return this.instruments;
    }
}
