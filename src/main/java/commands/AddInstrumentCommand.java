package commands;

import exceptions.EmptyDescriptionException;
import exceptions.IncorrectAddInstrumentException;
import exceptions.NegativeUsageException;
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

import java.time.LocalDateTime;

public class AddInstrumentCommand extends Command {
    private CommandParser cmdparser;
    private boolean isParse;

    public AddInstrumentCommand(String command, boolean isParse) {
        super(command);
        cmdparser = new CommandParser();
        this.isParse = isParse;
    }


    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager)
            throws IncorrectAddInstrumentException {


        Instrument newInstrument;
        if (this.isParse) {
            newInstrument = addInstrument(instrumentList, ui);
        } else {
            newInstrument = addInstrument(instrumentList, ui);
            if (newInstrument != null && !this.isParse) {
                addUser(newInstrument, userUtils);
            }
            instrumentList.addInstrument(newInstrument);
        }
        ui.printInstrumentList(instrumentList.getList());
    }

    /**
     *  Add instrument to the instrument list
     * @param instrumentList
     * @param ui
     * @return instrument
     */
    public Instrument addInstrument(InstrumentList instrumentList, Ui ui) {
        String[] userInput = cmdparser.separate(this.name.trim());

        String instrument = cmdparser.instrumentName(userInput);
        String model = cmdparser.modelName(userInput);
        int year = cmdparser.instrumentYear(userInput);

        boolean isRented = cmdparser.isRented(userInput, isParse);
        boolean isOverdue = cmdparser.isOverdue(userInput, isParse);
        LocalDateTime rentedFrom = cmdparser.rentedFrom(userInput, isParse);
        LocalDateTime rentedTo = cmdparser.rentedTo(userInput, isParse);
        int usage = cmdparser.usage(userInput, isParse);

        Instrument newInstrument = null;

        try {
            switch (instrument) {
            case "Flute":
                newInstrument = new Flute(instrument, model, year, isRented, isOverdue, rentedFrom, rentedTo);
                break;
            case "Piano":
                newInstrument = new Piano(instrument, model, year, isRented, isOverdue, rentedFrom, rentedTo);
                break;
            case "Guitar":
                newInstrument = new Guitar(instrument, model, year, isRented, isOverdue, rentedFrom, rentedTo);
                break;
            default:
                System.out.println("invalid instrument");
            }
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
            ui.printInstrumentList(instrumentList.getList());
        }

        try {
            if (!isParse && newInstrument != null) {
                newInstrument.setUsage(usage);
            } else {
                instrumentList.addInstrument(newInstrument);
            }
        } catch (NegativeUsageException e) {
            System.out.println(e.getMessage());
        }

        return newInstrument;
    }

    private void addUser(Instrument newInstrument, UserUtils userUtils) {
        User user = userUtils.queryAndAssignUser(newInstrument);
        newInstrument.setUser(user);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
