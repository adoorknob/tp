package user;

import org.junit.jupiter.api.Test;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserListTest {
    @Test
    void initUserList_unassignedUserAdded() {
        UserList userList = new UserList(new Ui());
        User unassignedUser = userList.getUnassignedUser();
        assertEquals(1, userList.getUsers().size(), "There should be one user");
        assertNotEquals(null, unassignedUser, "There should be an Unassigned user");
    }

    @Test
    void addUser_validUser() {
        UserList userList = new UserList (new Ui());
        User user = new User(null, userList);
        userList.addUser(user);
        assertEquals(1, userList.getUserCount(), "Users should be added to the list");
    }

    @Test
    void removeUser_validUser() {
        UserList userList = new UserList (new Ui());
        User user = new User(null, userList);
        userList.addUser(user);
        userList.removeUserById(1);
        assertEquals(0, userList.getUserCount(), "Users should be removed from the list");
    }

    @Test
    void getGuestCount_assertEquals() {
        UserList userList = new UserList (new Ui());
        User user1 = new User(null, userList);
        User user2 = new User(null, userList);
        userList.addUser(user1);
        userList.addUser(user2);
        assertEquals(2, userList.getNumberOfGuestUsers(), "Guest users should be 2");
    }

    @Test
    void getUserByIndex_validUser() {
        UserList userList = new UserList (new Ui());
        String userName = "TestUser";
        User user1 = new User(null, userList, userName);
        userList.addUser(user1);
        User output = userList.getUserByIndex(1);
        assertEquals(userName, output.getName(), "Username should be the same");
    }
}
