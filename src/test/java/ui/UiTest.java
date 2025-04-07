package ui;

import exceptions.instrument.EmptyInstrumentListException;
import instrument.Flute;
import instrument.Guitar;
import instrument.Instrument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user.User;
import user.UserList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UiTest {
    private Ui ui;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        ui = new Ui();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void printStartMessage_callingPrintStartMessage_expectStartMessage() {
        ui.printStartMessage();
        String output = outputStream.toString();
        assertTrue(output.contains("Welcome to"));
        assertTrue(output.contains(Ui.DUKEBOX));
    }

    @Test
    void readUserInput_callingReadUserInput_expectList() {
        String input = "list\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui testUi = new Ui();
        assertEquals("list", testUi.readUserInput());
    }

    @Test
    void getCommand_emptyInput_expectEmptyString() {
        String input = "";
        assertEquals("", ui.getCommand(input));
    }

    @Test
    void getCommand_nullInput_expectAssertionError() {
        assertThrows(AssertionError.class, () -> {
            ui.getCommand(null);
        });
    }

    @Test
    void getCommand_inputWithSpaces_expectEmptyString() {
        String input = "help me";
        assertEquals("help", ui.getCommand(input));
    }

    @Test
    void getCommand_inputWithoutSpaces_expectEmptyString() {
        String input = "help";
        assertEquals("help", ui.getCommand(input));
    }

    @Test
    void getRemainingWords_singleWordInput_expectEmptyString() {
        String input = "help";
        assertEquals("", ui.getRemainingWords(input));
    }

    @Test
    void getRemainingWords_multipleSpacedWordInput_expectEmptyString() {
        String input = "help me pls";
        assertEquals("me pls", ui.getRemainingWords(input));
    }

    @Test
    void getRemainingWords_nullInput_expectEmptyString() {
        assertThrows(AssertionError.class, () -> {
            ui.getCommand(null);
        });
    }



    @Test
    void testPrintCommandList() {
        ui.printCommandList();
        String output = outputStream.toString();
        assertTrue(output.contains("""
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
            exit: quit SirDukeBox"""));

    }

    @Test
    void printListSubcommandList_callFunction_expectSubcommandList() {
        ui.printListSubcommandList();
        String output = outputStream.toString();
        assertTrue(output.contains("""
            Available Subcommands:
            
            help: list all subcommands for `list`
            stock: list total, available and reserved quantities for each instrument
            filter by <FILTER> <SEARCH_TERM>: list instruments according to FILTER and SEARCH_TERM
            
            Available FILTERs and SEARCHTERMs: 
            
            FILTER: name, SEARCH_TERM: INSTRUMENT_NAME
            FILTER: model, SEARCH_TERM: INSTRUMENT_MODEL
            FILTER: year, SEARCH_TERM: INSTRUMENT_YEAR
            FILTER: reserved (SEARCH_TERM is not required)
            FILTER: available (SEARCH_TERM is not required)"""));
    }

    @Test
    void printFinanceCommandList_callFunction_expectFinanceCommandList() {
        ui.printFinanceCommandList();
        String output = outputStream.toString();
        assertTrue(output.contains("""
            `help` help
            `add:` add inflow payment
            `subtract:` subtract outflow payment
            `get` get total cash
            """));
    }

    @Test
    void printEmptyList_callFunction_expectEmptyList() {
        ui.printEmptyList();
        String output = outputStream.toString();
        assertTrue(output.contains("List is empty, let's add some instruments :)"));
    }

    @Test
    void printRecommendation_fluteWithIndex0_expectRecommendation() {
        Flute flute = new Flute("Flute","Model",2005);
        ui.printRecommendation(flute,0);
        String output = outputStream.toString();
        assertTrue(output.contains("0. Flute | Model | 2005"));
    }

    @Test
    void printInstrumentList_twoInstruments_expectRecommendation() {
        Flute flute = new Flute("Flute","Model",2005);
        Guitar guitar = new Guitar("Guitar","Model",2005);
        ArrayList<Instrument> instruments = new ArrayList<>();
        instruments.add(flute);
        instruments.add(guitar);

        ui.printInstrumentList(instruments);
        String output = outputStream.toString();
        assertTrue(output.contains("2. Guitar | Model | 2005"));

    }

    @Test
    void printInstrumentList_oneInstruments_expectRecommendation() {
        Flute flute = new Flute("Flute","Model",2005);
        ArrayList<Instrument> instruments = new ArrayList<>();
        instruments.add(flute);

        ui.printInstrumentList(instruments);
        String output = outputStream.toString();
        assertTrue(output.contains("1. Flute | Model | 2005"));

    }

    @Test
    void printInstrumentList_zeroInstruments_expectRecommendation() {
        ArrayList<Instrument> instruments = new ArrayList<>();
        assertThrows(EmptyInstrumentListException.class, () -> {
            ui.printInstrumentList(instruments);
        });

    }

    @Test
    void printStockList_callFunction_expectStockList() {
        Flute flute = new Flute("Flute","Model",2005);
        Guitar guitar = new Guitar("Guitar","Model",2005);
        ArrayList<Instrument> instruments = new ArrayList<>();
        instruments.add(flute);
        instruments.add(guitar);

        ui.printStockList(instruments);

        String output = outputStream.toString();
        assertTrue(output.contains("Here is the remaining stock of instruments:"));
    }

    @Test
    void printTableLines_allValidArguments_expectTableLines() {
        String col1 = "TestCol1";
        String col2 = "TestCol2";
        String col3 = "TestCol3";
        String col4 = "TestCol4Long";
        String colour = Ui.RED;
        String longestName = "TestCol4Long";

        ui.printTableLines(col1, col2, col3, col4, colour, longestName);

        String output = outputStream.toString();

        assertTrue(output.contains(Ui.RED));
        assertTrue(output.contains(Ui.RESET));
        assertTrue(output.contains("|TestCol1      |TestCol2    |TestCol3     |"));
    }

    @Test
    void  printFilteredList_byName_expectFilteredList() {
        Flute flute = new Flute("Flute","Model",2005);
        Guitar guitar = new Guitar("Guitar","Model",2005);
        ArrayList<Instrument> instruments = new ArrayList<>();
        instruments.add(flute);
        instruments.add(guitar);

        ui.printFilteredList(instruments, Ui.FILTERNAME, "Guitar");

        String output = outputStream.toString();

        assertTrue(output.contains("1. Guitar | Model | 2005"));
    }

    @Test
    void  printFilteredList_byModel_expectFilteredList() {
        Flute flute = new Flute("Flute","Model",2005);
        Guitar guitar = new Guitar("Guitar","Model",2005);
        ArrayList<Instrument> instruments = new ArrayList<>();
        instruments.add(flute);
        instruments.add(guitar);

        ui.printFilteredList(instruments, Ui.FILTERMODEL, "Model");

        String output = outputStream.toString();

        assertTrue(output.contains("2. Guitar | Model | 2005"));
    }

    @Test
    void  printFilteredList_byYear_expectFilteredList() {
        Flute flute = new Flute("Flute","Model",2002);
        Guitar guitar = new Guitar("Guitar","Model",2005);
        ArrayList<Instrument> instruments = new ArrayList<>();
        instruments.add(flute);
        instruments.add(guitar);

        ui.printFilteredList(instruments, Ui.FILTERYEAR, "2002");

        String output = outputStream.toString();

        assertTrue(output.contains("1. Flute | Model | 2002"));
    }

    @Test
    void  filterByName_twoSameNames_expectFilteredList() {
        Flute flute = new Flute("Flute", "Model", 2002);
        Guitar guitar = new Guitar("Guitar", "Model", 2005);
        Guitar guitar2 = new Guitar("Guitar", "Model", 2012);
        ArrayList<Instrument> instruments = new ArrayList<>();
        instruments.add(flute);
        instruments.add(guitar);
        instruments.add(guitar2);

        ArrayList<Instrument> filtered = ui.filterByName(instruments, "Guitar");

        assertEquals(2, filtered.size());
        for (Instrument inst : filtered) {
            assertEquals("Guitar", inst.name);
        }
    }

    @Test
    void  filterByName_threeSameModels_expectFilteredList() {
        Flute flute = new Flute("Flute", "Model", 2002);
        Guitar guitar = new Guitar("Guitar", "Model", 2005);
        Guitar guitar2 = new Guitar("Guitar", "Model", 2012);
        ArrayList<Instrument> instruments = new ArrayList<>();
        instruments.add(flute);
        instruments.add(guitar);
        instruments.add(guitar2);

        ArrayList<Instrument> filtered = ui.filterByModel(instruments, "Model");

        assertEquals(3, filtered.size());
        for (Instrument inst : filtered) {
            assertEquals("Model", inst.model);
        }
    }

    @Test
    void  filterByReserved_twoNotReserved_expectFilteredList() {
        Flute flute = new Flute("Flute", "Model", 2002);
        Guitar guitar = new Guitar("Guitar", "Model", 2005);
        Guitar guitar2 = new Guitar("Guitar", "Model", 2012);
        flute.rent();
        ArrayList<Instrument> instruments = new ArrayList<>();
        instruments.add(flute);
        instruments.add(guitar);
        instruments.add(guitar2);

        ArrayList<Instrument> filtered = ui.filterByReserved(instruments, false);

        assertEquals(2, filtered.size());
        for (Instrument inst : filtered) {
            assertEquals(false, inst.isRented());
        }
    }

    @Test
    void  filterByReserved_oneReserved_expectFilteredList() {
        Flute flute = new Flute("Flute", "Model", 2002);
        Guitar guitar = new Guitar("Guitar", "Model", 2005);
        Guitar guitar2 = new Guitar("Guitar", "Model", 2012);
        flute.rent();
        ArrayList<Instrument> instruments = new ArrayList<>();
        instruments.add(flute);
        instruments.add(guitar);
        instruments.add(guitar2);

        ArrayList<Instrument> filtered = ui.filterByReserved(instruments, true);

        assertEquals(1, filtered.size());
        for (Instrument inst : filtered) {
            assertEquals(true, inst.isRented());
        }
    }

    @Test
    void printGoodbye_callFunction_expectByeBye() {
        ui.printGoodbye();
        String output = outputStream.toString();
        assertTrue(output.contains("bye bye"));
    }

    @Test
    void printNoMatchingCommandError_callFunction_expectNoMatchingCommandFound() {
        ui.printNoMatchingCommandError();
        String output = outputStream.toString();
        assertTrue(output.contains("No matching command found"));
    }

    @Test
    void printDirectoryAlreadyExists_callFunction_expectDirectoryAlreadyExists() {
        ui.printDirectoryAlreadyExists();
        String output = outputStream.toString();
        assertTrue(output.contains("Directory already exists"));
    }

    @Test
    void printFileAlreadyExists_callFunction_expectFileAlreadyExists() {
        ui.printFileAlreadyExists();
        String output = outputStream.toString();
        assertTrue(output.contains("File already exists"));
    }

    @Test
    void printCreatingDirectory_validDirectory_expectCreatingDirectory() {
        String directory = "TEST";
        ui.printCreatingDirectory(directory);
        String output = outputStream.toString();
        assertTrue(output.contains("Creating directory: TEST"));
    }

    @Test
    void printCreatingDirectory_invalidDirectory_expectCreatingDirectory() {
        String directory = null;
        ui.printCreatingDirectory(directory);
        String output = outputStream.toString();
        assertTrue(output.contains("Creating directory: "));
    }

    @Test
    void printCreatingFile_validFile_expectCreatingDirectory() {
        String file = "TEST";
        ui.printCreatingFile(file);
        String output = outputStream.toString();
        assertTrue(output.contains("Creating file: TEST"));
    }

    @Test
    void printCreatingFile_invalidFile_expectCreatingDirectory() {
        String file = null;
        ui.printCreatingFile(file);
        String output = outputStream.toString();
        assertTrue(output.contains("Creating file: "));
    }

    @Test
    void queryUserIndex_emptyUserList_expect0() {
        UserList userList = new UserList(ui);
        assertEquals(0,ui.queryUserIndex(userList));

    }

    @Test
    void queryUserIndex_nullUserList_expect0() {
        assertThrows(AssertionError.class, () -> {
            ui.queryUserIndex(null);
        });
    }

    @Test
    void queryUserIndex_validUserInput_expect0() {
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Ui ui = new Ui();
        UserList userList = new UserList(ui);
        userList.addUser(new User(ui, userList, "Bobby"));
        userList.addUser(new User(ui, userList, "Jobby"));
        userList.addUser(new User(ui, userList, "Zobby"));

        int result = ui.queryUserIndex(userList);
        assertEquals(2, result);
    }

    @Test
    void getUserInputNumber_validUserInput_expect2() {
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui ui = new Ui();
        int result = ui.getUserInputNumber(1,3);
        assertEquals(2, result);
    }

    @Test
    void isUserIntInputValid_validUserInput_expectTrue() {
        String userInput = "2";

        Boolean result = ui.isUserIntInputValid(userInput,1,3);
        assertEquals(true, result);
    }

    @Test
    void isUserIntInputValid_invalidUserInput_expectTrue() {
        String userInput = "d";

        Boolean result = ui.isUserIntInputValid(userInput,1,3);
        assertEquals(false, result);
    }

    @Test
    void isUserIntInputValid_invalidUserInput2_expectTrue() {
        String userInput = "10";

        Boolean result = ui.isUserIntInputValid(userInput,1,3);
        assertEquals(false, result);
    }

    @Test
    void printPleaseInputValidNumber_callFunction_expectPleaseInputValidNumber() {

        ui.printPleaseInputValidNumber(1,10);
        String output = outputStream.toString();
        assertTrue(output.contains("Please input a number between 1 and 10"));
    }

    @Test
    void queryUserNameWithNoNameChoice_callFunction_expectEnterUserNameMessageAndTEST() {
        String input = "TEST\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Ui ui = new Ui();
        String result = ui.queryUserNameWithNoNameChoice();
        assertEquals("TEST", result);
        String output = outputStream.toString();
        assertTrue(output.contains("Enter user name (Leave empty if no name): "));
    }

    @Test
    void queryUserIndexForDelete_userListSizeOfThree_expect2() {
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Ui ui = new Ui();
        UserList userList = new UserList(ui);
        userList.addUser(new User(ui, userList, "Bobby"));
        userList.addUser(new User(ui, userList, "Jobby"));
        userList.addUser(new User(ui, userList, "Zobby"));

        int result = ui.queryUserIndexForDelete(userList);
        assertEquals(2, result);
    }

    @Test
    void printNoCurrentUsersCreatingNewUser_callFunction_expectNoCurrentUserFound() {
        ui.printNoCurrentUsersCreatingNewUser();
        String output = outputStream.toString();
        assertTrue(output.contains("No users exist currently. Creating new user..."));
    }

    @Test
    void printSelectUserFromListWithCreateOption_userListOfThree_expectUserListOf3() {
        ArrayList<User> userList = new ArrayList<>();
        UserList userList2 = new UserList(ui);
        userList.add(new User(ui, userList2, "Bobby"));
        userList.add(new User(ui, userList2, "Zobby"));
        userList.add(new User(ui, userList2, "Jobby"));


        ui.printSelectUserFromListWithCreateOption(userList);
        String output = outputStream.toString();
        assertTrue(output.contains("Please select from the following users:"));
        assertTrue(output.contains("...or enter '0' to create a new user"));
    }

    @Test
    void printUserListDisplay_userListOfThree_expectCorrectHeader() {
        UserList userList = new UserList(ui);
        userList.addUser(new User(ui, userList, "Bobby"));
        userList.addUser(new User(ui, userList, "Jobby"));
        userList.addUser(new User(ui, userList, "Zobby"));


        ui.printUserListDisplay(userList);
        String output = outputStream.toString();
        assertTrue(output.contains("Here is a list of registered users:"));
    }

    @Test
    void printUserListDisplay_emptyUserList_expectNoUsersCurrentlyExist() {
        UserList userList = new UserList(ui);


        ui.printUserListDisplay(userList);
        String output = outputStream.toString();
        assertTrue(output.contains("No users exist currently."));
    }

    @Test
    void printAcknowledgementCreatedNewUser_validUserName_expectCreatedNewUser() {

        String input = "Bobby";

        ui.printAcknowledgementCreatedNewUser(input);
        String output = outputStream.toString();
        assertTrue(output.contains("Created new user: Bobby"));
    }

    @Test
    void printAcknowledgementCreatedNewUser_invalidUserName_expectCreatedNewUser() {

        String input = null;

        ui.printAcknowledgementCreatedNewUser(input);
        String output = outputStream.toString();
        assertTrue(output.contains("Created new user: "));
    }

    @Test
    void printAcknowledgementDeletedUser_invalidUserName_expectDeletedNewUser() {

        String input = null;

        ui.printAcknowledgementDeletedUser(input);
        String output = outputStream.toString();
        assertTrue(output.contains("Deleted user: "));
    }

    @Test
    void printAcknowledgementDeletedUser_validUserName_expectDeletedNewUser() {

        String input = "Bobby";

        ui.printAcknowledgementDeletedUser(input);
        String output = outputStream.toString();
        assertTrue(output.contains("Deleted user: Bobby"));
    }

    @Test
    void printAddInstrumentToUser_flute_expectAddFlute() {

        Flute flute = new Flute("Flute","Model",2005);

        ui.printAddInstrumentToUser(flute);
        String output = outputStream.toString();
        assertTrue(output.contains("Added instrument to user: Flute | Model | 2005"));
    }

    @Test
    void printRemovedInstrumentFromUser_flute_expectRemovedFlute() {

        Flute flute = new Flute("Flute","Model",2005);

        ui.printRemovedInstrumentFromUser(flute, new User(ui, new UserList(ui), "Bobby"));
        String output = outputStream.toString();
        assertTrue(output.contains("Removed instrument [Flute | Model | 2005] from user [Bobby]"));
    }

    @Test
    void queryUserInstrumentListUserChoice_validInput_expect2() {

        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui ui = new Ui();
        UserList userList = new UserList(ui);
        userList.addUser(new User(ui, userList, "Bobby"));
        userList.addUser(new User(ui, userList, "Jobby"));
        userList.addUser(new User(ui, userList, "Zobby"));
        int result = ui.queryUserInstrumentListUserChoice(userList);
        assertEquals(2, result);
    }

    @Test
    void queryUserInstrumentListListChoice_validInput_expect2() {

        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui ui = new Ui();
        int result = ui.queryUserInstrumentListListChoice();
        assertEquals(2, result);
    }

    @Test
    void printSelectUserFromList_validInput_expectMessage() {
        ui.printSelectUserFromList(new ArrayList<>());
        String output = outputStream.toString();
        assertTrue(output.contains("Please select from the following existing users:"));
    }

    @Test
    void printUserList_userListSizeOfThree_expectUserListSizeOf3() {
        ArrayList<User> userList = new ArrayList<>();
        UserList userList2 = new UserList(ui);
        userList.add(new User(ui, userList2, "Bobby"));
        userList.add(new User(ui, userList2, "Zobby"));
        userList.add(new User(ui, userList2, "Jobby"));

        ui.printUserList(userList);
        String output = outputStream.toString();
        assertFalse(output.contains("0. Bobby"));
        assertTrue(output.contains("1. Zobby"));
        assertTrue(output.contains("2. Jobby"));
    }

    @Test
    void printPleaseInputANumber_callFunction_expectPleaseInputANumber() {

        ui.printPleaseInputANumber();
        String output = outputStream.toString();

        assertTrue(output.contains("Please input a number"));
    }

    @Test
    void isInstrumentAssignedToUser_yes_expectTrue() {
        String input = "Y\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui ui = new Ui();

        Boolean result = ui.isInstrumentAssignedToUser();
        String output = outputStream.toString();

        assertTrue(output.contains("Would you like to assign this instrument to a user? [Y/N]"));
        assertTrue(result);
    }


    @Test
    void isInstrumentAssignedToUser_no_expectFalse() {
        String input = "N\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui ui = new Ui();

        Boolean result = ui.isInstrumentAssignedToUser();
        String output = outputStream.toString();

        assertTrue(output.contains("Would you like to assign this instrument to a user? [Y/N]"));
        assertFalse(result);
    }

    @Test
    void isInstrumentAssignedToUser_randomInput_expectFalse() {
        String input = "dawdaiwujdnaw\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui ui = new Ui();

        Boolean result = ui.isInstrumentAssignedToUser();
        String output = outputStream.toString();

        assertTrue(output.contains("Would you like to assign this instrument to a user? [Y/N]"));
        assertFalse(result);
    }

    @Test
    void queryUserCommandChoice_one_expect1() {
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui ui = new Ui();

        int result = ui.queryUserCommandChoice();
        assertEquals(1, result);
    }

    @Test
    void printListOfUserCommands_callFunction_expectListOfUserCommands() {
        ui.printListOfUserCommands();
        String output = outputStream.toString();

        assertTrue(output.contains("""
            1. Add User
            2. Remove User
            3. Print list of users
            4. Print list of instruments of specific user"""));
    }

    @Test
    void printMessageWithTextBorder_message1_expectmessage1() {
        String input = "message1";
        ui.printMessageWithTextBorder(input);
        String output = outputStream.toString();

        assertTrue(output.contains("message1"));
    }

    @Test
    void printMessageWithTextBorder_emptyString_expectEmptyString() {
        String input = "";
        ui.printMessageWithTextBorder(input);
        String output = outputStream.toString();

        assertTrue(output.contains(""));
    }

    @Test
    void printAmount_oneHundred_expectTotalAmountIs100() {
        long input = 100;
        ui.printAmount(input);
        String output = outputStream.toString();

        assertTrue(output.contains("Total Amount is 100"));
    }

    @Test
    void printReceivedAmount_oneHundred_expectReceivedPaymentOf100() {
        long input = 100;
        ui.printReceivedAmount(input);
        String output = outputStream.toString();

        assertTrue(output.contains("Received payment of: 100"));
    }

    @Test
    void printPaymentAmount_oneHundred_expectTransferredPaymentOf100() {
        long input = 100;
        ui.printPaymentAmount(input);
        String output = outputStream.toString();

        assertTrue(output.contains("Transferred payment of: 100"));
    }

    @Test
    void printTextBorder_callFunction_expectTEXTBORDER() {

        ui.printTextBorder();
        String output = outputStream.toString();

        assertTrue(output.contains("*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-+-*-+=+-+-*-+"));
    }


























}
