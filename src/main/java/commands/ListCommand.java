package commands;

import instrument.InstrumentList;
import parser.CommandParser;
import ui.Ui;

import static ui.Ui.TEXTBORDER;

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
            } else if (subCmd.equals("filter")) { // search for a specific instrument name
                String[] parts = this.name.split("by: ", 3);
                String[] filterSearch = parts[1].split(" ");
                String filter = filterSearch[0].trim();
                if (filter.equals("reserved") || filter.equals("available")) {
                    ui.printFilteredList(instrumentList.getList(), filter, "");
                    return;
                }
                try {
                    String searchTerm = filterSearch[1].trim();
                    ui.printFilteredList(instrumentList.getList(), filter, searchTerm);
                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("The specified filter does not exist. Please try again");
                    System.out.println(TEXTBORDER);
                }
            } else {
                System.out.println("The specified subcommand does not exist. Please try again");
                System.out.println(TEXTBORDER);
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
