package commands.instrument;

import commands.Command;
import exceptions.instrument.IncorrectDescriptionException;
import exceptions.instrument.IncorrectInputForAddInstrumentException;
import finance.FinanceManager;
import instrument.Instrument;
import instrument.InstrumentList;
import instrument.Flute;
import instrument.Guitar;
import instrument.Piano;
import parser.CommandParser;

import ui.Ui;
import user.User;
import user.UserUtils;

import java.time.LocalDate;

public class AddInstrumentCommand extends Command {
    private CommandParser cmdParser;
    private boolean isStorageInstrument;
    private String instrumentUserName;

    public AddInstrumentCommand(String command, boolean isStorageInstrument) {
        super(command);
        cmdParser = new CommandParser();
        this.isStorageInstrument = isStorageInstrument;
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager)
            throws IncorrectInputForAddInstrumentException {
        addInstrumentToSession(instrumentList, ui, userUtils);
    }

    public void addInstrumentToSession(InstrumentList instrumentList, Ui ui, UserUtils userUtils) {
        Instrument newInstrument;
        newInstrument = createInstrument(instrumentList, ui);
        if (newInstrument != null) {
            addUser(newInstrument, userUtils);
            instrumentList.addInstrument(newInstrument);
            ui.printInstrumentList(instrumentList.getList());
        }
    }

    /**
     *  Add instrument to the instrument list
     * @param instrumentList
     * @param ui
     * @return instrument
     */
    private Instrument createInstrument(InstrumentList instrumentList, Ui ui) {
        String[] userInput = cmdParser.separate(this.name.trim());

        String instrument = cmdParser.instrumentName(userInput);
        String model = cmdParser.modelName(userInput);
        int year = cmdParser.instrumentYear(userInput);

        boolean isRented = cmdParser.isRented(userInput, isStorageInstrument);
        boolean isOverdue = cmdParser.isOverdue(userInput, isStorageInstrument);
        LocalDate rentedFrom = cmdParser.rentedFrom(userInput, isStorageInstrument);
        LocalDate rentedTo = cmdParser.rentedTo(userInput, isStorageInstrument);
        this.instrumentUserName = cmdParser.user(userInput, isStorageInstrument);
        int usage = cmdParser.usage(userInput, isStorageInstrument);

        Instrument newInstrument = null;

        try {
            switch (instrument) {
            case "Flute":
                newInstrument = new Flute(instrument, model, year, isRented, isOverdue,
                            rentedFrom, rentedTo, usage);
                break;
            case "Piano":
                newInstrument = new Piano(instrument, model, year, isRented, isOverdue,
                            rentedFrom, rentedTo, usage);
                break;
            case "Guitar":
                newInstrument = new Guitar(instrument, model, year, isRented, isOverdue,
                            rentedFrom, rentedTo, usage);
                break;
            default:
                System.out.println("invalid instrument");
            }
        } catch (IncorrectDescriptionException e) {
            System.out.println(e.getMessage());
            ui.printInstrumentList(instrumentList.getList());
        }

        return newInstrument;
    }

    private void addUser(Instrument newInstrument, UserUtils userUtils) {
        if (this.isStorageInstrument) {
            addKnownUser(newInstrument, userUtils);
            return;
        }
        User user = userUtils.queryAndAssignUser(newInstrument);
        newInstrument.setUser(user);
    }

    private void addKnownUser(Instrument newInstrument, UserUtils userUtils) {
        User knownUser = userUtils.addKnownUser(this.instrumentUserName);
        newInstrument.setUser(knownUser);
        knownUser.addInstrument(newInstrument);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
