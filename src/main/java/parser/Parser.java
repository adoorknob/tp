package parser;

import exceptions.incorrectAddInstrumentException;
import command.*;

public class Parser {
    private static final String HELP = "help";
    private static final String LIST = "list";
    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String RESERVE = "reserve";
    private static final String RETURN = "return";
    private static final String EXIT = "exit";

    public Parser() {}

    public static Command parse(String command, String input) {
        switch (command) {
        case HELP:
            return new HelpCommand();
        case LIST:
            return new ListCommand();
        case ADD:
            return new AddInstrumentCommand(input);
        case DELETE:
            return new DeleteCommand(input);
        case RESERVE:
            return new ReserveCommand(input);
        case RETURN:
            return new ReturnCommand(input);
        case EXIT:
            return new ExitCommand();
        default:
            return new DefaultCommand();
        }
    }

    public String[] separateNMY(String input) throws incorrectAddInstrumentException{
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
}
