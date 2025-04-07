package commands.instrument;

import commands.Command;
import exceptions.instrument.IncorrectAddInstrumentException;
import exceptions.instrument.IncorrectReserveInstrumentException;
import exceptions.instrument.IncorrectReturnInstructionException;
import instrument.InstrumentList;
import parser.CommandParser;
import ui.Ui;
import user.UserUtils;
import finance.FinanceManager;
import utils.DateTimeParser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReserveCommand extends Command {
    private CommandParser parser;

    public ReserveCommand(String command) {
        super(command);
        parser = new CommandParser();
    }

    /**
     *  Reserves item on instrument list
     * @param instrumentList list of instruments
     * @param ui UI object from SirDuke
     * @param userUtils userUtils from SirDuke
     * @param financeManager financeManger from SirDuke
     */
    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        String[] userInput = parser.splits(this.name);
        int indice = Integer.parseInt(userInput[0]);
        if (userInput.length > 1) {
            try {
                String[] parts = this.name.split("from: |to: ", 3);
                if (parts.length < 2) {
                    throw new IncorrectReserveInstrumentException("Invalid format: ");
                }
                LocalDate from = DateTimeParser.parseDate(parts[1]);
                LocalDate to = DateTimeParser.parseDate(parts[2]);
                assert from.isBefore(to) : "from: date must be before to: date";
                assert from.isAfter(LocalDate.now().minus(1, ChronoUnit.DAYS)) :
                        "from: date must be either today or a date in the future";
                instrumentList.reserveInstrumentFromTo(indice, from, to);
            } catch (DateTimeException d) {
                System.err.println(d.getMessage());
                return;
            }
        } else {
            try {
                instrumentList.reserveInstrumentFromTo(indice, LocalDate.now(), null);
            } catch (IncorrectReturnInstructionException e) {
               System.err.println(e.getMessage());
               return;
            }
        }

        ui.printInstrumentList(instrumentList.getList());

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
