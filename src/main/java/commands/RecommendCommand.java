package commands;

import exceptions.IncorrectAddInstrumentException;
import finance.FinanceManager;
import instrument.Instrument;
import instrument.InstrumentList;
import parser.CommandParser;

import ui.Ui;
import user.UserUtils;


public class RecommendCommand extends Command {
    private CommandParser cmdparser;

    public RecommendCommand(String command) {
        super(command);
        cmdparser = new CommandParser();

    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        String userInput = "";
        try{
            userInput = cmdparser.justGetInstrument(this.name);
        } catch(IncorrectAddInstrumentException e) {
            System.out.println(e.getMessage());
            return;
        }


        userInput = userInput.toLowerCase();

        switch (userInput) {
        case "flute":
            userInput = "Flute";
            break;
        case "guitar":
            userInput = "Guitar";
            break;
        case "piano":
            userInput = "Piano";
            break;
        default:
            System.out.println(userInput+" does not exist");
            return;
        }

        recommendInstrument(userInput,instrumentList, ui);


    }

    public void recommendInstrument(String userInput,InstrumentList instrumentList, Ui ui) {
        Instrument recommendedInstrument = null;
        int index = 0;
        int usage = 0;
        for (int i = 0; i < instrumentList.getList().size(); i++) {
            if (userInput.equals(instrumentList.getList().get(i).name)){
                if (instrumentList.getList().get(i).getUsage()>=usage && !instrumentList.getList().get(i).isRented()){
                    recommendedInstrument = instrumentList.getList().get(i);
                    usage = instrumentList.getList().get(i).getUsage();
                    index = i+1;
                }
            }
        }

        if (recommendedInstrument==null){
            System.out.println("There are no "+userInput+" that are available");
            return;
        }

        ui.printRecommendation(recommendedInstrument,index);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}