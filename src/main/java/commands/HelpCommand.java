package commands;

import instrument.InstrumentList;
import ui.Ui;
import user.UserUtils;

public class HelpCommand extends Command {
    // Constructor
    public HelpCommand() {
        super("Exit");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils) {
        ui.printCommandList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
