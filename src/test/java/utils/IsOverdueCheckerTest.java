package utils;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class IsOverdueCheckerTest {

    @Test
    void testisOverdue() {
        LocalDate today = LocalDate.now().minusDays(1);
        boolean output = IsOverdueChecker.isOverdue(today);
        assertTrue(output);
    }

    @Test
    void testisNotOverdue() {
        LocalDate today = LocalDate.now();
        boolean output = IsOverdueChecker.isOverdue(today);
        assertFalse(output);
    }

    @Test
    void testIsOverdue_withFutureDate_returnsFalse() {
        LocalDate futureDate = LocalDate.now().plusDays(2);
        assertFalse(IsOverdueChecker.isOverdue(futureDate));
    }
}
