package seedu.duke;

import commands.Command;
import exceptions.FileCannotBeFoundException;
import storage.Storage;
import ui.Ui;
import parser.Parser;
import utils.TimeChecker;
import instrument.InstrumentList;

import java.io.IOException;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private final Ui ui;
    private final Parser parser;
    private final InstrumentList instrumentList;
    private final Storage storage;
    private final TimeChecker timeChecker;

    private final String saveFilePath = "./data/SirDukeBox.txt";

    public Duke() {
        ui = new Ui();
        storage = new Storage(ui, saveFilePath);
        parser = new Parser();
        timeChecker = new TimeChecker();

        InstrumentList currentInstrumentList;
        try {
            currentInstrumentList = storage.loadOldFile();
        } catch (FileCannotBeFoundException e) {
            currentInstrumentList = new InstrumentList();
        }
        instrumentList = currentInstrumentList;

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

        try {
            storage.saveCurrentFile(instrumentList);
        } catch (IOException e) {
            throw new FileCannotBeFoundException(saveFilePath);
        }
    }

    public static void main(String[] args) {
        new Duke().runDuke();
    }
}
