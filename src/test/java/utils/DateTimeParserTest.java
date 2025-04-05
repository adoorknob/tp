package utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

class DateTimeParserTest {

    @Test
    void testParseDate_validFormats_returnCorrectDate() {
        assertEquals(LocalDate.of(2023, 3, 15), DateTimeParser.parseDate("15/3/2023"));
        assertEquals(LocalDate.of(2023, 3, 15), DateTimeParser.parseDate("15/3/23"));
        assertEquals(LocalDate.of(2023, 3, 15), DateTimeParser.parseDate("Mar 15 2023"));
        assertEquals(LocalDate.of(2023, 3, 15), DateTimeParser.parseDate("15/03/2023"));
        assertEquals(LocalDate.of(2023, 3, 15), DateTimeParser.parseDate("15/03/23"));
        assertEquals(LocalDate.of(2023, 3, 15), DateTimeParser.parseDate("2023-03-15"));
        assertEquals(LocalDate.of(2023, 3, 15), DateTimeParser.parseDate("03-15-2023"));
        assertEquals(LocalDate.of(2023, 3, 15), DateTimeParser.parseDate("15 March 2023"));
        assertEquals(LocalDate.of(2023, 3, 15), DateTimeParser.parseDate("15 Mar 2023"));
    }

    @Test
    void testParseDate_trimsInput() {
        assertEquals(LocalDate.of(2023, 3, 15),
                DateTimeParser.parseDate("   15/3/2023  "));
    }

    @Test
    void testParseDate_nullOrEmpty_returnsNull() {
        assertNull(DateTimeParser.parseDate(null));
        assertNull(DateTimeParser.parseDate(""));
        assertNull(DateTimeParser.parseDate("null"));
    }

    @Test
    void testParseDate_invalidFormat_throwsException() {
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.parseDate("15th March 2023"));
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.parseDate("03/15/2023"));
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.parseDate("2023/03/15"));
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.parseDate("random string"));
    }

    @Test
    void testFormatDate_returnsCorrectString() {
        LocalDate date = LocalDate.of(2023, 3, 15);
        assertEquals("15/3/2023", DateTimeParser.formatDate(date));
    }
}
