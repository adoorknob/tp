package commands.instrument;

import commands.Command;
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

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        try {
            int instrumentId = Integer.parseInt(this.name);
            Instrument instrument = instrumentList.getInstrument(instrumentId);
            deleteInstrumentFromUser(instrument);
            instrumentList.deleteInstrument(instrumentId);
            ui.printInstrumentList(instrumentList.getList());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

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
