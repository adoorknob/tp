package commands.instrument;

import commands.Command;
import exceptions.instrument.IncorrectReturnInstructionException;
import finance.FinanceManager;
import instrument.InstrumentList;
import instrument.Instrument;
import ui.Ui;
import user.UserUtils;

import java.time.LocalDate;

public class ReturnCommand extends Command {
    public ReturnCommand(String command) {
        super(command);
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        try {
            int number = Integer.parseInt(this.name);
            Instrument instrument = instrumentList.getInstrument(Integer.parseInt(this.name));

            if (instrument != null ) {
                financeManager.rentalPayment(instrument, LocalDate.now());
                if (instrument.isOverDue()) {
                    financeManager.overduePayment(instrument, LocalDate.now());
                }
            }
            instrumentList.returnInstrument(number);
            ui.printInstrumentList(instrumentList.getList());
        } catch (Exception | AssertionError e) {
            throw new IncorrectReturnInstructionException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
