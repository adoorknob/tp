package storage;

import exceptions.storage.FileCannotBeFoundException;
import exceptions.storage.FileCannotBeMadeException;
import finance.FinanceManager;
import ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinanceStorage {
    String outputFilePath;
    String outputText = "";
    Ui ui;
    FinanceManager financeManager = new FinanceManager();
    File file;

    public FinanceStorage(Ui ui, String outputFilePath) {
        this.ui = ui;
        this.outputFilePath = outputFilePath;
    }

    public FinanceManager loadOldFile() throws FileCannotBeFoundException {
        validateOutputFilepath();
        try {
            int cash = loadEntry();
            financeManager.setTotalCash(cash);
            return financeManager;
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return financeManager;
        }
    }

    public void saveCurrentFile(FinanceManager financeManager) throws IOException {
        updateOutputText(financeManager);
        writeOutputToFile();
    }


    private void updateOutputText(FinanceManager financeManager) {
        addEntryToOutputText(financeManager);
    }


    private void validateOutputFilepath() {
        try {
            validateFile();
        } catch (IOException e) {
            throw new FileCannotBeMadeException(outputFilePath);
        }
    }

    private void validateFile() throws IOException {
        file = new File(outputFilePath);
        ui.printCreatingFile(outputFilePath);
        if (!file.createNewFile()) {
            ui.printFileAlreadyExists();
        }
    }

    private int loadEntry() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("Cash:")) {
                    return parseCash(line);
                }
            }
        }
        throw new FileNotFoundException("No valid 'Cash: XXX' entry found in file.");
    }

    private int parseCash(String line) throws NumberFormatException {
        Pattern pattern = Pattern.compile("Cash:\\s*(\\d+)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new NumberFormatException("Invalid cash format in file.");
    }

    private void addEntryToOutputText(FinanceManager financeManager) {
        outputText += financeManager.toFileEntry() + "\n";
    }

    private void writeOutputToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(outputFilePath);
        fileWriter.write(outputText);
        fileWriter.close();
    }
}
