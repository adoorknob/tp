package parser;

import exceptions.incorrectAddInstrumentException;
import utils.TimeChecker;

public class commandParser {

    TimeChecker timeChecker;

    public commandParser() {
        timeChecker = new TimeChecker();
    }

    public String[] separateNMY(String input) throws incorrectAddInstrumentException {
        if (input == null || input.isEmpty()) {
            throw new incorrectAddInstrumentException("Input is Empty");
        }
        String[] split = Parser.parseFileEntryToInstrument(input);

        if (split.length < 3) {
            throw new incorrectAddInstrumentException("Input format is invalid: missing fields");
        }

        try {
            Integer.parseInt(split[2]);
            return split;
        } catch (NumberFormatException e) {
            throw new incorrectAddInstrumentException("Input year is invalid");
        }
    }

    public String instrumentName(String[] userInput) throws incorrectAddInstrumentException {
        if (userInput == null || userInput.length == 0 || userInput[0].isEmpty()) {
            throw new incorrectAddInstrumentException(" is Empty");
        }
        return userInput[0];
    }

    public String modelName(String[] userInput) throws incorrectAddInstrumentException {
        if (userInput == null || userInput.length < 1 || userInput[1].isEmpty() ) {
            throw new incorrectAddInstrumentException("Input is Empty");
        }
        return userInput[1];
    }

    public int instrumentYear(String[] userInput) throws incorrectAddInstrumentException {
        if (userInput == null || userInput.length <= 2 || userInput[2].isEmpty()) {
            throw new incorrectAddInstrumentException("Instrument year is missing");
        }

        try {
            return Integer.parseInt(userInput[2].trim());
        } catch (NumberFormatException e) {
            throw new incorrectAddInstrumentException("Invalid instrument year: " + userInput[2]);
        }
    }


    public boolean isRented(String[] userInput) {
        return userInput.length > 3 && Boolean.parseBoolean(userInput[3]);
    }

    public boolean isOverdue(String[] userInput) {
        return userInput.length > 4 && Boolean.parseBoolean(userInput[4]);
    }

    public String rentedFrom(String[] userInput) {
        return (userInput.length > 5 && userInput[5] != null) ? userInput[5] : "";
    }

    public String rentedTo(String[] userInput) {
        return (userInput.length > 6 && userInput[6] != null) ? userInput[6] : "";  // Fixed incorrect index
    }
}
