package seedu.duke;

import commands.Command;
import ui.Ui;
import parser.Parser;
import instrument.InstrumentList;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private final Ui ui;
    private final Parser parser;
    private final InstrumentList instrumentList;

    public Duke() {
        ui = new Ui();
        instrumentList = new InstrumentList();
        parser = new Parser();
    }

    public void runDuke() {
        ui.printStartMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.readUserInput();
                String command = ui.getCommand(userInput);
                String input = ui.getRemainingWords(userInput);

                assert command != null;
                assert input != null;

                Command commandObj = parser.parse(command, input);
                commandObj.execute(instrumentList, ui, parser);
                isExit = commandObj.isExit();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().runDuke();
    }
}
