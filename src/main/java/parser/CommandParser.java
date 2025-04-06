package parser;

import exceptions.instrument.IncorrectAddInstrumentException;
import exceptions.instrument.InvalidAddInstrumentException;
import utils.IsOverdueChecker;
import utils.DateTimeParser;
import utils.TimeChecker;

import java.time.LocalDate;

public class CommandParser {
    private static Integer currYEAR = TimeChecker.getCurrentYear();
    private static Integer minYEAR = 1600;


    public CommandParser() {}

    public String[] separate(String input) throws IncorrectAddInstrumentException {
        if (input == null || input.isEmpty()) {
            throw new IncorrectAddInstrumentException("Input is Empty");
        }
        String[] split = Parser.parseFileEntryToInstrument(input);

        if (split.length < 3) {
            throw new IncorrectAddInstrumentException("Input format is invalid: missing fields");
        }

        try {
            Integer.parseInt(split[2]);
            return split;
        } catch (NumberFormatException e) {
            throw new IncorrectAddInstrumentException("Input year or usage is invalid");
        }
    }

    public String justGetInstrument(String input) throws IncorrectAddInstrumentException {
        if (input == null || input.isEmpty()) {
            throw new IncorrectAddInstrumentException("Input is Empty");
        }

        String instrument = input.trim();
        return instrument;
    }

    public String[] splits(String input) {
        String[] splitInput = input.split("\\s+");

        // Trim each element (just in case there are extra spaces around)
        for (int i = 0; i < splitInput.length; i++) {
            splitInput[i] = splitInput[i].trim();
        }

        return splitInput;
    }

    public String instrumentName(String[] userInput) throws IncorrectAddInstrumentException {
        if (userInput == null || userInput.length == 0 || userInput[0].isEmpty()) {
            throw new IncorrectAddInstrumentException(" is Empty");
        }
        String instrument = userInput[0].trim();
        instrument = instrument.toLowerCase();

        return userInput[0];
    }

    public String modelName(String[] userInput) throws IncorrectAddInstrumentException {
        if (userInput == null || userInput.length < 1 || userInput[1].isEmpty()) {
            throw new IncorrectAddInstrumentException("Input is Empty");
        }
        return userInput[1];
    }

    public int instrumentYear(String[] userInput) throws IncorrectAddInstrumentException {
        if (userInput == null || userInput.length <= 2 || userInput[2].isEmpty()) {
            throw new IncorrectAddInstrumentException("Instrument year is missing");
        }

        try {
            int output = Integer.parseInt(userInput[2].trim());
            assert output > 0 : "Output year must be greater than zero";
            assert output <= currYEAR : "Output year must not be greater than current year";
            assert output > minYEAR : "Output year must be greater than min year";
            return output;
        } catch (NumberFormatException e) {
            throw new IncorrectAddInstrumentException("Invalid instrument year: " + userInput[2]);
        } catch (AssertionError e) {
            throw new IncorrectAddInstrumentException("Invalid instrument year: " + e.getMessage());
        }
    }


    public boolean isRented(String[] userInput, boolean isStorageInstrument) {
        return isStorageInstrument && userInput.length > 3 && Boolean.parseBoolean(userInput[3]);
    }

    public boolean isOverdue(String[] userInput, boolean isStorageInstrument) {
        if (isStorageInstrument && userInput.length > 6 && !userInput[6].isBlank()) {
            LocalDate dueDate = DateTimeParser.parseDate(userInput[6]);
            return IsOverdueChecker.isOverdue(dueDate);
        }
        return false;
    }


    public LocalDate rentedFrom(String[] userInput, boolean isStorageInstrument) {
        return (isStorageInstrument && userInput.length > 5 && userInput[5] != null) ?
                DateTimeParser.parseDate(userInput[5]) : null;
    }

    public LocalDate rentedTo(String[] userInput, boolean isStorageInstrument) {
        return (isStorageInstrument && userInput.length > 6 && userInput[6] != null) ?
                DateTimeParser.parseDate(userInput[6]) : null;
    }

    public int getUsage(String[] userInput, boolean isStorageInstrument) throws IncorrectAddInstrumentException {
        if (isStorageInstrument &&  userInput.length > 8 && userInput[8].isEmpty()) {
            try {
                return Integer.parseInt(userInput[8].trim());
            } catch (NumberFormatException e) {
                throw new InvalidAddInstrumentException("Invalid usage: " + userInput[8]);
            }
        }
        return 0;
    }

    public String getUser(String[] userInput, boolean isStorageInstrument) {
        if (isStorageInstrument && userInput.length > 7 && !userInput[7].isEmpty()) {
            return userInput[7].trim();
        }
        return null;
    }
}
