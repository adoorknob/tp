package ui;

import exceptions.instrument.EmptyInstrumentListException;
import instrument.Instrument;
import user.User;
import user.UserList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

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

    public static final String TABLEHEADER1 = "Instrument:";
    public static final String TABLEHEADER2 = "Total QTY:";
    public static final String TABLEHEADER3 = "Rented QTY:";
    public static final String TABLEHEADER4 = "Available QTY:";
    public static final String PADDING = "  ";
    public static final Integer CRITICAL_QTY = 2;
    public static final Integer WARNING_QTY = 5;

    public static final String FILTERNAME = "name";
    public static final String FILTERMODEL = "model";
    public static final String FILTERYEAR = "year";
    public static final String FILTERRESERVED = "reserved";
    public static final String FILTERAVAILABLE = "available";

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

    public static final String TEXTBORDER = "*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-+-*-+=+-+-*-+";

    private static final String COMMANDLIST = """
            Available Commands:
            help: list all commands
            list: list all instruments
            add: adds a new instrument
            delete: deletes an existing instrument
            reserve: reserves an available instrument
            extend: changes the return date of a reserved instrument
            return: returns a reserved instrument
            user: choose user commands
            recommend: recommends a recommended instrument
            finance: Manage finances: (use help flag to see commands)
            exit: quit SirDukeBox""";

    private static final String FINANCECOMMANDLIST = """
            `help` help
            `add:` add inflow payment
            `subtract:` subtract outflow payment
            `get` get total cash
            """;

    private static final String USERLISTCHOICES = """
            Available List Choices:
            1. Rental History
            2. Current Instruments""";

    private static final int USERLISTCHOICES_LENGTH = 2;

    private static final String USERCOMMANDS = """
            1. Add User
            2. Remove User
            3. Print list of users
            4. Print list of instruments of specific user""";

    private static final int USERCOMMANDS_LENGTH = 4;

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
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
        return (parsedInput.length > 0) ? parsedInput[0] : "";
    }

    public String getRemainingWords(String userInput) {
        assert userInput != null : "Input is null";
        try {
            String[] parsedInput = userInput.split(" ");
            return String.join(" ", Arrays.copyOfRange(parsedInput, 1, parsedInput.length));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void printCommandList() {
        System.out.println(TEXTBORDER);
        System.out.println("Here is a list of available commands:");
        System.out.println(COMMANDLIST);
    }

    public void printFinanceCommandList() {
        System.out.println(TEXTBORDER);
        System.out.println("Here is a list of available finance commands:");
        System.out.println(FINANCECOMMANDLIST);
    }

    public void printEmptyList() {
        System.out.println(TEXTBORDER);
        System.out.println("List is empty, let's add some instruments :)");
    }

    public void printRecommendation(Instrument instrument, int index) {
        System.out.println(TEXTBORDER);

        System.out.println("Here is our recommendation: ");
        System.out.println(index + ". " + instrument.toString());

        System.out.println(TEXTBORDER);
    }

    public void printInstrumentList(ArrayList<Instrument> instruments) {
        System.out.println(TEXTBORDER);
        if (instruments.size() <= 0) {
            throw new EmptyInstrumentListException("List is empty, let's add some instruments :)");
        }
        System.out.println("Here is the list of instruments:");

        for (int i = 0; i < instruments.size(); i++) {
            System.out.println((i + 1) + ". " + instruments.get(i).toString());
        }

    }

    public void printStockList(ArrayList<Instrument> instruments) {
        System.out.println(TEXTBORDER);
        System.out.println("Here is the remaining stock of instruments:");

        HashMap<String, Integer> stockCount = new HashMap<>();
        HashMap<String, Integer> rentCount = new HashMap<>();
        String longestName = TABLEHEADER1;
        Integer maxCount = 1;

        for (Instrument inst : instruments) {
            if (stockCount.containsKey(inst.name)) {
                Integer currCount = stockCount.get(inst.name);
                if (currCount > maxCount) {
                    maxCount = currCount;
                }
                stockCount.replace(inst.name, currCount + 1);
            } else {
                stockCount.put(inst.name, 1);
                if (inst.name.length() > longestName.length()) {
                    longestName = inst.name;
                }
            }
        }

        for (Instrument inst : instruments) {
            if (inst.isRented()) {
                if (rentCount.containsKey(inst.name)) {
                    Integer currCount = rentCount.get(inst.name);
                    rentCount.replace(inst.name, currCount + 1);
                } else {
                    rentCount.put(inst.name, 1);
                }
            }
        }

        printTableLines(TABLEHEADER1, TABLEHEADER2, TABLEHEADER3, TABLEHEADER4, RESET, longestName);

        for (Map.Entry<String, Integer> entry : stockCount.entrySet()) {
            String instName = entry.getKey();
            Integer instCount = entry.getValue();
            Integer rentedCount = (rentCount.get(instName) == null ? 0 : rentCount.get(instName));
            Integer availCount = instCount - rentedCount;
            if (availCount < CRITICAL_QTY) { // critical, must replenish soon
                printTableLines(instName, Integer.toString(instCount), Integer.toString(rentedCount),
                        Integer.toString(availCount), RED, longestName);
            } else if (availCount < WARNING_QTY) {
                printTableLines(instName, Integer.toString(instCount), Integer.toString(rentedCount),
                        Integer.toString(availCount), YELLOW, longestName);
            } else {
                printTableLines(instName, Integer.toString(instCount), Integer.toString(rentedCount),
                        Integer.toString(availCount), RESET, longestName);
            }
        }
    }

    public void printTableLines(String col1, String col2, String col3, String col4, String colour, String longestName) {
        String line = "|" + col1 + " ".repeat(longestName.length() - col1.length()) + PADDING
                + "|" + col2 + " ".repeat(TABLEHEADER2.length() - col2.length()) + PADDING
                + "|" + col3 + " ".repeat(TABLEHEADER3.length() - col3.length()) + PADDING
                + "|" + colour + col4 + RESET + " ".repeat(TABLEHEADER4.length() - col4.length()) + PADDING + "|";
        System.out.println(line);
    }

    public void printFilteredList(ArrayList<Instrument> instruments, String filter, String searchTerm) {
        ArrayList<Instrument> filteredInst;
        switch (filter) {
        case FILTERNAME:
            filteredInst = filterByName(instruments, searchTerm);
            break;
        case FILTERMODEL:
            filteredInst = filterByModel(instruments, searchTerm);
            break;
        case FILTERYEAR:
            filteredInst = filterByYear(instruments, searchTerm);
            break;
        case FILTERRESERVED:
            filteredInst = filterByReserved(instruments, true);
            break;
        case FILTERAVAILABLE:
            filteredInst = filterByReserved(instruments, false);
            break;
        default:
            System.out.println("The specified filter does not exist. Please try again");
            System.out.println(TEXTBORDER);
            return;
        }
        printInstrumentList(filteredInst);
    }

    public ArrayList<Instrument> filterByName(ArrayList<Instrument> instruments, String searchTerm) {
        ArrayList<Instrument> filteredInst = new ArrayList<>();
        for (Instrument inst : instruments) {
            if (inst.name.equals(searchTerm)) {
                filteredInst.add(inst);
            }
        }
        return filteredInst;
    }

    public ArrayList<Instrument> filterByModel(ArrayList<Instrument> instruments, String searchTerm) {
        ArrayList<Instrument> filteredInst = new ArrayList<>();
        for (Instrument inst : instruments) {
            if (inst.model.equals(searchTerm)) {
                filteredInst.add(inst);
            }
        }
        return filteredInst;
    }

    public ArrayList<Instrument> filterByYear(ArrayList<Instrument> instruments, String searchTerm) {
        ArrayList<Instrument> filteredInst = new ArrayList<>();
        for (Instrument inst : instruments) {
            if (inst.year == Integer.parseInt(searchTerm)) {
                filteredInst.add(inst);
            }
        }
        return filteredInst;
    }

    public ArrayList<Instrument> filterByReserved(ArrayList<Instrument> instruments, boolean status) {
        ArrayList<Instrument> filteredInst = new ArrayList<>();
        for (Instrument inst : instruments) {
            if (inst.isRented() == status) {
                filteredInst.add(inst);
            }
        }
        return filteredInst;
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

    public int queryUserIndex(UserList userList) {
        assert userList != null : "userList is null";
        if (userList.getUserCount() == 0) {
            printNoCurrentUsersCreatingNewUser();
            return 0;
        }
        printSelectUserFromListWithCreateOption(userList.getUsers());
        int maxValue = userList.getUserCount();
        return getUserInputNumber(1, maxValue);
    }

    private int getUserInputNumber(int minValue, int maxValue) {
        String userInput = readUserInput();
        while (!isUserIntInputValid(userInput, minValue, maxValue)) {
            userInput = readUserInput();
        }
        return Integer.parseInt(userInput);
    }

    private boolean isUserIntInputValid(String input, int minValue, int maxValue) {
        if (!input.matches("-?\\d+")) {
            printPleaseInputANumber();
        } else if (Integer.parseInt(input) < minValue || Integer.parseInt(input) > maxValue) {
            printPleaseInputValidNumber(minValue, maxValue);
        } else {
            return true;
        }
        return false;
    }

    private void printPleaseInputValidNumber(int minValue, int maxValue) {
        printMessageWithTextBorder("Please input a number between " + minValue + " and " + maxValue);
    }

    public String queryUserNameWithNoNameChoice() {
        System.out.println("Enter user name (Leave empty if no name): ");
        return readUserInput();
    }

    public int queryUserIndexForDelete(UserList userList) {
        printUserList(userList.getUsers());
        int maxValue = userList.getUserCount();
        System.out.println("Enter user index of the user you want to delete: ");
        return getUserInputNumber(1, maxValue);
    }

    public void printNoCurrentUsersCreatingNewUser() {
        printMessageWithTextBorder("No users exist currently. Creating new user...");
    }

    public void printSelectUserFromListWithCreateOption(ArrayList<User> userList) {
        System.out.println(TEXTBORDER);
        System.out.println("Please select from the following users:");
        printUserList(userList);
        System.out.println("...or enter '0' to create a new user");
    }

    public void printUserListDisplay(UserList userList) {
        if (userList.isEmpty()) {
            printMessageWithTextBorder("No users exist currently.");
            return;
        }
        System.out.println(TEXTBORDER);
        System.out.println("Here is a list of registered users:");
        printUserList(userList.getUsers());
        System.out.println(TEXTBORDER);
    }

    public void printAcknowledgementCreatedNewUser(String userName) {
        printMessageWithTextBorder("Created new user: " + userName);
    }

    public void printAcknowledgementDeletedUser(String userName) {
        printMessageWithTextBorder("Deleted user: " + userName);
    }

    public void printAddInstrumentToUser(Instrument instrument) {
        printMessageWithTextBorder("Added instrument to user: " + instrument);
    }

    public void printRemovedInstrumentFromUser(Instrument instrument, User user) {
        printMessageWithTextBorder("Removed instrument [" + instrument + "] from user [" + user.getName() + "]");
    }

    public int queryUserInstrumentListUserChoice(UserList userList) {
        printSelectUserFromList(userList.getUsers());
        int maxValue = userList.getUserCount();
        return getUserInputNumber(1, maxValue);
    }

    public int queryUserInstrumentListListChoice() {
        printMessageWithTextBorder(USERLISTCHOICES);
        return getUserInputNumber(1, USERLISTCHOICES_LENGTH);
    }

    private void printSelectUserFromList(ArrayList<User> userList) {
        System.out.println(TEXTBORDER);
        System.out.println("Please select from the following existing users:");
        printUserList(userList);
    }

    private void printUserList(ArrayList<User> userList) {
        for (int i = 1; i < userList.size(); i++) {
            System.out.println((i) + ". " + userList.get(i).getName());
        }
    }

    private void printPleaseInputANumber() {
        printMessageWithTextBorder("Please input a number");
    }

    public boolean isInstrumentAssignedToUser() {
        System.out.println("Would you like to assign this instrument to a user? [Y/N]");
        return scanner.nextLine().equalsIgnoreCase("y");
    }

    public int queryUserCommandChoice() {
        printListOfUserCommands();
        return getUserInputNumber(1, USERCOMMANDS_LENGTH);
    }

    private void printListOfUserCommands() {
        System.out.println(TEXTBORDER);
        System.out.println("What would you like to do?");
        System.out.println(USERCOMMANDS);
        System.out.println(TEXTBORDER);
    }

    private void printMessageWithTextBorder(String message) {
        System.out.println(TEXTBORDER);
        System.out.println(message);
        System.out.println(TEXTBORDER);
    }

    public void printAmount(long amount) {
        System.out.println(TEXTBORDER);
        System.out.println("Total Amount is " + amount);
    }

    public void printReceivedAmount(long amount) {
        System.out.println(TEXTBORDER);
        System.out.println("Received payment of: " + amount);
    }

    public void printPaymentAmount(long amount) {
        System.out.println(TEXTBORDER);
        System.out.println("Transferred payment of: " + amount);
    }

    public void printTextBorder() {
        System.out.println(TEXTBORDER);
    }
}
