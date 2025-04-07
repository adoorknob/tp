package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Class for handling Date Parsing for SirDukeBox
 */
public class DateTimeParser {
    private static final List<DateTimeFormatter> DATE_FORMATS = Arrays.asList(
            DateTimeFormatter.ofPattern("d/M/yyyy"),     // Day/Month/Year with 4-digit year
            DateTimeFormatter.ofPattern("d/M/yy"),        // Day/Month/Year with 2-digit year
            DateTimeFormatter.ofPattern("MMM dd yyyy"),  // Month (abbreviated)/Day/Year
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),   // Day/Month/Year with 4-digit year
            DateTimeFormatter.ofPattern("dd/MM/yy"),     // Day/Month/Year with 2-digit year
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),   // ISO format Year-Month-Day
            DateTimeFormatter.ofPattern("dd MMMM yyyy"), // Day FullMonth Year
            DateTimeFormatter.ofPattern("d MMM yyyy")    // Day AbbrMonth Year
    );

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy");

    /**
     * Parses date
     *
     * @param input String containing the date
     * @return the Date in localDate format
     * @throws DateTimeParseException When incorrect or invalid date is given
     */
    public static LocalDate parseDate(String input) throws DateTimeParseException {
        if (input == null || input.equals("null") || input.isEmpty()) {
            return null;
        }
        input = input.trim();

        for (DateTimeFormatter format : DATE_FORMATS) {
            try {
                return LocalDate.parse(input, format);
            } catch (DateTimeParseException e) {
                // Silent
            }
        }
        throw new DateTimeParseException("Please input a valid date (dd/MM/yyyy): " + input, input, 0);
    }

    public static String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

}
