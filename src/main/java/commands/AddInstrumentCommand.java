package commands;

import exceptions.EmptyDescriptionException;
import exceptions.IncorrectAddInstrumentException;
import instrument.Flute;
import instrument.Guitar;
import instrument.InstrumentList;
import instrument.Piano;
import parser.commandParser;
import ui.Ui;

public class AddInstrumentCommand extends Command {
    private commandParser cmdparser;

    public AddInstrumentCommand(String Command) {
        super(Command);
        cmdparser = new commandParser();
    }

    public void parse(InstrumentList instrumentList, Ui ui) throws IncorrectAddInstrumentException {
        String[] userInput = cmdparser.separate(this.Name.trim());

        String instrument = cmdparser.instrumentName(userInput);
        String model = cmdparser.modelName(userInput);
        int year = cmdparser.instrumentYear(userInput);
        boolean isRented =  cmdparser.isRented(userInput);
        boolean isOverdue = cmdparser.isOverdue(userInput);
        String rentedFrom = cmdparser.rentedFrom(userInput);
        String rentedTo = cmdparser.rentedTo(userInput);


        // TODO: abstract this into hashmap
        try {
            switch (instrument) {
            case "Flute":
                instrumentList.addInstrument(new Flute(instrument, model, year,
                        isRented, isOverdue, rentedFrom, rentedTo));
                break;
            case "Piano":
                instrumentList.addInstrument(new Piano(instrument, model, year,
                        isRented, isOverdue, rentedFrom, rentedTo));
                break;
            case "Guitar":
                instrumentList.addInstrument(new Guitar(instrument, model, year,
                        isRented, isOverdue, rentedFrom, rentedTo));
                break;
            default:
                System.out.println("invalid instrument");
            }
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        }
        ui.printInstrumentList(instrumentList.getList());
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui) throws IncorrectAddInstrumentException {
        String[] userInput = cmdparser.separate(this.Name.trim());

        String instrument = cmdparser.instrumentName(userInput);
        String model = cmdparser.modelName(userInput);
        int year = cmdparser.instrumentYear(userInput);
        boolean isRented =  cmdparser.isRented(userInput);

        // TODO: abstract this into hashmap
        try {
            switch (instrument) {
            case "Flute":
                instrumentList.addInstrument(new Flute(instrument, model, year));
                break;
            case "Piano":
                instrumentList.addInstrument(new Piano(instrument, model, year));
                break;
            case "Guitar":
                instrumentList.addInstrument(new Guitar(instrument, model, year));
                break;
            default:
                System.out.println("invalid instrument");
            }
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        }
        ui.printInstrumentList(instrumentList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
