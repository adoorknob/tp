package commands.instrument;

import commands.Command;
import exceptions.instrument.InvalidDeleteException;
import instrument.Instrument;
import instrument.InstrumentList;
import ui.Ui;
import user.User;
import user.UserUtils;
import finance.FinanceManager;

public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        super(command);
    }

    /**
     * Delete instrument from instrument List
     *
     * @param instrumentList instrument list initialised in main
     * @param ui             UI object initialised in main
     * @param userUtils      User utilities to manage user list
     * @param financeManager Finance manager used to manage finance
     */
    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        try {
            int instrumentId = Integer.parseInt(this.name);
            Instrument instrument = instrumentList.getInstrument(instrumentId);
            deleteInstrumentFromUser(instrument);
            instrumentList.deleteInstrument(instrumentId);
        } catch (NumberFormatException e) {
            throw new InvalidDeleteException("The instrument id was not a number");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDeleteException("Please delete an existing instrument");
        } catch (Exception | AssertionError f) {
            throw new InvalidDeleteException(f.getMessage());
        }
        ui.printInstrumentList(instrumentList.getList());

    }

    /**
     * Deletes instrument from user list
     *
     * @param instrument
     */
    private void deleteInstrumentFromUser(Instrument instrument) {
        try {
            User user = instrument.getUser();
            user.removeInstrument(instrument);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
