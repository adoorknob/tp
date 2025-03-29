package commands;

import instrument.InstrumentList;
import parser.Parser;
import ui.Ui;

public class DefaultCommand extends Command {
    public DefaultCommand() {
        super("Default Command");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, Parser parser) {
        ui.printNoMatchingCommandError();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
