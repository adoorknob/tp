package commands.datetime;

import commands.Command;
import exceptions.instrument.EmptyInstrumentListException;
import exceptions.instrument.IncorrectDescriptionException;
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
                String[] parts = getParts();
                LocalDate to = DateTimeParser.parseDate(parts[1]);
                instrumentList.extendInstrumentTo(indice, to);
            } catch (DateTimeException d) {
                throw new IncorrectDescriptionException("Please input a valid date (dd/MM/yyyy).");
            } catch (Exception | AssertionError f) {
                throw f;
            }
            ui.printInstrumentList(instrumentList.getList());
        } catch (NumberFormatException n) {
            throw new IncorrectDescriptionException("Please follow the format:\n" +
                    "extend INSTRUMENT_NUMBER to: END_DATE");
        } catch (Exception e) {
            throw e;
        }
    }

    private String[] getParts() {
        String[] parts = this.name.split("to: ", 3);
        if (parts.length != 2) {
            throw new IncorrectDescriptionException("Incorrect syntax. " +
                    "Please follow --> extend INSTRUMENT_NUMBER to: END_DATE");
        }
        return parts;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
