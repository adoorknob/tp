package user;

import instrument.Instrument;
import instrument.InstrumentList;
import ui.Ui;

public class UserUtils {
    UserList userList;
    Ui ui;

    public UserUtils(Ui ui, UserList userList) {
        this.userList = userList;
        this.ui = ui;
    }

    public User queryAndAssignUser(Instrument instrument) {
        if (ui.isInstrumentAssignedToUser()) {
            return assignSpecificUser(instrument);
        } else {
            return assignUnknownUser(instrument);
        }
    }

    private User assignSpecificUser(Instrument instrument) {
        int userIndex = ui.queryUserIndex(userList);
        if (userIndex == 0) {
            return assignNewUser(instrument);
        }
        return assignExistingUser(instrument, userIndex);
    }

    private User assignExistingUser(Instrument instrument, int userIndex) {
        return assignInstrumentToUser(instrument, userIndex);
    }

    private User assignUnknownUser(Instrument instrument) {
        User user = userList.getUnassignedUser();
        user.addInstrument(instrument);
        return user;
    }

    private User assignInstrumentToUser(Instrument instrument, int userIndex) {
        User user = userList.users.get(userIndex);
        user.addInstrument(instrument);
        return user;
    }

    private User assignNewUser(Instrument instrument) {
        User newUser = addUser();
        newUser.addInstrument(instrument);
        return newUser;
    }

    private User addUser() {
        String userName = ui.queryUserNameWithNoNameChoice();
        User newUser;
        if (userName.trim().isEmpty()) {
            newUser = new User(ui, userList);
        } else {
            newUser = new User(ui, userList, userName);
        }
        userList.addUser(newUser);
        return newUser;
    }

    public void executeUserCommand(int userChoice) {
        switch (userChoice) {
        case 1 -> addUser();
        case 2 -> removeUser();
        case 3 -> printUserList();
        case 4 -> printInstrumentListOfUser();
        default -> throw new IllegalArgumentException("Invalid user choice.");
        }
    }

    private void removeUser() {
        int userId = ui.queryUserIndexForDelete(userList);
        userList.removeUserById(userId);
    }

    private void printUserList() {
        ui.printUserListDisplay(userList);
    }

    private void printInstrumentListOfUser() {
        int userId = ui.queryUserInstrumentListUserChoice(userList.getUsers());
        int listId = ui.queryUserInstrumentListListChoice();
        executeListPrint(userId, listId);
    }

    private void executeListPrint(int userId, int listId) {
        User user = userList.getUserByIndex(userId);
        InstrumentList listToPrint = switch (listId) {
            case 1 -> user.getRentalHistory();
            case 2 -> user.getCurrentlyRented();
            default -> throw new IllegalArgumentException("Invalid list id");
        };
        ui.printInstrumentList(listToPrint.getList());
    }
}
