package user;

import exceptions.instrument.InstrumentFindException;
import instrument.Guitar;
import org.junit.jupiter.api.Test;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UserTest {
    @Test
    void getName_testWithName() {
        User user = new User(null, null, "TestName");
        String output = user.getName();
        assertEquals("TestName", output, "Name not properly initialised.");
    }

    @Test
    void getName_testWithoutName_expectGuest() {
        UserList userList = new UserList(null);
        User user = new User(null, userList);
        String output = user.getName();
        assertEquals("Guest1", output, "Name not properly initialised.");
    }

    @Test
    void getName_testNull_expectInvalid() {
        UserList userList = new UserList(null);
        User user = new User(null, userList, null);
        String output = user.getName();
        assertEquals("Invalid", output, "Name not properly initialised.");
    }

    @Test
    void addInstrument_assertTrue() {
        UserList userList = new UserList(null);
        User user = new User(new Ui(), userList);
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        user.addInstrument(guitar);
        int currentlyRentedSize = user.getCurrentlyRented().getList().size();
        int rentalHistorySize = user.getRentalHistory().getList().size();
        assertEquals(1, currentlyRentedSize, "Currently rented list size should be 1");
        assertEquals(1, rentalHistorySize, "Rental history list size should be 1");
    }

    @Test
    void removeInstrument_assertTrue() {
        UserList userList = new UserList(null);
        User user = new User(new Ui(), userList);
        Guitar guitar = new Guitar("Guitar", "Yamaha", 2000);
        user.addInstrument(guitar);
        user.removeInstrument(guitar);
        int currentlyRentedSize = user.getCurrentlyRented().getList().size();
        int rentalHistorySize = user.getRentalHistory().getList().size();
        assertEquals(0, currentlyRentedSize, "Currently rented list size should be 0");
        assertEquals(1, rentalHistorySize, "Rental history list size should be 1");
    }

    @Test
    void removeInstrument_removeInvalidInstrument_expectException() {
        UserList userList = new UserList(null);
        User user = new User(new Ui(), userList);
        Guitar guitar1 = new Guitar("Guitar", "Yamaha", 2000);
        Guitar guitar2 = new Guitar("Guitar", "Yamaha", 2000);
        user.addInstrument(guitar1);
        try {
            user.removeInstrument(guitar2);
            fail("Exception expected");
        } catch (InstrumentFindException e) {
            assertEquals("The instrument was not found from user"
                    + user.getName() + "'s list to delete", e.getMessage());
        }
    }
}
