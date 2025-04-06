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
            deleteInstrumentFromUser(instrumentList, instrumentId);
            instrumentList.deleteInstrument(instrumentId);
        } catch (Exception | AssertionError f) {
            System.out.println(f.getMessage());
            return;
        }
        ui.printInstrumentList(instrumentList.getList());

    }

    private void deleteInstrumentFromUser(InstrumentList instrumentList, int instrumentId) {
        Instrument instrument = instrumentList.getInstrument(instrumentId);
        User user = instrument.getUser();
        user.removeInstrument(instrument);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
