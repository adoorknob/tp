package user;

import instrument.Guitar;
import instrument.Instrument;
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserUtilsTest {
    static class StubUi extends Ui {
        boolean isInstrumentAssigned = false;
        boolean isNewUser = false;
        String username = null;
        int deleteUserIndex = -1;
        boolean listPrinted = false;
        int instrumentListUserChoice = -1;
        int instrumentListListChoice = -1;

        StubUi() {

        }

        StubUi(boolean isInstrumentAssigned, boolean isNewUser, String username, int deleteUserIndex) {
            this.isInstrumentAssigned = isInstrumentAssigned;
            this.isNewUser = isNewUser;
            this.username = username;
            this.deleteUserIndex = deleteUserIndex;
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

        @Override
        public int queryUserIndexForDelete(UserList userList) {
            return deleteUserIndex;
        }

        @Override
        public void printUserListDisplay(UserList userList) {
            this.listPrinted = true;
        }

        @Override
        public int queryUserInstrumentListUserChoice(UserList userList) {
            return instrumentListUserChoice;
        }

        @Override
        public int queryUserInstrumentListListChoice() {
            return instrumentListListChoice;
        }

        @Override
        public void printInstrumentList(ArrayList<Instrument> list) {
            this.listPrinted = true;
        }

        public void setInstrumentListUserChoice(int index) {
            instrumentListUserChoice = index;
        }

        public void setInstrumentListListChoice(int index) {
            instrumentListListChoice = index;
        }
    }

    @Test
    void addKnownUser_success() {
        UserList userList = new UserList(new Ui());
        UserUtils userUtils = new UserUtils(new Ui(), userList);
        String username = "TestName";

        User output = userUtils.addKnownUser(username);
        assertEquals(username, output.getName(), "Username should match.");
    }

    @Test
    void assignUser_newUser_success() {
        StubUi stubUi = new StubUi(true, true, "TestName", -1);
        UserList userList = new UserList(new Ui());
        UserUtils userUtils = new UserUtils(stubUi, userList);
        User output = userUtils.queryAndAssignUser(new Guitar("Guitar", "Yamaha", 2000));

        assertEquals(1, userUtils.userList.getUserCount(), "Users count should match.");
        assertEquals("TestName", output.getName(), "Username should match.");
        assertEquals(1, output.getCurrentlyRented().getList().size(),
                "List size should match.");
    }

    @Test
    void assignUser_newUserWithNoName_success() {
        StubUi stubUi = new StubUi(true, true, null, -1);
        UserList userList = new UserList(new Ui());
        UserUtils userUtils = new UserUtils(stubUi, userList);
        User output = userUtils.queryAndAssignUser(new Guitar("Guitar", "Yamaha", 2000));

        assertEquals(1, userUtils.userList.getUserCount(), "Users count should match.");
        assertEquals("Guest1", output.getName(), "Username should match.");
        assertEquals(1, output.getCurrentlyRented().getList().size(),
                "List size should match.");
    }

    @Test
    void assignUser_existingUser_success() {
        StubUi stubUi = new StubUi(true, false, "TestName", -1);
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
    void assignUser_unassignedUser_success() {
        StubUi stubUi = new StubUi(false, false, "TestName", -1);
        UserList userList = new UserList(new Ui());
        UserUtils userUtils = new UserUtils(stubUi, userList);
        User output = userUtils.queryAndAssignUser(new Guitar("Guitar", "Yamaha", 2000));

        assertEquals("Unassigned", output.getName(), "Username should match.");
        assertEquals(1, output.getCurrentlyRented().getList().size(),
                "List size should match.");
    }

    @Test
    void executeUserCommand_addUser_success() {
        String username = "TestName";
        StubUi stubUi = new StubUi(true, false, username, -1);
        UserList userList = new UserList(new Ui());
        UserUtils userUtils = new UserUtils(stubUi, userList);

        userUtils.executeUserCommand(1);

        assertEquals(1, userUtils.userList.getUserCount(), "Users count should match.");
    }

    @Test
    void executeUserCommand_removeUser_success() {
        String username = "TestName";
        StubUi stubUi = new StubUi(true, false, username, 1);
        UserList userList = new UserList(new Ui());
        UserUtils userUtils = new UserUtils(stubUi, userList);

        userUtils.executeUserCommand(1);
        userUtils.executeUserCommand(2);

        assertEquals(0, userUtils.userList.getUserCount(), "Users count should match.");
    }

    @Test
    void executeUserCommand_printUserList_success() {
        String username = "TestName";
        StubUi stubUi = new StubUi(true, false, username, 1);
        UserList userList = new UserList(new Ui());
        UserUtils userUtils = new UserUtils(stubUi, userList);

        userUtils.executeUserCommand(3);

        assertTrue(stubUi.listPrinted, "List should be printed.");
    }

    @Test
    void executeUserCommand_printUserInstrumentList_success() {
        String username = "TestName";
        StubUi stubUi = new StubUi(true, false, username, 1);
        stubUi.setInstrumentListUserChoice(1);
        stubUi.setInstrumentListListChoice(1);
        UserList userList = new UserList(new Ui());
        UserUtils userUtils = new UserUtils(stubUi, userList);

        userUtils.executeUserCommand(1);
        userUtils.executeUserCommand(4);

        assertTrue(stubUi.listPrinted, "List should be printed.");
    }
}
