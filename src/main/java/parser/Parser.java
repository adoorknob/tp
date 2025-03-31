package parser;

import commands.*;


public class Parser {
    private static final String HELP = "help";
    private static final String LIST = "list";
    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String RESERVE = "reserve";
    private static final String EXTEND = "extend";
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
        case EXTEND:
            return new ExtendCommand(input);
        case RETURN:
            return new ReturnCommand(input);
        case EXIT:
            return new ExitCommand();
        default:
            return new DefaultCommand();
        }
    }

    public static String parseFileDirectories(String outputFilePath) {
        int index = outputFilePath.lastIndexOf("/");
        return outputFilePath.substring(0, index);
    }

    public static String[] parseFileEntryToInstrument(String line) {
        String[] splitInput =  line.split("\\|");
        for (int i = 0; i < splitInput.length; i++) {
            splitInput[i] = splitInput[i].trim();
        }
        return splitInput;
    }
}
