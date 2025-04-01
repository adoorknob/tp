package commands;

import instrument.InstrumentList;
import ui.Ui;

public class DefaultCommand extends Command {
    public DefaultCommand() {
        super("Default Command");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui) {
        ui.printNoMatchingCommandError();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
