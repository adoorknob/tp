package ui;

import instrument.Instrument;

import java.io.IOException;
import java.util.*;

public class Ui {

    // ANSI color codes
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final Integer CRITICAL_QTY = 2;
    public static final Integer WARNING_QTY = 5;

    public static final String DUKEBOX = """
                            _.-'\\       /'-._
                        _.-'    _\\ .-. /_    '-._
                     .-'    _.-' |/.-.\\| '-._    '-.
                   .'    .-'    _||   ||_    '-.    '.
                  /    .'    .-' ||___|| '-.    '.    \\
                 /   .'   .-' _.-'-----'-._ '-.   '.   \\
                /   /   .' .-' ~       ~   '-. '.   \\   \\
               /   /   / .' ~   *   ~     ~   '. \\   \\   \\
              /   /   /.'........    *  ~   *  ~'.\\   \\   \\
              |  /   //:::::::::: ~   _____._____ \\\\   \\  |
              |  |  |/:::::::::::  * '-----------' \\|  |  |
             .--.|__||_____________________________||__|.--.
            .'   '----. .-----------------------. .----'   '.
            '.________' |o|==|o|====:====|o|==|o| '________.'
            '.________' |o|==|o|====:====|o|==|o| '________.'
            '.________' |o|==|o|====:====|o|==|o| '________.'
              |  |  ||  ____ |:| | | | | |:| ____  ||  |  |
              |  |  || |    ||:| | | | | |:||    | ||  |  |
              |  |  || |____||:           :||____| ||  |  |
              |  |  ||  |   /|:| | | | | |:|\\   |  ||  |  |
              |  |  ||  |_.` |:| | | | | |:| `._|  ||  |  |
              |  |  || .---.-'-'-'-'-'-'-'-'-.---. ||  |  |
              |  |  || |   |\\  /\\  / \\  /\\  /|   | ||  |  |
              |  |  || |   |~\\/  \\/ ~ \\/  \\/ |   | ||  |  |
              |  |  || |   | /\\ ~/\\ ~ /\\ ~/\\ |   | ||  |  |
              |  |  || |===|/  \\/  .-.  \\/  \\|===| ||  |  |
              |  |  || |   | ~ /\\ ( * ) /\\ ~ |   | ||  |  |
              |  |  || |    \\ /  \\/'-'\\/  \\ /    | ||  |  |
             /-._|__||  \\    \\ ~ /\\ ~ /\\~  /    /  ||__|_.-\\
             |-._/__/|   \\    './  .-.  \\.'    /   |\\__\\_.-|
             | | |  ||    '._    '-| |-'    _.'    ||  | | |
             | | |  ||      '._    | |    _.'      ||  | | |
             | | |  ||  __     '-._| |_.-'         ||  | | |
             | | |  || O__O        |_|             ||  | | |
             '.|_|__||_____________________________||__|_|.'
              |  |   [_____________________________]   |  |
              |  |   |/                           \\|   |  |
              '._|__.'                             '.__|_.'
            
            
                             ____          _       , __          \s
               () o         (|   \\        | |     /|/  \\         \s
               /\\     ,_     |    |       | |   _  | __/ __      \s
              /  \\|  /  |   _|    ||   |  |/_) |/  |   \\/  \\_/\\/ \s
             /(__/|_/   |_/(/\\___/  \\_/|_/| \\_/|__/|(__/\\__/  /\\_/
                                                                 \s
                                                                 \s
            
            """;

    private static final String TEXTBORDER = "*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-+-*-+=+-+-*-+";

    private static final String COMMANDLIST = """
            Available Commands:
            help: list all commands
            list: list all instruments
            add: adds a new instrument
            delete: deletes an existing instrument
            reserve: reserves an available instrument
            extend: changes the return date of a reserved instrument
            return: returns a reserved instrument
            exit: quit SirDukeBox""";;

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void print(String s) {
        System.out.println(s);
    }

    public void printStartMessage() {
        System.out.println("Welcome to");
        System.out.println(DUKEBOX);
        System.out.println("How can I help you?");
        System.out.println(TEXTBORDER);
    }

    public String readUserInput() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }

    public String getCommand(String userInput) throws IOException {
        assert userInput != null : "Input is null";
        String[] parsedInput = userInput.split(" ");
        return parsedInput.length > 0 ? parsedInput[0] : "";
    }

    public String getRemainingWords(String userInput) {
        assert userInput != null : "Input is null";
        String[] parsedInput = userInput.split(" ");
        return String.join(" ", Arrays.copyOfRange(parsedInput, 1, parsedInput.length));
    }

    public void printCommandList() {
        System.out.println(TEXTBORDER);
        System.out.println("Here is a list of available commands:");
        System.out.println(COMMANDLIST);
        System.out.println(TEXTBORDER);
    }

    public void printInstrumentList(ArrayList<Instrument> instruments) {
        System.out.println(TEXTBORDER);
        System.out.println("Here is the list of instruments:");

        for (int i = 0; i < instruments.size(); i++) {
            System.out.println((i + 1) + ". " + instruments.get(i).toString());
        }

        System.out.println(TEXTBORDER);
    }

    public void printStockList(ArrayList<Instrument> instruments) {
        System.out.println(TEXTBORDER);
        System.out.println("Here is the remaining stock of instruments:");

        HashMap<String, Integer> stockCount = new HashMap<>();

        for (Instrument inst : instruments) {
            if (stockCount.containsKey(inst.name)) {
                Integer currCount = stockCount.get(inst.name);
                stockCount.replace(inst.name, currCount + 1);
            } else {
                stockCount.put(inst.name, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : stockCount.entrySet()) {
            String instName = entry.getKey();
            Integer instCount = entry.getValue();
            if (instCount < CRITICAL_QTY) { // critical, must replenish soon
                System.out.println(instName + ": " + RED + instCount + RESET);
            } else if (instCount < WARNING_QTY) {
                System.out.println(instName + ": " + YELLOW + instCount + RESET);
            } else {
                System.out.println(instName + ": " + instCount);
            }
        }
        System.out.println(TEXTBORDER);
    }

    public void printGoodbye() {
        printMessageWithTextBorder("bye bye");
    }

    public void printNoMatchingCommandError() {
        printMessageWithTextBorder("No matching command found");
    }

    public void printDirectoryAlreadyExists() {
        printMessageWithTextBorder("Directory already exists");
    }

    public void printFileAlreadyExists() {
        printMessageWithTextBorder("File already exists");
    }

    public void printCreatingDirectory(String directory) {
        printMessageWithTextBorder("Creating directory: " + directory);
    }

    public void printCreatingFile(String file) {
        printMessageWithTextBorder("Creating file: " + file);
    }

    private void printMessageWithTextBorder(String message) {
        System.out.println(TEXTBORDER);
        System.out.println(message);
        System.out.println(TEXTBORDER);
    }
}
