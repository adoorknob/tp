package commands;

import instrument.InstrumentList;
import parser.CommandParser;
import ui.Ui;
import user.UserUtils;
import finance.FinanceManager;
import utils.DateTimeParser;

import java.time.LocalDate;

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
        try {
            String[] userInput = parser.splits(this.name);
            int indice = Integer.parseInt(userInput[0]);
            if (userInput.length > 1) {
                try {
                    String[] parts = this.name.split("from: |to: ", 3);
                    LocalDate from = DateTimeParser.parseDate(parts[1]);
                    LocalDate to = DateTimeParser.parseDate(parts[2]);
                    instrumentList.reserveInstrumentFromTo(indice, from, to);
                    financeManager.rentalPayment(instrumentList.getInstrument(indice), from, to);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                instrumentList.reserveInstrument(indice);
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
