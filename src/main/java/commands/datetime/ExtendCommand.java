package commands.datetime;

import commands.Command;
import exceptions.instrument.InvalidExtendDateException;
import instrument.InstrumentList;
import parser.CommandParser;
import ui.Ui;
import user.UserUtils;
import finance.FinanceManager;
import utils.DateTimeParser;

import java.time.DateTimeException;
import java.time.LocalDate;

public class ExtendCommand extends Command {
    private CommandParser parser;

    public ExtendCommand(String command) {
        super(command);
        parser = new CommandParser();
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        try {
            String[] userInput = parser.splits(this.name);
            int indice = Integer.parseInt(userInput[0]);
            try {
                String[] parts = this.name.split("to: ", 3);
                LocalDate to = DateTimeParser.parseDate(parts[1]);
                instrumentList.extendInstrumentTo(indice, to);
            } catch (InvalidExtendDateException e) {
                System.out.println(e.getMessage());
                return;
            } catch (DateTimeException d) {
                System.out.println("Please input a valid date (dd/MM/yyyy).");
                return;
            } catch (Exception | AssertionError f) {
                System.out.println(f.getMessage());
                return;
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
