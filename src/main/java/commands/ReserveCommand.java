package commands;

import instrument.InstrumentList;
import parser.Parser;
import ui.Ui;

public class ReserveCommand extends Command {
    public ReserveCommand(String command) {
        super(command);
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, Parser parser) {
        try {
            instrumentList.reserveInstrument(Integer.parseInt(this.Name));
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
