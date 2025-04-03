package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import instrument.InstrumentList;
import instrument.Instrument;

public class IsOverdueChecker {
    public static boolean isOverdue(LocalDateTime dueDate) {
        if (dueDate == null) {
            return false; // Treat empty due dates as not overdue
        }
        try {
            return dueDate.isBefore(LocalDateTime.now());
        } catch (DateTimeParseException e) {
            String dueDateString = dueDate.toString();
            System.err.println("Invalid date format: " + dueDateString);
            return false; // Treat invalid dates as not overdue
        }
    }

    public static void checkAll(InstrumentList list) {
        for (Instrument instrument : list.getList()) {
            LocalDateTime dueDate = instrument.getdueDate();
            if (IsOverdueChecker.isOverdue(dueDate)) {
                instrument.setOverdue(true);
            }
        }
    }
}
