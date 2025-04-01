package commands;

import instrument.InstrumentList;
import ui.Ui;
import user.User;
import user.UserUtils;

public class DefaultCommand extends Command {
    public DefaultCommand() {
        super("Default Command");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils) {
        ui.printNoMatchingCommandError();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
