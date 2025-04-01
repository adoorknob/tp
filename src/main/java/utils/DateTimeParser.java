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

    public static String parseDateTime(String input) throws DateTimeParseException {
        if (input == null || input.isEmpty()) {
            return input;
        }
        for (DateTimeFormatter format : FORMATS) {
            try {
                return LocalDateTime.parse(input, format).format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            } catch (DateTimeParseException e) {
                System.out.println(e);
            }
        }
        return input;
    }
}
