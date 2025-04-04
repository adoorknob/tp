package user;

import exceptions.user.UserListInitiationException;
import exceptions.user.UsernameMatchException;
import ui.Ui;

import java.util.ArrayList;

public class UserList {
    public static final int USERINDEXBUFFER = 0;
    ArrayList<User> users;
    int userCount;
    Ui ui;

    /**
     * Constructor for UserList. Initialises 'unassigned' for instruments that are not given a user
     *
     * @param ui Ui for entire program
     */
    public UserList(Ui ui) {
        users = new ArrayList<>();
        users.add(new User(ui, this, "Unassigned"));
        this.ui = ui;
    }

    public void addUser(User user) {
        users.add(user);
        user.setParentUserList(this);
        ui.printAcknowledgementCreatedNewUser(user.getName());
        userCount++;
    }

    public void removeUserById(int userId) {
        User deletedUser = users.remove(userId - USERINDEXBUFFER);
        ui.printAcknowledgementDeletedUser(deletedUser.getName());
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
     *
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

    public User findUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new UsernameMatchException("Username " + name + " not found");
    }

    public User getUserByIndex(int index) {
        assert users != null;
        return users.get(index);
    }

    public boolean isEmpty() {
        return userCount == 0;
    }
}
