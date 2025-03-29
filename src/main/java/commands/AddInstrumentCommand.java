package commands;

import instrument.InstrumentList;
import parser.*;
import ui.Ui;

public class AddInstrumentCommand extends Command {
    public AddInstrumentCommand(String Command) {
        super(Command);
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, Parser parser) {
        try {
            instrumentList.addInstrument(commandParser.separateNMY(this.Name.trim()));
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
