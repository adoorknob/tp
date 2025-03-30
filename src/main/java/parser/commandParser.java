package parser;

import exceptions.incorrectAddInstrumentException;
import exceptions.incorrectReserveInstrumentException;

import java.time.DateTimeException;
import java.time.LocalDate;

public class commandParser {
    public static String[] separateNMY(String input) throws incorrectAddInstrumentException {
        if (input == null || input.isEmpty()) {
            throw new incorrectAddInstrumentException("Input is Empty");
        }
        String[] split = input.split("\\|");
        if (split.length != 3) {
            throw new incorrectAddInstrumentException("Input instrument is invalid");
        }
        try {
            Integer.parseInt(split[2]);
            return split;
        } catch (NumberFormatException e) {
            throw new incorrectAddInstrumentException("Input year is invalid");
        }
    }

    public static String[] separateNY(String input) throws incorrectReserveInstrumentException {
        if (input == null || input.isEmpty()) {
            throw new incorrectReserveInstrumentException("Input is Empty");
        }
        String[] split = input.split("\\|");
        if (split.length != 2) {
            throw new incorrectReserveInstrumentException("Input instrument is invalid");
        }
        try {
            Integer.parseInt(split[0]);
            LocalDate.parse(split[1]);
            return split;
        } catch (NumberFormatException e) {
            throw new incorrectReserveInstrumentException("Input return date is invalid");
        } catch (DateTimeException d) {
            throw new incorrectReserveInstrumentException("Return date does not exist. Please try again");
        }
    }
}