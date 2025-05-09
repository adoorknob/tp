package commands;

import instrument.InstrumentList;
import ui.Ui;
import user.UserUtils;
import finance.FinanceManager;

public class ExitCommand extends Command {
    // Constructor for the ExitCommand
    public ExitCommand() {
        super("Exit");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        ui.printGoodbye();  // Show a goodbye message
    }

    @Override
    public boolean isExit() {
        // This command signals that the program should exit
        return true;
    }
}
