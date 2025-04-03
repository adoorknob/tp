package commands;

import finance.FinanceManager;
import instrument.InstrumentList;
import ui.Ui;
import user.UserUtils;

public class UserListCommand extends Command {
    public UserListCommand() {
        super("userlist");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        ui.printUserListDisplay(userUtils.getUserList().getUsers());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
