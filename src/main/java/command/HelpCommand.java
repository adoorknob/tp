package command;

import instrument.InstrumentList;
import parser.Parser;
import ui.Ui;

public class HelpCommand extends Command {
    // Constructor
    public HelpCommand() {
        super("Exit");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, Parser parser) {
        ui.printCommandList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
