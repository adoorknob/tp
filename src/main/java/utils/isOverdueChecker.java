package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import instrument.InstrumentList;
import instrument.Instrument;

public class isOverdueChecker {
    public static boolean isOverdue(String dueDateStr) {
        if (dueDateStr == null || dueDateStr.isBlank()) {
            return false; // Treat empty due dates as not overdue
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);
            return dueDate.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format: " + dueDateStr);
            return false; // Treat invalid dates as not overdue
        }
    }

    public static void checkAll(InstrumentList list) {
        System.out.println("Checking overdue instruments...");
        for (Instrument instrument : list.getList()) {
            String dueDate = instrument.dueDate();
            if (isOverdueChecker.isOverdue(dueDate)) {
                System.out.println("Overdue: " + instrument.toString());
                instrument.setOverdue(true);
            }
        }
        System.out.println("...Done checking overdue instruments");
    }
}
