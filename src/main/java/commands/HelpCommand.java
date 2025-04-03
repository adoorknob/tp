package commands;

import instrument.InstrumentList;
import ui.Ui;
import user.UserUtils;
import finance.FinanceManager;

public class HelpCommand extends Command {
    // Constructor
    public HelpCommand() {
        super("Exit");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        ui.printCommandList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
