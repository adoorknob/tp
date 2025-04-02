package commands;

import instrument.InstrumentList;
import parser.CommandParser;
import ui.Ui;
import user.UserUtils;

public class ReserveCommand extends Command {
    private CommandParser parser;

    public ReserveCommand(String command) {
        super(command);
        parser = new CommandParser();
    }

    // TODO add features to prevent invalid date/overdue from the start
    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils) {
        try {
            String[] userInput = parser.splits(this.name);
            int indice = Integer.parseInt(userInput[0]);
            if (userInput.length > 1) {
                try {
                    String[] parts = this.name.split("from: |to: ", 3);
                    String from = parts[1];
                    String to = parts[2];
                    instrumentList.reserveInstrumentFromTo(indice, from, to);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                instrumentList.reserveInstrument(indice);
            }

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
