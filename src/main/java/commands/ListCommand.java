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
        assert instrumentList != null;
        assert ui != null;
        if (instrumentList.getList().isEmpty()) {
            ui.printListIsEmpty();
        } else {
            ui.printInstrumentList(instrumentList.getList());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
