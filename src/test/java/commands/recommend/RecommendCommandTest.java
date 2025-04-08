package commands.recommend;

import finance.FinanceManager;
import instrument.Instrument;
import instrument.InstrumentList;
import org.junit.jupiter.api.Test;
import ui.Ui;
import user.UserUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class RecommendCommandTest {

    static class StubInstrument extends Instrument {
        private final int usage;
        private final boolean rented;

        StubInstrument(String name, int usage, boolean rented) {
            super(name, "SampleModel", 2000);
            this.usage = usage;
            this.rented = rented;
        }

        @Override
        public int getUsage() {
            return usage;
        }

        @Override
        public String playInstrument() {
            return "";
        }

        @Override
        public boolean isRented() {
            return rented;
        }
    }

    static class StubInstrumentList extends InstrumentList {
        ArrayList<Instrument> instruments;

        StubInstrumentList(ArrayList<Instrument> instruments) {
            this.instruments = instruments;
        }

        @Override
        public ArrayList<Instrument> getList() {
            return instruments;
        }
    }

    static class StubUi extends Ui {
        Instrument recommended = null;
        int index = -1;

        @Override
        public void printRecommendation(Instrument instrument, int idx) {
            this.recommended = instrument;
            this.index = idx;
        }
    }

    @Test
    void testRecommendCommand_validInstrumentType() {
        // Two guitars, one rented, one with higher usage
        StubInstrument g1 = new StubInstrument("Guitar", 5, true);
        StubInstrument g2 = new StubInstrument("Guitar", 10, false);
        ArrayList<Instrument> list = new ArrayList<>();
        list.add(g1);
        list.add(g2);

        StubInstrumentList stubList = new StubInstrumentList(list);
        StubUi stubUi = new StubUi();
        UserUtils dummyUser = new UserUtils(stubUi, null);
        FinanceManager dummyFinance = new FinanceManager(stubUi);

        RecommendCommand cmd = new RecommendCommand("guitar");
        cmd.execute(stubList, stubUi, dummyUser, dummyFinance);

        assertEquals(g2, stubUi.recommended, "Should recommend unrented guitar with highest usage");
        assertEquals(2, stubUi.index, "Index should match the 1-based position in list");
    }

    @Test
    void testRecommendCommand_noAvailableInstrument() {
        StubInstrument g1 = new StubInstrument("Guitar", 10, true);
        ArrayList<Instrument> list = new ArrayList<>();
        list.add(g1);

        StubInstrumentList stubList = new StubInstrumentList(list);
        StubUi stubUi = new StubUi();
        RecommendCommand cmd = new RecommendCommand("guitar");

        cmd.execute(stubList, stubUi, new UserUtils(stubUi, null), new FinanceManager(stubUi));

        assertNull(stubUi.recommended, "No recommendation should be made when all instruments are rented");
    }

    @Test
    void testRecommendCommand_invalidInstrumentType() {
        StubInstrumentList dummyList = new StubInstrumentList(new ArrayList<>());
        StubUi stubUi = new StubUi();

        RecommendCommand cmd = new RecommendCommand("triangle");

        cmd.execute(dummyList, stubUi, new UserUtils(stubUi, null), new FinanceManager(stubUi));
        assertNull(stubUi.recommended, "Should not recommend unknown instruments");
    }

    @Test
    void testIsExit() {
        RecommendCommand cmd = new RecommendCommand("piano");
        assertFalse(cmd.isExit());
    }
}
