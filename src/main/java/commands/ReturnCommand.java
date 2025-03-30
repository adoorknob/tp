package commands;

import instrument.InstrumentList;
import parser.Parser;
import ui.Ui;

public class ReturnCommand extends Command {
    public ReturnCommand(String command) {
        super(command);
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui) {
        instrumentList.returnInstrument(Integer.parseInt(this.Name));
        ui.printInstrumentList(instrumentList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
