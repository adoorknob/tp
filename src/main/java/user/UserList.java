package user;

import exceptions.UserListInitiationException;
import ui.Ui;

import java.util.ArrayList;

public class UserList {
    ArrayList<User> users;
    int userCount;
    Ui ui;

    /**
     * Constructor for UserList. Initialises 'unassigned' for instruments that are not given a user
     * @param ui Ui for entire program
     */
    public UserList(Ui ui) {
        users = new ArrayList<>();
        users.add(new User(ui, "Unassigned"));
        this.ui = ui;
    }

    public void addUser(User user) {
        users.add(user);
        userCount++;
    }

    public void removeUser(User user) {
        users.remove(user);
        userCount--;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public int getUserCount() {
        return userCount;
    }

    /**
     * Returns the number of users named "Guest"
     * @return guestCount
     */

    public int getNumberOfGuestUsers() {
        assert users != null;
        int guestCount = 0;
        if (users.isEmpty()) {
            return 0;
        }
        for (User user : users) {
            if (user.getName().contains("Guest")) {
                guestCount++;
            }
        }
        return guestCount;
    }

    public User getUnassignedUser() {
        assert users != null;
        for (User user : users) {
            if (user.getName().contains("Unassigned")) {
                return user;
            }
        }
        throw new UserListInitiationException("No unassigned user found, userList not initialized correctly");
    }
}
