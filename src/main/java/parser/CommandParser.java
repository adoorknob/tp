package parser;

import exceptions.IncorrectAddInstrumentException;
import utils.IsOverdueChecker;
import utils.DateTimeParser;

import java.time.LocalDateTime;

public class CommandParser {
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
            return Integer.parseInt(userInput[2].trim());
        } catch (NumberFormatException e) {
            throw new IncorrectAddInstrumentException("Invalid instrument year: " + userInput[2]);
        }
    }


    public boolean isRented(String[] userInput) {
        return userInput.length > 3 && Boolean.parseBoolean(userInput[3]);
    }

    public boolean isOverdue(String[] userInput) {
        if (userInput.length > 6 && !userInput[6].isBlank()) {
            LocalDateTime dueDate = DateTimeParser.parseDateTime(userInput[6]);
            return IsOverdueChecker.isOverdue(dueDate);
        }
        return false;
    }


    public LocalDateTime rentedFrom(String[] userInput) {
        return (userInput.length > 5 && userInput[5] != null) ? DateTimeParser.parseDateTime(userInput[5]) : null;
    }

    public LocalDateTime rentedTo(String[] userInput) {
        return (userInput.length > 6 && userInput[6] != null) ? DateTimeParser.parseDateTime(userInput[6]) : null;
    }

    public int usage(String[] userInput) throws IncorrectAddInstrumentException {
        if (userInput == null || userInput.length <= 7 || userInput[7].isEmpty()) {
            throw new IncorrectAddInstrumentException("Instrument usage is missing");
        }

        try {
            return Integer.parseInt(userInput[7].trim());
        } catch (NumberFormatException e) {
            throw new IncorrectAddInstrumentException("Invalid usage: " + userInput[7]);
        }
    }
}
