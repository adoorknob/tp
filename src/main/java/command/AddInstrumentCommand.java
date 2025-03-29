package command;

import instrument.InstrumentList;
import parser.Parser;
import ui.Ui;

public class AddInstrumentCommand extends Command {
    public AddInstrumentCommand(String Command) {
        super(Command);
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, Parser parser) {
        try {
            instrumentList.addInstrument(parser.separateNMY(this.Name.trim()));
            ui.printInstrumentList(instrumentList.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
