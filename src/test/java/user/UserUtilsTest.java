package user;

import instrument.Guitar;
import org.junit.jupiter.api.Test;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserUtilsTest {
    static class StubUi extends Ui {
        boolean isInstrumentAssigned = false;
        boolean isNewUser = false;
        String username = null;

        StubUi() {

        }

        StubUi(boolean isInstrumentAssigned, boolean isNewUser, String username) {
            this.isInstrumentAssigned = isInstrumentAssigned;
            this.isNewUser = isNewUser;
            this.username = username;
        }

        @Override
        public boolean isInstrumentAssignedToUser() {
            return isInstrumentAssigned;
        }

        @Override
        public int queryUserIndex(UserList userList) {
            return (isNewUser) ? 0 : 1;
        }

        @Override
        public String queryUserNameWithNoNameChoice() {
            return username == null ? "" : username;
        }

    }

    @Test
    void addKnownUser_assertTrue() {
        UserList userList = new UserList(new Ui());
        UserUtils userUtils = new UserUtils(new Ui(), userList);
        String username = "TestName";

        User output = userUtils.addKnownUser(username);
        assertEquals(username, output.getName(), "Username should match.");
    }

    @Test
    void assignUser_newUser_assertTrue() {
        StubUi stubUi = new StubUi(true, true, "TestName");
        UserList userList = new UserList(new Ui());
        UserUtils userUtils = new UserUtils(stubUi, userList);
        User output = userUtils.queryAndAssignUser(new Guitar("Guitar", "Yamaha", 2000));

        assertEquals(1, userUtils.userList.getUserCount(), "Users count should match.");
        assertEquals("TestName", output.getName(), "Username should match.");
        assertEquals(1, output.getCurrentlyRented().getList().size(),
                "List size should match.");
    }

    @Test
    void assignUser_newUserWithNoName_assertTrue() {
        StubUi stubUi = new StubUi(true, true, null);
        UserList userList = new UserList(new Ui());
        UserUtils userUtils = new UserUtils(stubUi, userList);
        User output = userUtils.queryAndAssignUser(new Guitar("Guitar", "Yamaha", 2000));

        assertEquals(1, userUtils.userList.getUserCount(), "Users count should match.");
        assertEquals("Guest1", output.getName(), "Username should match.");
        assertEquals(1, output.getCurrentlyRented().getList().size(),
                "List size should match.");
    }

    @Test
    void assignUser_existingUser_assertTrue() {
        StubUi stubUi = new StubUi(true, false, "TestName");
        UserList userList = new UserList(new Ui());
        userList.addUser(new User(new Ui(), userList, "ExistingUser"));
        UserUtils userUtils = new UserUtils(stubUi, userList);
        User output = userUtils.queryAndAssignUser(new Guitar("Guitar", "Yamaha", 2000));

        assertEquals(1, userUtils.userList.getUserCount(), "Users count should match.");
        assertEquals("ExistingUser", output.getName(), "Username should match.");
        assertEquals(1, output.getCurrentlyRented().getList().size(),
                "List size should match.");
    }

    @Test
    void assignUser_unassignedUser_assertTrue() {
        StubUi stubUi = new StubUi(false, false, "TestName");
        UserList userList = new UserList(new Ui());
        UserUtils userUtils = new UserUtils(stubUi, userList);
        User output = userUtils.queryAndAssignUser(new Guitar("Guitar", "Yamaha", 2000));

        assertEquals("Unassigned", output.getName(), "Username should match.");
        assertEquals(1, output.getCurrentlyRented().getList().size(),
                "List size should match.");
    }
}
