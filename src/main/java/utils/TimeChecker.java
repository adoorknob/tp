package utils;

import java.time.LocalTime;
import java.time.LocalDate;

public class TimeChecker {

    // Method to get current time
    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public static void main(String[] args) {
        TimeChecker timeChecker = new TimeChecker();
        System.out.println("Current time: " + timeChecker.getCurrentTime());
    }

    public static Integer getCurrentYear() {
        return LocalDate.now().getYear();
    }
}
