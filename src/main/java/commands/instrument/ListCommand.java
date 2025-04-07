package commands.instrument;

import commands.Command;
import exceptions.instrument.EmptyInstrumentListException;
import instrument.InstrumentList;
import parser.CommandParser;
import ui.Ui;
import user.UserUtils;
import finance.FinanceManager;
import utils.CasingStandardiser;

import java.util.regex.PatternSyntaxException;

import static ui.Ui.TEXTBORDER;

public class ListCommand extends Command {
    public static final String STOCK = "stock";
    public static final String FILTER = "filter";
    public static final String RESERVED = "reserved";
    public static final String AVAILABLE = "available";
    public static final String OVERDUE = "overdue";
    public static final String HELP = "help";
    private CommandParser parser;

    // Constructor
    public ListCommand(String command) {
        super(command);
        parser = new CommandParser();
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        try {
            assert instrumentList != null : "Program not initialized properly";
            assert ui != null : "Program not initialized properly";
            if (instrumentList.getList().isEmpty()) {
                throw new EmptyInstrumentListException("List is empty, let's add some instruments :)");
            }

            if (this.name.isEmpty()) {
                ui.printInstrumentList(instrumentList.getList());
                return;
            }

            String[] userInput = parser.splits(this.name);
            String subCmd = userInput[0];

            switch (subCmd) {
            case HELP:
                ui.printListSubcommandList();
                break;
            case STOCK:
                ui.printStockList(instrumentList.getList());
                break;
            case FILTER:
                String[] parts = getFilter();
                searchByFilter(instrumentList, ui, parts);
                break;
            default:
                System.out.println("The specified subcommand does not exist.");
                System.out.println("use `list help` to view available subcommands for `list`");
            }
        } catch (EmptyInstrumentListException m) {
            throw m;
        }
    }

    private String[] getFilter() {
        String[] parts = this.name.split("by: ", 3);
        if (parts.length != 2) {
            throw new EmptyInstrumentListException("Incorrect syntax. " +
                    "Please follow --> filter by: FILTER SEARCH_TERM");
        }
        return parts;
    }

    public void searchByFilter(InstrumentList instrumentList, Ui ui, String[] parts) {
        String[] filterSearch = parts[1].split(" ");
        String filter = filterSearch[0].trim();
        switch (filter) {
        case RESERVED:
            ui.printFilteredList(instrumentList.getList(), filter, "");
            return;
        case AVAILABLE:
            ui.printFilteredList(instrumentList.getList(), filter, "");
            return;
        case OVERDUE:
            ui.printFilteredList(instrumentList.getList(), filter, "");
            return;
        default:
            try {
                String searchTerm = CasingStandardiser.casingStandardise(filterSearch[1].trim());
                ui.printFilteredList(instrumentList.getList(), filter, searchTerm);
            } catch (ArrayIndexOutOfBoundsException a) {
                System.out.println("The specified filter does not exist. Please try again");
                System.out.println(TEXTBORDER);
            }
        }
    }


    @Override
    public boolean isExit() {
        return false;
    }

}
