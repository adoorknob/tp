package commands;

import instrument.InstrumentList;
import parser.Parser;
import ui.Ui;

public class HelpCommand extends Command {
    // Constructor
    public HelpCommand() {
        super("Exit");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui) {
        ui.printCommandList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
