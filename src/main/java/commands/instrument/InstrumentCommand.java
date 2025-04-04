package commands.instrument;

import instrument.InstrumentList;
import ui.Ui;
import user.UserUtils;
import finance.FinanceManager;

public abstract class InstrumentCommand {
    public String name;

    public InstrumentCommand(String name) {
        this.name = name;
    }

    // Abstract method to execute the command (to be implemented by subclasses)
    public abstract void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils,
                                 FinanceManager financeManager);

    // Abstract method to check if the command is an exit command
    public abstract boolean isExit();
}
