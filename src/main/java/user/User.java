package user;

import exceptions.instrument.InstrumentFindException;
import instrument.Instrument;
import instrument.InstrumentList;
import ui.Ui;

public class User {
    String name;
    InstrumentList instrumentsRentalHistory;
    InstrumentList instrumentsCurrentlyRented;
    UserList parentUserList;
    Ui ui;

    public User(Ui ui, UserList userList, String name) {
        this.name = name;
        this.parentUserList = userList;
        this.instrumentsRentalHistory = new InstrumentList();
        this.instrumentsCurrentlyRented = new InstrumentList();
        this.ui = ui;
    }

    public User(Ui ui, UserList userList) {
        this.parentUserList = userList;
        int guestNumber = parentUserList.getNumberOfGuestUsers() + 1;
        this.name = "Guest" + guestNumber;
        this.instrumentsRentalHistory = new InstrumentList();
        this.instrumentsCurrentlyRented = new InstrumentList();
        this.ui = ui;
    }

    public String getName() {
        return name;
    }

    public void setParentUserList(UserList parentUserList) {
        this.parentUserList = parentUserList;
    }

    public void addInstrument(Instrument instrument) {
        this.instrumentsRentalHistory.addInstrument(instrument);
        this.instrumentsCurrentlyRented.addInstrument(instrument);
        ui.printAddInstrumentToUser(instrument);
    }

    public void removeInstrument(Instrument instrument) {
        for (int i = 0; i < instrumentsCurrentlyRented.getList().size(); i++) {
            if (instrument.equals(instrumentsCurrentlyRented.getList().get(i))) {
                instrumentsCurrentlyRented.deleteInstrument(i + 1);
                ui.printRemovedInstrumentFromUser(instrument, this);
                return;
            }
        }
        throw new InstrumentFindException("The instrument was not found from user"
                + name + "'s list to delete");
    }

    public InstrumentList getRentalHistory() {
        return instrumentsRentalHistory;
    }

    public InstrumentList getCurrentlyRented() {
        return instrumentsCurrentlyRented;
    }
}
