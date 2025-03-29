package command;

import instrument.InstrumentList;
import parser.Parser;
import ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String Command) {
        super(Command);
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, Parser parser) {
        instrumentList.deleteInstrument(Integer.parseInt(this.Name));
        ui.printInstrumentList(instrumentList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
