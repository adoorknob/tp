package commands;

import instrument.InstrumentList;
import ui.Ui;

public class ReturnCommand extends Command {
    public ReturnCommand(String command) {
        super(command);
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui) {
        instrumentList.returnInstrument(Integer.parseInt(this.name));
        ui.printInstrumentList(instrumentList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
