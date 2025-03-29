package command;

import instrument.InstrumentList;
import ui.Ui;
import parser.Parser;

public class ListCommand extends Command {
    // Constructor
    public ListCommand() {
        super("Exit");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, Parser parser) {
        ui.printInstrumentList(instrumentList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
