package commands;

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
        instrumentList.returnInstrument(Integer.parseInt(this.name));
        Instrument instrument = instrumentList.getInstrument(Integer.parseInt(this.name));
        if (instrument != null && instrument.isOverDue()) {
            financeManager.overduePayment(instrument, LocalDate.now());
        }
        ui.printInstrumentList(instrumentList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
