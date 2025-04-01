package commands;

import instrument.InstrumentList;
import ui.Ui;
import user.UserUtils;

public abstract class Command {
    public String name;

    public Command(String name) {
        this.name = name;
    }

    // Abstract method to execute the command (to be implemented by subclasses)
    public abstract void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils);

    // Abstract method to check if the command is an exit command
    public abstract boolean isExit();
}
