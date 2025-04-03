package commands;

import exceptions.EmptyInstrumentListException;
import instrument.InstrumentList;
import ui.Ui;
import user.UserUtils;
import finance.FinanceManager;

public class ListCommand extends Command {
    // Constructor
    public ListCommand() {
        super("Exit");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        assert instrumentList != null;
        assert ui != null;
        ui.printInstrumentList(instrumentList.getList());
        ui.printStockList(instrumentList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
