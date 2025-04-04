package utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import instrument.InstrumentList;
import instrument.Instrument;

/**
 * Static class for checking whether an item (e.g., an instrument) is overdue.
 * This class provides methods to check if a single item's due date has passed,
 * as well as to check overdue status for all instruments in a list.
 */
public class IsOverdueChecker {

    /**
     * Checks if a given due date is overdue by comparing it to the current date.
     *
     * @param dueDate The due date to check.
     * @return {@code true} if the due date is before the current date, {@code false} otherwise.
     *         If the due date is {@code null} or invalid, it will return {@code false}.
     */
    public static boolean isOverdue(LocalDate dueDate) {
        // If the due date is null, it's treated as not overdue
        if (dueDate == null) {
            return false;
        }
        try {
            // Check if the due date is before the current date
            return dueDate.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            // Log error if there's a problem parsing the due date
            String dueDateString = dueDate.toString();
            System.err.println("Invalid date format: " + dueDateString);
            return false; // Treat invalid dates as not overdue
        }
    }

    /**
     * Checks all instruments in the provided list and marks those that are overdue.
     *
     * @param list The list of instruments to check.
     */
    public static void checkAll(InstrumentList list) {
        // Iterate over each instrument in the list
        for (Instrument instrument : list.getList()) {
            // Get the due date for the instrument
            LocalDate dueDate = instrument.getdueDate();
            // Check if the instrument is overdue
            if (IsOverdueChecker.isOverdue(dueDate)) {
                // Mark the instrument as overdue if the due date is past
                instrument.setOverdue(true);
            }
        }
    }
}
