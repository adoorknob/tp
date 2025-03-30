package commands;

import instrument.InstrumentList;
import ui.Ui;

public abstract class Command {
    public String Name;

    public Command(String name) {
        this.Name = name;
    }

    // Abstract method to execute the command (to be implemented by subclasses)
    public abstract void execute(InstrumentList instrumentList, Ui ui);

    // Abstract method to check if the command is an exit command
    public abstract boolean isExit();
}
