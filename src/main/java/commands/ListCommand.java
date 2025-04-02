package commands;

import instrument.InstrumentList;
import parser.CommandParser;
import ui.Ui;

public class ListCommand extends Command {
    private CommandParser parser;
    // Constructor
    public ListCommand(String command) {
        super(command);
        parser = new CommandParser();
    }


    @Override
    public void execute(InstrumentList instrumentList, Ui ui) {

        try {
            if (this.name.isEmpty()) {
                ui.printInstrumentList(instrumentList.getList());
                return;
            }
            String[] userInput = parser.splits(this.name);
            String subCmd = userInput[0];
            if (subCmd.equals("stock")) {
                ui.printStockList(instrumentList.getList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
