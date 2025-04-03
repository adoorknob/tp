package commands;

import instrument.InstrumentList;
import ui.Ui;
import user.UserUtils;
import finance.FinanceManager;

public class DefaultCommand extends Command {
    public DefaultCommand() {
        super("Default Command");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        ui.printNoMatchingCommandError();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
