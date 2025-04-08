package seedu.duke;

import commands.Command;
import exceptions.storage.FileCannotBeFoundException;
import storage.Storage;
import storage.FinanceStorage;
import ui.Ui;
import parser.Parser;
import instrument.InstrumentList;
import user.UserList;
import user.UserUtils;
import finance.FinanceManager;
import utils.IsOverdueChecker;
import utils.LowStockChecker;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class SirDukeBox {
    private final Ui ui;
    private final Parser parser;
    private final InstrumentList instrumentList;
    private final Storage storage;
    private final FinanceStorage financeStorage;
    private final ScheduledExecutorService scheduler;
    private final UserList userList;
    private final UserUtils userUtils;
    private final FinanceManager financeManager;

    private final String saveFilePath = "./data/SirDukeBox.txt";
    private final String saveFilePathFinance = "./data/DukeFinance.txt";

    public SirDukeBox() {
        ui = new Ui();
        parser = new Parser();
        financeStorage = new FinanceStorage(ui, saveFilePathFinance);
        scheduler = Executors.newSingleThreadScheduledExecutor();
        userList = new UserList(ui);
        userUtils = new UserUtils(ui, userList);
        storage = new Storage(ui, userUtils, saveFilePath);
        instrumentList = storage.loadOldFile();
        financeManager = financeStorage.loadOldFile();

        IsOverdueChecker.startDailyOverdueCheck(scheduler, instrumentList);
    }

    public void runDuke() {
        ui.printStartMessage();
        startStockCheck();

        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readUserInput();
                String command = ui.getCommand(userInput);
                String input = ui.getRemainingWords(userInput);

                assert command != null;
                assert input != null;

                Command commandObj = parser.parse(command, input);
                commandObj.execute(instrumentList, ui, userUtils, financeManager);
                isExit = commandObj.isExit();
                ui.printTextBorder();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                ui.printTextBorder();
            }
        }

        try {
            storage.saveCurrentFile(instrumentList);
            financeStorage.saveCurrentFile(financeManager);
        } catch (IOException e) {
            throw new FileCannotBeFoundException(saveFilePath);
        } finally {
            IsOverdueChecker.shutdownScheduler(scheduler);
        }
    }

    private void startStockCheck() {
        LowStockChecker.checkAll(instrumentList.getList());
    }

    public static void main(String[] args) {
        new SirDukeBox().runDuke();
    }
}
