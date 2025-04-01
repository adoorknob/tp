package commands;

import instrument.InstrumentList;
import ui.Ui;

public class ListCommand extends Command {
    // Constructor
    public ListCommand() {
        super("Exit");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui) {
        ui.printInstrumentList(instrumentList.getList());
        ui.printStockList(instrumentList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
