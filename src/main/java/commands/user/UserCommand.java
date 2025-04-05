package commands.user;

import commands.Command;
import finance.FinanceManager;
import instrument.InstrumentList;
import ui.Ui;
import user.UserUtils;

public class UserCommand extends Command {
    public UserCommand() {
        super("user");
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        int userChoice = ui.queryUserCommandChoice();
        userUtils.executeUserCommand(userChoice);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
