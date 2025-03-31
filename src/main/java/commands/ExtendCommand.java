package commands;

import instrument.InstrumentList;
import parser.Parser;
import parser.commandParser;
import ui.Ui;

import java.time.LocalDate;

public class ExtendCommand extends Command {
    public ExtendCommand(String command) {
        super(command);
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, Parser parser) {
        try {
            String[] input = commandParser.separateNY(this.Name.trim());
            int number = Integer.parseInt(input[0]);
            LocalDate date = LocalDate.parse(input[1]);
            instrumentList.extendInstrument(number, date);
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
