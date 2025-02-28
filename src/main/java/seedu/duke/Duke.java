package seedu.duke;

import ui.Ui;
import instrument.InstrumentList;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private Ui ui;
    private InstrumentList instrumentList;

    public Duke() {
        ui = new Ui();
        instrumentList = new InstrumentList();
    }

    public void runDuke() {
        boolean isDone = false;

        ui.printStartMessage();

        while (!isDone) {
            try {
                String userInput = ui.readUserInput();
                String command = ui.getCommand(userInput);

                switch (command) {
                case "help":
                    ui.printCommandList();
                    break;
                case "list":
                    ui.printInstrumentList(instrumentList.getList());
                    break;
                case "exit":
                    isDone = true;
                    break;
                default:
                    ui.printNoMatchingCommandError();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ui.printGoodbye();
    }

    public static void main(String[] args) {
        new Duke().runDuke();
    }
}
