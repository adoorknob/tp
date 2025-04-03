package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateTimeParser {
    private static final List<DateTimeFormatter> FORMATS = Arrays.asList(
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm"),
            DateTimeFormatter.ofPattern("dd MMMM yyyy , h a"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")
    );

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public static LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        if (input == null || input.isEmpty() || input.equals("null")) {
            return null;
        }
        for (DateTimeFormatter format : FORMATS) {
            try {
                return LocalDateTime.parse(input, format);
            } catch (DateTimeParseException e) {
                System.out.println(e);
            }
        }
        throw new DateTimeParseException("Unable to parse date-time: " + input, input, 0);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
