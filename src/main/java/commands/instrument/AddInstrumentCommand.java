package commands.instrument;

import commands.Command;
import exceptions.instrument.IncorrectAddInstrumentException;
import exceptions.storage.CorruptStorageException;
import finance.FinanceManager;
import instrument.*;
import parser.CommandParser;

import ui.Ui;
import user.User;
import user.UserUtils;
import utils.CasingStandardiser;

import java.time.LocalDate;

public class AddInstrumentCommand extends Command {
    private CommandParser cmdParser;
    private boolean isStorageInstrument;
    private String instrumentUserName;


    private String[] userInput;
    private String instrument;
    private String model;
    private int year;

    private boolean isRented;
    private boolean isOverdue;
    private LocalDate rentedFrom;
    private LocalDate rentedTo;
    private int usage;

    public AddInstrumentCommand(String command, boolean isStorageInstrument) {
        super(command);
        this.cmdParser = new CommandParser();
        this.isStorageInstrument = isStorageInstrument;
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager)
            throws IncorrectAddInstrumentException {
        try {
            addInstrumentToSession(instrumentList, ui, userUtils);
        } catch (Exception | AssertionError e) {
            if (isStorageInstrument) {
                throw new CorruptStorageException(e.getMessage());
            }
            System.out.println(e.getMessage());
            System.out.println("Instrument was not added.");
        }
    }

    /**
     *  Add instrument from storage
     * @param instrumentList List of instruments instantiated in Duke
     * @param ui UI object handling UI
     * @param userUtils userUtilities which handles user related commands
     */
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
    public Instrument createInstrument(InstrumentList instrumentList, Ui ui) {
        String[] userInput = cmdParser.separate(this.name.trim());

        try {
            this.instrument = cmdParser.instrumentName(userInput);
            this.instrument = CasingStandardiser.casingStandardise(instrument);
            this.model = cmdParser.modelName(userInput);
            this.year = cmdParser.instrumentYear(userInput);

            this.isRented = cmdParser.isRented(userInput, isStorageInstrument);
            this.isOverdue = cmdParser.isOverdue(userInput, isStorageInstrument);
            this.rentedFrom = cmdParser.rentedFrom(userInput, isStorageInstrument);
            this.rentedTo = cmdParser.rentedTo(userInput, isStorageInstrument);
            this.instrumentUserName = cmdParser.getUser(userInput, isStorageInstrument);
            this.usage = cmdParser.getUsage(userInput, isStorageInstrument);
        } catch (RuntimeException e) {
            if (isStorageInstrument) {
                throw new CorruptStorageException(e.getMessage());
            }
            throw new IncorrectAddInstrumentException(e.getMessage());
        }

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
                newInstrument = new GenericInstrument(instrument, model, year, isRented, isOverdue,
                        rentedFrom, rentedTo, usage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return newInstrument;
    }

    /**
     *  Add user to instrument and updates instrument and userList
     * @param newInstrument New instrument that is to be tagged
     * @param userUtils Utility object that handles Users and User List
     */
    private void addUser(Instrument newInstrument, UserUtils userUtils) {
        if (this.isStorageInstrument) {
            addKnownUser(newInstrument, userUtils);
            return;
        }
        User user = userUtils.queryAndAssignUser(newInstrument);
        newInstrument.setUser(user);
    }

    /**
     *  If the user is a known user
     * @param newInstrument New instrument that is to be tagged
     * @param userUtils Utility object that handles Users and User List
     */
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
