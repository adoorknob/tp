package user;

import instrument.Instrument;
import instrument.InstrumentList;
import ui.Ui;

public class User {
    String name;
    InstrumentList instrumentsRentalHistory;
    InstrumentList instrumentsCurrentlyRented;
    UserList parentUserList;
    Ui ui;

    public User(Ui ui, String name) {
        this.name = name;
        this.instrumentsRentalHistory = new InstrumentList();
        this.instrumentsCurrentlyRented = new InstrumentList();
        this.ui = ui;
    }

    public User(Ui ui) {
        int guestNumber = parentUserList.getNumberOfGuestUsers() + 1;
        this.name = "Guest" + guestNumber;
        this.instrumentsRentalHistory = new InstrumentList();
        this.instrumentsCurrentlyRented = new InstrumentList();
        this.ui = ui;
    }

    public String getName() {
        return name;
    }

    public void addInstrument(Instrument instrument) {
        this.instrumentsRentalHistory.addInstrument(instrument);
        this.instrumentsCurrentlyRented.addInstrument(instrument);
    }
}
