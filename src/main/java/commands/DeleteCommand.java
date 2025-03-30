package commands;

import instrument.InstrumentList;
import ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String Command) {
        super(Command);
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui) {
        instrumentList.deleteInstrument(Integer.parseInt(this.Name));
        ui.printInstrumentList(instrumentList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
