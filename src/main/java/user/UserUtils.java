package user;

import instrument.Instrument;
import ui.Ui;

public class UserUtils {
    UserList userList;
    Ui ui;

    public UserUtils(Ui ui, UserList userList) {
        this.userList = userList;
        this.ui = ui;
    }

    public User queryAndAssignUser(Instrument instrument) {
        if(ui.isInstrumentAssignedToUser()) {
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
        String userName = ui.queryUserName();
        User newUser;
        if (userName.trim().isEmpty()) {
            newUser = new User(ui);
        } else {
            newUser = new User(ui, userName);
        }
        newUser.addInstrument(instrument);
        userList.addUser(newUser);
        return newUser;
    }

    public UserList getUserList() {
        return userList;
    }
}
