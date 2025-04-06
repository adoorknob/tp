package commands.user;

import finance.FinanceManager;
import instrument.InstrumentList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Ui;
import user.UserList;
import user.UserUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UserCommandTest {
    Ui ui;
    UserList userList;
    UserUtils userUtils;

    static class StubUi extends Ui {
        private boolean wasCalled = false;
        private final int choiceToReturn;

        StubUi(int choiceToReturn) {
            this.choiceToReturn = choiceToReturn;
        }

        @Override
        public int queryUserCommandChoice() {
            wasCalled = true;
            return choiceToReturn;
        }

        public boolean wasCalled() {
            return wasCalled;
        }
    }

    static class StubUserUtils extends UserUtils {
        private int receivedChoice = -1;

        public StubUserUtils(Ui ui, UserList userList) {
            super(ui, userList);
        }

        @Override
        public void executeUserCommand(int userChoice) {
            this.receivedChoice = userChoice;
        }

        public int getReceivedChoice() {
            return receivedChoice;
        }
    }

    @BeforeEach
    void setUp() {
        ui = new Ui();
        userList = new UserList(ui);
        userUtils = new StubUserUtils(ui, userList);
    }

    @Test
    void execute_shouldCallUiAndPassChoiceToUserUtils() {
        StubUi stubUi = new StubUi(3);
        StubUserUtils stubUserUtils = new StubUserUtils(ui, userList);
        UserCommand userCommand = new UserCommand();

        InstrumentList dummyInstrumentList = null;
        FinanceManager dummyFinanceManager = null;

        userCommand.execute(dummyInstrumentList, stubUi, stubUserUtils, dummyFinanceManager);

        assertTrue(stubUi.wasCalled(), "Ui should be called to query user choice");
        assertEquals(3, stubUserUtils.getReceivedChoice(), "UserUtils should receive the correct user choice");
    }

    @Test
    void isExit_shouldReturnFalse() {
        UserCommand userCommand = new UserCommand();
        assertFalse(userCommand.isExit());
    }
}
