package seedu.duke;

import commands.Command;
import exceptions.FileCannotBeFoundException;
import storage.Storage;
import ui.Ui;
import parser.Parser;
import instrument.InstrumentList;
import utils.isOverdueChecker;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private final Ui ui;
    private final Parser parser;
    private final InstrumentList instrumentList;
    private final Storage storage;
    private final ScheduledExecutorService scheduler;

    private final String saveFilePath = "./data/SirDukeBox.txt";

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(ui, parser, saveFilePath);
        scheduler = Executors.newSingleThreadScheduledExecutor();

        InstrumentList currentInstrumentList;
        try {
            currentInstrumentList = storage.loadOldFile();
        } catch (FileCannotBeFoundException e) {
            currentInstrumentList = new InstrumentList();
        }
        instrumentList = currentInstrumentList;

        startDailyOverdueCheck();
    }


    /**
     * Starts a scheduled task to check for overdue instruments once per day.
     */
    private void startDailyOverdueCheck() {
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Running daily overdue check...");
            isOverdueChecker.checkAll(instrumentList);
        }, 0, 24, TimeUnit.HOURS); // Runs immediately, then every 24 hours
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
                commandObj.execute(instrumentList, ui);
                isExit = commandObj.isExit();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            storage.saveCurrentFile(instrumentList);
        } catch (IOException e) {
            throw new FileCannotBeFoundException(saveFilePath);
        } finally {
            shutdownScheduler();
        }
    }

    private void shutdownScheduler() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(3, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }

    public static void main(String[] args) {
        new Duke().runDuke();
    }
}
