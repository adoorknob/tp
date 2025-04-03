package storage;


import exceptions.FileCannotBeFoundException;
import exceptions.FileCannotBeMadeException;
import instrument.Instrument;
import instrument.InstrumentList;
import parser.Parser;
import ui.Ui;
import commands.AddInstrumentCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    String outputFilePath;
    String outputText = "";
    Ui ui;
    InstrumentList instrumentList;
    File file;

    public Storage(Ui ui, String outputFilePath) {
        this.ui = ui;
        instrumentList = new InstrumentList();
        this.outputFilePath = outputFilePath;
    }

    public InstrumentList loadOldFile() throws FileCannotBeFoundException {
        validateOutputFilepath();
        try {
            loadOldEntries();
            return instrumentList;
        } catch (FileNotFoundException e) {
            throw new FileCannotBeFoundException(outputFilePath);
        }
    }

    public void saveCurrentFile(InstrumentList instrumentList) throws IOException {
        updateOutputText(instrumentList);
        writeOutputToFile();
    }

    private void updateOutputText(InstrumentList instrumentList) {
        for (Instrument instrument : instrumentList.getList()) {
            addEntryToOutputText(instrument);
        }
    }

    private void validateOutputFilepath() {
        try {
            validateFileDirectories();
            validateFile();
        } catch (IOException e) {
            throw new FileCannotBeMadeException(outputFilePath);
        }
    }

    private void validateFileDirectories() {
        if (!outputFilePath.contains("/")) {
            return;
        }
        String directoryName = Parser.parseFileDirectories(outputFilePath);
        ui.printCreatingDirectory(directoryName);
        File directory = new File(directoryName);
        if (!directory.mkdirs()) {
            ui.printDirectoryAlreadyExists();
        }
    }

    private void validateFile() throws IOException {
        file = new File(outputFilePath);
        ui.printCreatingFile(outputFilePath);
        if (!file.createNewFile()) {
            ui.printFileAlreadyExists();
        }
    }

    private void loadOldEntries() throws FileNotFoundException {
        // format: Instrument|Model|Year|LoanDate|LoanDuration
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            addEntryToSession(line);
        }
    }

    private void addEntryToSession(String line) {
        AddInstrumentCommand c = new AddInstrumentCommand(line, true);
        c.addInstrument(instrumentList, ui);
    }

    private void addEntryToOutputText(Instrument instrument) {
        outputText += instrument.toFileEntry() + "\n";
    }

    private void writeOutputToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(outputFilePath);
        fileWriter.write(outputText);
        fileWriter.close();
    }
}
